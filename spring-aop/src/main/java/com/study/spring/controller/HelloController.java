package com.study.spring.controller;

import com.study.spring.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018-06-14
 *
 * @author liuzhaoyuan
 */
@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/hello2")
    @ResponseBody
    public String hello() {
        return "hello annotation spring";
    }

    @RequestMapping(value = "/hello3")
    @ResponseBody
    public String hello3() {
        helloService.sayHello();
        return "hello annotation spring333";
    }


    @RequestMapping(value = "/user")
    public String hello4() {
        return "views/user";
    }


}
