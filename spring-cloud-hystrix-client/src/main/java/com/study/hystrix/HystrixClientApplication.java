package com.study.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 2018-02-21
 *
 * @author liuzhaoyuan
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ServletComponentScan
@EnableHystrixDashboard
@EnableHystrix
//@ComponentScan(basePackages = {"com.study.hello.*"})
public class HystrixClientApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(HystrixClientApplication.class, args);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--- Hello Spring Cloud HystrixClientApplication ---");
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
