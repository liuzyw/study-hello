package com.springmvc.service.impl;

import com.springmvc.annotation.MyService;
import com.springmvc.service.HelloService;

/**
 * Created on 2018-04-21
 *
 * @author liuzhaoyuan
 */
@MyService("helloService")
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println(" hello service say hello");
    }
}
