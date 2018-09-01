package com.study.spring.etc;

import com.study.spring.rhino.Coupon;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created on 2018-08-18
 *
 * @author liuzhaoyuan
 */
public class CouponBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {


    @Override
    protected Class<?> getBeanClass(Element element) {
        return Coupon.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String value = element.getAttribute("value");
        String orderId = element.getAttribute("orderId");

        if (StringUtils.hasLength(value)) {
            builder.addPropertyValue("value", value);

        }

        if (StringUtils.hasLength(orderId)) {
            builder.addPropertyValue("orderId", orderId);
        }
    }
}
