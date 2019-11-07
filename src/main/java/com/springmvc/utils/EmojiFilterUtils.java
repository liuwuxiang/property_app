package com.springmvc.utils;


import org.apache.commons.lang.StringUtils;

/**
 * Emoji表情工具类
 */
public class EmojiFilterUtils {
    /**
     * 将emoji表情替换成''
     *
     * @param source
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source) {
        if(StringUtils.isNotBlank(source)){
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
        }else{
            return source;
        }
    }
}
