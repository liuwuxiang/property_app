package com.springmvc.controller.wx.integral_wnk;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.*;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 万能卡商家积分商城 - 用户操作控制器
 *
 * @author 杨新杰
 * @Date 2018/10/12 10:50
 */
@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class WnkUserController {

    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersService usersService;

    @Resource
    private WnkIntegralUserServer wnkIntegralUserServer;

    @Resource
    private WnkIntegralIncomeService wnkIntegralIncomeService;

    @Resource
    private WnkSendIntegralUserService wnkSendIntegralUserService;




    /**
     * 功能描述: 根据用户ID获取在此商家的赠送积分信息
     *
     * @param request     HttpServletRequest服务类
     * @param business_id 商家ID
     * @param user_id     用户ID
     * @return 返回此用户在此商家的积分信息, 如果是第一次进入此商家 则会创建对应积分记录
     * @author 张凡
     * @date 2018/10/12 9:56
     */
    @RequestMapping(value = "/getUserSendIntegral", method = RequestMethod.POST)
    @ResponseBody
    public Result getUserSendIntegral(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer user_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else {
                    // 业务开始
                    Map<String, Object> map = wnkSendIntegralUserService.getUserIntegral(business_id, user_id);
                    if (map == null) {
                        if (wnkSendIntegralUserService.addUserIntegral(business_id, user_id) > 0) {
                            map = wnkSendIntegralUserService.getUserIntegral(business_id, user_id);
                            result.setData(map);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("获取成功");
                        }
                    } else {
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }


                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    /**
     * 功能描述: 根据用户ID获取在此商家的积分信息
     *
     * @param request     HttpServletRequest服务类
     * @param business_id 商家ID
     * @param user_id     用户ID
     * @return 返回此用户在此商家的积分信息, 如果是第一次进入此商家 则会创建对应积分记录
     * @author 杨新杰
     * @date 2018/10/12 9:56
     */
    @RequestMapping(value = "/getUserIntegral", method = RequestMethod.POST)
    @ResponseBody
    public Result getUserIntegral(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer user_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else {
                    // 业务开始
                    Map<String, Object> map = wnkIntegralUserServer.getUserIntegral(business_id, user_id);
                    if (map == null) {
                        if (wnkIntegralUserServer.addUserIntegral(business_id, user_id) > 0) {
                            map = wnkIntegralUserServer.getUserIntegral(business_id, user_id);
                            result.setData(map);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("获取成功");
                        }
                    } else {
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }


                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    /**
     * 功能描述: 获取积分明细列表
     *
     * @param request     HttpServletRequest服务类
     * @param business_id 商家ID
     * @param user_id     用户ID
     * @return 返回此用户在此商家的积分信息, 如果是第一次进入此商家 则会创建对应积分记录
     * @author 杨新杰
     * @date 2018/10/12 9:56
     */
    @RequestMapping(value = "/getIntegralCountByWnk", method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralCountByWnk(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer user_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else {
                    // 业务开始
                    List<Map<String, Object>> list = wnkIntegralIncomeService.getIntegralCountById(business_id, user_id);
                    if (list.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("获取失败");
                    } else {
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }
                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    /**
     * 功能描述: 查询所有商家和用户再次商家拥有的积分数量
     *
     * @param request     HttpServletRequest服务类
     * @param user_id     用户ID
     * @return 返回此用户在此商家的积分信息, 如果是第一次进入此商家 则会创建对应积分记录
     * @author 杨新杰
     * @date 2018/10/12 9:56
     */
    @RequestMapping(value = "/selectIntegralBusinessAll", method = RequestMethod.POST)
    @ResponseBody
    public Result selectIntegralBusinessAll(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer sort_list) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else {
                    // 业务开始
                    List<Map<String, Object>> list = this.wnkIntegralUserServer.selectIntegralBusinessAll(user_id, sort_list);
                    if (list.size() > 0 ){
                        for (Map<String, Object> map:list){
                            String cover_photo = (String) map.get("cover_photo");
                            if (cover_photo == null){
                                map.put("cover_photo",ImageToolsController.imageShowURL+"?imageid=logo.jpg");
                            } else {
                                map.put("cover_photo",ImageToolsController.imageShowURL+"?imageid="+cover_photo);
                            }
                        }
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    } else {
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("暂无数据");
                    }
                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    /**
     * 功能描述: 查询所有商家和用户再次商家拥有的积分数量
     *
     * @param request     HttpServletRequest服务类
     * @param user_id     用户ID
     * @return 返回此用户在此商家的积分信息, 如果是第一次进入此商家 则会创建对应积分记录
     * @author 杨新杰
     * @date 2018/10/12 9:56
     */
    @RequestMapping(value = "/selectIntegralBusinessInfoByBusinessId", method = RequestMethod.POST)
    @ResponseBody
    public Result selectIntegralBusinessInfoByBusinessId(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer business_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else {
                    // 业务开始
                    Map<String, Object> map = this.wnkIntegralUserServer.selectIntegralBusinessInfoByBusinessId(business_id, user_id);
                    if (map != null){
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("查询失败");
                    }
                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }



    /**
     * 功能描述: 查询所有商家和用户再次商家拥有的积分数量
     *
     * @param request     HttpServletRequest服务类
     * @param user_id     用户ID
     * @return 返回此用户在此商家的积分信息, 如果是第一次进入此商家 则会创建对应积分记录
     * @author 杨新杰
     * @date 2018/10/12 9:56
     */
    @RequestMapping(value = "/selectIntegralBusinessGoodsByBusinessId", method = RequestMethod.POST)
    @ResponseBody
    public Result selectIntegralBusinessGoodsByBusinessId(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer business_id, Integer type_sort) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else {
                    // 业务开始
                    List<Map<String, Object>> map = this.wnkIntegralUserServer.selectIntegralBusinessGoodsByBusinessId(business_id, type_sort);
                    if (map.size() > 0){
                        for (Map<String, Object> m : map){
                            m.put("img",ImageToolsController.imageShowURL + "?imageid=" + m.get("img"));
                        }
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("查询失败");
                    }
                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }




}
