package com.study.spring.vo;

import java.io.Serializable;

/**
 * Created on 2018-06-25
 *
 * @author liuzhaoyuan
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -822467247385194L;

    private T data;

    private int code;

    public Result(T data, int code) {
        this.data = data;
        this.code = code;
    }

    public Result() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
