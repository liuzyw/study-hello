package com.study.cloud.feign.demo;

import com.study.cloud.feign.FeignConsumeClient;
import com.study.cloud.feign.entity.User;
import feign.Feign;
import feign.gson.GsonDecoder;

/**
 * Created on 2018-02-18
 *
 * @author liuzhaoyuan
 */
public class FeignDemo {

    public static void main(String[] args) {

        // 如果返回值是基本类型，包括String 类型，不需要解码器，返回user时，需要解码器

        FeignConsumeClient consumeClient = Feign.builder()
            .decoder(new GsonDecoder()).target(FeignConsumeClient.class,"http://localhost:8112");

//        String hello = consumeClient.hello();

//        System.out.println(" - getHello -  " + hello);

        User user = consumeClient.getUser();

        System.out.println(" - getUser -  " + user);

    }
}
