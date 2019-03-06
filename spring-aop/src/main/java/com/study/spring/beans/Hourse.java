package com.study.spring.beans;

import lombok.Data;
import lombok.ToString;

/**
 * Created on 2018-06-14
 *
 * @author liuzhaoyuan
 */
@Data
@ToString
public class Hourse {

    private Integer id;

    private String name;

    public Hourse() {
    }

    public Hourse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void say() {
        System.out.println(name + " hourse say ...");
    }

    public void init() {
        System.out.println(name + " Hourse init...");
    }

    public void destory() {
        System.out.println("Hourse destory..");
    }
}
