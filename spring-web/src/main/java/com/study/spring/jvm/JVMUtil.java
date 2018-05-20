package com.study.spring.jvm;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Properties;

/**
 * Created on 2018-05-20
 *
 * @author liuzhaoyuan
 */
public class JVMUtil {


    public static void main(String[] args) {

        System.out.println(getJvmInfo());
    }

    public static Map<String, Object> getJvmInfo() {
        Map<String, Object> map = Maps.newHashMap();

        // 获取jvm运行环境
        Runtime runtime = Runtime.getRuntime();

        // 获取jvm系统属性
        Properties properties = System.getProperties();

        // 操作系统版本
        String osVersion = properties.getProperty("os.version");
        map.put("osVersion", osVersion);

        //java运行环境供应商
        String vendor = properties.getProperty("java.vendor");
        map.put("vendor", vendor);

        String osName = properties.getProperty("os.name");
        map.put("osName", osName);

        Map<String, String> currentOsEvn = System.getenv();

        String username = currentOsEvn.get("USER");
        map.put("username", username);

        long totalMemory = runtime.totalMemory();
        map.put("totalMemory", totalMemory);

        long freeMemory = runtime.freeMemory();
        map.put("freeMemory", freeMemory);

        long maxMemory = runtime.maxMemory();
        map.put("maxMemory", maxMemory);

        int cpu = runtime.availableProcessors();
        map.put("cpu", cpu);

        return map;
    }
}
