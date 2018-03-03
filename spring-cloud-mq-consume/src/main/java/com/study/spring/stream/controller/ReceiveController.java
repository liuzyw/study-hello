package com.study.spring.stream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 2018-03-03
 *
 * @author liuzhaoyuan
 */
@RestController
public class ReceiveController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    @ResponseBody
    public String router() {
        String json = restTemplate.getForObject("http://spring-cloud-eureka-producer/hello", String.class);
        return json;
    }

}
