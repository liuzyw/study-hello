package com.study.dao.impl;

import com.study.dao.ConditionGroupDao;
import com.study.model.ConditionGroup;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConditionGroupDaoImpl implements ConditionGroupDao {

    @Override
    public List<ConditionGroup> QueryConditionGroupByRuleId(String ruleId) {
        return null;
    }
}
