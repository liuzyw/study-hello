package com.study.cloud.feign.demo;

import com.study.cloud.feign.FeignConsumeClient;
import com.study.cloud.feign.MyClient;
import com.study.cloud.feign.entity.User;
import feign.Feign;
import feign.gson.GsonDecoder;

/**
 * Created on 2018-02-19
 *
 * @author liuzhaoyuan
 */
public class HttpClient {

    public static void main(String[] args) {
        FeignConsumeClient consumeClient = Feign.builder().client(new MyClient())
            .decoder(new GsonDecoder()).target(FeignConsumeClient.class, "http://localhost:8112");

        User user = consumeClient.getUser();

        System.out.println(" - HttpClient getUser -  " + user);
    }

}
