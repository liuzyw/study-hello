package com.study.spring.state.model;

import com.study.spring.base.ToString;

/**
 * Created on 2020-03-15
 *
 * @author liuzhaoyuan
 */
public class StateScene extends ToString {

    private StateNode stateNode;

    private String bizCategory;


    public StateScene() {
    }

    public StateNode getStateNode() {
        return stateNode;
    }

    public void setStateNode(StateNode stateNode) {
        this.stateNode = stateNode;
    }

    public String getBizCategory() {
        return bizCategory;
    }

    public void setBizCategory(String bizCategory) {
        this.bizCategory = bizCategory;
    }

    public StateScene(StateNode stateNode, String bizCategory) {
        this.stateNode = stateNode;
        this.bizCategory = bizCategory;
    }
}
