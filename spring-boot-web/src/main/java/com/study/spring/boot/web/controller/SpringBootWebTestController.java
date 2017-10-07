package com.study.spring.boot.web.controller;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2017-10-07
 *
 * @author liuzhaoyuan
 */
@RestController
@EnableAutoConfiguration
public class SpringBootWebTestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootWebTestController.class);

    @RequestMapping("/hello")
    public String home() {
        LOGGER.info(" spring boot web log ...");
        return "... Hello My First Spring Boot Web Test ..." + new Date();
    }


}
