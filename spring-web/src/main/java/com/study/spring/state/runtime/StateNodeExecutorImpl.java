package com.study.spring.state.runtime;

import com.study.spring.state.action.ActionResult;
import com.study.spring.state.model.ActionCondition;
import com.study.spring.state.model.StateNode;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class StateNodeExecutorImpl implements StateNodeExecutor {


    @Autowired
    private ActionConditionExecutor actionConditionExecutor;

    @Override
    public ActionResult executeNode(StateNode stateNode, Map<String, Object> input) {

        ActionResult actionResult = new ActionResult();

        ActionCondition targetActionCondition = null;

        try {

            for (ActionCondition actionCondition : stateNode.getActionConditions()) {
                ConditionResult conditionResult = ConditionExecutor.evaluate(input, actionCondition.getCondition());

                if (conditionResult.isMatched()) {
                    targetActionCondition = actionCondition;
                }
            }

            actionResult = actionConditionExecutor.executeAction(targetActionCondition, input);

        } catch (Exception e) {
            actionResult.setSuccess(false);
            actionResult.setErrorMsg(e.getMessage());
        }

        return actionResult;
    }
}
