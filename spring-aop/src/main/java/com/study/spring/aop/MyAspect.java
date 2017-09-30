package com.study.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created on 2017-09-27
 *
 * @author liuzhaoyuan
 */

public class MyAspect {


    public void beforeWay(){
        System.out.println("before way...");
    }

    public void afterWay(){
        System.out.println("after way...");
    }

    public void aroundWay(ProceedingJoinPoint jp){
        try {
            System.out.println("aroundWay before ....");
            jp.proceed();
            System.out.println("aroundWay after ....");
        } catch (Throwable e){

        }
    }

}
