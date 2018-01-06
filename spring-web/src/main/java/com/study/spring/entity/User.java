package com.study.spring.entity;

import java.io.Serializable;

/**
 * Created by liuzhaoyuan on 2017/7/22.
 */
public class User implements Serializable {

    private static final Long serialVersionUID = 1L;


    private Integer id;
    private String name;
    private String pass;
    private Integer age;
    private String address;
    private Sex sex;

    public User() {
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", pass='" + pass + '\'' +
            ", age=" + age +
            ", address='" + address + '\'' +
            ", sex=" + sex +
            '}';
    }
}
