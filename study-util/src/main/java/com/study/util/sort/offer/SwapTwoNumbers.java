package com.study.util.sort.offer;

/**
 * Created on 2020-05-04
 *
 * @author liuzhaoyuan
 */
public class SwapTwoNumbers {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) throws Exception {

        swap1(2, 3);
        swap1(0, 3);
        swap1(2, 0);

        swap2(2, 3);
        swap2(0, 3);
        swap2(2, 0);
    }


    private static void swap1(int n, int m) {

        System.out.println("n:" + n + " ,m:" + m);

        n = n ^ m;
        m = n ^ m;
        n = n ^ m;

        System.out.println("n:" + n + " ,m:" + m);

        System.out.println("==============");
    }

    private static void swap2(int n, int m) {

        System.out.println("n:" + n + " ,m:" + m);

        n = n + m;
        m = n - m;
        n = n - m;

        System.out.println("n:" + n + " ,m:" + m);

        System.out.println("==============");
    }


}
