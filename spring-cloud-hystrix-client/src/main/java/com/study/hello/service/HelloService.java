package com.study.hello.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 2018-02-27
 *
 * @author liuzhaoyuan
 */
@Service
public class HelloService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getHelloFallback", groupKey = "HelloGroup", commandKey = "HelloCommandKey",
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
        }, threadPoolProperties = {
        @HystrixProperty(name = "coreSize", value = "2")
    })
    public String hello() {
        try {
            Thread.sleep(2500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String member = restTemplate.getForEntity("http://spring-cloud-eureka-producer/hello", String.class).getBody();
        return member;
    }

    public String getHelloFallback() {
        LOGGER.info("getHelloFallback");
        return "hystrix hello";
    }
}
