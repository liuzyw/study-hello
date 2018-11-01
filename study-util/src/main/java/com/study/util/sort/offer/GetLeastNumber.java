package com.study.util.sort.offer;

import java.util.Arrays;

/**
 * Created on 2018-10-30
 *
 * @author liuzhaoyuan
 */
public class GetLeastNumber {


    public static void main(String[] args) {
        int[] data = {1, -2, 3, 10, -4, 7, 2, -5};
        int[] data2 = {-2, -8, -1, -5, -9};
        int[] data3 = {2, 8, 1, 5, 9};

        find(data);
        find(data2);
        find(data3);



    }

    private static void find(int[] arr) {
        int[] re = new int[arr.length];

        int i = 0;
        int max = arr[0];
        while ( i < arr.length && arr[i] < 0) {
            if (max < arr[i]) {
                max = arr[i];
            }
            i++;
        }

        if (i == arr.length) {
            System.out.println(max);
            return;
        } else if (i > 0) {
            i--;
        }

        int t = 0;

        int[] so = new int[arr.length];

        re[t++] = arr[i];

        max = arr[i];

        int sum = arr[i];

        for (int j = i + 1; j < arr.length; j++) {
            if (arr[j] >= 0) {
                sum += arr[j];
                re[t++] = arr[j];
                if (max < sum) {
                    max = sum;
                    for (int k = 0; k < t; k++) {
                        so[k] = re[k];
                    }
                }

            } else {
                if (arr[j] + sum < 0) {
                    sum = 0;
                    re = new int[arr.length];
                    t = 0;
                } else {
                    sum += arr[j];
                    re[t++] = arr[j];
                }
            }
        }

        System.out.println(Arrays.toString(so));


    }

}
