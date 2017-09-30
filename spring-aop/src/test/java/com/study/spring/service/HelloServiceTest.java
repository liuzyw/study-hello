package com.study.spring.service;

import com.study.spring.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created on 2017-09-25
 *
 * @author liuzhaoyuan
 */
public class HelloServiceTest extends BaseTest{

    @Autowired
    @Qualifier("helloService")
    private HelloService helloService;

    @Test
    public void testSay(){
        helloService.sayHello();
    }

}
