package com.study.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2017-10-07
 *
 * @author liuzhaoyuan
 */
public class DateUtils {

    private static final String TIME_START = "1970-01-01 00:00:01";

    private DateUtils() {
    }

    /**
     * 格式化日期 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String formatDateBySecond(Date date) {
        if (date == null) {
            return TIME_START;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = format.format(date);
            return str;
        } catch (Exception e) {
            System.out.println("format date fail " + e);
            return TIME_START;
        }
    }

    /**
     * 格式化日期 yyyy-MM-dd
     * @param date
     * @return
     */
    public static String formatDateByDay(Date date) {
        if (date == null) {
            return TIME_START;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String str = format.format(date);
            return str;
        } catch (Exception e) {
            System.out.println("format date fail " + e);
            return TIME_START;
        }
    }

}
