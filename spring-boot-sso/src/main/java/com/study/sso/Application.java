package com.study.sso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created on 2018-01-04
 *
 * @author liuzhaoyuan
 */
@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println();
        LOGGER.info("=========== SpringBoot SSO Start Success ===========");
        System.out.println();
        System.out.println();
    }
}
