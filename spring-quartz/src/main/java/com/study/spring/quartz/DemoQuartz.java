package com.study.spring.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created on 2017-10-09
 *
 * @author liuzhaoyuan
 */
public class DemoQuartz {

    public static void main(String[] args) {
//        String resource = "classpath:appPoJob.xml";
        String resource = "classpath:appQuartzJobBean.xml";
//        String resource = "classpath:imoocQuartz.xml";
        ApplicationContext factory = new ClassPathXmlApplicationContext(resource);
    }


}
