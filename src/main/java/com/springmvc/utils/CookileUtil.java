package com.springmvc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 *cookile操作类
 */
public class CookileUtil {
//    /*
//     * 保存cookie(保存的为用户id,后台cookie名使用admin,前端用户app的cookie名称使用user_app,前端用户微信端cookie名使用user_wx)
//     * @params:type(0-后台,1-用户端app,2-用户端微信版)
//     * */
    /**
     *
     * 功能描述:保存cookie,保存的为用户id<br>
     *         后台cookie名使用admin<br>
     *         前端用户app的cookie名称使用user_app<br>
     *         前端用户微信端cookie名使用user_wx<br>
     * @param  response HttpServletResponse
     * @param  user_id  用户ID
     * @param  type     保存类型
     * @author 刘武祥
     * @date   2018/10/22 0022 15:52
     */
    public void saveCookie(HttpServletResponse response, String user_id, int type) {

        if (type == 1 || type == 2) {
            type = 2;
        }
        Cookie cookie;
        /// @brief 后台
        if (type == 0) {
            cookie = new Cookie("admin", user_id);
            /*设置cookie有效值为15天*/
            cookie.setMaxAge(3600 * 24 * 15);
            //设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
            cookie.setPath("/");
            response.addCookie(cookie);//加入响应
        } else if (type == 1) {
            cookie = new Cookie("user_app", user_id);
            /*设置cookie有效值为15天*/
            cookie.setMaxAge(3600 * 24 * 15);
            //设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
            cookie.setPath("/");
            response.addCookie(cookie);
        } else if (type == 2) {
            cookie = new Cookie("user_wx", user_id);
            /*设置cookie有效值为15天*/
            cookie.setMaxAge(3600 * 24 * 15);
            //设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
            cookie.setPath("/");
            response.addCookie(cookie);
        } else if (type == 3) {
            cookie = new Cookie("wnk_business_wx", user_id);
            /*设置cookie有效值为15天*/
            cookie.setMaxAge(3600 * 24 * 15);
            //设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        /// @brief 用户端app
        else {
            cookie = new Cookie("user_app", user_id);
            /*设置cookie有效值为15天*/
            cookie.setMaxAge(3600 * 24 * 15);
            //设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }

//    /**
//     * 读取coookie(后台cookie名使用admin,前端用户app的cookie名称使用user_app,前端用户微信端cookie名使用user_wx)
//     *
//     * @params:type(0-后台,1-用户端app,2-用户端微信版,3-万能卡商家)
//     * @return:用户id(null不存在此用户)
//     */
    /**
     * 读取cookie<br>
     * 后台cookie名使用admin<br>
     * 前端用户app的cookie名称使用user_app<br>
     * 前端用户微信端cookie名使用user_wx<br>
     * @param request HttpServletRequest
     * @param type    读取类型
     * @return  用户id(null不存在此用户)
     */
    public String getCookie_user_id(HttpServletRequest request, int type) {
        if (type == 1 || type == 2) {
            type = 2;
        }
        String user_id = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            /// @brief 后台
            if (type == 0) {
                if (cookie.getName().equals("admin")) {
                    user_id = cookie.getValue();
                    return user_id;
                }
            }
            /// @brief 用户端app
            else if (type == 1) {
                if (cookie.getName().equals("user_app")) {
                    user_id = cookie.getValue();
                    return user_id;
                }
            }
            /// @brief 用户微信端
            else if (type == 2) {
                if (cookie.getName().equals("user_wx")) {
                    user_id = cookie.getValue();
                    return user_id;
                }
            }
            /// @brief 万能卡商家
            else if (type == 3) {
                if (cookie.getName().equals("wnk_business_wx")) {
                    user_id = cookie.getValue();
                    return user_id;
                }
            }
            /// @brief 用户端app
            else {
                if (cookie.getName().equals("user_app")) {
                    user_id = cookie.getValue();
                    return user_id;
                }
            }
        }

        return user_id;
    }

//    /*
//     * 清除cookie
//     * @params:type(0-后台,1-前端),user_id:用户id
//     * */
    /**
     * 清除cookie
     * @param type     cookie 类型
     * @param user_id  用户id
     * @param response HttpServletResponse
     */
    public void clearCookie(int type, String user_id, HttpServletResponse response) {
        Cookie newCookie; //假如要删除名称为username的Cookie
        if (type == 0) {
            newCookie = new Cookie("admin", user_id);
        } else if (type == 1) {
            newCookie = new Cookie("user_app", user_id);
        } else if (type == 2) {
            newCookie = new Cookie("user_wx", user_id);
        } else if (type == 3) {
            newCookie = new Cookie("wnk_business_wx", user_id);
        }
        /// @brief 用户端app
        else {
            newCookie = new Cookie("user_app", user_id);
        }
        //立即删除型
        newCookie.setMaxAge(0);
        //项目所有目录均有效，这句很关键，否则不敢保证删除
        newCookie.setPath("/");
        //重新写入，将覆盖之前的
        response.addCookie(newCookie);
    }
}
