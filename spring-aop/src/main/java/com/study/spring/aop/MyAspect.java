package com.study.spring.aop;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created on 2017-09-27
 *
 * @author liuzhaoyuan
 */
@Aspect
public class MyAspect {

    public static final String POINT = "execution(* com.study.spring.service.*.*(..)) || execution(* com.study.spring.beans.*.*(..))";

    @Before(POINT)
    public void beforeWay(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("before way... " + joinPoint.getSignature().getName() + ": " + Arrays.toString(args));
    }

    @After(POINT)
    public void afterWay(JoinPoint joinPoint) {
        System.out.println("after way...");
    }

    /**
     * 利用了动态代理，手动推进方法运行
     *
     * @param jp
     */
//    @Around(POINT)
    public void aroundWay(ProceedingJoinPoint jp) {
        try {
            System.out.println("aroundWay before ....");
            jp.proceed();
            System.out.println("aroundWay after ....");
        } catch (Throwable e) {

        }
    }

    @AfterReturning(value = POINT, returning = "result")
    public void afterReturn(Object result) {
        System.out.println("after return .... result: " + result);
    }

    @AfterThrowing(value = POINT, throwing = "ex")
    public void exception(Exception ex) {
        System.out.println("exception ... ex:" + ex);
    }

}
