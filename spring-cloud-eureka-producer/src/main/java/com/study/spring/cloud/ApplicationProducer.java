package com.study.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p这个是客户端</p>
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ApplicationProducer {

    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
//        String port = scanner.nextLine();
        //        new SpringApplicationBuilder(ApplicationProducer.class).properties("server.port=" + port).run(args);

        SpringApplication.run(ApplicationProducer.class, args);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--- Hello Spring Cloud ApplicationProducer ---");
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
