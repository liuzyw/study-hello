package com.study.mybatis.entity;

/**
 * Created by liuzhaoyuan on 2017/7/23.
 */
public enum Sex {

    FEMALE(0, "女"), MALE(1, "男");

    private int code;
    private String name;

    private Sex(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static Sex getSexByCode(int code) {
        if (code == 1) {
            return Sex.MALE;
        } else if (code == 2) {
            return Sex.FEMALE;
        } else {
            return null;
        }
    }

    public static Sex getSexByName(String name) {
        for (Sex sex : Sex.values()) {
            if (sex.getName().equals(name)) {
                return sex;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Sex{" +
            "code=" + code +
            ", name='" + name + '\'' +
            '}';
    }
}
