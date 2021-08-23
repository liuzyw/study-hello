package com.study.model;

import com.study.base.ToString;

public class ConditionCombination extends ToString {
    private Long Id;

    private String ConditionGroupId;

    private String ConditionId;

    private String ConditionHandler;

    private String ConditionExpress;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getConditionGroupId() {
        return ConditionGroupId;
    }

    public void setConditionGroupId(String conditionGroupId) {
        ConditionGroupId = conditionGroupId;
    }

    public String getConditionId() {
        return ConditionId;
    }

    public void setConditionId(String conditionId) {
        ConditionId = conditionId;
    }

    public String getConditionExpress() {
        return ConditionExpress;
    }

    public void setConditionExpress(String conditionExpress) {
        ConditionExpress = conditionExpress;
    }

    public String getConditionHandler() {
        return ConditionHandler;
    }

    public void setConditionHandler(String conditionHandler) {
        ConditionHandler = conditionHandler;
    }
}
