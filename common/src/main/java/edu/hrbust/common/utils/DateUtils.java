package edu.hrbust.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date parse2Date(String patten, String date) {
        DateFormat format = new SimpleDateFormat(patten);
        Date d1 = null;
        try {
            d1 = format.parse(date);
        } catch (Exception exp) {
        }
        return d1;
    }

    public static String parse2String(String patten, Date date) {
        DateFormat format = new SimpleDateFormat(patten);
        return format.format(date);
    }
}
