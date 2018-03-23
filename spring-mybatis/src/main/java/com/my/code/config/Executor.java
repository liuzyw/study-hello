package com.my.code.config;

public interface Executor {

    <T> T query(String statement, Object parameter);
}