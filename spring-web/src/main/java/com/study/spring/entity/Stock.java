package com.study.spring.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created on 2018-06-23
 *
 * <p>库存表</>
 *
 * @author liuzhaoyuan
 */
public class Stock implements Serializable {

    private static final Long serialVersionUID = 1231231231232L;

    private Long id;

    private Long productId;

    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Stock{" +
            "id=" + id +
            ", productId=" + productId +
            ", count=" + count +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stock)) {
            return false;
        }
        Stock stock = (Stock) o;
        return Objects.equals(getId(), stock.getId()) &&
            Objects.equals(productId, stock.productId) &&
            Objects.equals(getCount(), stock.getCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), productId, getCount());
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
