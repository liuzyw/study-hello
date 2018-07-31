package com.study.util.tx;

/**
 * Created on 2018-07-31
 *
 * @author liuzhaoyuan
 */
public abstract class AbstractOperation<T> implements Operation<T> {

    @Override
    public boolean isContinue() {
        return true;
    }
}
