package com.study.web.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebChatApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebChatApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(WebChatApplication.class, args);
        System.out.println();
        LOGGER.info("=========== WebChatApplication Web Chat Success ===========");
        System.out.println();
        System.out.println();
        System.out.println();
    }
}  