package com.study.spring.cloud.controller;

import com.study.util.date.DateUtils;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String helloClient() {
        List<String> services = discoveryClient.getServices();
        if (!services.isEmpty()) {
            for (String service : services) {
                LOGGER.info("host:" + service + " , serviceId:" + service);
            }

        }
        return "hello " + DateUtils.getCurTimeStr();
    }
}
