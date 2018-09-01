package com.study.spring.etc;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created on 2018-08-18
 *
 * @author liuzhaoyuan
 */
public class MyNameSpaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("coupon", new CouponBeanDefinitionParser());
    }
}
