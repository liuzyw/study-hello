package com.study.spring;

import com.study.spring.beans.Blue;
import com.study.spring.beans.Hourse;
import com.study.spring.etc.MyFactoryBean;
import com.study.spring.config.MyConfig;
import com.study.spring.service.HelloService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created on 2018-06-19
 *
 * @author liuzhaoyuan
 */
public class Test1 {

    @Test
    public void te() throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);

        HelloService helloService = (HelloService) applicationContext.getBean("helloService");

        Hourse hourse = (Hourse) applicationContext.getBean("hourse");
        hourse.say();

        helloService.sayHello();
        helloService.div(10, 5);

        MyFactoryBean myFactoryBean = (MyFactoryBean) applicationContext.getBean("&myFactoryBean");

        myFactoryBean.getObject().say();

        Blue blue = (Blue) applicationContext.getBean("myFactoryBean");
        blue.say();


        applicationContext.close();
    }
}