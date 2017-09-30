package com.study.spring.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created on 2017-09-25
 *
 * @author liuzhaoyuan
 */
public class MyBeanLifeCycle implements BeanNameAware, BeanFactoryAware,
    ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {

    public MyBeanLifeCycle(){
        System.out.println("MyBeanLifeCycle instance");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("MyBeanLifeCycle bean id : " + s);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("MyBeanLifeCycle destroy");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanLifeCycle beanFactory : " + beanFactory.toString());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("MyBeanLifeCycle applicationContext : " + applicationContext.getApplicationName());
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("MyBeanLifeCycle postProcessBeforeInitialization : " + s);
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("MyBeanLifeCycle postProcessAfterInitialization : " + s);
        return o;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyBeanLifeCycle afterPropertiesSet");
    }
}
