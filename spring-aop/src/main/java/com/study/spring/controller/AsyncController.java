package com.study.spring.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018-06-23
 *
 * @author liuzhaoyuan
 */
@Controller
public class AsyncController {

    @RequestMapping(value = "/call")
    @ResponseBody
    public Callable<String> callable() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return "dasdsadd";
            }
        };
    }

}
