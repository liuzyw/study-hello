package com.study.util.sort.thread.ss;

import com.study.util.sort.thread.TUt;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2020-05-18
 *
 * @author liuzhaoyuan
 */
public class PrintAbc4 {

    private static int charSeq = 1;

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition numA = lock.newCondition();
    private static Condition numB = lock.newCondition();
    private static Condition numC = lock.newCondition();
    ThreadPoolExecutor executor = new ThreadPoolExecutor(1,2,1000, TimeUnit.SECONDS,new LinkedBlockingDeque<>(10));

    Semaphore semaphore = new Semaphore(1);
    {
//        semaphore.acquire();
//        semaphore.release();;
    }

    public static void main(String[] args) throws Exception {

        PrintAbc4 printAbc4 = new PrintAbc4();
        new Thread(printAbc4.new A()).start();
        new Thread(printAbc4.new B()).start();
        new Thread(printAbc4.new C()).start();
    }


    public class A implements Runnable {
        @Override
        public void run() {
            TUt.sel(2000);
            while (true) {
                TUt.sel(1000);
                lock.lock();
                try {
                    while (charSeq != 1) {
                        numA.wait();
                    }
                    System.out.println("a  ");
                    charSeq = 2;
                    numB.signal();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }

            }

        }
    }

    public class B implements Runnable {

        @Override
        public void run() {
            TUt.sel(2000);

            while (true) {
                TUt.sel(1000);

                lock.lock();
                try {
                    while (charSeq != 2) {
                        numB.wait();

                    }

                    System.out.println("b  ");
                    charSeq = 3;

//            TUt.sel(500);

                    numC.signal();

                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }

            }

        }
    }

    public class C implements Runnable {

        @Override
        public void run() {
            TUt.sel(2000);

            while (true) {
                TUt.sel(1000);

                lock.lock();
                try {
                    while (charSeq != 3) {
                        numC.wait();

                    }

                    System.out.println("c  ");
                    charSeq = 1;

//            TUt.sel(500);

                    numA.signal();

                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }

            }

        }
    }


}
