package com.study.dao;

import com.study.model.ConditionGroup;

import java.util.List;

public interface ConditionGroupDao {

    List<ConditionGroup> QueryConditionGroupByRuleId(String ruleId);
}
