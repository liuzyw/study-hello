package com.study.spring.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * Created on 2018-01-11
 *
 * @author liuzhaoyuan
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setName("shiroFilter");
        return registration;
    }

}
