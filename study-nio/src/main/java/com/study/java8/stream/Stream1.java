package com.study.java8.stream;

import com.study.java8.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 2018-03-17
 *
 * @author liuzhaoyuan
 */
public class Stream1 {

    private static List<User> list = new ArrayList<>();

    static {
        list.add(new User("aaa", 21));
        list.add(new User("ccc", 21));
        list.add(new User("bbb", 21));
        list.add(new User("ggg", 22));
        list.add(new User("aaa", 21));
        list.add(new User("ccc", 21));
        list.add(new User("bbb", 21));
        list.add(new User("ggg", 22));
        list.add(new User("fff", 24));
        list.add(new User("kkk", 20));
        list.add(new User("aaa", 25));
        list.add(new User("fff", 24));
        list.add(new User("kkk", 20));
        list.add(new User("aaa", 25));

    }

    public static void main(String[] args) {
//        aaa();
//        bbb();
//        ccc();
        ddd();
    }


    private static void aaa() {
        List<Integer> list = new ArrayList<>();
        Stream<Integer> stream = list.stream();

        Integer[] arr = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(arr);
    }

    private static void bbb() {
        // 筛选
        list.stream().filter((user) -> user.getAge() > 23).forEach(System.out::println);
        //  截取
        list.stream().limit(5).forEach(System.out::println);
        // 跳过
        list.stream().skip(3).forEach(System.out::println);
        // 去重
        list.stream().distinct().forEach(System.out::println);
    }

    // 映射
    private static void ccc() {

        List<String> arr = Arrays.asList("aa", "bb", "cc", "dd");

        arr.stream().map((str) -> str.toUpperCase()).forEach(System.out::println);

        list.stream().sorted(Comparator.comparing(User::getAge)).forEach(System.out::println);
    }

    // 匹配机制
    private static void ddd() {

        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println("arr sum: " + arr.stream().reduce(0, (x, y) -> x + y));

        list.stream().allMatch(user -> user.getAge() == 24);

        list.stream().anyMatch(user -> user.getAge() == 20);

        list.stream().noneMatch(user -> user.getAge() == 20);

        list.stream().map(User::getName).collect(Collectors.toList()).forEach(System.out::println);

    }


}
