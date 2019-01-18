package com.study.web.chat.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2019-01-12
 *
 * @author liuzhaoyuan
 */
@RestController
public class HelloController implements Serializable {

    private static final Long serialVersionUID = 1L;


    @GetMapping("/")
    public String index() {
        return "web chat index " + LocalDateTime.now();
    }

    @GetMapping("/hello")
    public String hello() {
        return "web chat hello " + LocalDateTime.now();
    }

}
