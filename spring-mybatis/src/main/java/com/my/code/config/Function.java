package com.my.code.config;

import lombok.Data;

@Data
public class Function {

    private String sqltype;
    private String funcName;
    private String sql;
    private Object resultType;
    private String parameterType;
    //省略 get set方法
}