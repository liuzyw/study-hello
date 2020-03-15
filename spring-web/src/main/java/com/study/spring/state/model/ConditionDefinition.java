package com.study.spring.state.model;

import com.study.spring.base.ToString;
import java.util.List;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class ConditionDefinition extends ToString {


    private String jexlExpression;

    private List<RuleDefinition> ruleDefinitions;

    public ConditionDefinition() {
    }

    public ConditionDefinition(String jexlExpression, List<RuleDefinition> ruleDefinitions) {
        this.jexlExpression = jexlExpression;
        this.ruleDefinitions = ruleDefinitions;
    }

    public String getJexlExpression() {
        return jexlExpression;
    }

    public void setJexlExpression(String jexlExpression) {
        this.jexlExpression = jexlExpression;
    }

    public List<RuleDefinition> getRuleDefinitions() {
        return ruleDefinitions;
    }

    public void setRuleDefinitions(List<RuleDefinition> ruleDefinitions) {
        this.ruleDefinitions = ruleDefinitions;
    }
}
