package com.study.hystrix.feign;

import com.study.hystrix.factory.HystrixClientFactory;
import special.config.FooConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * fallback & fallbackFactory 只能用一个
 */
@FeignClient(name = "spring-cloud-eureka-producer",configuration = FooConfiguration.class,
    /*fallback = HelloHystrixClient.class*/fallbackFactory = HystrixClientFactory.class)
public interface HelloClient {

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String hello();


    @RequestMapping(method = RequestMethod.GET, value = "/getUserAge/{name}")
    int getUserAge(@PathVariable("name") String name);

}
