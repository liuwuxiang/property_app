package com.springmvc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Cookie工具类
 */
public class CookieUtil {
    /**
     * 将cookie封装到Map里面
     * @param request HttpServletRequest
     * @return 返回封装号的map
     */
    public static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 获取cookie值(语言类型的cookie名为:lanage(value=0:))
     * @param request HttpServletRequest
     * @param name cookie名称
     * */
    public static String getCookieValue(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = CookieUtil.ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie.getValue();
        }else{
            if (name.equals("lanage")){
                return "0";
            }
            return null;
        }
    }

    /**
     * 修改cookie
     * @param request   HttpServletRequest
     * @param response  HttpServletResponse
     * @param name  cookie名称
     * @param value cookie值
     * */
    public static void setCookieValue(HttpServletRequest request,HttpServletResponse response,String name,String value){
        Map<String,Cookie> cookieMap = CookieUtil.ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            cookie.setValue(value);
            cookie.setPath("/");
            cookie.setMaxAge(30 * 60);// 设置为30min
            System.out.println("被修改的cookie名字为:"+cookie.getName()+",新值为:"+cookie.getValue());
            response.addCookie(cookie);
        }else{
            Cookie cookie = new Cookie(name, value);
            cookie.setMaxAge(30 * 60);// 设置为30min
            cookie.setPath("/");
            System.out.println("已添加===============");
            response.addCookie(cookie);
        }
    }
}
