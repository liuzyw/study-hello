package com.study.hystrix.factory;

import com.study.hystrix.feign.HelloClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created on 2018-04-22
 *
 * @author liuzhaoyuan
 */
@Component
public class HystrixClientFactory implements FallbackFactory<HelloClient> {

    @Override
    public HelloClient create(Throwable cause) {
        return new HelloClient() {
            @Override
            public String hello() {
                return "fallback; reason was: " + cause.getMessage();
            }

            @Override
            public int getUserAge(String name) {
                return -1000;
            }
        };
    }
}
