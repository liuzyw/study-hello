package com.study.jvm;

import java.util.Map;

/**
 * Created on 2018-05-20
 *
 * @author liuzhaoyuan
 */
public class JstackTest {

    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();

        for (Map.Entry<Thread, StackTraceElement[]> entry : map.entrySet()) {

            System.out.println("Thread Name: " + entry.getKey().getName());

            for (StackTraceElement element : entry.getValue()) {
                System.out.println("        class:" + element.getClass());
                System.out.println("        className:" + element.getClassName());
                System.out.println("        methodName:" + element.getMethodName());
                System.out.println("        fileName:" + element.getFileName());
                System.out.println("        lineNumber:" + element.getLineNumber());

            }

        }

    }

}
