package com.study.util.sort.offer;

/**
 * Created on 2018-10-24
 *
 * @author liuzhaoyuan
 */
public class FindInPartiallySortedMatrix {


    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 8, 9},
            {2, 4, 9, 12},
            {4, 7, 10, 13},
            {6, 8, 11, 15}
        };
        System.out.println(find(matrix, 0, 4, 7));    // 要查找的数在数组中
        System.out.println(find(matrix, 0, 4, 5));    // 要查找的数不在数组中
        System.out.println(find(matrix, 0, 4, 1));    // 要查找的数是数组中最小的数字
        System.out.println(find(matrix, 0, 4, 15));   // 要查找的数是数组中最大的数字
        System.out.println(find(matrix, 0, 4, 0));    // 要查找的数比数组中最小的数字还小
        System.out.println(find(matrix, 0, 4, 16));   // 要查找的数比数组中最大的数字还大
    }


    /**
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * <p/>
     * 规律：首先选取数组中右上角的数字。如果该数字等于要查找的数字，查找过程结束：
     * 如果该数字大于要查找的数字，剔除这个数字所在的列：如果该数字小于要查找的数字，剔除这个数字所在的行。
     * 也就是说如果要查找的数字不在数组的右上角，则每－次都在数组的查找范围中剔除）行或者一列，这样每一步都可以缩小
     * 查找的范围，直到找到要查找的数字，或者查找范围为空。
     *
     *
     *
     * 我们的思路是从左下角开始，递归查找
     *
     * @return 查找结果，true找到，false没有找到
     */
    public static boolean find(int[][] matrix, int col, int row, int number) {

        int maxCol = matrix[0].length;

        int r = row - 1;

        if (col >= maxCol) {
            return false;
        }

        for (int i = row - 1; i >= 0; i--) {

            if (matrix[i][col] == number) {
                return true;
            } else if (matrix[i][col] < number) {
                return find(matrix, col + 1, i + 1, number);
            }

        }

        return false;
    }

}
