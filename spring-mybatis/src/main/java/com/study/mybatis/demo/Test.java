package com.study.mybatis.demo;

import com.study.mybatis.entity.Sex;

/**
 * Created by liuzhaoyuan on 2017/7/23.
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(Sex.getSexByName("男"));
        System.out.println(Sex.getSexByCode(2));
    }


}
