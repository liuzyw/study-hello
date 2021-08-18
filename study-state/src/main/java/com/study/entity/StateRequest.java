package com.study.entity;

import com.study.base.ToString;

import java.util.Map;

public class StateRequest extends ToString {

    private String outOrderId;

    private String bizScene;

    private String toState;

    private String serviceId;

    private Map<String, String> params;


    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public String getBizScene() {
        return bizScene;
    }

    public void setBizScene(String bizScene) {
        this.bizScene = bizScene;
    }

    public String getToState() {
        return toState;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setToState(String toState) {
        this.toState = toState;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
