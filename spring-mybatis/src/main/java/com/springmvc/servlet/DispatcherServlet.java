package com.springmvc.servlet;

import com.springmvc.ClassTools;
import com.springmvc.annotation.MyAutowrited;
import com.springmvc.annotation.MyController;
import com.springmvc.annotation.MyRequestMapping;
import com.springmvc.annotation.MyService;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author
 * @Description 请求几种处理类
 * @date 2017年8月30日下午5:23:54
 */
public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = 1378531571714153483L;

    private static final String CONTROLLER_KEY = "controller";

    private static final String METHOD_KEY = "method";

    private Properties properties = new Properties();


    /**
     * 存放Controller中url和方法的对应关系，格式：{url:{controller:实例化后的对象,method:实例化的方法}}
     */
    private static Map<String, Map<String, Object>> urlMethodMapping = new HashMap<>();
    // 存放Controller和Service的Map，格式：{beanName:实例化后的对象}
    private Map<String, Object> instanceMap = new HashMap<String, Object>();
    // 存放Service接口类型与接口实例对象的Map，格式：{Service.instance.class:实现类实例化后的对象}
    private Map<Class<?>, Object> instanceTypeMap = new HashMap<Class<?>, Object>();
    private Set<Class<?>> classes;


    public DispatcherServlet() {
        super();
    }

    /**
     * 初始化方法，用于实例化扫描到的对象，并做注入和url映射（注：该方法逻辑上已经判断了，只执行一次）
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        // 只处理一次
        if (urlMethodMapping.size() > 0) {
            return;
        }
        // 加载配置
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        String PACKAGE = properties.getProperty("scanPackage");
        // 开始扫描包下全部class文件
        classes = ClassTools.getClasses(PACKAGE);

        // 组装instanceMap
        buildInstanceMap(classes);

        // 开始注入
        doIoc();

        // 注入完之后开始映射url和method
        buildUrlMethodMapping();

        System.out.println(urlMethodMapping);
        System.out.println(instanceMap);
        System.out.println(instanceTypeMap);
        System.out.println(classes);
        System.out.println();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 完整路径
        String url = req.getRequestURI();
        // 跟路径
        String path = req.getContextPath();
        // 计算出method上配置的路径
        String finallyUrl = url.replace(path, "");

        // 取出这个url对应的Controller和method
        Map<String, Object> map = urlMethodMapping.get(finallyUrl);
        if (map == null) {
            resp.getWriter().write("url not found 404");
            return;
        }
        Method method = (Method) map.get(METHOD_KEY);

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
            if ("String".equals(requestParam)) {
                for (Entry<String, String[]> param : parameterMap.entrySet()) {
                    String value = Arrays
                        .toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
                    paramValues[i] = value;
                }
            }
        }
        //利用反射机制来调用
        try {
            method.invoke(map.get(CONTROLLER_KEY), paramValues);//第一个参数是method所对应的实例 在ioc容器中
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doLoadConfig(String location) {

        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(location)) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 组装instanceMap
     */
    private void buildInstanceMap(Set<Class<?>> classes) {
        // 开始循环全部class
        for (Class<?> clasz : classes) {

            // 组装instanceMap
            // 判断是否是是加了Controller注解的java对象
            if (clasz.isAnnotationPresent(MyController.class)) {
                try {
                    // 实例化对象
                    Object obj = clasz.newInstance();
                    MyController controller = clasz.getAnnotation(MyController.class);

                    // 如果没有设置别名，那么用类名首字母小写做key
                    if (controller.value().isEmpty()) {
                        instanceMap.put(firstLowerName(clasz.getSimpleName()), obj);
                    } else {
                        // 如果设置了别名那么用别名做key
                        instanceMap.put(controller.value(), obj);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("初始化instanceMap时在处理Controller注解时出现了异常");
                }
            } else if (clasz.isAnnotationPresent(MyService.class)) {
                // 实例化对象
                Object obj = null;
                try {
                    // 实例化对象
                    obj = clasz.newInstance();
                    MyService service = clasz.getAnnotation(MyService.class);

                    // 如果没有设置别名，那么用类名首字母小写做key
                    if (service.value().isEmpty()) {
                        instanceMap.put(firstLowerName(clasz.getSimpleName()), obj);
                    } else {
                        // 如果设置了别名那么用别名做key
                        instanceMap.put(service.value(), obj);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("初始化instanceMap时在处理Service注解时出现了异常");
                }
                // 实现的接口数组
                Class<?>[] interfaces = clasz.getInterfaces();
                for (Class<?> class1 : interfaces) {
                    if (instanceTypeMap.get(class1) != null) {
                        throw new RuntimeException(class1.getName() + "接口不能被多个类实现！");
                    }
                    instanceTypeMap.put(class1, obj);
                }
            } else {
                continue;
            }
        }
    }

    /**
     * 根据实例Map开始注入
     */
    private void doIoc() {
        // 开始注入，我们只对加了@Controller和@Service标签中的，属性加了@autowired的进行注入操作
        for (Entry<String, Object> entry : instanceMap.entrySet()) {

            // 取出全部的属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();

            // 循环属性校验哪些是加了@autowired注解的
            for (Field field : fields) {
                field.setAccessible(true);// 可访问私有属性

                // 有注解的时候
                if (field.isAnnotationPresent(MyAutowrited.class)) {

                    // 没有配别名注入的时候
                    if (field.getAnnotation(MyAutowrited.class).value().isEmpty()) {
                        // 直接获取
                        try {
                            // 根据类型来获取他的实现类
                            Object object = instanceTypeMap.get(field.getType());
                            field.set(entry.getValue(), object);
                            System.out.println("auto by type: " + field.getType() + ", " + entry.getValue());
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            // 将被注入的对象
                            Object object = instanceMap.get(field.getAnnotation(MyAutowrited.class).value());
                            System.out.println(
                                "auto by name: " + field.getAnnotation(MyAutowrited.class).value() + ", " + entry
                                    .getValue());
                            field.set(entry.getValue(), object);
                        } catch (Exception e) {
                            throw new RuntimeException("开始注入时出现了异常");
                        }
                    }
                }
            }
        }
    }

    /**
     * 注入完之后开始映射url和method
     */
    private void buildUrlMethodMapping() {
        // 注入完之后开始映射url和method
        // 组装urlMethodMapping
        for (Entry<String, Object> entry : instanceMap.entrySet()) {

            // 迭代出所有的url
            String parenturl = "";

            // 判断Controller上是否加了requestMapping
            if (entry.getValue().getClass().isAnnotationPresent(MyRequestMapping.class)) {
                parenturl = entry.getValue().getClass().getAnnotation(MyRequestMapping.class).value();
            }

            // 取出全部的method
            Method[] methods = entry.getValue().getClass().getMethods();

            // 迭代全部的方法，检查哪些方法上加了requestMaping注解
            for (Method method : methods) {
                if (method.isAnnotationPresent(MyRequestMapping.class)) {
                    // 得到一个完整的url请求
                    String url = parenturl + method.getAnnotation(MyRequestMapping.class).value();
                    Map<String, Object> value = new HashMap<>();
                    value.put(CONTROLLER_KEY, entry.getValue());
                    value.put(METHOD_KEY, method);
                    urlMethodMapping.put(url, value);
                }
            }
        }
    }

    /**
     * 首字母小写
     *
     * @return
     */
    private String firstLowerName(String name) {
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }
}
