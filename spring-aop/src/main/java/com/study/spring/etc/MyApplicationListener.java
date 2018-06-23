package com.study.spring.etc;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created on 2018-06-20
 *
 * @author liuzhaoyuan
 */
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    /**
     * 当容器中发布事件后，方法触发
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("MyApplicationListener ..... " + event);
    }
}
