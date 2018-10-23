package com.study.util.sort.base;

import com.study.util.sort.RandomUtils;
import java.util.Arrays;

/**
 * Created on 2018-10-23
 *
 * @author liuzhaoyuan
 */
public class SortDemo {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Integer[] array = RandomUtils.generateIntegerArray(100, 20);

            shellSort(array, 0, array.length);

            System.out.println(RandomUtils.isSort(array, true));

            print(array);
        }

    }

    public static void print(Integer[] arr) {
        System.out.println(Arrays.toString(arr));
        System.out.println("----------------------- >> ");
    }

    public static void swap(Integer[] arr, int a, int b) {
        Integer temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * pass
     *
     * @param arr
     * @param fromIndex
     * @param toIndex
     */
    public static void insertSort(Integer[] arr, int fromIndex, int toIndex) {

        int len = toIndex - fromIndex;

        for (int i = 0; i < len - 1; i++) {
            int temp = arr[i + 1];
            out:
            for (int j = i + 1; j > 0; j--) {
                if (temp < arr[j - 1]) {
                    arr[j] = arr[j - 1];
                } else {
                    arr[j] = temp;
                    break out;
                }
            }

        }
    }


    /**
     * pass
     *
     * @param arr
     * @param fromIndex
     * @param toIndex
     */
    public static void bubbleSort(Integer[] arr, int fromIndex, int toIndex) {
        int len = toIndex - fromIndex;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }

        }
    }


    /**
     * pass
     *
     * @param arr
     * @param fromIndex
     * @param toIndex
     */
    public static void selectSort(Integer[] arr, int fromIndex, int toIndex) {
        int len = toIndex - fromIndex;

        for (int i = 0; i < len; i++) {
            int maxIndex = 0;
            for (int j = 1; j < len - i; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            swap(arr, len - i - 1, maxIndex);

        }
    }

    /**
     * pass
     *
     * @param arr
     * @param fromIndex
     * @param toIndex
     */
    public static void shellSort(Integer[] arr, int fromIndex, int toIndex) {
        int len = toIndex - fromIndex;

        for (int step = len / 2; step > 0; step /= 2) {

            for (int semg = step; semg < len; semg++) {

                for (int i = semg; i >= step; i -= step) {
                    if (arr[i] < arr[i - step]) {
                        swap(arr, i, i - step);
                    }
                }

            }

        }
    }

    public static void mergeSort(Integer[] arr, int fromIndex, int toIndex) {

    }



}
