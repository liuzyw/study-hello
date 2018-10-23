package com.study.util.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created on 2018-10-23
 *
 * @author liuzhaoyuan
 */
public class RandomUtils {

    private RandomUtils() {
    }


    public static Integer[] generateIntegerArray(int maxNum, int size) {

        Integer[] arr = new Integer[size];

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int anInt = random.nextInt(maxNum);
            arr[i] = anInt;
        }

        System.out.println(Arrays.toString(arr));

        return arr;

    }


    public static <T extends Comparable<? super T>> boolean isSort(T[] arr, boolean asc) {
        for (int i = 1; i < arr.length; i++) {
            if (asc && arr[i].compareTo(arr[i - 1]) < 0) {
                return false;
            }
            if (!asc && arr[i].compareTo(arr[i - 1]) >= 0) {
                return false;
            }
        }

        return true;
    }

}
