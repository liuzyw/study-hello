package com.study.hello.controller;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import com.study.hello.feign.HelloClient;
import com.study.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello() {
        return helloClient.hello();
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


}
