package com.study.util.sort.offer;

/**
 * Created on 2020-05-03
 * <p>
 * 统计一个数在排序数组中出现的次数
 *
 * @author liuzhaoyuan
 */
public class GetFirstKNum {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) throws Exception {

        int[] arr = new int[]{1, 2, 3, 3, 3, 3, 4, 5};


        System.out.println(front(arr, 0, arr.length, 3));
        System.out.println(last(arr, 0, arr.length, 3));
    }



    private static int front(int[] arr, int begin, int end, int key) {

        int mid = begin + (end - begin) / 2;

        if (key == arr[mid]) {

            if (mid == 0) {
                return mid;
            } else {
                if (arr[mid - 1] < key) {
                    return mid;
                } else {
                    return front(arr, begin, mid, key);
                }
            }
        } else if (key > arr[mid]) {
            return front(arr, mid + 1, end, key);
        } else {
            return front(arr, begin, mid, key);
        }
    }

    private static int last(int[] arr, int begin, int end, int key) {

        int mid = begin + (end - begin - 1) / 2;

        if (key == arr[mid]) {

            if (mid == arr.length - 1) {
                return mid;
            } else {
                if (arr[mid + 1] > key) {
                    return mid;

                } else {
                    return last(arr, mid + 1, end, key);

                }
            }


        } else if (key > arr[mid]) {

            return last(arr, mid + 1, end, key);


        } else {
            return last(arr, mid + 1, end, key);
        }

    }

}
