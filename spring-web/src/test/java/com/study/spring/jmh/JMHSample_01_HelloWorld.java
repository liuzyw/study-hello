package com.study.spring.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMHSample_01_HelloWorld {



    @Benchmark
    public String stringConcat() {
        String a = "a";
        String b = "b";
        String c = "c";
        String s = a + b + c;

        return s;
    }



    public static void main(String[] args) throws RunnerException {
        // 使用一个单独进程执行测试，执行5遍warmup，然后执行5遍测试
        Options opt = new OptionsBuilder().include(JMHSample_01_HelloWorld.class.getSimpleName())
            .forks(1).warmupIterations(2)
            .measurementIterations(2).build();

        new Runner(opt).run();
    }

}