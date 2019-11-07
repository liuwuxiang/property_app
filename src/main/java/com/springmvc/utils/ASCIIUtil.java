package com.springmvc.utils;


import java.util.*;

public class ASCIIUtil {
    /**
     *
     * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param map   要排序的Map对象
     * @return 获取排序后的字符串
     */
    public static String formatUrlMap(Map<String, Object> map)
    {
        StringBuffer resultDate = new StringBuffer();
        Map<String, Object> result = new TreeMap<String, Object>(new Comparator<String>() {

            @Override
            public int compare(String key, String afterkey) {
                return key.compareTo(afterkey);
            }
        });
        for (String key : map.keySet()) {
            result.put(key, map.get(key).toString());
        }

        for (String key : result.keySet()) {
            resultDate.append(key + "=" + result.get(key) + "&");
        }
        return resultDate.toString();


    }


}
