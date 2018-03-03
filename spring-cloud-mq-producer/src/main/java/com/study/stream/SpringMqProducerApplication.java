package com.study.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created on 2018-03-03
 *
 * @author liuzhaoyuan
 */
@SpringBootApplication
@EnableEurekaClient
@EnableBinding(SendService.class)
public class SpringMqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMqProducerApplication.class, args);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--- Hello Spring Cloud SpringMqProducerApplication ---");
        System.out.println();
        System.out.println();
        System.out.println();
    }

}
