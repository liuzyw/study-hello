package com.study.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created on 2018-02-18
 *
 * @author liuzhaoyuan
 */

@SpringBootApplication
@EnableEurekaClient
public class FeignProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignProducerApplication.class, args);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--- Hello Spring Cloud FeignProducerApplication ---");
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
