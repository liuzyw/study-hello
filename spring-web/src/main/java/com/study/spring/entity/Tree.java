package com.study.spring.entity;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * Created on 2018-03-11
 *
 * @author liuzhaoyuan
 */
@Data
public class Tree implements Serializable {

    private static final long serialVersionUID = 1231232131L;

    private String name;
    private Integer age;

    private List<String> lefts;


}
