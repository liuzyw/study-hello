package com.study.spring.state.action;

import com.study.spring.base.ToString;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class ActionItemDefinition extends ToString implements Comparable<ActionItemDefinition> {


    private int order;

    private String code;

    private String desc;


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int compareTo(ActionItemDefinition other) {
        return this.order - other.getOrder();
    }
}
