package com.study.cloud.config.bus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018-04-26
 *
 * @author liuzhaoyuan
 */
@RestController
public class BusController {

    @Autowired
    private ApplicationContext ctx;

    @GetMapping("/")
    public String index() {
        return "bus index";
    }

    @GetMapping("/hello")
    public String hello() {
        return "bus hello ";
    }
}
