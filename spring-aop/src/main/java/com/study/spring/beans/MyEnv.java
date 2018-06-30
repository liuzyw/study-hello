package com.study.spring.beans;

/**
 * Created on 2018-06-29
 *
 * @author liuzhaoyuan
 */
public class MyEnv {

    public String env;

    public String value;

    public MyEnv() {
    }

    public MyEnv(String env, String value) {
        this.env = env;
        this.value = value;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "MyEnv{" +
            "env='" + env + '\'' +
            ", value='" + value + '\'' +
            '}';
    }
}
