package com.study.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created on 2018-03-16
 *
 * @author liuzhaoyuan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private String name;

    private Integer age;

}
