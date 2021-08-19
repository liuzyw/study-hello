package com.study.util;

import java.util.Stack;

public class RuleExpressComputeUtil {

    private static char cf[] = {'#', '|', '&', '(', ')'};

    private RuleExpressComputeUtil() {
    }


    public static int compute(String expression) {
        String s = expression.replaceAll("\\s", "");

        Stack<Integer> numStack = new Stack<>();//存放操作数
        Stack<Character> opStack = new Stack<>();//存放操作符

        opStack.push('#');
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0' || s.charAt(i) != '1') {
                boolean b = true;
                while (b) {
                    if (judgeOp(s.charAt(i), opStack.peek())) {
                        opStack.push(s.charAt(i));
                        if (opStack.peek() == '(') {
                            opStack.push('#');
                        }
                        if (opStack.peek() == ')') {
                            opStack.pop();
                            while (opStack.peek() != '#') {
                                numStack.push(compute(numStack.pop(), numStack.pop(), opStack.pop()));
                            }
                            opStack.pop();
                            opStack.pop();
                        }
                        b = false;
                    } else {
                        numStack.push(compute(numStack.pop(), numStack.pop(), opStack.pop()));
                    }
                }
            } else {
                numStack.push(Integer.valueOf(s.charAt(i) + ""));
            }

        }

        while (!numStack.isEmpty() && opStack.peek() != '#') {
            numStack.push(compute(numStack.pop(), numStack.pop(), opStack.pop()));
        }
        //判断运算后栈长度，用以判定表达式是否合法
        if (opStack.peek() != '#') {
            return -1;
        } else {
            return numStack.pop();
        }
    }


    //判断运算符优先级，c1 > c2则返回true
    private static boolean judgeOp(char c1, char c2) {
        int num1 = 0, num2 = 0;
        for (int i = 0; i < cf.length; i++) {
            if (cf[i] == c1) {
                num1 = i;
            }
            if (cf[i] == c2) {
                num2 = i;
            }
        }
        if (num1 > num2) {
            return true;
        }
        return false;
    }

    //根据运算符对操作数求值
    private static int compute(int t1, int t2, char c) {
        if (c == '|') {
            return t2 | t1;
        } else {
            return t2 & t1;
        }
    }
}
