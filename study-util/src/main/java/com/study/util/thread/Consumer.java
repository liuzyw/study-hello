package com.study.util.thread;


/**
 * 队列任务
 *
 * @param <T>
 */
public interface Consumer<T> {

    /**
     * 具体的执行方法
     *
     * @return
     *
     * @throws Exception
     */
    T consume() throws Exception;

    /**
     * 回调方法
     *
     * @throws Exception
     */
    void fallback() throws Exception;
}
