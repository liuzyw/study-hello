package com.study.spring.state.model;

import com.study.spring.base.ToString;
import java.util.List;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class StateNodeDefinition extends ToString {


    private boolean start = false;

    private String startCode;

    private List<ActionConditionDefinition> actionConditionDefinitions;


    private List<StateConditionDefinition> stateConditionDefinitions;


    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public String getStartCode() {
        return startCode;
    }

    public void setStartCode(String startCode) {
        this.startCode = startCode;
    }

    public List<ActionConditionDefinition> getActionConditionDefinitions() {
        return actionConditionDefinitions;
    }

    public void setActionConditionDefinitions(List<ActionConditionDefinition> actionConditionDefinitions) {
        this.actionConditionDefinitions = actionConditionDefinitions;
    }

    public List<StateConditionDefinition> getStateConditionDefinitions() {
        return stateConditionDefinitions;
    }

    public void setStateConditionDefinitions(List<StateConditionDefinition> stateConditionDefinitions) {
        this.stateConditionDefinitions = stateConditionDefinitions;
    }
}
