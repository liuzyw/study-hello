package com.study.java8.lambda;

import com.study.java8.User;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * Created on 2018-03-16
 *
 * @author liuzhaoyuan
 */
public class Lambda1 {

    private static List<User> list = new ArrayList<>();

    static {
        list.add(new User("aaa", 21));
        list.add(new User("ccc", 21));
        list.add(new User("bbb", 21));
        list.add(new User("ggg", 22));
        list.add(new User("fff", 24));
        list.add(new User("kkk", 20));
        list.add(new User("aaa", 25));

    }

    public static void main(String[] args) {
        aaa();

        bbb();
    }

    private static void aaa() {

        Comparator<Integer> comparator = (x, y) -> x.compareTo(y);

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    private static void bbb() {

        Comparator<Integer> comparator = (x, y) -> x.compareTo(y);

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);

        list.forEach(System.out::println);
        System.out.println("--------");
        list.stream().filter((user) -> user.getAge() > 23).forEach(System.out::println);
        System.out.println("--------");

        list.sort((x, y) -> x.getAge().compareTo(y.getAge()));
        list.forEach(System.out::println);

        new Thread(() -> System.out.println("--a--")).start();
    }

}
