package com.study.util.sort.offer;

/**
 * Created on 2020-05-03
 *
 * @author liuzhaoyuan
 */
public class FindGreatestSumOfSubArray {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) throws Exception {

        int[] arr = new int[]{1, -2, 3, 10, -4, 7, 2, -5};

        System.out.println(getMaxSum(arr));

    }


    private static int getMaxSum(int[] arr) {
        int sum = 0;
        int max = sum;

        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];

            if (sum > max) {
                max = sum;
            }

            if (sum < 0) {
                sum = 0;
            }


        }

        return max;


    }


}
