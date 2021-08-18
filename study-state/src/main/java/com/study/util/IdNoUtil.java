package com.study.util;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class IdNoUtil {
    private IdNoUtil() {
    }

    private static SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMddHHmmss");


    public static String getStateOrderId() {
        return "SOID" + timeFormatter.format(new Date());
    }


}
