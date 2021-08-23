package com.study.service;

import com.study.dao.ConditionCombinationDao;
import com.study.dao.ConditionGroupDao;
import com.study.dao.RuleDao;
import com.study.handler.StateHandlerService;
import com.study.handler.condition.ConditionHandler;
import com.study.model.ConditionCombination;
import com.study.model.ConditionGroup;
import com.study.model.Rule;
import com.study.util.RuleExpressComputeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleDao ruleDao;

    @Autowired
    private ConditionGroupDao conditionGroupDao;

    @Autowired
    private ConditionCombinationDao conditionCombinationDao;

    @Autowired
    private StateHandlerService stateHandlerService;

    @Override
    public boolean executeRule(String ruleId, Map<String, String> params) {

        Rule rule = ruleDao.getRule(ruleId);
        String str = rule.getExpression();
        if (rule == null || StringUtils.isEmpty(rule.getExpression())) {
            System.out.println("规则未配置完成");
            return false;
        }


        List<ConditionGroup> conditionGroups = conditionGroupDao.QueryConditionGroupByRuleId(rule.getRuleId());

        for (ConditionGroup conditionGroup : conditionGroups) {

            List<ConditionCombination> conditionCombinations = conditionCombinationDao.queryConditionCombinationByConditionGroupId(conditionGroup.getConditionGroupId());
            boolean re = false;

            for (ConditionCombination conditionCombination : conditionCombinations) {
                ConditionHandler conditionHandler = stateHandlerService.getConditionHandler(conditionCombination.getConditionHandler());
                re = conditionHandler.executeCondition(conditionCombination, params);
                if (!re) {
                    break;
                }
            }

            str = str.replaceAll(conditionGroup.getConditionGroupId(), String.valueOf(re ? 1 : 0));
        }

        int compute = RuleExpressComputeUtil.compute(str);


        return compute == 1 ? true : false;
    }
}
