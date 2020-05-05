package com.study.util.sort.offer;

/**
 * Created on 2020-05-05
 *
 * @author liuzhaoyuan
 */
public class GCB {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) throws Exception {

        System.out.println(gcb2(10, 5));

    }


    private static int gcb(int a, int b) {

        if (a < b) {
            return gcb(b, a);
        }

        if (b == 0) {
            return a;
        }
        return gcb(a - b, b);

    }

    private static int gcb2(int a, int b) {

        int c = a;
        while (b != 0) {
            c = a % b;
            a = b;
            b = c;
        }
        c = a;
        return c;
    }

}
