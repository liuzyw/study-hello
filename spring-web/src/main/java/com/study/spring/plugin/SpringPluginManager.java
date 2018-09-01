package com.study.spring.plugin;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.aopalliance.aop.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;

/**
 * Created on 2018-05-24
 *
 * @author liuzhaoyuan
 */
public class SpringPluginManager {


    private static final Logger LOGGER = LoggerFactory.getLogger(SpringPluginManager.class);

    @Autowired
    private ApplicationContext applicationContext;

    private static Map<String, Plugin> configs = new HashMap<>();

    // 已经激活的插件
    private static Map<String, Plugin> activePlugins = new HashMap<>();

    // 已经安装的插件
    private static Map<String, Plugin> installPlugins = new HashMap<>();


    private static String BASE_DIR = "/Users/liuzhaoyuan/gitwork/study-hello/logs/";


    /**
     * 安装插件
     *
     * @param plugin
     *
     * @return
     *
     * @throws Exception
     */
    public boolean installPlugin(Plugin plugin) throws Exception {

        if (installPlugins.containsKey(plugin.getId())) {
            LOGGER.info("plugin allReady install:" + plugin.getId());
            return true;
        }

        // jar包下载到本地
        String fileName = BASE_DIR + "/" + plugin.getJarFile();
        Path jarPath = Paths.get(fileName);

        try {
            Files.deleteIfExists(jarPath);
        } catch (Exception e) {
            LOGGER.error("delete fail", e);
        }

        File jarFile = new File(fileName);

        try {
            if (!jarFile.exists()) {
                URL url = new URL(plugin.getJarRemoteUrl());
                InputStream inputStream = url.openStream();
                jarFile.getParentFile().mkdirs();
                try {
                    Files.copy(inputStream, jarFile.toPath());
                } catch (Exception e) {
                    jarFile.deleteOnExit();
                    LOGGER.error("copy file fail", e);
                }
                inputStream.close();
            }
        } catch (Exception e) {
            LOGGER.error("load remote jar fail", e);
        }

        // 加载（不连接)
        URLClassLoader loader = (URLClassLoader) getClass().getClassLoader();
        URL targetUrl = jarPath.toUri().toURL();
        boolean isLoader = false;
        for (URL url : loader.getURLs()) {
            if (url.equals(targetUrl)) {
                isLoader = true;
                break;
            }
        }

        if (!isLoader) {
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
            // 暴力访问
            add.setAccessible(true);
            add.invoke(loader, targetUrl);
        }

        // 判断类型是否正确
        String className = plugin.getClassName();
        Class<?> adviceClass = loader.loadClass(className);
        if (!Advice.class.isAssignableFrom(adviceClass)) {
            throw new Exception(String.format("配置错误，%s非%s的实现类！", className, Advice.class));
        }
        // 放进缓存
        installPlugins.put(plugin.getId(), plugin);

        return true;
    }


    /**
     * 获取IOC容器中所有代理类
     *
     * @return
     */
    public List<String> getAdvised() {
        List<String> result = Lists.newArrayList();
        for (String name : applicationContext.getBeanDefinitionNames()) {

            Object bean = applicationContext.getBean(name);

            if (bean == this) {
                continue;
            }

            if (!AopUtils.isAopProxy(bean)) {
                continue;
            }

            if (bean instanceof Advised) {
                result.add(bean.getClass().getName());
            }
        }

        return result;
    }

    /**
     * 将插件类注入IOC容器
     *
     * @param className
     *
     * @return
     */
    private Advice buildAdvice(String className) {
        Object bean = registerBean(className);
        return (Advice) bean;
    }

    /**
     * 注册一个bean到IOC容器
     * 使用根据类名生成的名称
     *
     * @param className 全类名
     */
    private Object registerBean(String className) {
        // 根据类名获取默认bean名
        int index = className.lastIndexOf(".");
        String classSimpleName = index != -1 ? className.substring(index + 1) : className;
        String beanName = classSimpleName.substring(0, 1).toLowerCase()
            + classSimpleName.substring(1, classSimpleName.length());

        return registerBean(className, beanName);
    }

    /**
     * 注册一个bean到IOC容器
     * 并命名为指定名称
     *
     * @param className 全类名
     */
    private Object registerBean(String className, String beanName) {
        try {
            Class<?> beanClazz = getClass().getClassLoader().loadClass(className);

            // 获取bean工厂和bean声明
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClazz);

            //动态注册bean
            defaultListableBeanFactory.registerBeanDefinition(beanName, builder.getBeanDefinition());

            return applicationContext.getBean(beanName);
        } catch (ClassNotFoundException e) {
            LOGGER.error("register bean fail", e);
            return null;
        }
    }


    /**
     * 激活插件
     *
     * @param pluginId
     */
    public void activePlugin(String pluginId) {
        if (!installPlugins.containsKey(pluginId)) {
            LOGGER.info("plugin not install :" + pluginId);
            return;
        }

        if (activePlugins.containsKey(pluginId)) {
            LOGGER.info("plugin is active " + pluginId);
            return;
        }

        Plugin plugin = installPlugins.get(pluginId);
        // 注入到Ioc中
        Advice advice = buildAdvice(plugin.getClassName());

        for (String name : applicationContext.getBeanDefinitionNames()) {
            Object bean = applicationContext.getBean(name);
            // 过滤掉当前bean和非代理bean
            if (bean == this || !(bean instanceof Advised)) {
                continue;
            }
            // 过滤掉非选中的bean
            if (findAdvice(plugin.getClassName(), (Advised) bean) != null) {
                continue;
            }
            // 过滤掉已添加此通知的bean
            for (Advisor advisor : ((Advised) bean).getAdvisors()) {
                if (!advisor.getAdvice().getClass().getName().equalsIgnoreCase(plugin.getClassName())) {
                    // 添加通知
                    ((Advised) bean).addAdvice(advice);
                }
            }

        }

        activePlugins.put(pluginId, plugin);

    }

    private Advice findAdvice(String className, Advised advised) {
        for (Advisor advisor : advised.getAdvisors()) {
            if (advisor.getAdvice().getClass().getName().equalsIgnoreCase(className)) {
                return advisor.getAdvice();
            }
        }
        return null;
    }

    /**
     * 禁用插件
     *
     * @param pluginId
     */
    public void disablePlugin(String pluginId) {
        if (!activePlugins.containsKey(pluginId)) {
            LOGGER.info("plugin is not active:" + pluginId);
            return;
        }

        Plugin plugin = activePlugins.get(pluginId);
        String className = plugin.getClassName();

        for (String name : applicationContext.getBeanDefinitionNames()) {
            Object bean = applicationContext.getBean(name);
            // 过滤掉当前bean和非代理bean
            if (bean == this || !(bean instanceof Advised)) {
                continue;
            }

            // 过滤掉已添加此通知的bean
            for (Advisor advisor : ((Advised) bean).getAdvisors()) {
                if (advisor.getAdvice().getClass().getName().equalsIgnoreCase(plugin.getClassName())) {
                    // 禁用通知
                    ((Advised) bean).removeAdvice(advisor.getAdvice());

                }
            }
        }

        // 将bean从IOC删除
        removeBeanByClass(className);

        activePlugins.remove(pluginId);
    }

    /**
     * 根据类名从IOC容器bean, 属于该类型的将全部删除
     *
     * @param className
     */
    private void removeBeanByClass(String className) {
        try {
            Class<?> beanClazz = getClass().getClassLoader().loadClass(className);
            // 获取bean工厂
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
            // 获取所有bean的名称，并转为流
            Arrays.stream(applicationContext.getBeanDefinitionNames())
                // 过滤掉不属于指定类型的bean
                .filter(definitionName -> {
                    Object bean = applicationContext.getBean(definitionName);
                    return beanClazz.isAssignableFrom(bean.getClass());
                })
                // 删除bean
                .forEach(defaultListableBeanFactory::removeBeanDefinition);
        } catch (ClassNotFoundException e) {
            LOGGER.error("remove bean fail", e);
        }
    }

    /**
     * 从IOC容器移除指定名称的bean
     *
     * @param beanName bean名称
     */
    private boolean removeBeanByName(String beanName) throws ClassNotFoundException, InterruptedException {
        // 判断指定bean是否存在
        if (!applicationContext.containsBean(beanName)) {
            return false;
        }
        // 获取bean工厂
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        // 删除bean.
        defaultListableBeanFactory.removeBeanDefinition(beanName);
        return true;
    }

    /**
     * 卸载插件
     *
     * @param pluginId
     */
    public void uninstallPlugin(String pluginId) {
        if (!installPlugins.containsKey(pluginId)) {
            LOGGER.warn("plugin is not install " + pluginId);
            return;
        }
        installPlugins.remove(pluginId);
    }

    /**
     * 获取已安装插件Id列表
     *
     * @return
     */
    public Set<String> getInstallPlugins() {
        return installPlugins.keySet();
    }

    /**
     * 获取已激活插件Id列表
     *
     * @return
     */
    public Set<String> getActivePlugins() {
        return activePlugins.keySet();
    }

    /**
     * 获取Cglib代理对象的目标对象
     *
     * @param proxy
     *
     * @return
     *
     * @throws Exception
     */
    public Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);

        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();

        return target;
    }

    /**
     * 获取JDK代理对象的目标对象
     *
     * @param proxy
     *
     * @throws Exception
     * @returnBaseLog
     */
    public Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);

        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();

        return target;
    }


}

