package com.study.util.thread.day;

/**
 * Created on 2018-12-08
 *
 * @author liuzhaoyuan
 */
public class LockSync {


    private static Object object = new Object();


    public synchronized int add() {
        return 2;
    }


    public static synchronized void say() {

    }

    public void hello() {
        synchronized (object) {
            System.out.println("------>");
        }
    }


    public void other(){
        System.out.println("dsadas");
    }

    public static void main(String[] args) {
        new LockSync().add();
    }

}
