package com.study.util.sort.offer;

/**
 * Created on 2020-05-04
 *
 * @author liuzhaoyuan
 */
public class LastRemaining {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) throws Exception {

    }


    private static int last(int n, int count) {

        if (n < 1 || count < 1) {
            return -1;
        }

        int last = 0;

        for (int i = 2; i <= n; i++) {
            last = (last + count) % i;
        }

        return last;
    }


}
