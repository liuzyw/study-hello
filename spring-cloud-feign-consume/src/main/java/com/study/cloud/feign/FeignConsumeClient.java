package com.study.cloud.feign;

import com.study.cloud.feign.entity.User;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * Created on 2018-02-18
 *
 * @author liuzhaoyuan
 */
public interface FeignConsumeClient {

    @RequestLine("GET /hello")
    String hello();

    @RequestLine("GET /getRestUser")
    User getUser();

    @RequestLine("GET /getUserAge/{name}")
    int getUserAge(@Param("name") String name);

    @RequestLine("POST /createUser")
    @Headers("Content-Type: application/json")
    boolean createUser(User user);

    @RequestLine("POST /userXML")
    @Headers("Content-Type: application/xml")
    User createXMLUser(User user);

}
