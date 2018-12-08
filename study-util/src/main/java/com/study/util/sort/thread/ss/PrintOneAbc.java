package com.study.util.sort.thread.ss;

import com.study.util.sort.thread.TUt;

/**
 * Created on 2018-12-07
 * <p>
 * 两个线程打印  1,a,2,b,3,c ......
 * <p>
 * ，。  共享内存版
 *
 * @author liuzhaoyuan
 */
public class PrintOneAbc {

    private static volatile boolean isNum = true;

    private static int numA = 0;

    private static int numB = 0;

    private static char[] chars = new char[]{'a', 'b', 'c'};


    public static void main(String[] args) {

        PrintOneAbc printOneAbc = new PrintOneAbc();

        new Thread(printOneAbc.new Abc()).start();

        TUt.sel(1000);

        new Thread(printOneAbc.new Num()).start();

    }

    public class Num implements Runnable {

        @Override
        public void run() {
            while (true) {
                while (!isNum) {

                }

                System.out.println(numA++ % 3 + 1);
                TUt.sel(500);
                isNum = false;

            }
        }
    }


    public class Abc implements Runnable {

        @Override
        public void run() {
            while (true) {
                while (isNum) {

                }

                System.out.println(chars[numB++ % 3]);
                TUt.sel(500);
                isNum = true;

            }
        }
    }

}
