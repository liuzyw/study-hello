package com.study.util.sort.offer;

/**
 * Created on 2018-10-29
 *
 * @author liuzhaoyuan
 */
public class VerifySquenceOfBST {


    public static void main(String[] args) {
        int[] src = new int[]{5, 7, 6, 9, 11, 3, 10, 8};

        System.out.println(isS(src, 0, src.length - 1));
    }


    private static boolean isS(int[] arr, int from, int to) {

        int mid = arr[to];

        int index = from;
        while (arr[index] < mid) {
            index++;
        }
        int t = index;
        while (t < to){
            if (arr[t++] < mid){
                return false;
            }
        }
        index--;

        boolean left = true;
        if (index > from) {
            left = isS(arr, from, index);
        }

        boolean right = true;

        if (index < to - 1) {
            right = isS(arr, index + 1, to - 1);
        }

        return left && right;


    }

}
