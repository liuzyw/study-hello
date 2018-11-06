package com.study.util.sort.offer;


/**
 * Created on 2018-11-03
 *
 * <p>求N！ 末位有多少个 0
 * </>
 *
 * @author liuzhaoyuan
 */
public class NZone {

    public static void main(String[] args) {

        System.out.println(zone(5));

    }

    private static int zone(int N) {
        int re = 0;

        while (N != 0) {
            re += N / 5;
            N = N / 5;
        }

        return re;
    }

    /**
     * N! 的阶乘， 二进制中 1 的个数
     *
     * @param N
     *
     * @return
     */
    private static int lowstOne(int N) {

        if (N == 0) {
            return 0;
        }

        int re = 0;
        while (N != 0) {
            N = N >> 1;
            re += N;
        }
        return re;
    }

}
