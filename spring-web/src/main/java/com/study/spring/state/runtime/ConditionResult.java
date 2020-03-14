package com.study.spring.state.runtime;

import com.study.spring.base.ToString;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class ConditionResult extends ToString {


    private boolean matched;

    // 异常原因
    private String exMsg;


    private Map<String, RuleResult> ruleResultMap = new HashMap<>();


    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public String getExMsg() {
        return exMsg;
    }

    public void setExMsg(String exMsg) {
        this.exMsg = exMsg;
    }

    public Map<String, RuleResult> getRuleResultMap() {
        return ruleResultMap;
    }

    public void setRuleResultMap(Map<String, RuleResult> ruleResultMap) {
        this.ruleResultMap = ruleResultMap;
    }

    public void put(String key, RuleResult value) {
        ruleResultMap.put(key, value);
    }
}
