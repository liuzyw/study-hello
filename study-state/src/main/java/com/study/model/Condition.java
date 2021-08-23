package com.study.model;

import com.study.base.ToString;

public class Condition extends ToString {


    private Long Id;

    private String ConditionId;

    private String ConditionName;

    private String ConditionHandler;

    private String ConditionExpress;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getConditionId() {
        return ConditionId;
    }

    public void setConditionId(String conditionId) {
        ConditionId = conditionId;
    }

    public String getConditionName() {
        return ConditionName;
    }

    public void setConditionName(String conditionName) {
        ConditionName = conditionName;
    }

    public String getConditionHandler() {
        return ConditionHandler;
    }

    public void setConditionHandler(String conditionHandler) {
        ConditionHandler = conditionHandler;
    }

    public String getConditionExpress() {
        return ConditionExpress;
    }

    public void setConditionExpress(String conditionExpress) {
        ConditionExpress = conditionExpress;
    }
}
