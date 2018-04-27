package com.study.hystrix.feign;

import org.springframework.stereotype.Component;

/**
 * Created on 2018-02-28
 *
 * @author liuzhaoyuan
 */
@Component
public class HelloHystrixClient2 implements HelloClient2{

    @Override
    public String hello() {
        return "hystrix error hello2";
    }

    @Override
    public int getUserAge(String name) {
        return -232;
    }
}
