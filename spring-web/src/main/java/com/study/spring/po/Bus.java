package com.study.spring.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * Created on 2017-09-24
 *
 * @author liuzhaoyuan
 */

public class Bus {

    private String name;
    private String color;
    // spring ajax 默认返回 long 类似的时间，使用此注解可以自定义时间类型
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Bus() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Bus{" +
            "name='" + name + '\'' +
            ", color='" + color + '\'' +
            ", date=" + date +
            '}';
    }
}
