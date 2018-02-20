package com.study.cloud.feign.controller;

import com.study.cloud.feign.HelloClient;
import com.study.cloud.feign.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018-02-20
 *
 * @author liuzhaoyuan
 */
@RestController
public class FeignConsumeController {

    @Autowired
    private HelloClient helloClient;

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello() {
        String result = helloClient.hello();
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserAge/{name}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public int getUserAge(@PathVariable("name") String name) {
        int p = helloClient.getUserAge(name);
        return p;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getRestUser")
    public User getRestUser() {
        return helloClient.getRestUser();
    }
}
