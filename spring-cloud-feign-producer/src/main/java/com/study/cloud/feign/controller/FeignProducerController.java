package com.study.cloud.feign.controller;

import com.study.cloud.feign.entity.User;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2017-10-29
 *
 * @author liuzhaoyuan
 */
@RestController
public class FeignProducerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeignProducerController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloClient(HttpServletRequest request) {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        LOGGER.info("host:" + serviceInstance.getHost() + " , serviceId:" + serviceInstance.getServiceId());
        return "hello " + "--spring-cloud-feign-producer--";
    }


    @RequestMapping(value = "/getRestUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User restUser() {

        User user = new User();
        user.setAge(121);
        user.setName("kangbazi");
        user.setSex("man");

        return user;
    }

    @RequestMapping(value = "/getUserAge/{name}", method = RequestMethod.GET)
    public int getUserAge(@PathVariable("name") String name) {
        LOGGER.info("getUserAge by name:{}", name);
        return 23;
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public boolean createUser(@RequestBody User user) {
        LOGGER.info("createUser:{}", user);
        return true;
    }

    @RequestMapping(value = "/userXML")
    public String userXML(@RequestBody User user) {
        LOGGER.info("userXML:{}", user);
        return "<user><name>tom</name></user>";
    }
}
