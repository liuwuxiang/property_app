package com.springmvc.utils;


import java.util.Random;

/**
 * 随机数工具类
 */
public class RandomUtil {

    /**
     * 生成10位随机整数
     * @return 返回10位随机整数
     */
    public static String getRandomNumber() {
        StringBuilder randomNumber = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            randomNumber.append(r.nextInt());
        }
        return randomNumber.toString();
    }
}
