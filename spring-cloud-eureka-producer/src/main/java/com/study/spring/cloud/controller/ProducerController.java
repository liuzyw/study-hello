package com.study.spring.cloud.controller;

import com.study.spring.cloud.entity.User;
import com.study.util.date.DateUtils;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2017-10-29
 *
 * @author liuzhaoyuan
 */
@RestController
public class ProducerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String helloClient(HttpServletRequest request) {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        LOGGER.info("host:" + serviceInstance.getHost() + " , serviceId:" + serviceInstance.getServiceId());
        return "hello " + "--spring-cloud-eureka-producer--" + request.getRequestURL() + DateUtils.getCurTimeStr();
    }


    @RequestMapping(value = "/getRestUser", method = RequestMethod.GET)
    public User restUser() {

        User user = new User();
        user.setAge(12);
        user.setName("kangbazi");
        user.setSex("man");

        return user;
    }
}
