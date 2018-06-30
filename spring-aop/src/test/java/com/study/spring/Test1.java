package com.study.spring;

import com.study.spring.beans.Blue;
import com.study.spring.beans.Dog;
import com.study.spring.beans.Hourse;
import com.study.spring.beans.MyEnv;
import com.study.spring.config.MyConfig;
import com.study.spring.etc.MyFactoryBean;
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

    @Test
    public void tr2() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);

        Hourse hourse = (Hourse) applicationContext.getBean(Hourse.class);
        hourse.say();

//        hourse = (Hourse) applicationContext.getBean("hourse02");
//        hourse.say();

    }

    @Test
    public void testProperties() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        printBean(applicationContext);
        Dog bean = (Dog) applicationContext.getBean("dog01");
        System.out.println(bean.getName());

    }

    /**
     * 1. 命令行参数激活
     */
    @Test
    public void testProfile() {
        // 1. 创建 applicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 2. 设置激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test");

        // 3. 注册配置类
        applicationContext.register(MyConfig.class);

        // 4. 刷新容器
        applicationContext.refresh();

        printBean(applicationContext);
        MyEnv bean = applicationContext.getBean(MyEnv.class);

        System.out.println("----------------------------------------");
        System.out.println(bean.getEnv());

    }

    private void printBean(AnnotationConfigApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String definitionName : beanDefinitionNames) {
            System.out.println("-->  " + definitionName);
        }
    }
}
