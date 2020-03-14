package com.study.spring.base;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class ToString implements Serializable {

    private static final Long serialVersionUID = 1L;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
