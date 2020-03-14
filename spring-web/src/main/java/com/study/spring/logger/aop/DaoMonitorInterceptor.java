package com.study.spring.logger.aop;

import com.alibaba.fastjson.JSON;
import com.study.spring.logger.LoggerConstant;
import com.study.spring.logger.LoggerUtil;
import java.lang.reflect.Method;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class DaoMonitorInterceptor implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        System.out.println("DaoMonitorInterceptor ......");

        long startTime = System.currentTimeMillis();

        Method method = methodInvocation.getMethod();

        String className = method.getDeclaringClass().getSimpleName();

        String methodName = className + "." + method.getName();

        String success = "Y";

        try {
            return methodInvocation.proceed();
        } catch (Exception e) {
            success = "N";
            LoggerUtil.error("DAO invoke", methodName, " exception,[param=",
                JSON.toJSONString(methodInvocation.getArguments()), "]", e);

            throw e;
        } finally {

            long elapseTime = System.currentTimeMillis() - startTime;

            LoggerUtil.info(LoggerConstant.DAO_DIGEST_LOG, methodName, ",",
                success, ",", elapseTime, "ms");

        }

    }
}
