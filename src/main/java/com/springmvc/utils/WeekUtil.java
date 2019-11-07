package com.springmvc.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class WeekUtil {

    /**
     * 获取当前日期是星期几
     *
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate() {
        Date dt = new Date();
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 判断时间是否在时间段内
     * @param time System.currentTimeMillis()
     * @param strDateBegin 开始时间 00:00:00
     * @param strDateEnd 结束时间 00:05:00
     * @return 返回true表示在此时间段,返回false表示不在此时间段
     */
    public static boolean isInDate(long time, String strDateBegin, String strDateEnd) {
        Calendar calendar = Calendar.getInstance();
        // 处理开始时间
        String[] startTime = strDateBegin.split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(startTime[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(startTime[1]));
        calendar.set(Calendar.SECOND, Integer.valueOf(startTime[2]));
        calendar.set(Calendar.MILLISECOND, 0);
        long startTimeL = calendar.getTimeInMillis();
        // 处理结束时间
        String[] endTime = strDateEnd.split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(endTime[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(endTime[1]));
        calendar.set(Calendar.SECOND, Integer.valueOf(endTime[2]));
        calendar.set(Calendar.MILLISECOND, 0);
        long endTimeL = calendar.getTimeInMillis();

        return time >= startTimeL && time <= endTimeL;
    }
}
