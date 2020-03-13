package com.study.spring.logger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2020-03-13
 *
 * @author liuzhaoyuan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DigestLogAnnotation {


    /**
     * 业务名称
     *
     * @return
     */
    String bizName() default "-";


    /**
     * 日志类型
     * <p>
     * dao   service   sal
     *
     * @return
     */
    LoggerType loggerType();


    /**
     * 打印参数
     *
     * @return
     */
    boolean printParam() default false;


    /**
     * 打印结果
     *
     * @return
     */
    boolean printResult() default false;

}
