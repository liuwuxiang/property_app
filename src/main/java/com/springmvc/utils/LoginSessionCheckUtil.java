package com.springmvc.utils;

import com.springmvc.entity.LoginSessionIds;
import com.springmvc.service.impl.LoginSessionIdsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @brief Session验证机制组建类
 * @Auth 张凡
 * */

public class LoginSessionCheckUtil {
    /**登录有效时长(以分为单位)*/
    private final static long effectiveTimeLength = 1440;

//    /**
//     * @brief 保存登录session
//     * @Return
//     * @Param userId：用户id
//     *        type：类型(0-后台,1-用户端app或用户端微信版)
//     *        request：可通过request获取到sessionId
//     *        sessionIdsService：session服务类
//     * @Auth 张凡
//     * */
    /**
     * 保存登录session
     * @param userId  用户id
     * @param type    类型(0-后台,1-用户端app或用户端微信版)
     * @param request 可通过request获取到sessionId
     * @param sessionIdsService session服务类
     * @return
     */
    public static String saveSessionId(Integer userId, Integer type, HttpServletRequest request, LoginSessionIdsService sessionIdsService){
        if (type == 1 || type == 2){
            type = 2;
        }
        String sessionId = request.getSession().getId();
        if (sessionId == null || sessionId.equals("")){
            sessionId = UUDUtil.getOrderIdByUUId();
        }
        else{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(new Date());
            dateString = dateString.replaceAll(" ","-");
            sessionId = sessionId+dateString;
        }
        LoginSessionIds loginSessionIds = sessionIdsService.selectByUserIdAndType(userId,type);
        if (loginSessionIds == null){
            loginSessionIds = new LoginSessionIds();
            loginSessionIds.setSession_id(sessionId);
            loginSessionIds.setUser_id(userId);
            loginSessionIds.setType(type);
            loginSessionIds.setLogin_date(new Date());
            sessionIdsService.addSessionIdRecord(loginSessionIds);
        }
        else{
            loginSessionIds.setSession_id(sessionId);
            loginSessionIds.setLogin_date(new Date());
            sessionIdsService.updateSessionIdByUserId(loginSessionIds);
        }

        return sessionId;

    }

//    /**
//     * @brief 验证是否已经登录
//     * @Return 返回null为未登录，若不为空返回的则为用户id字符串
//     * @Param userId：当前验证的用户id
//     *        type：类型(0-后台,1-用户端app或用户端微信版)
//     *        request：可通过request获取到sessionId
//     *        sessionIdsService：session服务类
//     * @Auth 张凡
//     * */

    /**
     * 验证是否已经登录
     * @param userId   当前验证的用户id
     * @param type     类型(0-后台,1-用户端app或用户端微信版)
     * @param request  可通过request获取到sessionId
     * @param response
     * @param sessionIdsService session服务类
     * @return 返回null为未登录，若不为空返回的则为用户id字符串
     */
    public static String checkIsLogin(Integer userId, Integer type, HttpServletRequest request, HttpServletResponse response, LoginSessionIdsService sessionIdsService){
        if (type == 1 || type == 2){
            type = 2;
        }
        String sessionId = request.getHeader("login_session");
        LoginSessionIds loginSessionIds = sessionIdsService.selectBySessionId(sessionId);
        if (loginSessionIds == null){
            CookileUtil  cookileUtil = new CookileUtil();
            String user_id_str = cookileUtil.getCookie_user_id(request,type);
            if (user_id_str == null){
                if (userId == null){
                    return null;
                }
                else{
                    cookileUtil.clearCookie(type,userId.toString(),response);
                    return null;
                }

            }
            else{
                return user_id_str;
            }
        }
        else if (loginSessionIds.getUser_id() == userId && loginSessionIds.getType() == type){
            Date nowDate = new Date();
            Date sessionDate = loginSessionIds.getLogin_date();
            long between=(nowDate.getTime()-sessionDate.getTime())/1000;//除以1000是为了转换成秒
            long min=between/60;
            if (min >= effectiveTimeLength){
                return null;
            }
            return userId.toString();
        }
        else if (userId == null && loginSessionIds.getType() == type){
            Date nowDate = new Date();
            Date sessionDate = loginSessionIds.getLogin_date();
            long between=(nowDate.getTime()-sessionDate.getTime())/1000;//除以1000是为了转换成秒
            long min=between/60;
            if (min >= effectiveTimeLength){
                return null;
            }
            return loginSessionIds.getUser_id().toString();
        }
        else{
            return null;
        }
    }


//    /**
//     * @brief 微信端网页验证是否已经登录
//     * @Return 返回null为未登录，若不为空返回的则为用户id字符串
//     * @Param userId：当前验证的用户id
//     *        type：类型(0-后台,1-用户端app或用户端微信版)
//     *        request：可通过request获取到sessionId
//     *        sessionIdsService：session服务类
//     * @Auth 张凡
//     * */

    /**
     * 微信端网页验证是否已经登录
     * @param userId  当前验证的用户id
     * @param type    类型(0-后台,1-用户端app或用户端微信版)
     * @param sessionIdsService session服务类
     * @return 返回null为未登录，若不为空返回的则为用户id字符串
     */
    public static String wxPageCheckIsLogin(Integer userId, Integer type, LoginSessionIdsService sessionIdsService){
        if (type == 1 || type == 2){
            type = 2;
        }
        LoginSessionIds loginSessionIds = sessionIdsService.selectByUserIdAndType(userId,type);
        if (loginSessionIds == null){
            return null;
        }
        else{
            return loginSessionIds.getUser_id().toString();
        }
    }


//    /**
//     * @brief 微信端网页获取用户session
//     * @Return 返回null为未登录，若不为空返回的则为用户id字符串
//     * @Param userId：当前验证的用户id
//     *        type：类型(0-后台,1-用户端app或用户端微信版)
//     *        request：可通过request获取到sessionId
//     *        sessionIdsService：session服务类
//     * @Auth 张凡
//     * */

    /**
     * 微信端网页获取用户session
     * @param userId 当前验证的用户id
     * @param type   类型(0-后台,1-用户端app或用户端微信版)
     * @param sessionIdsService session服务类
     * @return 返回null为未登录，若不为空返回的则为用户id字符串
     */
    public static String wxPageGetUserLoginSession(Integer userId, Integer type, LoginSessionIdsService sessionIdsService){
        if (type == 1 || type == 2){
            type = 2;
        }
        LoginSessionIds loginSessionIds = sessionIdsService.selectByUserIdAndType(userId,type);
        if (loginSessionIds == null){
            return null;
        }
        else{
            return loginSessionIds.getSession_id();
        }
    }

//    /**
//     * @brief 退出登录(删除sessionId)
//     * @Return 无返回值
//     * @Param userId：当前验证的用户id
//     *        type：类型(0-后台,1-用户端app或用户端微信版)
//     *        request：可通过request获取到sessionId
//     *        sessionIdsService：session服务类
//     * @Auth 张凡
//     * */

    /**
     * 退出登录(删除sessionId)
     * @param userId  当前验证的用户id
     * @param type    类型(0-后台,1-用户端app或用户端微信版)
     * @param request 可通过request获取到sessionId
     * @param sessionIdsService session服务类
     */
    public static void exitLogin(Integer userId, Integer type, HttpServletRequest request, LoginSessionIdsService sessionIdsService){
        if (type == 1 || type == 2){
            type = 2;
        }
        String sessionId = request.getHeader("login_session");
        sessionIdsService.deleteRecordBySessionId(sessionId);
    }
}
