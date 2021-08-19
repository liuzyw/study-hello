package com.study.service;

import com.study.dao.ConditionGroupDao;
import com.study.dao.RuleDao;
import com.study.model.ConditionGroup;
import com.study.model.Rule;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleDao ruleDao;

    @Autowired
    private ConditionGroupDao conditionGroupDao;

    @Override
    public boolean executeRule(String ruleId, Map<String, String> params) {

        Rule rule = ruleDao.getRule(ruleId);

        if (rule == null || StringUtils.isEmpty(rule.getExpression())) {
            System.out.println("规则未配置完成");
            return false;
        }

        List<ConditionGroup> conditionGroups = conditionGroupDao.QueryConditionGroupByRuleId(rule.getRuleId());


        conditionGroups.forEach(new Consumer<ConditionGroup>() {
            @Override
            public void accept(ConditionGroup conditionGroup) {

            }
        });


        return false;
    }
}
