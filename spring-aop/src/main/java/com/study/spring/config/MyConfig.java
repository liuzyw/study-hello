package com.study.spring.config;

import com.study.spring.aop.MyAspect;
import com.study.spring.beans.Book;
import com.study.spring.beans.Hourse;
import com.study.spring.etc.MyCondition;
import com.study.spring.etc.MyImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
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
@ComponentScan(basePackages = {"com.study.spring.aop", "com.study.spring.service", "com.study.spring.etc"}/*,
    excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = Controller.class)}, useDefaultFilters = false*/)
@Import({Book.class, MyImportSelector.class,MyImportBeanDefinitionRegistrar.class,MyProperties.class,MyProfile.class})

public class MyConfig {


    @Conditional({MyCondition.class})
    @Bean(name = "hourse", initMethod = "init", destroyMethod = "destory")
    public Hourse hourse() {
        return new Hourse(11, "mac");
    }

    @Conditional({MyCondition.class})
    @Bean(name = "hourse02", initMethod = "init", destroyMethod = "destory")
    public Hourse hourse02() {
        return new Hourse(14, "linux");
    }

    /**
     * 把切面类加入容器
     * @return
     */
    @Bean
    public MyAspect myAspect() {
        return new MyAspect();
    }


}
