package com.study.util.sort.base;

import com.study.util.sort.SortUtils;

/**
 * Created on 2018-10-23
 *
 * @author liuzhaoyuan
 */
public class FindDemo {


    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 7, 8, 22, 26, 30, 37, 38, 40, 51, 52, 55, 57, 69, 80, 81, 85, 93, 94};

        int re = binaryFind(arr, 0, arr.length, 1);

        System.out.println(re);

        re = binaryFind(arr, 4, arr.length, 51);

        System.out.println(re);

        re = binaryFind(arr, 0, arr.length, 2);

        System.out.println(re);

        re = binaryFind(arr, 5, arr.length, 39);

        System.out.println(re);

        re = binaryFind(arr, 0, arr.length, -1);

        System.out.println(re);

        re = binaryFind(arr, 0, arr.length, 0);

        System.out.println(re);

        re = binaryFind(arr, 0, arr.length, 100);

        System.out.println(re);

        re = binaryFind(arr, 6, arr.length, 94);

        System.out.println(re);

        re = binaryFind(arr, 0, arr.length, 93);

        System.out.println(re);

    }


    public static int binaryFind(Integer[] arr, int from, int to, int num) {

        return SortUtils.binaryFind(arr, from, to, num);

//        if (to == from && to == 0){
//            return -1;
//        }
//
//        if (to == from){
//            return -from;
//        }
//
//
//        int mid = (from + to) / 2;
//        if (arr[mid] == num) {
//            return mid;
//        } else if (arr[mid] > num) {
//            return binaryFind(arr, from, mid, num);
//        } else {
//            return binaryFind(arr, mid + 1, to, num);
//        }
    }

}
