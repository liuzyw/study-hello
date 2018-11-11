package com.study.web.server.servlet;

/**
 * Created on 2018-11-10
 *
 * @author liuzhaoyuan
 */
public class Entity {

    private String name;

    private String clazz;

    @Override
    public String toString() {
        return "Entity{" +
            "name='" + name + '\'' +
            ", clazz='" + clazz + '\'' +
            '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
