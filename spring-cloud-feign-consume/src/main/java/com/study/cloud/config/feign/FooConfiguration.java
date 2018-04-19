package com.study.cloud.config.feign;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2018-04-19
 *
 * @author liuzhaoyuan
 */
@Configuration
public class FooConfiguration {

    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }
}
