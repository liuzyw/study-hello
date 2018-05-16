package com.study.hystrix.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by liuzhaoyuan on 2017/7/23.
 */
public class Fruit implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private BigDecimal price;
    private Double weight;

    public Fruit() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Fruit{" +
            "name='" + name + '\'' +
            ", price=" + price +
            ", weight=" + weight +
            '}';
    }
}
