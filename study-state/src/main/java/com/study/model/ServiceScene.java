package com.study.model;

import com.study.base.ToString;

public class ServiceScene extends ToString {

    private Long id;

    private String bizScene;

    private String serviceId;

    private String ruleId;

    private Integer version;

    private String status;

    private String stateConfig;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStateConfig() {
        return stateConfig;
    }

    public void setStateConfig(String stateConfig) {
        this.stateConfig = stateConfig;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBizScene() {
        return bizScene;
    }

    public void setBizScene(String bizScene) {
        this.bizScene = bizScene;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
