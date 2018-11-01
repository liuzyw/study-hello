package com.study.util.sort.offer;

/**
 * Created on 2018-11-01
 *
 * @author liuzhaoyuan
 */
public class FindSequence {


    public static void main(String[] args) {
        find(15);
    }

    private static void find(int num) {
        if ((num & 1) == 0) {
            return;
        }
        int mid = num >>> 1;

        int low = 0;
        int fast = 0;

        int sum = 0;

        for (int i = 1; i <= mid + 1; i++) {
            fast = i;
            sum += fast;
            while (sum > num) {
                sum -= low;
                low++;
            }

            if (sum == num) {
                System.out.println(low + "-" + fast);
            }
        }


    }

}
