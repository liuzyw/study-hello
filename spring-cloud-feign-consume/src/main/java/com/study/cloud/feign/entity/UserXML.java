package com.study.cloud.feign.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created on 2018-02-19
 *
 * @author liuzhaoyuan
 */
@XmlRootElement
public class UserXML {

    @XmlElement
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
