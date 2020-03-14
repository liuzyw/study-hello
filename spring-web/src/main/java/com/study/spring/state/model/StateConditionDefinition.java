package com.study.spring.state.model;

import com.study.spring.base.ToString;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class StateConditionDefinition extends ToString {


    private ConditionDefinition conditionDefinition;


    private StateNodeDefinition stateNodeDefinition;


    public ConditionDefinition getConditionDefinition() {
        return conditionDefinition;
    }

    public void setConditionDefinition(ConditionDefinition conditionDefinition) {
        this.conditionDefinition = conditionDefinition;
    }

    public StateNodeDefinition getStateNodeDefinition() {
        return stateNodeDefinition;
    }

    public void setStateNodeDefinition(StateNodeDefinition stateNodeDefinition) {
        this.stateNodeDefinition = stateNodeDefinition;
    }
}
