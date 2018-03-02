package com.study.hystrix.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "spring-cloud-eureka-producer",fallback = HelloHystrixClient.class)
public interface HelloClient {

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String hello();


    @RequestMapping(method = RequestMethod.GET, value = "/getUserAge/{name}")
    int getUserAge(@PathVariable("name") String name);

}
