package com.xbb.cases.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    // 计算两个日期之间相差的天数
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (Exception e) {
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days)) + 1;
    }

    public static int daysBetween(String strSmdate, String strBdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date smdate = null;
        Date bdate = null;
        try {
            smdate = sdf.parse(strSmdate);
            bdate = sdf.parse(strBdate);
        } catch (Exception e) {}
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days)) + 1;
    }

    // 获取当前星期
    public static String getWeek(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        return sdf.format(date);
    }

    // 获取当前年份
    public static int getYear(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    // 获取当前月份
    public static int getMonth(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

}
