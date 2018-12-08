package com.study.util.sort.thread;

/**
 * Created on 2018-12-07
 *
 * @author liuzhaoyuan
 */
public class TUt {


    public static void sel(long mm) {
        try {
            Thread.sleep(mm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
