package com.study.spring.state.runtime;

import com.study.spring.state.action.ActionItemDefinition;
import com.study.spring.state.action.ActionResult;
import com.study.spring.state.action.NormalAction;
import com.study.spring.state.model.ActionCondition;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class ActionConditionExecutorImpl implements ActionConditionExecutor {


    @Autowired
    private NormalAction normalAction;

    @Override
    public ActionResult executeAction(ActionCondition actionCondition, Map<String, Object> input) {

        ActionResult actionResult = new ActionResult();

        try {
            List<ActionItemDefinition> actionItemDefinitions = actionCondition.getActionItemDefinitions();

            if (actionItemDefinitions == null || actionItemDefinitions.size() == 0) {
                actionResult.setSuccess(true);
                return actionResult;
            }

            normalAction.execute(actionItemDefinitions, input, actionResult);
            return actionResult;
        } catch (Exception e) {
            actionResult.setSuccess(false);
            actionResult.setErrorMsg(e.getMessage());
        }

        return actionResult;
    }


}
