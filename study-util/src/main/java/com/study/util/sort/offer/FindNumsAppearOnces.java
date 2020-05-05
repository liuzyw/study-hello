package com.study.util.sort.offer;

import java.util.Arrays;

/**
 * Created on 2020-05-03
 *
 * @author liuzhaoyuan
 */
public class FindNumsAppearOnces {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) throws Exception {

        int[] arr = new int[]{2, 2, 4, 4, 6, 14, 10, 10};

        int a = 0;
        for (int i = 0; i < arr.length; i++) {
            a ^= arr[i];
        }
        System.out.println(a);

        char[] chars = Integer.toBinaryString(Integer.valueOf(a)).toCharArray();

        System.out.println(Arrays.toString(chars));

        int j = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '1') {
                j = i;
                break;
            }
        }

        j = chars.length - j;

        System.out.println(j);

        System.out.println(1 << j-1);


    }


}
