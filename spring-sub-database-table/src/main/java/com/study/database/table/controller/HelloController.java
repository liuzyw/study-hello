package com.study.database.table.controller;

import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018-05-11
 *
 * @author liuzhaoyuan
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello " + LocalDateTime.now();
    }


    @RequestMapping(value = "/receipt", method = RequestMethod.GET)
    public String receipt() {
        return "receipt";
    }

}
