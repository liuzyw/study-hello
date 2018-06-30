package com.study.spring.beans;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created on 2018-06-27
 *
 * @author liuzhaoyuan
 */
public class Dog {

    /**
     * @Value 可以写基本数值
     * <p>
     * 可以写 #{20-18}
     * <p>
     * 可以写 ${} 从配置文件
     */
    @Value("${dog.name}")
    private String name;

    @Value("my app")
    public String app;

    @Value("#{20 - 12}")
    public Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
            "name='" + name + '\'' +
            ", app='" + app + '\'' +
            ", age=" + age +
            '}';
    }
}
