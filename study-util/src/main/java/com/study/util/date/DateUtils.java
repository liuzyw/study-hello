package com.study.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * Created on 2017-10-07
 *
 * @author liuzhaoyuan
 */
public class DateUtils {

    private static final String TIME_START = "1970-01-01 00:00:01";

    private static final String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    private static final String YYYY_MM_DD = "yyyy-MM-dd";

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
            SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_mm_ss);
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
            SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD);
            String str = format.format(date);
            return str;
        } catch (Exception e) {
            System.out.println("format date fail " + e);
            return TIME_START;
        }
    }

    /**
     * 格式化当前时间 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurTimeStr(){
        return new DateTime().toString(YYYY_MM_DD_HH_mm_ss);
    }

    /**
     * 当前月份的第几天 yyyy-MM-dd
     * @param dayOfMonth
     * @return
     */
    public static Date getMonthDay(int dayOfMonth){
        DateTime dt = new DateTime();
        LocalDate theDayOfMonth = dt.toLocalDate().withDayOfMonth(dayOfMonth);
        return theDayOfMonth.toDate();
    }

    /**
     * 当前时间所在周的第一天
     * @return
     */
    public static Date getWeekFirstDay(){
//        DateTime.now().dayOfWeek().withMaximumValue().toString("yyyy-MM-dd"))
//        DateTime.now().dayOfMonth().withMaximumValue().toString("yyyy-MM-dd"))
//        DateTime.now().dayOfYear().withMaximumValue().toString("yyyy-MM-dd")
         return DateTime.now().dayOfWeek().withMinimumValue().toDate();
    }

    /**
     * 当前时间后的多少天时间
     * @param day
     * @return
     */
    public static Date getAfterDay(int day){
//        DateTime.now().dayOfYear().addToCopy(day)
        return DateTime.now().plusDays(day).toDate();
    }

    /**
     * 当前时间前多少天
     * @param day
     * @return
     */
    public static Date getFrontDat(int day){
//        DateTime.now().minusHours(day)
        return DateTime.now().minusDays(day).toDate();
    }


}
