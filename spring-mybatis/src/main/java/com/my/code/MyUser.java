package com.my.code;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2018-03-23
 *
 * @author liuzhaoyuan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUser implements Serializable {

    private static final long serialVersionUID = 3897600906339948647L;

    private Integer id;
    private String name;
    private Integer age;

}
