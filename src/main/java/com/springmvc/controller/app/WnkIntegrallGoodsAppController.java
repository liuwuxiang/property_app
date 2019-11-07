package com.springmvc.controller.app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.Users;
import com.springmvc.entity.WnkIntegralGoods;
import com.springmvc.entity.WnkIntegralOrder;
import com.springmvc.entity.WnkIntegralType;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/31 00:04
 * @Description:
 */
@Controller
@RequestMapping(value = "/app/v1.0.0")
public class WnkIntegrallGoodsAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersService usersService;
    @Resource
    private WnkIntegralTypeServer wnkIntegralTypeServer;
    @Resource
    private WnkIntegralGoodsServer wnkIntegralGoodsServer;
    @Resource
    private WnkIntegralOrderServer wnkIntegralOrderServer;
    @Resource
    private WnkIntegralIncomeService wnkIntegralIncomeService;
    @Resource
    private WnkSendIntegralDetailUserService wnkSendIntegralDetailUserService;

    // 获取万能卡商家积分商品分类
    @RequestMapping(value = "/getWnkBusinessGoodsTypes", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getWnkBusinessGoodsTypes(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    List<WnkIntegralType> list = this.wnkIntegralTypeServer.getAllIntegralType();
                    for (Integer index = 0;index < list.size();index++){
                        WnkIntegralType wnkIntegralType = list.get(index);
                        wnkIntegralType.setImg(ImageToolsController.imageShowURL+"?imageid="+wnkIntegralType.getImg());
                    }
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    // 获取万能卡推荐积分商品
    @RequestMapping(value = "/getWnkBusinessRecommendGoods", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getWnkBusinessRecommendGoods(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    List<Map<String,Object>> list = this.wnkIntegralGoodsServer.getRecommednGoods();
                    for (Integer index = 0;index < list.size();index++){
                        Map<String,Object> map = list.get(index);
                        map.put("img",ImageToolsController.imageShowURL+"?imageid="+map.get("img"));
                    }
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    // 获取万能卡积分商品分类下的商品
    @RequestMapping(value = "/getWnkBusinessIntegralGoodsByTypeId", method = RequestMethod.POST,params = {"user_id","type_id"})
    @ResponseBody
    public Result getWnkBusinessIntegralGoodsByTypeId(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer type_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    List<WnkIntegralGoods> list = this.wnkIntegralGoodsServer.getGoodsByTypeId(type_id);
                    for (Integer index = 0;index < list.size();index++){
                        WnkIntegralGoods wnkIntegralGoods = list.get(index);
                        wnkIntegralGoods.setImg(ImageToolsController.imageShowURL+"?imageid="+wnkIntegralGoods.getImg());
                    }
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    // 获取用户商家积分商城订单
    @RequestMapping(value = "/getWnkBusinessIntegralMallOrders", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getWnkBusinessIntegralMallOrders(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    List<Map<String,Object>> list = this.wnkIntegralOrderServer.getAllIntegralWnkOrderByUserId(user_id);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg(ImageToolsController.imageShowURL + "?imageid=");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 获取用户在商家处的积分明细
     *
     * @param business_id 商家ID
     * @param user_id 用户ID
     * @param income_type 收支类型(0-收入 1-支出)
     * @return:
     * @author: zhangfan
     * @date: 2018/10/31 4:42 AM
     */
    @RequestMapping(value = "/getUserBusinessIntegralDetailByUserIdAndBusinessId", method = RequestMethod.POST,params = {"user_id","business_id","income_type"})
    @ResponseBody
    public Result getUserBusinessIntegralDetailByUserIdAndBusinessId(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer business_id,Integer income_type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (income_type != 0 && income_type != 1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    List<Map<String,Object>> list = this.wnkIntegralIncomeService.getIntegralCountByIdAndType(business_id, user_id, income_type);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg(ImageToolsController.imageShowURL + "?imageid=");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    /**
     *
     * 功能描述: 获取用户在商家处的赠送积分明细
     *
     * @param business_id 商家ID
     * @param user_id 用户ID
     * @param income_type 收支类型(0-收入 1-支出)
     * @return:
     * @author: zhangfan
     * @date: 2018/10/31 4:42 AM
     */
    @RequestMapping(value = "/getUserBusinessSendIntegralDetailByUserIdAndBusinessId", method = RequestMethod.POST,params = {"user_id","business_id","income_type"})
    @ResponseBody
    public Result getUserBusinessSendIntegralDetailByUserIdAndBusinessId(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer business_id,Integer income_type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (income_type != 0 && income_type != 1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    List<Map<String,Object>> list = this.wnkSendIntegralDetailUserService.getIntegralCountByIdAndType(business_id, user_id, income_type);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }
}
