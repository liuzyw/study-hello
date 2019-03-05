package com.liu.drools.entity;

import lombok.Data;

/**
 * Created on 2019-03-05
 *
 * @author liuzhaoyuan
 */
@Data
public class Item {

    private static final Long serialVersionUID = 1L;

    private int itemId;

    private int quantity;

    private int productId;


}
