package com.study.spring.listener;

import com.study.spring.netty.WSServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created on 2019-01-13
 *
 * @author liuzhaoyuan
 */
@Component
public class AppListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (event.getApplicationContext().getParent() == null) {

            LOGGER.info("my spring web start , start ... ");

            System.out.println("app start .... ");

            new Thread(() -> new WSServer().start()).start();
        }

    }
}
