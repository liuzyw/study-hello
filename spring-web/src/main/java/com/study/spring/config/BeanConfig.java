package com.study.spring.config;

import java.io.Serializable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created on 2019-01-18
 *
 * @author liuzhaoyuan
 */
@Configuration
public class BeanConfig implements Serializable {

    private static final Long serialVersionUID = 1L;


    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
