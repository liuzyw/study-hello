package com.study.data.table.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * Created on 2018-05-11
 *
 * @author liuzhaoyuan
 */
@Data
public class Receipt implements Serializable {

    private static final long serialVersionUID = -661460848386846947L;

    private Long id;

    private Integer userId;

    private Long receiptId;

    private String coupon;
}
