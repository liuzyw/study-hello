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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
