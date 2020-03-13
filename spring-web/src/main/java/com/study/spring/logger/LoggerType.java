package com.study.spring.logger;

/**
 * Created on 2020-03-13
 *
 * @author liuzhaoyuan
 */
public enum LoggerType {


    DAO("dao 层日志"),


    SERVICE("服务接口层日志"),


    SAL("外部调用日志");


    public String desc;


    LoggerType(String desc) {
        this.desc = desc;
    }
}
