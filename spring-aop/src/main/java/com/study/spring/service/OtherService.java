package com.study.spring.service;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Created on 2018-06-20
 *
 * @author liuzhaoyuan
 */
@Service
public class OtherService {


    @EventListener(classes = ApplicationEvent.class)
    public void listener(ApplicationEvent event) {
        System.out.println("listener event: " + event);
    }

}
