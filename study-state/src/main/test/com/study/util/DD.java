package com.study.util;

import junit.framework.TestCase;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class DD extends TestCase {


    public void testAdasd() {
        String FUNCTION = "x/y + (x+y)*z";
        // 构建表达式，并声明变量定义
        ExpressionBuilder builder = new ExpressionBuilder(FUNCTION).variables("x", "y", "z");

        try {

            Expression expression = builder.build();
            expression.setVariable("x", 6);
            expression.setVariable("y", 3);
            expression.setVariable("z", 4);


            System.out.println(expression.evaluate());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void testAdasd3() {
        String FUNCTION = "(a&b) || (c&d)";
        // 构建表达式，并声明变量定义
        ExpressionBuilder builder = new ExpressionBuilder(FUNCTION).variables("a", "b", "c","d");

        try {

            Expression expression = builder.build();
            expression.setVariable("a", 6);
            expression.setVariable("b", 3);
            expression.setVariable("c", 4);
            expression.setVariable("d", 4);


            System.out.println(expression.evaluate());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
