package com.study.spring.zuul.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018-03-01
 *
 * @author liuzhaoyuan
 */
@RestController
public class ZuulController {

    @RequestMapping(value = "/zuul/hello/{name}", method = RequestMethod.GET)
    public String helloClient(@PathVariable("name") String name) {
        System.out.println("----");
        return "hello " + "--spring-cloud-zuul-- " + name;
    }

    @RequestMapping(value = "/bb/{name}", method = RequestMethod.GET)
    public String zuul(@PathVariable("name") String name) {
        return "hello " + "--spring-cloud-zuul-- " + name;
    }

    @RequestMapping(value = "/bb/hello", method = RequestMethod.GET)
    public String zuul1() {
        return "hello " + "--spring-cloud-zuul-- ";
    }


}
