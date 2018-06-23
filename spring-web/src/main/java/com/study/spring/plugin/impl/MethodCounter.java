package com.study.spring.plugin.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018-05-24
 *
 * @author liuzhaoyuan
 */
public class MethodCounter implements Serializable {

    private static final long serialVersionUID = -1103685206031307821L;

    public Map<String, Integer> map = new HashMap<>();

    private int allCount = 0;

    public void count(Method method) {
        count(method.getName());
    }

    public void count(String name) {
        Integer count = map.get(name);
        if (count == null) {
            map.put(name, 1);
        } else {
            map.put(name, count + 1);
        }
        allCount++;
    }

    public int getMethodCount(String methodName) {
        Integer in = map.get(methodName);
        return in == null ? 0 : in;
    }

    public int getAllCount() {
        return allCount;
    }
}
