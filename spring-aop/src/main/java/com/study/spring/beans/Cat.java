package com.study.spring.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created on 2018-06-27
 *
 * @author liuzhaoyuan
 */
public class Cat implements InitializingBean,DisposableBean {


    public String name;


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Cat init afterPropertiesSet ...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Cat destroy afterPropertiesSet ...");

    }
}
