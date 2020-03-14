package com.study.spring.state.runtime;

import com.study.spring.base.ToString;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class RuleResult extends ToString {


    private String code;

    private boolean passes;

    private Object input;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isPasses() {
        return passes;
    }

    public void setPasses(boolean passes) {
        this.passes = passes;
    }

    public Object getInput() {
        return input;
    }

    public void setInput(Object input) {
        this.input = input;
    }
}
