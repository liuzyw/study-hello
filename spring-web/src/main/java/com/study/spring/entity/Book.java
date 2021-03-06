package com.study.spring.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * Created on 2017-09-30
 *
 * @author liuzhaoyuan
 */

@Data
public class Book implements Serializable {

    private Integer id;
    private String name;
    private String type;
    private Integer price;

    @Override
    public String toString() {
        return "Book{" +
            "name='" + name + '\'' +
            ", type='" + type + '\'' +
            ", price=" + price +
            '}';
    }
}
