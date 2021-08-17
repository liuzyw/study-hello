package com.study.dao.impl;

import com.study.dao.ConditionCombinationService;
import com.study.model.ConditionCombination;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConditionCombinationServiceImpl implements ConditionCombinationService {

    @Override
    public List<ConditionCombination> queryConditionCombinationByRuleId(String ruleId) {
        return null;
    }
}
