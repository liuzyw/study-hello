package com.study.spring.etc;

import com.study.spring.beans.Blue;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created on 2018-06-21
 *
 * @author liuzhaoyuan
 */
@Component("myFactoryBean")
public class MyFactoryBean implements FactoryBean<Blue> {

    @Override
    public Blue getObject() throws Exception {
        return new Blue();
    }

    @Override
    public Class<?> getObjectType() {
        return Blue.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
