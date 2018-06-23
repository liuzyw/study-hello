package com.study.spring.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created on 2018-06-16
 *
 * @author liuzhaoyuan
 */
public class Red implements ApplicationContextAware, BeanNameAware {

    private ApplicationContext applicationContext;

    private String name;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
