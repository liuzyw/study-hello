package com.study.model;

import com.study.base.ToString;

public class ConditionGroup extends ToString {

    private Long Id;

    private String RuleId;

    private String ConditionGroupId;

    private String ConditionGroupName;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRuleId() {
        return RuleId;
    }

    public void setRuleId(String ruleId) {
        RuleId = ruleId;
    }

    public String getConditionGroupId() {
        return ConditionGroupId;
    }

    public void setConditionGroupId(String conditionGroupId) {
        ConditionGroupId = conditionGroupId;
    }

    public String getConditionGroupName() {
        return ConditionGroupName;
    }

    public void setConditionGroupName(String conditionGroupName) {
        ConditionGroupName = conditionGroupName;
    }
}
