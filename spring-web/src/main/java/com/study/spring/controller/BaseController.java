package com.study.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2017-09-24
 *
 * @author liuzhaoyuan
 */
@Controller
public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);


    @RequestMapping("testLog")
    public String testLog() {
        LOGGER.debug("------test debug------");
        LOGGER.info("------test info------");
        LOGGER.warn("------test warn------");

        try {
            int a = 12 / 0;
            System.out.println(a);
        } catch (Exception e) {
            LOGGER.error("------test error------" + e);
        }

        System.out.println("=======" + LOGGER.getClass());

        LOGGER.trace("my trace log4j2 -- 日志 -- trace");
        LOGGER.debug("my debug log4j2 -- 日志 -- debug");
        LOGGER.info("my info log4j2 -- 日志 -- info");
        LOGGER.warn("my warn log4j2 -- 日志 -- warn");
        LOGGER.error("my error log4j2 -- 日志 -- error");

        return "log";
    }

    @RequestMapping("/goAjaxReq")
    public String goAjaxReq() {
        return "ajax";
    }

    @RequestMapping("/goUser")
    public String goUser(){
        return "user/user";
    }

    @RequestMapping("/goFruit")
    public String goFruit(){
        return "fruit/fruit";
    }

    @RequestMapping("/goBook")
    public String goBook(){
        return "book/book";
    }

    @RequestMapping("/goUploadFile")
    public String goUploadFile(){
        return "upLoadFile";
    }

    @RequestMapping("/goRedis")
    public String goRedis(){
        return "redis/redis";
    }

}
