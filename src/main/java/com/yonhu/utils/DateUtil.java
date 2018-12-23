package com.yonhu.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddEEEE = "yyyy-MM-dd EEEE";


    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static String nowStr() {
        return new SimpleDateFormat(yyyyMMddHHmmss).format(new Date());
    }

    public static String nowStr(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public static String toStr(Date date) {
        return new SimpleDateFormat(yyyyMMddHHmmss).format(date);
    }

    public static String toStr(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }
}
