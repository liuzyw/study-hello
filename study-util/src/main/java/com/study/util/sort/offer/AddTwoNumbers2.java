package com.study.util.sort.offer;

/**
 * Created on 2020-05-04
 *
 * @author liuzhaoyuan
 */
public class AddTwoNumbers2 {

    private static final Long serialVersionUID = 1L;

    public static void main(String[] args) throws Exception {

        System.out.println(add(5, 17));

        System.out.println(add(15, 15));
        System.out.println(add(0, 3));
        System.out.println(add(2, 222));


    }


    private static int add(int n, int m) {

        while (m != 0) {

            int c = n ^ m;

            int b = (n & m) << 1;

            n = c;
            m = b;

        }

        return n;

    }

}
