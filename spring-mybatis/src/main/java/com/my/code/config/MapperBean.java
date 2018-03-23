package com.my.code.config;

import java.util.List;
import lombok.Data;

@Data
public class MapperBean {

    private String interfaceName; //接口名
    private List<Function> list; //接口下所有方法
    //省略 get  set方法...
}