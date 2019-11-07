package com.springmvc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 身份证工具类
 */
public class IDCardNumberUtil {

    /**
     * 验证身份证号码是否正确
     * @param idCardNumber 需要验证的身份证号
     * @return 正确返回出生年月,不正确返回null
     */
    public static Date checkIdCardNumber(String idCardNumber){
        if (idCardNumber.length() != 18){
            return null;
        }
        else{
            String year = idCardNumber.substring(6, 10);
            String month = idCardNumber.substring(10, 12);
            String day = idCardNumber.substring(12, 14);
            if (year.length() != 4){
                return null;
            }
            else if (Integer.parseInt(year) <= 1918){
                return null;
            }
            else if (Integer.parseInt(month) > 12 || Integer.parseInt(month) < 1){
                return null;
            }
            else if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31){
                return null;
            }
            else{
                String birthdayStr = year+"-"+month+"-"+day;
                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr);
                    return date;
                } catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    /**
     * 计算当前用户年龄
     * @param birthdayDate 生日
     * @return 返回用户年龄
     */
    public static Integer calculationUserAge(Date birthdayDate){
        String[] currentDateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString().split("-");
        Integer currentYear = Integer.parseInt(currentDateStr[0]);
        Integer currentMonth = Integer.parseInt(currentDateStr[1]);
        Integer currentDay = Integer.parseInt(currentDateStr[2]);

        String[] birthdayStr = new SimpleDateFormat("yyyy-MM-dd").format(birthdayDate).toString().split("-");
        Integer birthdayYear = Integer.parseInt(birthdayStr[0]);
        Integer birthdayMonth = Integer.parseInt(birthdayStr[1]);
        Integer birthdayDay = Integer.parseInt(birthdayStr[2]);

        Integer age = currentYear - birthdayYear;
        if (currentMonth < birthdayMonth){
            age--;
        }
        else if (currentDay < birthdayDay && currentMonth == birthdayMonth){
            age--;
        }

        return age;

    }
}
