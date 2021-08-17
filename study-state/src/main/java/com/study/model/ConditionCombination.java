package com.study.model;

import com.study.base.ToString;

public class ConditionCombination extends ToString {
    private Long Id;

    private String ConditionGroupId;

    private String ConditionId;


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
}
