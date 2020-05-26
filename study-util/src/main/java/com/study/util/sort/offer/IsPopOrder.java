package com.study.util.sort.offer;

import java.util.Stack;

/**
 * Created on 2018-10-29
 *
 * @author liuzhaoyuan
 */
public class IsPopOrder {


    public static void main(String[] args) {

        int[] src = new int[]{1, 2, 3, 4, 5};
        int[] desc = new int[]{4, 5, 3, 2, 1};

        System.out.println(isPop(src, desc));
    }


    public static boolean isPop(int[] src, int[] desc) {

        Stack<Integer> stack = new Stack<>();
        int index = 0;

        for (int i = 0; i < src.length; i++) {
            stack.push(src[i]);
            while (!stack.isEmpty() && stack.peek().equals(desc[index])) {
                stack.pop();
                index++;
            }
        }

        if (index == desc.length && stack.isEmpty()) {
            return true;
        }

        return false;

    }

}
