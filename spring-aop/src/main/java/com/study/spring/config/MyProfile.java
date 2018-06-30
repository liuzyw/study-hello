package com.study.spring.config;

import com.study.spring.beans.MyEnv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created on 2018-06-28
 *
 * @author liuzhaoyuan
 */
@Configuration
public class MyProfile {


    /**
     * 加了环境标识的bean，只有当环境激活才能 加载， 默认default环境
     *
     * @return
     */
    @Profile("default")
    @Bean
    public MyEnv myEnvDefault() {
        return new MyEnv("default", "liu");
    }

    @Profile("dev")
    @Bean
    public MyEnv myEnvDev() {
        return new MyEnv("dev", "Tom");
    }

    @Profile("test")
    @Bean
    public MyEnv myEnvTest() {
        return new MyEnv("test", "Jeck");
    }

    @Profile("prod")
    @Bean
    public MyEnv myEnvProd() {
        return new MyEnv("dev", "Ead");
    }

}
