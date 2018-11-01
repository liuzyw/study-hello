package com.study.util.sort.offer;

/**
 * Created on 2018-10-27
 *
 * @author liuzhaoyuan
 */
public class PrintOndTONity {

    public static void main(String[] args) {
        printOneToNthDigits(2);
    }


    public static void printOneToNthDigits(int n) {
        // 创建一个数组用于打存放值
        int[] arr = new int[n];
        printOneToNthDigits(0, arr);
    }

    public static void printOneToNthDigits(int n, int[] arr) {

        if (n == arr.length) {
            print(arr);
            return;
        }

        for (int i = 0; i < 10; i++) {
            arr[n] = i;
            printOneToNthDigits(n + 1, arr);
        }

    }


    public static void print(int[] arr) {
        int index = 0;
        while (index < arr.length && arr[index] == 0) {
            index++;
        }

        for (int j = index; j < arr.length; j++) {
            System.out.print(arr[j]);
        }

        System.out.println();
    }
}
