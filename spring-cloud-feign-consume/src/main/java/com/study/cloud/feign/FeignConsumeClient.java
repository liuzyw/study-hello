package com.study.cloud.feign;

import com.study.cloud.config.feign.FooConfiguration;
import com.study.cloud.feign.entity.User;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created on 2018-02-18
 *
 * @author liuzhaoyuan
 */
//@FeignClient(name = "spring-cloud-feign-producer", configuration = FooConfiguration.class)
public interface FeignConsumeClient {

    /**
     * 这个是 feign 的注解，需要配置 feign 的
     * @return
     */
    @RequestLine("GET /hello")
    String hello();

    @RequestLine("POST /getRestUser")
    User getUser();

    @RequestLine("GET /getUserAge/{name}")
    int getUserAge(@Param("name") String name);

    @RequestLine("POST /createUser")
    @Headers("Content-Type: application/json")
    boolean createUser(User user);

//    @RequestLine("POST /userXML")
//    @Headers("Content-Type: application/xml")
//    User createXMLUser(User user);

}
