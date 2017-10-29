package com.study.cloud.controller;

import com.study.util.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 2017-10-29
 *
 * @author liuzhaoyuan
 */
@RestController
public class ConsumeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloClient() {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        LOGGER.info("host:" + serviceInstance.getHost() + " , serviceId:" + serviceInstance.getServiceId());
        return "hello " + "-spring-cloud-eureka-client-consume--" + DateUtils.getCurTimeStr();
    }

    @RequestMapping(value = "/consume", method = RequestMethod.GET)
    public String helloConsume() {
        return restTemplate.getForEntity("http://spring-cloud-eureka-producer/hello", String.class).getBody();
    }

}
