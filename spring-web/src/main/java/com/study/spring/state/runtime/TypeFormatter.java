package com.study.spring.state.runtime;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public interface TypeFormatter<T extends Comparable> {


    T format(Object obj);

}
