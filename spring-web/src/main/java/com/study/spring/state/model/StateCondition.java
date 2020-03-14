package com.study.spring.state.model;

import com.study.spring.base.ToString;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class StateCondition extends ToString {


    private StateNode stateNode;


    private Condition condition;


    public static StateCondition create() {
        return new StateCondition();
    }


    public StateNode getStateNode() {
        return stateNode;
    }

    public void setStateNode(StateNode stateNode) {
        this.stateNode = stateNode;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
