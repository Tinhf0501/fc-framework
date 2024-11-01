package com.fcm.common.utils;

import lombok.experimental.UtilityClass;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@UtilityClass
public class CommonUtils {
    public static Date getDateWith00h00(Date dateInput) {
        if (Objects.isNull(dateInput))
            return null;
        Calendar cal = Calendar.getInstance();
//        cal.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        cal.setTime(dateInput);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        // Bo sung
        cal.set(Calendar.MILLISECOND, 000);
        return cal.getTime();
    }

    public static Date getDateWith23h59(Date dateInput) {
        if (Objects.isNull(dateInput))
            return null;
        Calendar cal = Calendar.getInstance();
//        cal.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        cal.setTime(dateInput);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        // Bo sung
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }
}

