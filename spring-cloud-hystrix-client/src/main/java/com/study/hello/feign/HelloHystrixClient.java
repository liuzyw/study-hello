package com.study.hello.feign;

import org.springframework.stereotype.Component;

/**
 * Created on 2018-02-28
 *
 * @author liuzhaoyuan
 */
@Component
public class HelloHystrixClient implements HelloClient{

    @Override
    public String hello() {
        return "hystrix error hello";
    }

    @Override
    public int getUserAge(String name) {
        return -23;
    }
}
