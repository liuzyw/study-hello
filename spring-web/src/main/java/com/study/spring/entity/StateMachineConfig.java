package com.study.spring.entity;

import com.study.spring.base.ToString;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class StateMachineConfig extends ToString {


    private long id;

    private String bizCategory;

    private String content;


    public StateMachineConfig() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getBizCategory() {
        return bizCategory;
    }

    public void setBizCategory(String bizCategory) {
        this.bizCategory = bizCategory;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
