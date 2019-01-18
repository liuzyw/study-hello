package com.study.spring.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * Created on 2019-01-13
 *
 * @author liuzhaoyuan
 */
@Data
public class Chat implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String id;

    private String friend;

    private String msg;


    @Override
    public String toString() {
        return "Chat{" +
            "id=" + id +
            ", friend=" + friend +
            ", msg=" + msg +
            '}';
    }
}
