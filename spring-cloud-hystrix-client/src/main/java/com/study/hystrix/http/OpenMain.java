package com.study.hystrix.http;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class OpenMain {

    public static void main(String[] args) {
        // 10秒内 连续 多少次失败打开短路器

        for (int i = 0; i < 40; i++) {
            ErrorCommand c = new ErrorCommand(i + 1);
            String re = c.execute();
            // 输出断路器状态
            System.out.println("status " + i + " - " + re + " -->  " + c.isCircuitBreakerOpen());
        }
    }

    static class ErrorCommand extends HystrixCommand<String> {

        private int num;

        public ErrorCommand(int num) {
            super(Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))

                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ExampleThreadPool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(10)
                    .withMaximumSize(20).withMaxQueueSize(100).withKeepAliveTimeMinutes(1))

                .andCommandPropertiesDefaults(
                    HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)
                        .withExecutionIsolationStrategy(ExecutionIsolationStrategy.THREAD)
                        .withCircuitBreakerRequestVolumeThreshold(6)));
            this.num = num;
        }

        @Override
        protected String run() throws Exception {
            if (num % 10 < 8) {
                Thread.sleep(1000);
            }
            return "ni ni";
        }

        @Override
        protected String getFallback() {
            return "fallback";
        }


    }
}
