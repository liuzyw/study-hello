package com.study.util.sort.offer;

import com.study.util.sort.SortUtils;

/**
 * Created on 2018-10-30
 *
 * @author liuzhaoyuan
 */
public class MoreThanHalfNum {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};

        System.out.println(find(arr, 0, arr.length));

    }

    private static int find(int[] arr, int from, int to) {
        if (to > from) {

            int mid = findMore(arr, from, to);

            if (mid == 4) {
                return arr[mid];
            } else if (mid < 4) {
                return find(arr, mid + 1, to);
            } else {
                return find(arr, from, mid);
            }

        }
        return -1;
    }

    private static int findMore(int[] arr, int from, int to) {
        int mid = (from + to) / 2;

        int low = from;

        int high = to - 1;

        while (low < high) {
            while (low < mid && arr[low] <= arr[mid]) {
                low++;
            }

            SortUtils.swap(arr, low, mid);
            mid = low;

            while (mid < high && arr[high] >= arr[mid]) {
                high--;
            }

            SortUtils.swap(arr, high, mid);
            mid = high;
        }
        return mid;
    }


    private static int find(int[] arr) {

        int times = 1;
        int re = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (times == 0) {
                re = arr[i];
                times = 1;
            } else if (arr[i] == re) {
                times++;
            } else {
                times--;
            }
        }

        return re;

    }

}
