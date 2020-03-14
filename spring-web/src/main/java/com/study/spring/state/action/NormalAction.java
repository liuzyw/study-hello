package com.study.spring.state.action;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class NormalAction implements Action {

    @Autowired
    private ActionItemRegistry actionItemRegistry;

    @Override
    public void execute(List<ActionItemDefinition> actionItemDefinitions, Map<String, Object> input, ActionResult actionResult) {
        for (ActionItemDefinition actionItemDefinition : actionItemDefinitions) {

            ActionItem actionItem = actionItemRegistry.getActionItemByCode(actionItemDefinition.getCode());

            Object result = actionItem.doBiz(input);

            actionResult.put(actionItemDefinition.getCode(), result);

        }

        actionResult.setSuccess(true);
    }
}
