package com.study.util.sort.offer;

import java.util.Arrays;

/**
 * Created on 2020-05-01
 *
 * @author liuzhaoyuan
 */
public class AddTwoNumbers {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) throws Exception {

        int[] num1 = new int[]{9, 4, 7};
        int[] num2 = new int[]{9, 4, 5, 6};



        System.out.println(Arrays.toString(addTowNum(num1, num2)));

    }


    public static int[] addTowNum(int[] num1, int[] num2) {

        int pos = 0;

        int[] result = new int[10];
        int maxLen = result.length - 1;

        int len = num1.length < num2.length ? num1.length : num2.length;

        int j = num2.length - 1;

        for (int k = len - 1; k >= 0; k--) {
            int o = num1[k] + num2[j--] + pos;
            if (o >= 10) {
                pos = 1;
                result[maxLen--] = o - 10;
            } else {
                pos = 0;
                result[maxLen--] = o;
            }
        }

        while (j >= 0) {
            int o = num2[j--] + pos;
            if (o >= 10) {
                pos = 1;
                result[maxLen--] = o - 10;
            } else {
                pos = 0;
                result[maxLen--] = o;
            }
        }

        if (pos == 1) {
            result[maxLen--] = 1;

        }

        return result;


    }


}
