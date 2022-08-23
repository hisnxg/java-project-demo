package com.kaikeba.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    /**
     * 时间格式化操作
     */
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String format(Date date){
        return format.format(date);
    }

    /**
     *     将字符串类型的时间转换为long类型
     */
    public static long toTime(String formatString){
        try {
            return format.parse(formatString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
