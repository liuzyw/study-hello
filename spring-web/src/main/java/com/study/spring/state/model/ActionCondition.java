package com.study.spring.state.model;

import com.study.spring.base.ToString;
import com.study.spring.state.action.ActionItemDefinition;
import java.util.List;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class ActionCondition extends ToString {


    private List<ActionItemDefinition> actionItemDefinitions;


    private Condition condition;


    public static ActionCondition create() {
        return new ActionCondition();
    }


    public List<ActionItemDefinition> getActionItemDefinitions() {
        return actionItemDefinitions;
    }

    public void setActionItemDefinitions(List<ActionItemDefinition> actionItemDefinitions) {
        this.actionItemDefinitions = actionItemDefinitions;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
