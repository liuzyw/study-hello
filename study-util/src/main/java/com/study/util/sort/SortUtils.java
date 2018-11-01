package com.study.util.sort;

import org.apache.poi.ss.formula.functions.T;

/**
 * Created on 2017-12-08
 *
 * @author liuzhaoyuan
 */
public class SortUtils {

    private SortUtils() {
    }


    /**
     * 二分查找
     *
     * @param arr
     * @param from
     * @param to
     * @param num
     * @param <T>
     *
     * @return
     */
    public static <T extends Comparable<? super T>> int binaryFind(T[] arr, int from, int to, T num) {

        if (to == from && to == 0) {
            return -1;
        }

        if (to == from) {
            return -from;
        }

        int mid = (from + to) / 2;
        if (arr[mid].compareTo(num) == 0) {
            return mid;
        } else if (arr[mid].compareTo(num) > 0) {
            return binaryFind(arr, from, mid, num);
        } else {
            return binaryFind(arr, mid + 1, to, num);
        }
    }


    /**
     * 插入排序
     *
     * @param arr
     * @param fromIndex
     * @param toIndex
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertSort(T[] arr, int fromIndex, int toIndex) {
        int j;
        for (int i = fromIndex + 1; i < toIndex + 1; i++) {
            // 一次插入过程
            T temp = arr[i];
            for (j = i; (j >= fromIndex + 1 && temp.compareTo(arr[j - 1]) < 0); j--) {
                arr[j] = arr[j - 1];
            }
            // 指定位置插入
            arr[j] = temp;
        }
    }


    /**
     * 冒泡排序
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr, int fromIndex, int toIndex) {

        for (int i = fromIndex + 1; i < toIndex + 1; i++) {
            // 比较相邻元素，大的向后冒泡
            for (int j = fromIndex; j < toIndex + 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     */
    public static <T extends Comparable<? super T>> void selectSort(T[] arr, int fromIndex, int toIndex) {

        for (int i = fromIndex; i < toIndex; i++) {
            int index = fromIndex;
            for (int j = fromIndex + 1; j < toIndex + 1 - i; j++) {
                if (arr[j].compareTo(arr[index]) > 0) {
                    // 查找最大元素
                    index = j;
                }
            }
            T temp = arr[toIndex - i];
            arr[toIndex - i] = arr[index];
            arr[index] = temp;
        }
    }


    /**
     * 希尔排序
     */
    public static <T extends Comparable<? super T>> void shellSort(T[] arr, int fromIndex, int toIndex) {
        for (int step = (toIndex - fromIndex) / 2; step > 0; step /= 2) {
            // 根据步长进行一次插入排序
            int j;
            for (int i = fromIndex + step; i < toIndex + 1; i++) {
                T temp = arr[i];
                for (j = i; (j >= fromIndex + step) && temp.compareTo(arr[j - step]) < 0; j -= step) {
                    arr[j] = arr[j - step];
                }
                arr[j] = temp;
            }
        }
    }

    /**
     * 堆排序
     */
    public static <T extends Comparable<? super T>> void heapSort(T[] arr, int fromIndex, int toIndex) {

        for (int i = (toIndex + fromIndex) / 2; i >= fromIndex; i--) {
            percDown(arr, i, toIndex + 1);
        }
        for (int i = toIndex; i > fromIndex; i--) {
            T temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            percDown(arr, fromIndex, i);
        }
    }

    /**
     * 一次向下调整过程
     */
    private static <T extends Comparable<? super T>> void percDown(T[] arr, int fromIndex, int toIndex) {
        T temp;
        int child;
        for (temp = arr[fromIndex]; 2 * fromIndex + 1 < toIndex; fromIndex = child) {
            child = 2 * fromIndex + 1;
            if (child != toIndex - 1 && arr[child].compareTo(arr[child + 1]) < 0) {
                child++;
            }
            if (temp.compareTo(arr[child]) < 0) {
                arr[fromIndex] = arr[child];
            } else {
                break;
            }
        }
        arr[fromIndex] = temp;
    }

    /**
     * 归并排序
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] arr, int left, int right) {

        if (left >= right) {
            return;
        }
        int center = (left + right) / 2;
        mergeSort(arr, left, center);
        mergeSort(arr, center + 1, right);
        // 合并结果
        merge(arr, left, center, right);

    }

    /**
     * 一次归并结果
     */
    private static <T extends Comparable<? super T>> void merge(T[] arr, int left, int center, int right) {
        T[] tempArr = (T[]) new Comparable[arr.length];
        // 右数据第一个元素
        int rightIndex = center + 1;
        // 左数据第一个元素
        int leftIndex = left;
        // 临时数据索引
        int third = left;

        while (left <= center && rightIndex <= right) {
            if (arr[left].compareTo(arr[rightIndex]) < 0) {
                tempArr[third++] = arr[left++];
            } else {
                tempArr[third++] = arr[rightIndex++];
            }
        }

        // 剩余部分
        while (rightIndex <= right) {
            tempArr[third++] = arr[rightIndex++];
        }
        while (left <= center) {
            tempArr[third++] = arr[left++];
        }

        while (leftIndex <= right) {
            arr[leftIndex] = tempArr[leftIndex];
            leftIndex++;
        }

    }


    /**
     * 快速排序
     *
     * @param arr
     * @param fromIndex
     * @param toIndex
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void fastSort(T[] arr, int fromIndex, int toIndex) {

        if (fromIndex < toIndex - 1) {

            int right = toIndex - 1;
            int left = fromIndex;

            int mid = (right + left) / 2;

            T key = arr[mid];

            while (left < right) {
                while (left < mid && arr[left].compareTo(key) <= 0) {
                    left++;
                }

                T temp = arr[mid];
                arr[mid] = arr[left];
                arr[left] = temp;

                mid = left;

                while (mid < right && arr[right].compareTo(key) >= 0) {
                    right--;
                }
                temp = arr[mid];
                arr[mid] = arr[right];
                arr[right] = temp;

                mid = right;
            }

            fastSort(arr, fromIndex, mid);

            fastSort(arr, mid + 1, toIndex);

        }
    }

    public static void swap(T[] arr, int a, int b) {
        if (a == b) {
            return;
        }

        T t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    public static void swap(int[] arr, int a, int b) {
        if (a == b) {
            return;
        }

        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

}
