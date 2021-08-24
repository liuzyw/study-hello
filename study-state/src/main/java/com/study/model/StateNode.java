package com.study.model;

import com.study.base.ToString;

import java.util.List;
import java.util.Map;

public class StateNode extends ToString implements Comparable<StateNode> {

    private String stateName;

    private List<String> childs;
    private String ruleId;
    // 分主题
    private Map<String, List<String>> eventMap;
    private Integer order;


    public StateNode() {
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

    public Map<String, List<String>> getEventMap() {
        return eventMap;
    }

    public void setEventMap(Map<String, List<String>> eventMap) {
        this.eventMap = eventMap;
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
