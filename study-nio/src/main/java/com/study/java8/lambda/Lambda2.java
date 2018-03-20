package com.study.java8.lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 * 		void accept(T t);
 *
 * Supplier<T> : 供给型接口
 * 		T get();
 *
 * Function<T, R> : 函数型接口
 * 		R apply(T t);
 *
 * Predicate<T> : 断言型接口
 * 		boolean test(T t);
 *
 */
public class Lambda2 {

    public static void main(String[] args) {
        consumer(123, (m) -> System.out.println("---- consumer ---"));
        System.out.println(supplier(123, () -> "123"));
        System.out.println(function(123, (num) -> num + ""));
        System.out.println(predicate(123,(num) -> false));

    }

    private static void consumer(Integer num, Consumer<Integer> consumer) {
        consumer.accept(num);
    }

    private static String supplier(Integer num, Supplier<String> supplier) {
        return supplier.get() + "supplier";
    }

    private static String function(Integer num, Function<Integer, String> function) {
        return function.apply(num) + "function";
    }

    private static boolean predicate(Integer num, Predicate<Integer> predicate) {
        return predicate.test(num) == false;
    }


}
