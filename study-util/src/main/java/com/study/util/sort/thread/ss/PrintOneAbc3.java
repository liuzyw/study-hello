package com.study.util.sort.thread.ss;


import com.study.util.sort.thread.TUt;

/**
 * Created on 2020-05-18
 *
 * @author liuzhaoyuan
 */
public class PrintOneAbc3 {

    private static volatile int charSeq = 1;


    public static void main(String[] args) throws Exception {

        PrintOneAbc3 printOneAbc3 = new PrintOneAbc3();

        new Thread(printOneAbc3.new A()).start();
        ;
        new Thread(printOneAbc3.new B()).start();
        ;
        new Thread(printOneAbc3.new C()).start();
        ;

    }


    public class A implements Runnable {

        @Override
        public void run() {


            while (true) {
                TUt.sel(1000);

                while (charSeq != 1) {

                }

                System.out.println("a ");
                charSeq = 2;
            }
        }
    }

    public class B implements Runnable {

        @Override
        public void run() {

            while (true) {
                TUt.sel(1000);

                while (charSeq != 2) {

                }

                System.out.println("b ");
                charSeq = 3;
            }
        }
    }

    public class C implements Runnable {

        @Override
        public void run() {

            while (true) {
                TUt.sel(1000);

                while (charSeq != 3) {

                }
                System.out.println("c ");
                charSeq = 1;
            }
        }
    }


}
