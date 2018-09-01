package com.study.spring;

import com.study.spring.rhino.Coupon;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created on 2018-08-18
 *
 * @author liuzhaoyuan
 */
public class Test2 {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        Coupon coupon =(Coupon)context.getBean("testK");
        System.out.println(coupon.getValue());
    }
}
