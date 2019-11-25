package com.xqh.severshiro.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类 . <br>
 * 
 * @author hkb <br>
 */
public final class DateUtils {

    /** 线程安全的日期格式对象-包含时分秒 */
    private static final ThreadLocal<DateFormat> DATE_FORMAT = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            // 完整日期
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

    };

    /** 线程安全的日期格式对象-年月日 */
    private static final ThreadLocal<DateFormat> YMD = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            // 年月日
            return new SimpleDateFormat("yyyy-MM-dd");
        }

    };

    /**
     * 私有构造函数
     */
    private DateUtils() {
        super();
    }

    /**
     * 当前日期与传入比较
     * @param date
     * @return 小于-1，相等 0 ，大于 1
     */
    public static int compareDate(Date date){
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        Calendar calendar = Calendar.getInstance();
        Date s = calendar.getTime();
        long diff = date.getTime() - s.getTime();
        //long min = diff % nd % nh / nm;
        long min = diff / nm;
        return (min < 0)? -1 : (min == 0 ? 0 : 1);
    }

    /**
     * 格式化完整日期
     * 
     * @param date
     * @return yyyy-MM-dd HH:mm:ss格式的字符串
     */
    public static String formatDate(Date date) {
        return DATE_FORMAT.get().format(date);
    }

    /**
     * 格式化年月日
     * 
     * @param date
     * @return yyyy-MM-dd格式的字符串
     */
    public static String formatYMD(Date date) {
        return YMD.get().format(date);
    }

    /**
     * 获取指定时间的毫秒数
     * @param dateStr
     * @return
     */
    public static Long strToLong(String dateStr) throws ParseException {
        Date date = DATE_FORMAT.get().parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTime().getTime();
    }

    /**
     * 获取当前时间的毫秒数
     * @return
     */
    public static Long getLocalTime(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime().getTime();
    }
    /**
     * 获取指定日期的0点的字符串
     * 
     * @param date
     * @return
     */
    public static String getZeroPointStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return formatDate(calendar.getTime());
    }

    /**
     * 获取指定日期的末点的字符串
     * 
     * @param date
     * @return
     */
    public static String getLastPointStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return formatDate(calendar.getTime());
    }

    /**
     * 获取指定日期的0点的毫秒数
     * 
     * @param date
     * @return
     */
    public static long getZeroPointMillisecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    /**
     * 获取指定日期的末点的毫秒数
     * 
     * @param date
     * @return
     */
    public static long getLastPointMillisecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime().getTime();
    }

}
