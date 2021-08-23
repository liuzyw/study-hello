package com.study.handler.condition;

import com.study.model.ConditionCombination;

import java.util.Map;

public interface ConditionHandler {

    String getConditionName();

    boolean executeCondition(ConditionCombination condition, Map<String, String> params);

}
