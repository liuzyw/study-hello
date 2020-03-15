package com.study.spring.state.enums;

/**
 * Created on 2020-03-15
 *
 * @author liuzhaoyuan
 */
public enum OrderStatusEnum {

    INIT("INIT", "初始化"),

    WAIT("WAIT", "待使用"),

    FINISH("FINISH", "完结"),

    CLOSE("CLOSE", "取消");


    public String code;
    public String desc;


    OrderStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
