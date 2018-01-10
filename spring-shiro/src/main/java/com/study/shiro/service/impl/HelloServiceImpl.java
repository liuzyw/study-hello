package com.study.shiro.service.impl;

import com.study.shiro.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created on 2018-01-10
 *
 * @author liuzhaoyuan
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void hello() {
        System.out.println(getClass().getName() + " hello");
    }

    @Override
    public void hello1() {
        System.out.println(getClass().getName() + " hello1");

    }

    @Override
    public void hello2() {
        System.out.println(getClass().getName() + " hello2");

    }

    @Override
    public void hello3() {
        System.out.println(getClass().getName() + " hello3");

    }
}
