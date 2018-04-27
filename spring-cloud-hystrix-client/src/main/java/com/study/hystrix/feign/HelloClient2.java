package com.study.hystrix.feign;

import special.config.FooConfiguration2;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "spring-cloud-feign-producer",configuration = FooConfiguration2.class, fallback = HelloHystrixClient2.class)
public interface HelloClient2 {

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String hello();


    @RequestMapping(method = RequestMethod.GET, value = "/getUserAge/{name}")
    int getUserAge(@PathVariable("name") String name);

}
