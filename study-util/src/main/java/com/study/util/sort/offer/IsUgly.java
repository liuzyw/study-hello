package com.study.util.sort.offer;

import java.util.Arrays;

/**
 * Created on 2018-10-31
 *
 * @author liuzhaoyuan
 */
public class IsUgly {

    public static void main(String[] args) {
        System.out.println(getUglyNumber2(10));

        System.out.println(aaa(10));
    }

    public static int getUglyNumber2(int index) {
        if (index <= 0) {
            return 0;
        }

        int[] pUglyNumbers = new int[index];
        pUglyNumbers[0] = 1;
        int nextUglyIndex = 1;

        int p2 = 0;
        int p3 = 0;
        int p5 = 0;

        while (nextUglyIndex < index) {
            int min = min(pUglyNumbers[p2] * 2, pUglyNumbers[p3] * 3, pUglyNumbers[p5] * 5);
            pUglyNumbers[nextUglyIndex] = min;

            while (pUglyNumbers[p2] * 2 <= pUglyNumbers[nextUglyIndex]) {
                p2++;
            }

            while (pUglyNumbers[p3] * 3 <= pUglyNumbers[nextUglyIndex]) {
                p3++;
            }

            while (pUglyNumbers[p5] * 5 <= pUglyNumbers[nextUglyIndex]) {
                p5++;
            }

            nextUglyIndex++;
        }
        System.out.println(Arrays.toString(pUglyNumbers));

        return pUglyNumbers[nextUglyIndex - 1];
    }

    private static int min(int n1, int n2, int n3) {
        int min = n1 < n2 ? n1 : n2;
        return min < n3 ? min : n3;
    }

    private static int aaa(int n) {

        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = 5;
        arr[5] = 6;

        int index2 = 0;
        int index3 = 0;
        int index5 = 0;

        for (int i = 6; i <= n; i++) {

            int cur = arr[i - 1];

            while (arr[index2] * 2 <= cur) {
                index2++;
            }
            while (arr[index3] * 3 <= cur) {
                index3++;
            }
            while (arr[index5] * 5 <= cur) {
                index5++;
            }
            int next = min(arr[index2] * 2, arr[index3] * 3, arr[index5] * 5);
            arr[i] = next;

        }

        System.out.println(Arrays.toString(arr));

        return arr[n];

    }

}
