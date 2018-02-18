package com.study.cloud.controller;

import com.study.util.date.DateUtils;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
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
    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

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

    @GetMapping(value = "/getServiceCount")
    public String getServiceCount() {
        List<String> list = discoveryClient.getServices();
        if (CollectionUtils.isNotEmpty(list)) {
            return "services count: " + list;
        }
        return "services count: 0";
    }

    @GetMapping("/getServiceInstance")
    public ServiceInstance getServiceInstance(){
        return loadBalancerClient.choose("spring-cloud-eureka-producer");
    }

}
