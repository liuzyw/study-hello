package com.study.cloud.feign.jaxrs;

import feign.Feign;
import feign.jaxrs.JAXRSContract;

public class RsMain {

    public static void main(String[] args) {
        RsClient client = Feign.builder().contract(new JAXRSContract()).target(RsClient.class, "http://localhost:8112");

        String result = client.hello();
        System.out.println(result);
    }

}
