package com.study.util.sort.offer;

import java.util.Arrays;

/**
 * Created on 2018-10-30
 *
 * <p>全排列</>
 *
 * @author liuzhaoyuan
 */
public class Permutation {


    public static void main(String[] args) {

        char[] chars = new char[]{'a', 'b', 'c', 'd'};

//        print(chars, new char[3], 0);

        /**
         * 求子集
         */
        for (int i = 0; i <= chars.length; i++) {
            print3(chars, 0, new char[i], 0);

        }


    }

    /**
     * 全排列
     *
     * @param src
     * @param arr
     * @param len
     */
    private static void print(char[] src, char[] arr, int len) {

        if (len == src.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = 0; i < src.length; i++) {
            arr[len] = src[i];
            print(src, arr, len + 1);
        }

    }


    private static void print3(char[] src, int begin, char[] pr, int len) {

        if (len == pr.length) {
            System.out.println(Arrays.toString(pr));
            return;
        }

        for (int i = begin; i < src.length; i++) {
            pr[len] = src[i];

            print3(src, i + 1, pr, len + 1);
        }
    }


}
