package com.study.spring.state.runtime;

import com.study.spring.state.action.ActionResult;
import com.study.spring.state.model.ActionCondition;
import com.study.spring.state.model.StateNode;
import java.util.Map;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class StateMachineContext {


    private StateNode targetNode;

    private StateNode sourceNode;

    private ActionCondition currentAction;

    private Map<String, Object> input;

    private ActionResult actionResult;


    public StateNode getTargetNode() {
        return targetNode;
    }

    public void setTargetNode(StateNode targetNode) {
        this.targetNode = targetNode;
    }

    public StateNode getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(StateNode sourceNode) {
        this.sourceNode = sourceNode;
    }

    public ActionCondition getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(ActionCondition currentAction) {
        this.currentAction = currentAction;
    }

    public Map<String, Object> getInput() {
        return input;
    }

    public void setInput(Map<String, Object> input) {
        this.input = input;
    }

    public ActionResult getActionResult() {
        return actionResult;
    }

    public void setActionResult(ActionResult actionResult) {
        this.actionResult = actionResult;
    }
}
