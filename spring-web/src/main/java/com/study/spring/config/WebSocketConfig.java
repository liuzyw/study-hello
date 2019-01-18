package com.study.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created on 2019-01-18
 *
 * @author liuzhaoyuan
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry) {

        // 配置消息代理
        messageBrokerRegistry.enableSimpleBroker("/mass", "/queue");

        messageBrokerRegistry.setUserDestinationPrefix("/queue");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/endpiontMark")
            .setAllowedOrigins("*")
            .withSockJS();

    }

}
