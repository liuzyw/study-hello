package com.study.spring.config;

import com.study.spring.aop.MyAspect;
import com.study.spring.beans.Hourse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created on 2018-06-14
 * <p>
 * <p>作为spring的根容器，不扫描web相关类</>
 *
 * @author liuzhaoyuan
 */
@EnableTransactionManagement
@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = {"com.study.spring.aop", "com.study.spring.service", "com.study.spring.etc"},
    excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = Controller.class)}, useDefaultFilters = false)
public class MyConfig {


    @Bean(name = "hourse", initMethod = "init", destroyMethod = "destory")
    public Hourse hourse() {
        return new Hourse(11, "tom");
    }

    @Bean
    public MyAspect myAspect() {
        return new MyAspect();
    }


}
