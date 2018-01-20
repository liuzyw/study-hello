package com.study.spring.boot.web.controller;

import com.study.util.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2017-10-07
 *
 * @author liuzhaoyuan
 */
@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);


    @RequestMapping("/hello")
    public String home() {
        LOGGER.info(" spring boot web log hello");
        return "--- Hello My First Spring Boot Web Test dev --- \n" + DateUtils.getCurTimeStr();
    }


}
