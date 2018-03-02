package com.study.spring.zuul.config;

import com.study.spring.zuul.filter.MyFilter;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2018-03-01
 *
 * @author liuzhaoyuan
 */
@Configuration
public class ZuulConfig {

    /**
     * 访问网关的 /sale/**，将会被路由到 spring-zuul-sale 服务进行处理
     */
//    @Bean
    public PatternServiceRouteMapper patternServiceRouteMapper() {
        return new PatternServiceRouteMapper(
            "(spring)-(zuul)-(?<module>.+)", "${module}/**");
    }

    @Bean
    public MyFilter myFilter() {
        return new MyFilter();
    }
}
