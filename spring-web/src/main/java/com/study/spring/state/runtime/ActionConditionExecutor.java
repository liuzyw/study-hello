package com.study.spring.state.runtime;

import com.study.spring.state.action.ActionResult;
import com.study.spring.state.model.ActionCondition;
import java.util.Map;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public interface ActionConditionExecutor {


    ActionResult executeAction(ActionCondition actionCondition, Map<String, Object> input);

}
