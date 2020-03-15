package com.study.spring.state.model;

import com.study.spring.base.ToString;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class StateMachineConfigDefinition extends ToString {


    private String bizCategory;

    private StateNodeDefinition stateNodeDefinition;

    public String getBizCategory() {
        return bizCategory;
    }

    public void setBizCategory(String bizCategory) {
        this.bizCategory = bizCategory;
    }

    public StateNodeDefinition getStateNodeDefinition() {
        return stateNodeDefinition;
    }

    public void setStateNodeDefinition(StateNodeDefinition stateNodeDefinition) {
        this.stateNodeDefinition = stateNodeDefinition;
    }
}
