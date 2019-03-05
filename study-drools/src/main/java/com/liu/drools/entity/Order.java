package com.liu.drools.entity;

import java.util.Date;
import lombok.Data;

/**
 * Created on 2019-03-05
 *
 * @author liuzhaoyuan
 */
@Data
public class Order {

    private static final Long serialVersionUID = 1L;

    private int orderId;

    private Date date;

    private int status;

}
