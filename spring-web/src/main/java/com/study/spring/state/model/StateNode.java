package com.study.spring.state.model;

import com.study.spring.base.ToString;
import com.study.spring.state.runtime.ConditionExecutor;
import com.study.spring.state.runtime.ConditionResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class StateNode extends ToString {


    private boolean start;

    private String startCode;


    private List<ActionCondition> actionConditions;

    private List<StateCondition> stateConditions;


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

    public List<ActionCondition> getActionConditions() {
        return actionConditions;
    }

    public void setActionConditions(List<ActionCondition> actionConditions) {
        this.actionConditions = actionConditions;
    }

    public List<StateCondition> getStateConditions() {
        return stateConditions;
    }

    public void setStateConditions(List<StateCondition> stateConditions) {
        this.stateConditions = stateConditions;
    }


    public boolean isEnd() {
        return stateConditions == null || stateConditions.size() == 0;
    }


    public StateNode nextNode(Map<String, Object> input) {
        if (this.isEnd()) {
            return null;
        }

        for (StateCondition stateCondition : stateConditions) {
            ConditionResult result = ConditionExecutor.evaluate(input, stateCondition.getCondition());
            if (result != null && result.isMatched()) {
                return stateCondition.getStateNode();
            }
        }
        return null;
    }


    public ActionCondition currentAction(Map<String, Object> input) {
        if (actionConditions == null || actionConditions.size() == 0) {
            return null;
        }
        for (ActionCondition actionCondition : actionConditions) {
            ConditionResult result = ConditionExecutor.evaluate(input, actionCondition.getCondition());

            if (result != null && result.isMatched()) {
                return actionCondition;
            }
        }
        return null;
    }


    public Map<String, StateNode> fetchAllChildNode() {
        Map<String, StateNode> childMap = new HashMap<>();
        iteratorChildNode(childMap);
        return childMap;
    }

    private void iteratorChildNode(Map<String, StateNode> resultMap) {
        if (resultMap.containsKey(this.startCode)) {
            resultMap.put(this.startCode, this);
            return;
        }

        if (this.isEnd()) {
            resultMap.put(this.startCode, this);
            return;
        }

        for (StateCondition stateCondition : this.stateConditions) {
            StateNode stateNode = stateCondition.getStateNode();
            stateNode.iteratorChildNode(resultMap);
            resultMap.put(stateNode.getStartCode(), stateNode);
        }
    }


}
