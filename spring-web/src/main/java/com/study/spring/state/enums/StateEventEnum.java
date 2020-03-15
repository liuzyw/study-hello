package com.study.spring.state.enums;

/**
 * Created on 2020-03-15
 *
 * @author liuzhaoyuan
 */
public enum StateEventEnum {

    EVT_INIT("EVT_INIT", "初始化"),

    EVT_WAIT("EVT_WAIT", "待使用"),

    EVT_FINISH("EVT_FINISH", "完结"),

    EVT_CLOSE("EVT_CLOSE", "取消"),

    ;

    public String code;
    public String desc;


    StateEventEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
