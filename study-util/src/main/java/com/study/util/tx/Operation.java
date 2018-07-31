package com.study.util.tx;

/**
 * Created on 2018-07-31
 *
 * @author liuzhaoyuan
 */
public interface Operation<T> {

    T execute();

    boolean rollback();

    boolean isContinue();

}
