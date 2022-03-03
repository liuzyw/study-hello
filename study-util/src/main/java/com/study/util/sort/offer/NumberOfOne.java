package com.study.util.sort.offer;

/**
 * Created on 2018-10-27
 *
 * @author liuzhaoyuan
 */
public class NumberOfOne {

    public static void main(String[] args) {

        System.out.println(count2(9));
        System.out.println(count2(20));
        System.out.println(count2(2));


    }

    private static int count(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }

        int flag = 1;
        int result = 0;

        int i = 0;
        while (i++ < 32) {
            if ((flag & num) > 0) {
                result++;
            }
            flag = flag << 1;
        }

        return result;
    }

    private static int count2(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }

        int result = 0;
// 从最右边的1开始，每一次操作都使n的最右的一个1变成了0
        int i = 0;
        while (num != 0) {
            num = num & (num - 1);
            result++;
        }

        return result;
    }

}
