package com.study.spring.logger;

import org.slf4j.Logger;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public final class LoggerUtil {


    private static final String RIGHT_TAG = "]";

    private static final String LEFT_TAG = "[";


    public static void debug(Logger logger, Object... objects) {
        if (logger.isDebugEnabled()) {
            logger.debug(getLoggerStr(objects));
        }
    }


    public static void info(Logger logger, Object... objects) {
        if (logger.isInfoEnabled()) {
            logger.info(getLoggerStr(objects));
        }
    }


    public static void warn(Logger logger, Object... objects) {
        if (logger.isWarnEnabled()) {
            logger.warn(getLoggerStr(objects));
        }
    }

    public static void error(Logger logger, Object... objects) {
        if (logger.isErrorEnabled()) {
            logger.error(getLoggerStr(objects));
        }
    }


    public static void info(Object... objects) {
        info(LoggerConstant.APP_DEFAULT, objects);
    }


    public static void warn(Object... objects) {
        warn(LoggerConstant.APP_ALERT, objects);
    }

    public static void error(Object... objects) {
        error(LoggerConstant.APP_ERROR, objects);
    }


    private static String getLoggerStr(Object... objects) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LEFT_TAG).append(Thread.currentThread().getId()).append(RIGHT_TAG)
            .append(LEFT_TAG);

        for (Object object : objects) {
            stringBuilder.append(object);
        }

        stringBuilder.append(RIGHT_TAG);

        return stringBuilder.toString();
    }


}
