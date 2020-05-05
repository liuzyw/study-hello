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

        char[] chars = new char[]{'a', 'b', 'c'};

//        print(chars, new char[3], 0);

        print2(chars, new char[2], 0);

    }

    /**
     * 全排列
     *
     * @param arr
     * @param print
     * @param len
     */
    private static void print(char[] arr, char[] print, int len) {

        if (len == arr.length) {
            System.out.println(Arrays.toString(print));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            print[len] = arr[i];
            print(arr, print, len + 1);
        }

    }

    private static void print2(char[] arr, char[] pr, int len) {

        if (len == pr.length) {
            System.out.println(Arrays.toString(pr));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            pr[len] = arr[i];

            char[] arr2 = new char[arr.length - 1 - i];
            int k = 0;
            for (int j = i + 1; j < arr.length; j++) {
                arr2[k++] = arr[j];
            }
            print2(arr2, pr, len + 1);
        }


    }


}
