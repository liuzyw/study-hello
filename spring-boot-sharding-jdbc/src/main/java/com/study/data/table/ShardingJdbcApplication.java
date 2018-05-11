package com.study.data.table;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created on 2018-05-12
 *
 * @author liuzhaoyuan
 */
@SpringBootApplication
public class ShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class, args);
        System.out.println("---------------ShardingJdbcApplication----------------");
    }
}
