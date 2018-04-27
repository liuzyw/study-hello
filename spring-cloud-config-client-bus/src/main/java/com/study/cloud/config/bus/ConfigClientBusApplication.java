package com.study.cloud.config.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * Created on 2018-03-03
 *
 * @author liuzhaoyuan
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigClientBusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientBusApplication.class, args);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--- Hello Spring Cloud ConfigClientBusApplication ---");
        System.out.println();
        System.out.println();
        System.out.println();
    }

}
