package com.study.spring.boot.web.aspect;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created on 2018-01-20
 *
 * @author liuzhaoyuan
 */
@Aspect
@Component
public class UserLoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoggerAspect.class);


    @Pointcut("execution(public * com.study.spring.boot.web.controller.*.*(..))")
    public void doLog() {
        LOGGER.info("user operation log");
    }

    @Before("doLog()")
    public void doBefore(JoinPoint joinPoint) {
        LOGGER.info("doBefore method ...");
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        LOGGER.info("url:" + request.getRequestURI());
        LOGGER.info("ip:" + request.getRemoteHost());
        LOGGER.info("method:" + request.getMethod());
        LOGGER.info("class_method:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature()
            .getName());
        LOGGER.info("args:" + joinPoint.getArgs());

    }

    @After("doLog()")
    public void doAfter(JoinPoint joinPoint) {
        LOGGER.info("doAfter method...");
    }

    @AfterReturning(returning = "result", pointcut = "doLog()")
    public void doAfterReturning(Object result) {
        LOGGER.info("doAfterReturning" + result);
    }


}
