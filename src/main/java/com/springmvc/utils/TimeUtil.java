package com.springmvc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 时间工具类
 *
 * @author 杨新杰
 */
public class TimeUtil {

    /**
     *
     * 方法说明:去除13位时间戳的时分秒,保留年月日
     *
     * @author 杨新杰
     * @Date  2019/1/22
     * @param timeStamp 13位时间戳
     * @return 去除了时分秒的13位时间戳
     **/
    public static Long delHMS(Long timeStamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        date.setTime(timeStamp);
        String format = sdf.format(date);
        return new  Date(format).getTime();
    }

}
