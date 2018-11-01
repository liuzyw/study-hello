package com.study.util.sort.offer;

import java.util.Arrays;

/**
 * Created on 2018-10-25
 *
 * @author liuzhaoyuan
 */
public class KMP {

    public static void main(String[] args) {


        char[] par = new char[]{'b', 'c', 'b', 'c', 'a', 'c', 'a', 'a', 'b', 'c', 'a', 'c', 'c', 'b', 'f'};

        char[] sub = new char[]{'b', 'c', 'a', 'c'};

        int[] next = new int[]{0, 1, 1, 2, 2, 3, 1, 2};

        System.out.println(Arrays.toString(makeNext(sub)));

        int index = findIndex(par, sub, next);

        System.out.println(index);

        System.out.println(KMP("bcbcacadd", "bcac"));
    }

    private static int findIndex(char[] par, char[] sub, int[] next) {

        int j = 0;
        for (int i = 0; i < par.length; ) {
            if (par[i] == sub[j]) {
                i++;
                j++;
                if (j == sub.length) {
                    return i - j;
                }
            } else {
                j = next[j];

                if (par[i] == sub[j]) {
                    i++;
                    j++;
                } else {
                    i++;
                    j = 0;
                }
            }
        }

        return -1;
    }

    public static int[] makeNext(char[] P) {
        int[] next = new int[P.length];
        int q, k;//q:模版字符串下标；k:最大前后缀长度
        int m = P.length;//模版字符串长度
        next[0] = 0;//模版字符串的第一个字符的最大前后缀长度为0
        for (q = 1, k = 0; q < m; ++q)//for循环，从第二个字符开始，依次计算每一个字符对应的next值
        {
            while (k > 0 && P[q] != P[k])//递归的求出P[0]···P[q]的最大的相同的前后缀长度k
            {
                k = next[k - 1];          //不理解没关系看下面的分析，这个while循环是整段代码的精髓所在，确实不好理解
            }
            if (P[q] == P[k])//如果相等，那么最大相同前后缀长度加1
            {
                k++;
            }
            next[q] = k;
        }

        return next;
    }

    public static int KMP(String ts, String ps) {

        char[] t = ts.toCharArray();

        char[] p = ps.toCharArray();

        int i = 0; // 主串的位置

        int j = 0; // 模式串的位置

        int[] next = getNext(ps);

        while (i < t.length && j < p.length) {

            if (j == -1 || t[i] == p[j]) { // 当j为-1时，要移动的是i，当然j也要归0

                i++;

                j++;

            } else {

                // i不需要回溯了

                // i = i - j + 1;

                j = next[j]; // j回到指定位置

            }

        }

        if (j == p.length) {

            return i - j;

        } else {

            return -1;

        }

    }

    public static int[] getNext(String ps) {

        char[] p = ps.toCharArray();

        int[] next = new int[p.length];

        next[0] = -1;

        int j = 0;

        int k = -1;

        while (j < p.length - 1) {

            if (k == -1 || p[j] == p[k]) {

                if (p[++j] == p[++k]) { // 当两个字符相等时要跳过

                    next[j] = next[k];

                } else {

                    next[j] = k;

                }

            } else {

                k = next[k];

            }

        }
        System.out.println(Arrays.toString(next));
        return next;

    }


    public static int[] dsad() {
        char[] sub = new char[]{'a', 'b', 'a', 'a', 'b', 'c', 'a', 'c'};
// [-1, 0, -1, 1, 0, 2, -1, 1]
        int j = 2;

        int[] next = new int[sub.length];
        next[0] = -1;
        next[1] = 0;

        int k = -1;

        while (j < sub.length - 1) {

            while (sub[j - 1] == sub[j / 2]) {
                next[j] = next[j - 1] + 1;
            }
        }

        return null;
    }

}
