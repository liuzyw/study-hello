package com.study.shiro.factory;

import java.util.LinkedHashMap;

/**
 * Created on 2018-01-11
 *
 * @author liuzhaoyuan
 */
public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        // 以下是添加过滤
//        map.put("/logout","logout");

        return map;
    }

}
