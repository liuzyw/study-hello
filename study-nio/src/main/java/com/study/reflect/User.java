package com.study.reflect;

/**
 * Created on 2018-05-28
 *
 * @author liuzhaoyuan
 */
public class User {

    private int id;

    private String name;

    public String address;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            '}';
    }

    public void say() {
        System.out.println("say hello ");
    }

    public int sum(int a, int b) {
        return a + b;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
