package com.study.model;

import com.study.base.ToString;

public class BizScene extends ToString {

    private Long Id;

    private String BizScene;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getBizScene() {
        return BizScene;
    }

    public void setBizScene(String bizScene) {
        BizScene = bizScene;
    }
}
