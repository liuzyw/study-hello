package com.study.mybatis.entity;

import java.io.Serializable;

/**
 * Created by liuzhaoyuan on 2017/7/23.
 */
public class UserSimple implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String name;
    private Integer age;

    public UserSimple() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserSimple{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
