package com.study.spring.interceptor;

/**
 * Created on 2018-11-07
 *
 * @author liuzhaoyuan
 */
public class ValueThreadLocal {

    private ValueThreadLocal() {
    }


    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();


    public static void set(String value) {
        threadLocal.set(value);
    }

    public static String get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
