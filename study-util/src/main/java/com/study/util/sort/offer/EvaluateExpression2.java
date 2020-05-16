package com.study.util.sort.offer;

import java.util.Iterator;
import java.util.Stack;


public class EvaluateExpression2 {


    public static void main(String[] args) {

        char[] chars = "7 + 3 + 6 / 2 - 6 / 3 + 1 + 2 * 1".replaceAll("\\s", "").toCharArray();
        System.out.println(computeStr(chars));

        System.out.println(computeStr(chars) == 14);

        chars = "7 + 3 + 6 / 2 * 6 / 3 + 1 + 2 * 1".replaceAll("\\s", "").toCharArray();
        System.out.println(computeStr(chars));

        System.out.println(computeStr(chars) == 19);

        chars = "7 + 3 + 6 - 2 - 6 + 3 + 1 + 2 + 1".replaceAll("\\s", "").toCharArray();
        System.out.println(computeStr(chars));

        System.out.println(computeStr(chars) == 15);

        chars = "6 / 3 * 6 / 2 / 6 * 3 * 2 * 2 / 2".replaceAll("\\s", "").toCharArray();
        System.out.println(computeStr(chars));

        System.out.println(computeStr(chars) == 6);

    }


    private static int computeStr(char[] chars) {
        Stack<Integer> numStack = new Stack<>();//存放操作数
        Stack<Character> opStack = new Stack<>();//存放操作符

        boolean flag = true;

        for (int i = 0; i < chars.length; i++) {

            if (chars[i] >= '0' && chars[i] <= '9') {
                numStack.push(Integer.valueOf(chars[i] + ""));
                flag = true;
                while (opStack.size() > 1 && flag) {
                    char currentOp = opStack.pop();
                    char frontOp = opStack.peek();

                    if (judgeOp(currentOp, frontOp)) {

                        int nextNum = numStack.pop();
                        int frontNum = numStack.pop();

                        numStack.push(compute(frontNum, nextNum, currentOp));


                    } else {
                        opStack.push(currentOp);
                        flag = false;
                    }

                }

            } else {
                opStack.push(chars[i]);
            }

        }

        int result = 0;

        Iterator<Character> opIter = opStack.iterator();
        Iterator<Integer> numIter = numStack.iterator();

        int front = numIter.next();

        while (opIter.hasNext()) {
            result = compute(front, numIter.next(), opIter.next());
            front = result;
        }

        return result;

    }



    //判断运算符优先级，c1 > c2则返回true
    private static boolean judgeOp(char c1, char c2) {
        int num1 = 0, num2 = 0;

        num1 = switchOpOrder(c1);
        num2 = switchOpOrder(c2);

        if (num1 > num2) {
            return true;
        }
        return false;
    }


    private static int switchOpOrder(char op) {

        switch (op) {
            case '#':
                return 0;

            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '(':
            case ')':
                return 3;

        }
        return -1;

    }

    //根据运算符对操作数求值
    private static int compute(int t1, int t2, char c) {
        if (c == '+') {
            return t1 + t2;
        } else if (c == '-') {
            return t1 - t2;
        } else if (c == '*') {
            return t1 * t2;
        } else {
            return t1 / t2;
        }
    }

}