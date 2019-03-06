package com.study.spring.config;

import com.study.spring.beans.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created on 2018-06-28
 *
 * @author liuzhaoyuan
 */
@Configuration
@PropertySource(value = {"classpath:app.properties"}, ignoreResourceNotFound = true)
public class MyProperties {


    @Bean(name = "dog01", initMethod = "init", destroyMethod = "destory")
    public Dog dog01() {
        return new Dog();
    }

    /**
     * 采用 @Value 属性注入的时候，需要显示加入该bean
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
