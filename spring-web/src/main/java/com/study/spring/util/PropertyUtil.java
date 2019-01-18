package com.study.spring.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static Properties props;

    private static Map<String, String> cityCodeMap = new HashMap<>();

    static {
        loadProps();
    }

    private static synchronized void loadProps() {
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
//<!--第一种，通过类加载器进行获取properties文件流-->
            in = PropertyUtil.class.getClassLoader().getResourceAsStream("city.properties");
// <!--第二种，通过类进行获取properties文件流-->
            //in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
//            props.load(in);
            props.load(new InputStreamReader(in, "UTF-8"));
        } catch (FileNotFoundException e) {
            logger.error("jdbc.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("jdbc.properties文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
        logger.info("properties文件内容：" + props.getProperty("101020100"));
        Set<Entry<Object, Object>> entrySet = props.entrySet();//返回的属性键值对实体
        for (Map.Entry<Object, Object> entry : entrySet) {
//            System.out.println((String) entry.getValue() + (String) entry.getKey());
            cityCodeMap.put((String) entry.getValue(), (String) entry.getKey());
        }

        System.out.println("cityCodeMap.get " + cityCodeMap.get("合肥"));
    }

    public static String getProperty(String key) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }

    public static String getCityCodeByName(String name) {
        if (cityCodeMap.size() == 0) {
            loadProps();
        }
        return cityCodeMap.get(name);
    }
}