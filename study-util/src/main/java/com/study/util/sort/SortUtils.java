package com.study.util.sort;

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
     * @param from
     * @param to
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertSort(T[] arr, int from, int to) {
        int j;
        for (int i = from + 1; i < to; i++) {
            // 一次插入过程
            T temp = arr[i];
            for (j = i; (j >= from + 1 && temp.compareTo(arr[j - 1]) < 0); j--) {
                arr[j] = arr[j - 1];
            }
            // 指定位置插入
            arr[j] = temp;
        }
    }


    /**
     * 冒泡排序
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr, int from, int to) {

        for (int i = from + 1; i < to; i++) {
            // 比较相邻元素，大的向后冒泡
            for (int j = from; j < to - i + from; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 选择排序
     */
    public static <T extends Comparable<? super T>> void selectSort(T[] arr, int from, int to) {

        for (int i = from; i < to - 1; i++) {
            int index = from;
            for (int j = from + 1; j < to - i + from; j++) {
                if (arr[j].compareTo(arr[index]) > 0) {
                    // 查找最大元素
                    index = j;
                }
            }
            swap(arr, index, to - i + from - 1);
        }
    }


    /**
     * 希尔排序
     */
    public static <T extends Comparable<? super T>> void shellSort(T[] arr, int from, int to) {
        for (int step = (to - from) / 2; step > 0; step /= 2) {
            // 根据步长进行一次插入排序
            int j;
            for (int i = from + step; i < to; i++) {
                T temp = arr[i];
                for (j = i; (j >= from + step) && temp.compareTo(arr[j - step]) < 0; j -= step) {
                    arr[j] = arr[j - step];
                }
                arr[j] = temp;
            }
        }
    }

    /**
     * 堆排序
     */
    public static <T extends Comparable<? super T>> void heapSort(T[] arr, int from, int to) {

        for (int i = (to - from - 1) / 2 + from; i >= from; i--) {
            adjustDown(arr, i, to);
        }
        for (int i = to - 1; i > from; i--) {
            swap(arr, from, i);
            adjustDown(arr, from, i);
        }
    }


    /**
     * 一次向下调整过程
     */
    private static <T extends Comparable<? super T>> void adjustDown(T[] arr, int from, int to) {

        int child;
        for (; 2 * from + 1 < to; from = child) {
            child = 2 * from + 1;
            if (child + 1 < to && arr[child].compareTo(arr[child + 1]) < 0) {
                child++;
            }
            if (arr[from].compareTo(arr[child]) < 0) {
                swap(arr, from, child);
            } else {
                return;
            }
        }
    }

    /**
     * 闭区间
     *
     * <p>不能做部分排序， 因为 顺序变了， 不满足 2* from < to</>
     * <p>
     * 归并排序
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] arr, int from, int to) {

        if (from < to) {
            int mid = (from + to) / 2;
            mergeSort(arr, from, mid);
            mergeSort(arr, mid + 1, to);
            merge(arr, from, mid, to);
        }

    }

    /**
     * 一次归并结果
     */
    private static <T extends Comparable<? super T>> void merge(T[] arr, int from, int mid, int to) {
        T[] temp = (T[]) new Comparable[to - from + 1];
        int left = from;
        int right = mid + 1;
        int k = 0;
        // 把较小的数先移到新数组中
        while (left <= mid && right <= to) {
            if (arr[left].compareTo(arr[right]) < 0) {
                temp[k++] = arr[left++];
            } else {
                temp[k++] = arr[right++];
            }
        }
        // 把左边剩余的数移入数组
        while (left <= mid) {
            temp[k++] = arr[left++];
        }
        // 把右边边剩余的数移入数组
        while (right <= to) {
            temp[k++] = arr[right++];
        }
        // 把新数组中的数覆盖nums数组
        for (int x = 0; x < temp.length; x++) {
            arr[x + from] = temp[x];
        }
    }


    /**
     * 快速排序
     *
     * @param arr
     * @param from
     * @param to
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void fastSort(T[] arr, int from, int to) {

        if (from < to) {

            int right = to - 1;
            int left = from;

            int mid = (right + left) / 2;

            T key = arr[mid];

            while (left < right) {
                while (left < mid && arr[left].compareTo(key) <= 0) {
                    left++;
                }

                swap(arr, mid, left);
                mid = left;

                while (mid < right && arr[right].compareTo(key) >= 0) {
                    right--;
                }
                swap(arr, mid, right);
                mid = right;
            }

            fastSort(arr, from, mid);

            fastSort(arr, mid + 1, to);
        }
    }

    public static <T extends Comparable<? super T>> void swap(T[] arr, int from, int to) {
        if (from == to) {
            return;
        }

        T t = arr[from];
        arr[from] = arr[to];
        arr[to] = t;
    }

    public static void swap(int[] arr, int from, int to) {
        if (from == to) {
            return;
        }

        int t = arr[from];
        arr[from] = arr[to];
        arr[to] = t;
    }

}
