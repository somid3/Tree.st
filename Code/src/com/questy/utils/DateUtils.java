package com.questy.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {


    public static Date minutesAgo (Integer minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, - minutes);
        return cal.getTime();
    }

    public static Date hoursAgo (Integer hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, - hours);
        return cal.getTime();
    }

    public static Date daysAgo (Integer days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, - days);
        return cal.getTime();
    }

    public static Date yearsAgo (Integer years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, - years);
        return cal.getTime();
    }
}
