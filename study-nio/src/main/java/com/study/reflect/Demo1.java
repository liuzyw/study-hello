package com.study.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created on 2018-05-28
 *
 * @author liuzhaoyuan
 */
public class Demo1 {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.study.reflect.User");
        User user = (User) clazz.newInstance();
        user.say();
        System.out.println(user);

        Field field = clazz.getField("address");
        field.getModifiers();
        field.getType();

        field.set(user, "八里");

        System.out.println(user);

        Field field1 = clazz.getDeclaredField("name");
        field1.setAccessible(true);
        field1.set(user, "liu");

        System.out.println(user);

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        Class<?> supper = clazz.getSuperclass();
        Type type = clazz.getGenericSuperclass();

        // 子类和父类的public方法
        clazz.getMethods();
        // 子类声明的方法
        clazz.getDeclaredMethods();

        Method method = clazz.getDeclaredMethod("say");
        method.setAccessible(true);
        method.invoke(user);

        method.getName();
        method.getModifiers();
        method.getReturnType();
        method.getParameterTypes();
        method.getDeclaredAnnotations();
        method.getAnnotations();

        Annotation[] annotations = clazz.getAnnotations();

        Method method1 = clazz.getDeclaredMethod("sum", new Class[]{int.class, int.class});

        System.out.println(method1.invoke(user, new Object[]{3, 4}));

        ClassLoader loader = Demo1.class.getClass().getClassLoader();
        User u = (User) loader.loadClass("com.study.reflect.User").newInstance();
        u.say();


        Constructor constructor = clazz.getConstructor(String.class);
        constructor.setAccessible(true);
        User user1 = (User) constructor.newInstance("Tom");

    }
}
