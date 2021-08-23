package com.study.dao.impl;

import com.study.dao.ConditionCombinationDao;
import com.study.model.ConditionCombination;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConditionCombinationDaoImpl implements ConditionCombinationDao {

    @Override
    public List<ConditionCombination> queryConditionCombinationByConditionGroupId(String conditionGroupId) {
        return null;
    }
}
