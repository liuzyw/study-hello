package com.study.web.server.servlet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 2018-11-10
 *
 * @author liuzhaoyuan
 */
public class Mapping {

    private String name;

    private Set<String> patterns = new HashSet<>();

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Mapping{" +
            "name='" + name + '\'' +
            ", patterns=" + patterns +
            '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPatterns() {
        return patterns;
    }


    public void add(String pattern) {
        patterns.add(pattern);
    }
}
