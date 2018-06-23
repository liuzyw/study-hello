package com.datasorcespool;

/**
 * Created on 2018-06-23
 * <p>
 * <p>单例模式，用于得到IMyPool实现。</>
 *
 * @author liuzhaoyuan
 */
public class MyPoolFactory {


    private static class MyDataPoolHolder {

        private static final MyPool myDefaultPool = new MyDefaultPool();
    }

    public static MyPool getInstance() {
        return MyDataPoolHolder.myDefaultPool;
    }

}
