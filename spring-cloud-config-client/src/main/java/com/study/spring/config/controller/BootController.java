package com.study.spring.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018-03-03
 *
 * @author liuzhaoyuan
 */
@RestController
@RefreshScope
public class BootController {


    @Autowired
    private ApplicationContext ctx;

    @Value("${content}")
    private String content;

    @RequestMapping("/text")
    public String text() {
        Environment env = ctx.getEnvironment();

        return "content:" + content + env.getProperty("content");
    }

    @RequestMapping("/")
    public String index() {
        return "index:";
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        System.out.println("############  ");
        Environment env = ctx.getEnvironment();
        System.out.println(env.getProperty("test.user.name"));
        System.out.println(env.getProperty("test.pass.name"));
        return env.getProperty("test.user.name") + env.getProperty("test.pass.name");
    }

}
