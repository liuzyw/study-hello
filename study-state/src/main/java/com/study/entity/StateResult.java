package com.study.entity;

import com.study.base.ToString;

public class StateResult extends ToString {

    private boolean result;


    public StateResult() {
    }

    public StateResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
