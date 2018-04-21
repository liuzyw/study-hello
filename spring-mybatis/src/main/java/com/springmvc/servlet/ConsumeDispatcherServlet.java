package com.springmvc.servlet;

import com.springmvc.annotation.MyAutowrited;
import com.springmvc.annotation.MyController;
import com.springmvc.annotation.MyRequestMapping;
import com.springmvc.annotation.MyService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Created on 2018-04-21
 *
 * @author liuzhaoyuan
 */
public class ConsumeDispatcherServlet extends HttpServlet {

    private Properties properties = new Properties();

    private List<String> classNames = new ArrayList<>();

    private Map<String, Object> ioc = new HashMap<>();

    private Map<Class<?>, Object> instanceTypeMap = new HashMap<>();

    private Map<String, Method> handlerMapping = new HashMap<>();
    private Map<String, Object> controllerMap = new HashMap<>();


    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("--- ConsumeDispatcherServlet ---");
        System.out.println("--- load config file ---");
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        System.out.println("--- scanner class ---");
        doScanner(properties.getProperty("scanPackage"));

        System.out.println("--- load Ioc ---");
        doInstance();

        System.out.println("--- autowrited ---");
        doAutowrited();

        System.out.println("--- init handler mapper ---");
        doHandlerMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(classNames);
        System.out.println(ioc);
        System.out.println(handlerMapping);
        System.out.println(controllerMap);
        doDispatcher(req, resp);
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (MapUtils.isNotEmpty(handlerMapping)) {
            String url = req.getRequestURI();
            String contextPath = req.getContextPath();
            url.replace(contextPath, "");
            url = ("/" + url).replaceAll("/+", "/");
            System.out.println("request url: " + url);
            Method method = handlerMapping.get(url);
            if (method != null) {
                //获取方法的参数列表
                Class<?>[] parameterTypes = method.getParameterTypes();

                //获取请求的参数
                Map<String, String[]> parameterMap = req.getParameterMap();

                //保存参数值
                Object[] paramValues = new Object[parameterTypes.length];

                //方法的参数列表
                for (int i = 0; i < parameterTypes.length; i++) {
                    //根据参数名称，做某些处理
                    String requestParam = parameterTypes[i].getSimpleName();

                    if ("HttpServletRequest".equals(requestParam)) {
                        //参数类型已明确，这边强转类型
                        paramValues[i] = req;
                        continue;
                    }
                    if ("HttpServletResponse".equals(requestParam)) {
                        paramValues[i] = resp;
                        continue;
                    }
                    if (requestParam.equals("String")) {
                        for (Entry<String, String[]> param : parameterMap.entrySet()) {
                            String value = Arrays
                                .toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
                            paramValues[i] = value;
                        }
                    }
                }
                //利用反射机制来调用
                try {
                    method.invoke(this.controllerMap.get(url), paramValues);//第一个参数是method所对应的实例 在ioc容器中
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                resp.getWriter().write("url not found 404");

            }

        }
    }

    private void doLoadConfig(String location) {

        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(location)) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doScanner(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        String fileName = url.getFile();
        File file = new File(fileName);

        for (File file1 : file.listFiles()) {
            if (file1.isDirectory()) {
                doScanner(packageName + "." + file1.getName());
            } else {
                String calssName = packageName + "." + file1.getName().replaceAll(".class", "");
                classNames.add(calssName);
            }
        }
    }

    private void doInstance() {
        if (CollectionUtils.isNotEmpty(classNames)) {
            try {
                for (String className : classNames) {
                    Class<?> clazz = Class.forName(className);

                    if (clazz.isAnnotationPresent(MyController.class)) {
                        char[] chars = clazz.getSimpleName().toCharArray();
                        chars[0] += 32;
                        String beanName = String.valueOf(chars);
                        ioc.put(beanName, clazz.newInstance());
                    } else if (clazz.isAnnotationPresent(MyService.class)) {
                        MyService service = clazz.getAnnotation(MyService.class);
                        String beanName = service.value();
                        if (StringUtils.isNotEmpty(beanName)) {
                            ioc.put(beanName, clazz.newInstance());
                        } else {
                            char[] chars = clazz.getSimpleName().toCharArray();
                            chars[0] += 32;
                            beanName = String.valueOf(chars, 0, chars.length - 4);
                            ioc.put(beanName, clazz.newInstance());
                        }

                        Class<?>[] interfaces = clazz.getInterfaces();
                        for (Class<?> class1 : interfaces) {
                            if (instanceTypeMap.get(class1) != null) {
                                throw new RuntimeException(class1.getName() + "接口不能被多个类实现！");
                            }
                            instanceTypeMap.put(class1, clazz.newInstance());
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void doAutowrited() {
        try {
            if (MapUtils.isNotEmpty(ioc)) {
                for (Entry<String, Object> entry : ioc.entrySet()) {

                    // 取出全部的属性
                    Field[] fields = entry.getValue().getClass().getDeclaredFields();

                    // 循环属性校验哪些是加了@autowired注解的
                    for (Field field : fields) {
                        field.setAccessible(true);// 可访问私有属性

                        // 有注解的时候
                        if (field.isAnnotationPresent(MyAutowrited.class)) {
                            String beanName = field.getAnnotation(MyAutowrited.class).value();
                            // 没有配别名注入的时候
                            if (beanName.isEmpty()) {
                                // 直接获取
                                try {
                                    // 根据类型来获取他的实现类
                                    Object object = instanceTypeMap.get(field.getType());
                                    System.out.println("autowrited: t " + field.getType());

                                    field.set(entry.getValue(), object);
                                } catch (IllegalArgumentException | IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    // 将被注入的对象
                                    Object object = ioc.get(beanName);
                                    System.out.println("autowrited: " + beanName);

                                    field.set(entry.getValue(), object);
                                } catch (Exception e) {
                                    throw new RuntimeException("开始注入时出现了异常");
                                }
                            }
                        }
                    }
                }
//                for (Entry<String, Object> entry : ioc.entrySet()) {
//                    Field[] fields = entry.getValue().getClass().getDeclaredFields();
//                    for (Field field : fields) {
//                        field.setAccessible(true);
//                        if (field.isAnnotationPresent(MyAutowrited.class)) {
//                            MyAutowrited autowrited = field.getAnnotation(MyAutowrited.class);
//                            String beanName = autowrited.value();
//                            if (StringUtils.isEmpty(beanName)) {
//                                beanName = field.getName();
////                                char[] chars = beanName.toCharArray();
////                                chars[0] += 32;
////                                beanName = String.valueOf(chars);
//                            }
//                            System.out.println("autowrited: " + beanName);
//                            field.set(entry.getValue(), ioc.get(beanName));
//                        }
//                    }
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doHandlerMapper() {
        if (MapUtils.isNotEmpty(ioc)) {
            try {
                for (Entry<String, Object> entry : ioc.entrySet()) {
                    Class<?> clazz = entry.getValue().getClass();
                    if (clazz.isAnnotationPresent(MyController.class)) {
                        String baseUrl = "";
                        if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                            MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                            baseUrl += requestMapping.value();
                        }

                        Method[] methods = clazz.getMethods();

                        for (Method method : methods) {
//                        method.setAccessible(false);

                            if (method.isAnnotationPresent(MyRequestMapping.class)) {
                                MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                                String url = "";
                                if (StringUtils.isNotEmpty(requestMapping.value())) {
                                    System.out.println("requestMapping: " + requestMapping);
                                    url = baseUrl + requestMapping.value();
                                }

                                if (!url.equals("/")) {
                                    url = ("/" + url).replaceAll("/+", "/");
                                }
                                System.out.println("url: " + url + ", method: " + method.getName());
                                handlerMapping.put(url, method);
                                controllerMap.put(url, clazz.newInstance());
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
