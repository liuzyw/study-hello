package com.study.cloud.feign;

import com.study.cloud.feign.entity.User;
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

}
