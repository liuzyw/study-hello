package com.study.util.thread;

/**
 * Created on 2018-11-25
 *
 * @author liuzhaoyuan
 */
public abstract class AbstractConsumer<T> implements Consumer<T> {


    /**
     * 不需要回调方法的，继承该类
     *
     * @throws Exception
     */
    @Override
    public void fallback() throws Exception {
        // do nothing
    }
}
