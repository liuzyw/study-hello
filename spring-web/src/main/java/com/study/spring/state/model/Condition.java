package com.study.spring.state.model;

import com.study.spring.base.ToString;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.jexl2.Expression;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class Condition extends ToString {


    private Expression jexlExpression;


    private List<Rule> rules;

    private Map<String, Rule> ruleMap;


    public Expression getJexlExpression() {
        return jexlExpression;
    }

    public void setJexlExpression(Expression jexlExpression) {
        this.jexlExpression = jexlExpression;
    }


    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
        generateRuleMap(rules);
    }

    private void generateRuleMap(List<Rule> rules) {
        ruleMap = new HashMap<>();
        for (Rule rule : rules) {
            ruleMap.put(rule.getId(), rule);
        }
    }

    public Map<String, Rule> getRuleMap() {
        return ruleMap;
    }

    public void setRuleMap(Map<String, Rule> ruleMap) {
        this.ruleMap = ruleMap;
    }


    public boolean containRule(String ruleId) {
        return ruleMap.containsKey(ruleId);
    }
}
