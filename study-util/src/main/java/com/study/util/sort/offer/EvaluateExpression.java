package com.study.util.sort.offer;

import java.util.Stack;

//定义运算符及对应优先级
class OP {

    char c;
    int num;

    public OP(char c, int num) {
        this.c = c;
        this.num = num;
    }
};

public class EvaluateExpression {

    private static OP op[] = new OP[7];
    private static char cf[] = {'#', '+', '-', '*', '/', '(', ')'};

    static {
        for (int i = 0; i < 7; i++) {
            op[i] = new OP(cf[i], i);
        }
    }

    //判断运算符优先级，c1 > c2则返回true
    public static boolean judge(char c1, char c2) {
        int num1 = 0, num2 = 0;
        for (int i = 0; i < 7; i++) {
            if (op[i].c == c1) {
                num1 = i;
            }
            if (op[i].c == c2) {
                num2 = i;
            }
        }
        if (op[num1].num > op[num2].num) {
            return true;
        }
        return false;
    }

    //根据运算符对操作数求值
    public static int option(int t1, int t2, char c) {
        if (c == '+') {
            return t1 + t2;
        } else if (c == '-') {
            return t2 - t1;
        } else if (c == '*') {
            return t1 * t2;
        } else {
            return t2 / t1;
        }
    }

    public static void main(String[] args) {

        String s = "11 * (5-3) - 6 + 8 / 2".replaceAll("\\s", "");

        Stack<Integer> stk_int = new Stack<>();//存放操作数
        Stack<Character> stk_str = new Stack<>();//存放操作符
        //数据存入栈中
        int index = 0;
        stk_str.push('#');
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                if (index != i) {
                    stk_int.push(Integer.parseInt(s.substring(index, i)));
                }
                index = i + 1;
                boolean b = false;
                while (b == false) {
                    if (judge(s.charAt(i), stk_str.peek())) {
                        stk_str.push(s.charAt(i));
                        if (stk_str.peek() == '(') {
                            stk_str.push('#');
                        }
                        if (stk_str.peek() == ')') {
                            stk_str.pop();
                            while (stk_str.peek() != '#') {
                                stk_int.push(option(stk_int.pop(), stk_int.pop(), stk_str.pop()));
                            }
                            stk_str.pop();
                            stk_str.pop();
                        }
                        b = true;
                    } else {
                        stk_int.push(option(stk_int.pop(), stk_int.pop(), stk_str.pop()));
                    }
                }
            }
            if (index != s.length() && i == s.length() - 1) {
                stk_int.push(Integer.parseInt(s.substring(index, s.length())));
            }
        }
        while (!stk_int.isEmpty() && stk_str.peek() != '#') {
            stk_int.push(option(stk_int.pop(), stk_int.pop(), stk_str.pop()));
        }
        //判断运算后栈长度，用以判定表达式是否合法
        if (stk_str.peek() != '#') {
            System.out.println("-1");
        } else {
            System.out.println(stk_int.pop());
        }
    }
}