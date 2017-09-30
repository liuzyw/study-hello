package com.study.spring.service.impl;

import com.study.spring.service.HelloService;

/**
 * Created on 2017-09-25
 *
 * @author liuzhaoyuan
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("hello world ....");
    }
}
