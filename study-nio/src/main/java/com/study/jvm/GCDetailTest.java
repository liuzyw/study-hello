package com.study.jvm;

/**
 * Created on 2018-05-13
 *
 * @author liuzhaoyuan
 */
public class GCDetailTest {

    public GCDetailTest() {
        byte[] bytes = new byte[20 * 1024];
    }

    private Object object = new Object();


    public static void main(String[] args) {
        GCDetailTest gcDetailTest1 = new GCDetailTest();
        GCDetailTest gcDetailTest2 = new GCDetailTest();

        gcDetailTest1.object = gcDetailTest2;
        gcDetailTest2.object = gcDetailTest1;

        gcDetailTest1 = null;
        gcDetailTest2 = null;

        System.gc();

    }
}
