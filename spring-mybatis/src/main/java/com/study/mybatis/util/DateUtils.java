package com.study.mybatis.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2017-08-10 22:40
 *
 * @author liuzhaoyuan
 */
public class DateUtils {

    public static final String DATE_STRING = "yyyy-MM-dd";

    private DateUtils() {
    }

    /**
     * 格式日期精确到天 yyyy-MM-dd
     * @param date
     * @return
     */
    public static String formatToDay(Date date) {
        if (date == null){
            return "";
        } else {
            SimpleDateFormat format = new SimpleDateFormat(DateUtils.DATE_STRING);
            return format.format(date);
        }
    }

}
