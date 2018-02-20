package com.study.cloud.feign.entity;

import java.io.Serializable;

/**
 * Created on 2018-02-18
 *
 * @author liuzhaoyuan
 */
public class User implements Serializable {

    private static final Long serialVersionUID = 2132131231L;

    private String name;

    private Integer age;

    private String sex;

    public User() {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", sex='" + sex + '\'' +
            '}';
    }
}
