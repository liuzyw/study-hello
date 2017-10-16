package com.study.util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    private static final String YYYYMMDD = "yyyyMMdd";

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

    public static Date parse(String dateStr, String patten) {
        if (dateStr == null || patten == null) {
            return null;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat(patten);
            Date date = format.parse(dateStr);
            return date;
        } catch (Exception e) {
            System.out.println("parse date fail " + e);
            return null;
        }
    }

    /**
     * 格式化日期 yyyy-MM-dd
     *
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
     *
     * @return
     */
    public static String getCurTimeStr() {
        return new DateTime().toString(YYYY_MM_DD_HH_mm_ss);
    }

    /**
     * 当前月份的第几天 yyyy-MM-dd
     *
     * @return
     */
    public static Date getMonthDay(int dayOfMonth) {
        DateTime dt = new DateTime();
        LocalDate theDayOfMonth = dt.toLocalDate().withDayOfMonth(dayOfMonth);
        return theDayOfMonth.toDate();
    }

    /**
     * 当前时间所在周的第一天
     *
     * @return
     */
    public static Date getWeekFirstDay() {
//        DateTime.now().dayOfWeek().withMaximumValue().toString("yyyy-MM-dd"))
//        DateTime.now().dayOfMonth().withMaximumValue().toString("yyyy-MM-dd"))
//        DateTime.now().dayOfYear().withMaximumValue().toString("yyyy-MM-dd")
        return DateTime.now().dayOfWeek().withMinimumValue().toDate();
    }

    /**
     * 当前时间后的多少天时间
     *
     * @return
     */
    public static Date getAfterDay(int day) {
//        DateTime.now().dayOfYear().addToCopy(day)
        return DateTime.now().plusDays(day).toDate();
    }

    /**
     * 当前时间前多少天
     *
     * @return
     */
    public static Date getFrontDat(int day) {
//        DateTime.now().minusHours(day)
        return DateTime.now().minusDays(day).toDate();
    }

//    public static int getYear(Date oldDate,Date newDate){
//
//    }

    public static int age(Date birthDay, Date dateToCompare) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateToCompare);

        if (cal.before(birthDay)) {
            return -999;
        }

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthDay);
        int age = year - cal.get(Calendar.YEAR);

        int monthBirth = cal.get(Calendar.MONTH);
        if (month == monthBirth) {
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
            if (dayOfMonth < dayOfMonthBirth) {
                // 如果生日在当月，但是未达到生日当天的日期，年龄减一
                age--;
            }
        } else if (month < monthBirth) {
            // 如果当前月份未达到生日的月份，年龄计算减一
            age--;
        }

        return age;
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int sYear = calendar.get(Calendar.YEAR);
        return sYear;
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int ss = calendar.get(Calendar.MONTH);
        return ss;
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int ss = calendar.get(Calendar.DAY_OF_MONTH);
        return ss;
    }

    /**
     * 是否闰年
     *
     * @param year 年
     *
     * @return 是否闰年
     */
    public static boolean isLeapYear(int year) {
        return new GregorianCalendar().isLeapYear(year);
    }

    /**
     * 验证是否为生日<br>
     *
     * @param year 年
     * @param month 月
     * @param day 日
     *
     * @return 是否为生日
     */
    public static boolean isBirthday(int year, int month, int day) {
        //验证年
        int thisYear = getYear(new Date());
        if (year < 1930 || year > thisYear) {
            return false;
        }

        //验证月
        if (month < 1 || month > 12) {
            return false;
        }

        //验证日
        if (day < 1 || day > 31) {
            return false;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
            return false;
        }
        if (month == 2) {
            if (day > 29 || (day == 29 && false == isLeapYear(year))) {
                return false;
            }
        }
        return true;
    }

}
