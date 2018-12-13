package com.study.util.sort.base;

import com.study.util.sort.RandomUtils;
import com.study.util.sort.SortUtils;
import java.util.Arrays;

/**
 * Created on 2018-10-23
 *
 * @author liuzhaoyuan
 */
public class SortDemo {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Integer[] array = RandomUtils.generateIntegerArray(100, 40);

//            Integer[] array = new Integer[]{20, 24, 46, 11, 49, 98, 33, 46, 37, 13, 50, 52, 8, 74, 9, 33, 84, 29, 41, 56};
            SortUtils.fastSort(array, 0, array.length);

            System.out.println(RandomUtils.isSort(array, true));

            print(array);
        }

    }

    public static void print(Integer[] arr) {
        System.out.println(Arrays.toString(arr));
        System.out.println("----------------------- >> ");
    }

    public static void swap(Integer[] arr, int a, int b) {
        Integer temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * pass
     *
     * @param arr
     * @param fromIndex
     * @param toIndex
     */
    public static void insertSort(Integer[] arr, int fromIndex, int toIndex) {

        int len = toIndex - fromIndex;

        for (int i = 0; i < len - 1; i++) {
            int temp = arr[i + 1];
            out:
            for (int j = i + 1; j > 0; j--) {
                if (temp < arr[j - 1]) {
                    arr[j] = arr[j - 1];
                } else {
                    arr[j] = temp;
                    break out;
                }
            }

        }
    }


    /**
     * pass
     *
     * @param arr
     * @param fromIndex
     * @param toIndex
     */
    public static void bubbleSort(Integer[] arr, int fromIndex, int toIndex) {
        int len = toIndex - fromIndex;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }

        }
    }


    /**
     * pass
     *
     * @param arr
     * @param fromIndex
     * @param toIndex
     */
    public static void selectSort(Integer[] arr, int fromIndex, int toIndex) {
        int len = toIndex - fromIndex;

        for (int i = 0; i < len; i++) {
            int maxIndex = 0;
            for (int j = 1; j < len - i; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            swap(arr, len - i - 1, maxIndex);

        }
    }

    /**
     * pass
     *
     * @param arr
     * @param fromIndex
     * @param toIndex
     */
    public static void shellSort(Integer[] arr, int fromIndex, int toIndex) {
        int len = toIndex - fromIndex;

        for (int step = len / 2; step > 0; step /= 2) {

            for (int semg = step; semg < len; semg++) {

                for (int i = semg; i >= step; i -= step) {
                    if (arr[i] < arr[i - step]) {
                        swap(arr, i, i - step);
                    }
                }

            }

        }
    }


    /**
     * pass
     *
     * @param arr
     * @param fromIndex
     * @param toIndex
     */
    public static void fastSort(Integer[] arr, int fromIndex, int toIndex) {

        int pivot = 0;

        if (fromIndex < toIndex - 1) {

//            pivot = partition1(arr, fromIndex, toIndex);

            int right = toIndex - 1;
            int left = fromIndex;

            int mid = (right + left) / 2;

            int key = arr[mid];

            while (left < right) {

                while (left < mid && arr[left] <= key) {

                    left++;

                }

                swap(arr, left, mid);
                mid = left;

                while (mid < right && arr[right] >= key) {

                    right--;

                }
                swap(arr, mid, right);
                mid = right;


            }

            pivot = mid;

            fastSort(arr, fromIndex, pivot);

            fastSort(arr, pivot + 1, toIndex);

        }


    }

    private static int partition(Integer[] arr, int left, int right) {

        int key = arr[left];

        while (left < right) {

            while (left < right && arr[right] >= key) {

                right--;

            }

            arr[left] = arr[right];

            while (left < right && arr[left] <= key) {

                left++;

            }

            arr[right] = arr[left];

        }

        arr[left] = key;

        return left;
    }

    /**
     * pass
     *
     * @param arr
     * @param left
     * @param right
     *
     * @return
     */
    private static int partition1(Integer[] arr, int left, int right) {

        int mid = (right + left) / 2;

        int key = arr[mid];

        while (left < right) {

            while (left < mid && arr[left] <= key) {

                left++;

            }

            swap(arr, left, mid);
            mid = left;

            while (mid < right && arr[right] >= key) {

                right--;

            }
            swap(arr, mid, right);
            mid = right;


        }

        return mid;
    }

    /**
     * pass
     *
     * @param arr
     * @param fromIndex
     * @param toIndex
     */
    public static void mergeSort(Integer[] arr, int fromIndex, int toIndex) {

        if (toIndex - fromIndex < 1) {

            return;
        }

        int mid = (fromIndex + toIndex) / 2;

        mergeSort(arr, fromIndex, mid);
        mergeSort(arr, mid + 1, toIndex);

        merge(arr, fromIndex, mid, toIndex);

    }

    private static void merge(Integer[] arr, int left, int mid, int right) {
        Integer[] temp = new Integer[right - left];

        int leftIndex = left;
        int rightIndex = mid;

        int t = 0;
        while (leftIndex < mid && rightIndex < right) {
            if (arr[leftIndex] <= arr[rightIndex]) {
                temp[t++] = arr[leftIndex++];
            } else {
                temp[t++] = arr[rightIndex++];
            }
        }

        if (leftIndex < mid) {

            for (; leftIndex < mid; ) {
                temp[t++] = arr[leftIndex++];
            }

        }

        if (rightIndex < right) {

            for (; rightIndex < right; ) {
                temp[t++] = arr[rightIndex++];
            }

        }

        for (int i = 0; i < t; i++) {
            arr[left++] = temp[i];
        }
    }


    public static void heapSort(Integer[] arr, int fromIndex, int toIndex) {

        int mid = fromIndex + (toIndex - fromIndex) / 2 - 1;

        for (int i = mid; i >= fromIndex; i--) {
            just(arr, i, toIndex);
        }

        for (int i = fromIndex; i < toIndex; i++) {
            swap(arr, fromIndex, toIndex - i - 1);
            just(arr, fromIndex, toIndex - i - 1);
        }

    }

    private static void just1(Integer[] arr, int from, int to) {
        for (int i = from; i * 2 + 1 < to; ) {

            int left = i * 2 + 1;
            int right = i * 2 + 2;
            if (right < to) {
                if (arr[i] < arr[left]) {
                    if (arr[left] < arr[right]) {
                        swap(arr, i, right);
                        i = right;
                    } else {
                        swap(arr, i, left);
                        i = left;

                    }
                } else {
                    if (arr[i] < arr[right]) {
                        swap(arr, i, right);
                        i = right;
                    } else {
                        return;
                    }
                }
            } else {
                if (arr[i] < arr[left]) {
                    swap(arr, i, left);
                    i = left;
                } else {
                    return;
                }
            }


        }
    }


    /**
     * pass
     *
     * @param arr
     * @param from
     * @param to
     */
    private static void just(Integer[] arr, int from, int to) {
        for (int i = from; i * 2 + 1 < to; ) {

            int left = i * 2 + 1;
            int right = i * 2 + 2;
            int next = left;
            if (right < to && arr[right] > arr[left]) {
                next = right;
            }

            if (arr[i] < arr[next]) {
                swap(arr, i, next);
            }
            i = next;

        }
    }


}
