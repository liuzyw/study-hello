package com.study.spring.state.action;

import java.util.List;
import java.util.Map;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public interface Action {


    void execute(List<ActionItemDefinition> actionItemDefinitions, Map<String, Object> input, ActionResult actionResult);

}
