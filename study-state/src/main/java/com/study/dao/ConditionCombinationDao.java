package com.study.dao;

import com.study.model.ConditionCombination;

import java.util.List;

public interface ConditionCombinationDao {

    List<ConditionCombination> queryConditionCombinationByConditionGroupId(String conditionGroupId);
}
