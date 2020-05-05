package com.study.util.sort.offer;

import java.util.Arrays;

/**
 * Created on 2020-05-04
 * <p>
 * <p>
 * 扑克牌顺序
 *
 * @author liuzhaoyuan
 */
public class IsContinuous {

    private static final Long serialVersionUID = 1L;

    public static void main(String[] args) throws Exception {
        int[] arr = new int[]{0, 1, 3, 4, 5};

        System.out.println(isOrder(arr));

        arr = new int[]{0, 0, 1, 4, 5, 6};

        System.out.println(isOrder(arr));

    }


    private static boolean isOrder(int[] arr) {

        Arrays.sort(arr);

        int countZone = 0;

        int pre = -1;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == 0) {
                countZone++;
                continue;
            } else {

                if (pre == -1) {
                    pre = arr[i];
                } else {

                    int cha = arr[i] - pre;

                    if (cha == 0) {
                        return false;
                    }

                    if (cha == 1) {
                        pre = arr[i];

                        continue;
                    }

                    if ((cha - 1) > countZone) {
                        return false;
                    }

                    countZone = cha - 1 - countZone;

                    pre = arr[i];

                }

            }


        }

        return true;
    }


}
