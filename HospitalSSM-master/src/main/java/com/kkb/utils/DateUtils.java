package com.kkb.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mj
 * @create 2021-03-23 14:42
 * @describe:
 */
public class DateUtils {

    public static String dateToStr(String rule, Date date){
        SimpleDateFormat format = new SimpleDateFormat(rule);
        return format.format(date);
    }

    public static String dateToStr( Date date){
        if (date==null){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String dateToStrMiao( Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
