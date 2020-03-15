package com.study.spring.state.runtime;

import com.study.spring.state.model.Condition;
import com.study.spring.state.model.Rule;
import java.util.Map;
import org.apache.commons.jexl2.JexlContext;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class ConditionExecutor {


    public static ConditionResult evaluate(Map<String, Object> input, Condition condition) {

        ConditionResult conditionResult = new ConditionResult();

        Object evaluate = condition.getJexlExpression().evaluate(new JexlContext() {
            @Override
            public Object get(String name) {
                try {
                    RuleResult ruleResult = new RuleResult();
                    conditionResult.put(name, ruleResult);
                    ruleResult.setCode(name);

                    Rule rule = condition.getRuleMap().get(name);
                    Object value = input.get(rule.getInput());
                    ruleResult.setInput(value);
                    TypeFormatter typeFormatter = rule.getRuleTypeEnum().typeFormatter;
                    boolean result = rule.getConstraintEnum().compareTo(typeFormatter.format(value), typeFormatter.format(rule.getConstraintValue()));

                    ruleResult.setPasses(result);
                    return result;
                } catch (Exception e) {
                    conditionResult.setExMsg(e.getMessage());
                    e.printStackTrace();
                    throw e;
                }
            }

            @Override
            public void set(String s, Object o) {

            }

            @Override
            public boolean has(String name) {
                return condition.containRule(name);
            }
        });

        boolean match = false;
        if (evaluate instanceof Boolean) {
            match = (Boolean) evaluate;
        }

        conditionResult.setMatched(match);

        return conditionResult;

    }

}
