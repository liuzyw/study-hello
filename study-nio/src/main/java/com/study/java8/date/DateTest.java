package com.study.java8.date;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * Created on 2018-03-19
 *
 * @author liuzhaoyuan
 */
public class DateTest {

    public static void main(String[] args) {
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();

        LocalDate localDate = LocalDate.now();

        aaa();
        System.out.println("-----------------");
        bbb();
        System.out.println("-----------------");
        ccc();
    }

    private static void aaa() {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);

        LocalDateTime dateTime1 = LocalDateTime.of(2017, 9, 3, 12, 34, 43);
        System.out.println(dateTime1);

        System.out.println(dateTime.plusYears(2));

        System.out.println(dateTime.minusYears(3));

        System.out.println(dateTime.getDayOfYear());

        System.out.println(dateTime.getDayOfMonth());

        System.out.println(dateTime.getYear());

    }

    private static void bbb() {
        // 默认 UTC 的时间
        Instant instant = Instant.now();
        LocalDateTime dateTime1 = LocalDateTime.of(2012, 3, 13, 12, 34, 43);
        LocalDateTime dateTime = LocalDateTime.now();
        Duration duration = Duration.between(dateTime1, dateTime);
        System.out.println("day: " + duration.toDays());

        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2012, 3, 13);
        Period period = Period.between(localDate1, localDate);
        System.out.println("day: " + period.getDays());

        System.out.println(instant);
        // 毫秒
        System.out.println(instant.toEpochMilli());
        System.out.println(System.currentTimeMillis());

        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));

        System.out.println(offsetDateTime);

    }

    private static void ccc() {
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime dateTime1 = dateTime.withDayOfMonth(2);
        System.out.println(dateTime1);

        System.out.println(dateTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        System.out.println(formatter.format(dateTime));
    }
}
