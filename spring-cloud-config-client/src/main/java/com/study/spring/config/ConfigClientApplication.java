package com.study.spring.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created on 2018-03-03
 *
 * @author liuzhaoyuan
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--- Hello Spring Cloud ConfigClientApplication ---");
        System.out.println();
        System.out.println();
        System.out.println();
    }

}
