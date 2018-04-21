package com.study.util.other;

import java.lang.reflect.Field;

public class InvokeFiled {

    /**
     * 获取字段方法和前面获取构造器和方法大同小异。getFileds():获取所有Public修饰的字段以及继承自父类的字段   规则与获取构造和方法大同小异
     */
    public static void main(String[] args) throws Exception {
        Class claz = ABSD.class;
        Class claz1 = Class.forName("com.study.util.other.ABSD");

        Field[] fields = claz.getFields();
        for (Field field : fields) {
            System.out.println("获取所有的public及继承的字段" + field);
        }

        Field[] fieldsDecrs = claz.getDeclaredFields();
        for (Field fieldDecr : fieldsDecrs) {
            System.out.println("获取所有的字段包括private/public，但不包括继承的字段" + fieldDecr.getName());
        }
        System.out.println("-----");
    
    /*获取指定的字段：参数传字段名称*/
        Field field1 = claz.getField("name");
        System.out.println("获取指定的public字段" + field1);
    
    /*获取指定的私有字段*/
        Field fieldP = claz.getDeclaredField("number");
        System.out.println("获取指定的私有字段:" + fieldP);

        /**
         *为字段设值：setxxx(obj,基本类型数据)基本类型字段设值；  set(obj,引用类型数据)  引用类型设值 obj为字段所在底层对象 如果是静态字段则obj可以为NULL
         */
       /*给字段设置值*/
        field1.set(claz.newInstance(), "张飞");
        System.out.println(field1.get(claz.newInstance()));
        
        /*给私有字段设置值*/
        fieldP.setAccessible(true);
        fieldP.setInt(claz.newInstance(), 12);
        System.out.println(fieldP.getInt(claz.newInstance()));

        /**
         *   获取字段值 ：getxxx(obj) get(obj) 如果字段为static修饰则obj可以为null
         */
        /*获取私有静态字段的值*/
        Field fieldd = claz.getDeclaredField("str");
        fieldd.setAccessible(true);
        System.out.println(fieldd.get(null) + "******私有静态引用类型");
            
            /*获取私有的字段的值*/
        Field fieldnum = claz.getDeclaredField("num");
        fieldnum.setAccessible(true);
        System.out.println(fieldnum.getInt(claz.newInstance()) + "******私有基本类型");


    }
}

class ABSD {

    public String name;

    private int age;

    public int ccc;
}