package com.study.util.sort.offer;

import java.util.Stack;


public class EvaluateExpression3 {

    private static char cf[] = {'#', '|', '&', '(', ')'};


    public static void main(String[] args) {

//        String s = "9*(5-3) - 6 + 8 / 2 - (1+2*6-2-3) + 2-3+4+4+3-4-6/3-2".replaceAll("\\s", "");

        String s = "(0&1)|(0&1)|1&0".replaceAll("\\s", "");

        Stack<Integer> numStack = new Stack<>();//存放操作数
        Stack<Character> opStack = new Stack<>();//存放操作符

        opStack.push('#');
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
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
            System.out.println("-1");
        } else {
            System.out.println(numStack.pop());
        }
    }


    //判断运算符优先级，c1 > c2则返回true
    public static boolean judgeOp(char c1, char c2) {
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
    public static int compute(int t1, int t2, char c) {
        if (c == '|') {
            return t2 | t1;
        } else {
            return t2 & t1;
        }
    }

}