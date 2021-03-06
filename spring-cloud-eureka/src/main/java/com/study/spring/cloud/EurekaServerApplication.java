package com.study.spring.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p这个是服务端</p>
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(EurekaServerApplication.class).web(true).run(args);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--- Hello Spring Cloud EurekaServerApplication---");
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
