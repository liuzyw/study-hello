package com.study.spring.state.model;

import com.study.spring.base.ToString;
import com.study.spring.state.action.ActionItemDefinition;
import java.util.List;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class ActionConditionDefinition extends ToString {

    private ConditionDefinition conditionDefinition;

    private List<ActionItemDefinition> actionItemDefinitions;

    public ConditionDefinition getConditionDefinition() {
        return conditionDefinition;
    }

    public void setConditionDefinition(ConditionDefinition conditionDefinition) {
        this.conditionDefinition = conditionDefinition;
    }

    public List<ActionItemDefinition> getActionItemDefinitions() {
        return actionItemDefinitions;
    }

    public void setActionItemDefinitions(List<ActionItemDefinition> actionItemDefinitions) {
        this.actionItemDefinitions = actionItemDefinitions;
    }
}
