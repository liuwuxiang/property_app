package com.springmvc.controller.wx.integral_wnk;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.service.impl.WnkIntegralTypeServer;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 万能卡商家积分商城 - 分类操作控制器
 * @author 杨新杰
 * @Date 2018/10/12 09:41
 */
@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class WnkUserTypeController {

    @Resource
    private WnkIntegralTypeServer wnkIntegralTypeServer;

    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersService usersService;


    /**
     *
     * 功能描述: 获取商家分类信息
     *
     * @param  request     HttpServletRequest服务类
     * @param  business_id 商家ID
     * @param  user_id     用户ID
     * @return 返回此商家的分类信息
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/getIntegralTypeByTrue",method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralTypeByTrue(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer user_id) {
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
                    List<Map<String, Object>> map = wnkIntegralTypeServer.getIntegralTypeByTrue(business_id);
                    if (map.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无分类");
                    }
                    else{
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg(ImageToolsController.imageShowURL + "?imageid=");
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
     *
     * 功能描述: 获取商家分类对应的商品信息
     *
     * @param  request     HttpServletRequest服务类
     * @param  business_id 商家ID
     * @param  user_id     用户ID
     * @param  type_id     分类ID
     * @return 返回此商家的分类信息
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/getGoodsByTypeIdAndWnk",method = RequestMethod.POST)
    @ResponseBody
    public Result getGoodsByTypeIdAndWnk(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer user_id,Integer type_id) {
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
                    List<Map<String,Object>> list = wnkIntegralTypeServer.getGoodsByTypeIdAndWnk(business_id,type_id);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无商品");
                    }
                    else{
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg(ImageToolsController.imageShowURL+"?imageid=");
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



}
