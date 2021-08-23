package com.study.model;

import com.study.base.ToString;

import java.util.List;

public class StateConfig extends ToString {

    private List<StateNode> stateNodes;


    public List<StateNode> getStateNodes() {
        return stateNodes;
    }

    public void setStateNodes(List<StateNode> stateNodes) {
        this.stateNodes = stateNodes;
    }
}
