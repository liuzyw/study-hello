package com.study.util.sort.offer;

import java.util.Arrays;
import java.util.Queue;

/**
 * Created on 2018-10-27
 *
 * @author liuzhaoyuan
 */
public class ReOrderAdd {

    public static void main(String[] args) {
//        int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] arr = new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3, 1};

//        order(arr);



        oneTwoThree(arr);

        System.out.println(Arrays.toString(arr));
    }


    public static void order(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            while (arr[left] % 2 != 0 && left <= right) {
                left++;
            }

            while (arr[right] % 2 == 0 && left <= right) {
                right--;
            }

            int t = arr[left];
            arr[left] = arr[right];
            arr[right] = t;

        }
    }


    public static void oneTwoThree(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            while (arr[left] == 1 && left <= right) {
                left++;
            }

            while (arr[right]  == 3 && left <= right) {
                right--;
            }

            int t = arr[left];
            arr[left] = arr[right];
            arr[right] = t;
            left++;
            right--;

        }

    }


}
