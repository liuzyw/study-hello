package com.study.util.sort;

import java.util.Arrays;

/**
 * Created on 2018-10-27
 *
 * @author liuzhaoyuan
 */
public class LD {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Integer[] array = RandomUtils.generateIntegerArray(100, 40);
//            System.out.println(Arrays.toString(array));

            SortUtils.mergeSort(array, 0, array.length-1);
//            print(array);

            System.out.println(RandomUtils.isSort(array, true));

        }

        System.out.println("--------------->");
        System.out.println("--------------->");

        for (int i = 0; i < 10; i++) {
            Integer[] array = RandomUtils.generateIntegerArray(100, 40);

//            Integer[] array = new Integer[]{73, 78, 69, 96, 28, 71, 27, 13, 88, 76};

//            System.out.println(Arrays.toString(array));
            SortUtils.mergeSort(array, 10, 30);
//            print(array);
//            System.out.println(Arrays.toString(array));

            System.out.println(RandomUtils.isSort(array, true, 10, 30));

        }
    }

    public static void print(Integer[] arr) {
        System.out.println(Arrays.toString(arr));
        System.out.println("----------------------- >> ");
    }


}
