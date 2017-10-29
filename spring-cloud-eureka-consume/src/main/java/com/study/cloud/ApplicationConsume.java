package com.study.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * <p这个是客户端</p>

 */
@EnableDiscoveryClient
@SpringBootApplication
public class ApplicationConsume {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {

        SpringApplication.run(ApplicationConsume.class, args);
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
