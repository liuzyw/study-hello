package com.study.dao;

import com.study.model.ConditionCombination;

import java.util.List;

public interface ConditionCombinationService {

    List<ConditionCombination> queryConditionCombinationByRuleId(String ruleId);
}
