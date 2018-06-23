package com.study.spring.service.impl;

import com.study.spring.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created on 2017-09-25
 *
 * @author liuzhaoyuan
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("hello world ....");
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
