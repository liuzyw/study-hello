package com.study.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p这个是客户端</p>

 */
//@EnableDiscoveryClient
//@SpringBootApplication
public class ApplicationClient {

    public static void main(String[] args) {

        SpringApplication.run(ApplicationClient.class, args);
//        new SpringApplicationBuilder(ApplicationClient.class).web(true).run(args);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--- Hello Spring Cloud ---");
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
