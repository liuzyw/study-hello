package com.study.model;

import com.study.base.ToString;

import java.util.List;

public class StateNode extends ToString implements Comparable<StateNode> {

    private String stateName;

    private List<String> childs;
    private String ruleId;
    private List<String> events;
    private Integer order;


    public StateNode() {
    }

    public StateNode(String stateName, List<String> childs, String ruleId, List<String> events, Integer order) {
        this.stateName = stateName;
        this.childs = childs;
        this.ruleId = ruleId;
        this.events = events;
        this.order = order;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public List<String> getChilds() {
        return childs;
    }

    public void setChilds(List<String> childs) {
        this.childs = childs;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public int compareTo(StateNode o) {
        return this.order.intValue() - o.order.intValue();
    }
}
