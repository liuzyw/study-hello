package com.study.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2018-02-18
 *
 * @author liuzhaoyuan
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
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
