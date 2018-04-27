package com.study.hystrix.controller;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import com.study.hystrix.feign.HelloClient;
import com.study.hystrix.feign.HelloClient2;
import com.study.hystrix.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018-02-21
 *
 * @author liuzhaoyuan
 */
@RestController
public class HystrixClientController {


    @Autowired
    private HelloService helloService;

    @Autowired
    private HelloClient helloClient;

    @Autowired
    private HelloClient2 helloClient2;

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello() {
        return helloClient.hello();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello2")
    public String hello2() {
        return helloClient2.hello();
    }

    @RequestMapping(value = "/aaa", method = RequestMethod.GET)
    public String aaa() {
        return helloService.hello();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index hello";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserAge/{name}")
    public int getUserAge(@PathVariable("name") String name) {
        int result = helloClient.getUserAge(name);
        System.out.println("----" + result);
        HystrixCircuitBreaker breaker = HystrixCircuitBreaker.Factory
            .getInstance(HystrixCommandKey.Factory
                .asKey("HelloClient#hello()"));
        System.out.println("断路器状态：" + breaker.isOpen());
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserAge2/{name}")
    public int getUserAge2(@PathVariable("name") String name) {
        int result = helloClient2.getUserAge(name);
        System.out.println("--2--" + result);
        return result;
    }


}
