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

        print(chars, new char[3], 0);

    }

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

}
