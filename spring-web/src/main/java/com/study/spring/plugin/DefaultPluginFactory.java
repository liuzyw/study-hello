package com.study.spring.plugin;

import com.alibaba.fastjson.JSON;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.aopalliance.aop.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Created on 2018-05-24
 *
 * @author liuzhaoyuan
 */
public class DefaultPluginFactory implements PluginFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPluginFactory.class);

    @Autowired
    private ApplicationContext applicationContext;

    private static Map<String, Plugin> configs = new HashMap<>();

    private static Map<String, Advice> adviceCache = new HashMap<>();

    private static String BASE_DIR = "/Users/liuzhaoyuan/gitwork/study-hello/logs/";


    public void doBefore() {
        for (String beanName : adviceCache.keySet()) {
            Object bean = applicationContext.getBean(beanName);
            try {
                Method method = bean.getClass().getDeclaredMethod("doBefore");
                method.invoke(bean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void disablePlugin(String pluginId) {

    }

    @Override
    public void unInstallPlugin(String pluginId) {

    }

    @Override
    public List<Plugin> getPlugins() {
        return (List<Plugin>) configs.values();
    }

    @Override
    public String getPluginStatus(String pluginId) {
        return null;
    }

    @Override
    public void installPlugin(Plugin plugin, Boolean active) {

        if (configs.containsKey(plugin.getId())) {
            LOGGER.info("插件已经存在");
            return;
        }

        configs.put(plugin.getId(), plugin);

        // 下载远程插件
        try {
            buildActive(plugin);
        } catch (Exception e) {
            configs.remove(plugin.getId());
            e.printStackTrace();
        }

        // 安装到本地
        try {
            storeConfigs(plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (active != null && active) {
            activePlugin(plugin.getId());
        }

    }

    @Override
    public void activePlugin(String pluginId) {
        if (!configs.containsKey(pluginId)) {
            LOGGER.info("插件未安装");
        }
        Plugin plugin = configs.get(pluginId);

        for (String name : applicationContext.getBeanDefinitionNames()) {
            Object bean = applicationContext.getBean(name);
            if (bean == this) {
                continue;
            }
            if (!(bean instanceof Advised)) {
                continue;
            }
            if (findAdvice(plugin.getClassName(), (Advised) bean) != null) {
                continue;
            }
            Advice advice = null;
            try {
                advice = buildActive(plugin);
                ((Advised) bean).addAdvice(advice);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                plugin.setActive(true);
                storeConfigs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void storeConfigs() throws IOException {
        String baseDir = BASE_DIR;
        File configFile = new File(baseDir + "PluginConfigs.json");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            configFile.createNewFile();
        }
        PluginStore store = new PluginStore();

        store.setPlugins(new ArrayList<>(configs.values()));
        store.setLastModify(System.currentTimeMillis());
        OutputStream outputStream = new FileOutputStream(configFile);
        String json = JSON.toJSONString(store, true);
        outputStream.write(json.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();


    }


    private void storeConfigs(Plugin plugin) {
    }

    private Advice buildActive(Plugin plugin) throws Exception {
        if (adviceCache.containsKey(plugin.getClassName())) {
            return adviceCache.get(plugin.getClassName());
        }

        File jarFile = new File(getLocalJarFile(plugin));

        if (!jarFile.exists()) {
            URL url = new URL(plugin.getJarRemoteUrl());
            InputStream inputStream = url.openStream();
            jarFile.getParentFile().mkdirs();
            try {
                Files.copy(inputStream, jarFile.toPath());
            } catch (Exception e) {
                jarFile.deleteOnExit();
                e.printStackTrace();
            }
            inputStream.close();
        }

        URLClassLoader urlClassLoader = (URLClassLoader) this.getClass().getClassLoader();
        URL targetUrl = jarFile.toURI().toURL();
        boolean isLoader = false;
        for (URL url : urlClassLoader.getURLs()) {
            if (url.equals(targetUrl)) {
                isLoader = true;
                break;
            }
        }
        if (!isLoader) {
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
            add.setAccessible(true);
            add.invoke(urlClassLoader, targetUrl);
        }

        Class<?> adviceClass = urlClassLoader.loadClass(plugin.getClassName());
        if (!Advice.class.isAssignableFrom(adviceClass)) {
            LOGGER.info("插件配置错误");
        }
        adviceCache.put(adviceClass.getName(), (Advice) adviceClass.newInstance());
        return adviceCache.get(adviceClass.getName());

    }

    private String getLocalJarFile(Plugin plugin) {
        String jarUlr = plugin.getJarRemoteUrl();
        String jarName = jarUlr.substring(jarUlr.indexOf("\\") + 1, jarUlr.length());
        return BASE_DIR + jarName;
    }

    private Advice findAdvice(String className, Advised advised) {
        for (Advisor advisor : advised.getAdvisors()) {
            if (advisor.getAdvice().getClass().getName().equals(className)) {
                return advisor.getAdvice();
            }
        }
        return null;
    }


    public Map<String, Plugin> readerLocalConfigs() throws Exception {
        Map<String, Plugin> result = new HashMap<>();

        String baseDir = BASE_DIR;
        File configFile = new File(baseDir + "PluginConfigs.json");
        if (!configFile.exists()) {
            return result;
        }
        InputStream inputStream = new FileInputStream(configFile);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            copy(inputStream, outputStream);
            PluginStore store = JSON.parseObject(outputStream.toString("UTF-8"), PluginStore.class);

            for (Plugin plugin : store.getPlugins()) {
                result.put(plugin.getId(), plugin);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            outputStream.close();
        }

        return result;

    }

    private void copy(InputStream inputStream, ByteArrayOutputStream outputStream) {
        long nread = 0L;
        byte[] bytes = new byte[2048];
    }

    public void afterProertiesSet() throws Exception {
        loaderLocals();
    }

    private void loaderLocals() {
        try {
            Map<String, Plugin> loadConfig = new HashMap<>();
            loadConfig = readerLocalConfigs();
            if (loadConfig.size() == 0) {
                return;
            }
            configs.putAll(loadConfig);
            for (Plugin plugin : loadConfig.values()) {
                if (plugin.getActive()) {
                    activePlugin(plugin.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
