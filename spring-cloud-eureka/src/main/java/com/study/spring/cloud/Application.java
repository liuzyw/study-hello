package com.study.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p></p>
 */
@EnableEurekaServer
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

//        SpringApplication.run(Application.class, args);
        new SpringApplicationBuilder(Application.class).web(true).run(args);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--- Hello Spring Cloud ---");
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
