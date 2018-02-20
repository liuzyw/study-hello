package com.study.cloud.feign.demo;

import com.study.cloud.feign.FeignConsumeClient;
import com.study.cloud.feign.entity.User;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBDecoder;
import feign.jaxb.JAXBEncoder;

/**
 * Created on 2018-02-18
 *
 * @author liuzhaoyuan
 */
public class FeignDemo {

    public static void main(String[] args) {

        // 如果返回值是基本类型，包括String 类型，不需要解码器，返回user时，需要解码器

        FeignConsumeClient consumeClient = Feign.builder()
            .decoder(new GsonDecoder()).target(FeignConsumeClient.class, "http://localhost:8112");

        FeignConsumeClient consumeClient1 = Feign.builder().target(FeignConsumeClient.class, "http://localhost:8112");

        FeignConsumeClient consumeClient2 = Feign.builder().decoder(new GsonDecoder()).encoder(new GsonEncoder()).target(FeignConsumeClient.class, "http://localhost:8112");



        User user = consumeClient.getUser();

        System.out.println(" - getUser -  " + user);

        String hello = consumeClient1.hello();

        System.out.println(" - getHello -  " + hello);

        int age = consumeClient.getUserAge("com");

        System.out.println("- getUserAge -" + age);

        boolean re = consumeClient2.createUser(user);
        System.out.println("- createUser -" + re);


        JAXBContextFactory jaxbFactory = new JAXBContextFactory.Builder().build();
        // 获取服务接口
        FeignConsumeClient client = Feign.builder()
            .encoder(new JAXBEncoder(jaxbFactory))
            .decoder(new JAXBDecoder(jaxbFactory))
            .target(FeignConsumeClient.class, "http://localhost:8112");
        // 构建参数
        User p = new User();
        p.setName("angus");
        p.setAge(33);
        // 调用接口并返回结果
        User result = client.createXMLUser(p);
        System.out.println(result.getName());

    }
}
