package com.study.spring.state.model;

import com.study.spring.base.ToString;
import com.study.spring.state.enums.ConstraintEnum;
import com.study.spring.state.enums.RuleTypeEnum;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class Rule extends ToString {


    private String id;


    private RuleTypeEnum ruleTypeEnum;

    private ConstraintEnum constraintEnum;

    private String input;

    private String constraintValue;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getConstraintValue() {
        return constraintValue;
    }

    public void setConstraintValue(String constraintValue) {
        this.constraintValue = constraintValue;
    }

    public RuleTypeEnum getRuleTypeEnum() {
        return ruleTypeEnum;
    }

    public void setRuleTypeEnum(RuleTypeEnum ruleTypeEnum) {
        this.ruleTypeEnum = ruleTypeEnum;
    }

    public ConstraintEnum getConstraintEnum() {
        return constraintEnum;
    }

    public void setConstraintEnum(ConstraintEnum constraintEnum) {
        this.constraintEnum = constraintEnum;
    }
}
