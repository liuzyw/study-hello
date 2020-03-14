package com.study.spring.logger.aop;

import com.alibaba.fastjson.JSON;
import com.study.spring.logger.DigestLogAnnotation;
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
public class ServiceMonitorInterceptor implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        System.out.println("ServiceMonitorInterceptor ......");

        // 没注解直接调用
        if (!methodInvocation.getMethod().isAnnotationPresent(DigestLogAnnotation.class)) {
            return methodInvocation.proceed();
        }

        DigestLogAnnotation logAnnotation = methodInvocation.getMethod().getAnnotation(DigestLogAnnotation.class);

        String argsStr = "-";
        if (logAnnotation.printParam()) {
            argsStr = JSON.toJSONString(methodInvocation.getArguments());
        }
        String resultStr = "-";

        long startTime = System.currentTimeMillis();

        Method method = methodInvocation.getMethod();

        String className = method.getDeclaringClass().getSimpleName();

        String methodName = className + "." + method.getName();

        String success = "Y";

        Object result = null;

        try {
            result = methodInvocation.proceed();
            if (logAnnotation.printResult()) {
                resultStr = JSON.toJSONString(result);
            }

        } catch (Exception e) {
            success = "N";
            LoggerUtil.error("SERVICE invoke", methodName, " exception,[param=",
                argsStr, "result=", resultStr, "]", e);

            throw e;
        } finally {
            long elapseTime = System.currentTimeMillis() - startTime;
            LoggerUtil.info(LoggerConstant.SERVICE_DIGEST_LOG, methodName, ",", "(", argsStr, ")", "(", resultStr,
                ")", success, ",", elapseTime, "ms");

        }
        return result;
    }
}
