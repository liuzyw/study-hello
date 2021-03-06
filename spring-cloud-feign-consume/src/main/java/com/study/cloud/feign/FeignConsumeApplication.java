package com.study.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created on 2018-02-18
 *
 * @author liuzhaoyuan
 */

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignConsumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignConsumeApplication.class, args);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--- Hello Spring Cloud FeignConsumeApplication ---");
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
