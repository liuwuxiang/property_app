package com.springmvc.controller.app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.Users;
import com.springmvc.entity.WnkCommodities;
import com.springmvc.entity.WnkOrderCommodity;
import com.springmvc.entity.WnkOrders;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.wechat.WechatPayLineUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class WnkMyOrderAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersBankCardService usersBankCardService;
    @Resource
    private UsersService usersService;
    @Resource
    private WnkCommoditiesService wnkCommoditiesService;
    @Resource
    private WnkOrdersService wnkOrdersService;
    @Resource
    private WnkOrderCommodityService wnkOrderCommodityService;
    @Resource
    private WnkQrCodeMakeRecordService wnkQrCodeMakeRecordService;

    // 获取某用户所有的购买订单
    @RequestMapping(value = "/wnkWnkMyOrder", method = RequestMethod.POST,params = {"user_id","state"})
    @ResponseBody
    public Result wnkWnkMyOrder(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer state){
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
                    List<Map<Object,Object>> orderList = this.wnkOrdersService.userSelectUserOrder(user_id,state);
                    if (orderList.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无订单");
                    }
                    else{
                        Double count_amount = 0.00;
                        for (Integer index = 0;index < orderList.size();index++){
                            Map<Object,Object> map = orderList.get(index);
                            List<Map<Object,Object>> commodity_list = this.wnkOrderCommodityService.selectCommodityByOrderId((Integer) map.get("id"));
                            map.put("commodity_list",commodity_list);
                            map.put("commodity_count",commodity_list.size());
                            count_amount = count_amount + (Double) map.get("amount");
                        }
                        Map<Object,Object> returnMap = new HashMap<Object,Object>();
                        returnMap.put("list",orderList);
                        returnMap.put("xf_cs",orderList.size());
                        returnMap.put("count_amount",count_amount);
                        result.setData(returnMap);
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

    // 获取某用户万能卡权益订单数据
    @RequestMapping(value = "/wnkQuanyiOrder", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result wnkQuanyiOrder(HttpServletRequest request, HttpServletResponse response, Integer user_id){
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
                    List<Map<Object,Object>> orderList = this.wnkQrCodeMakeRecordService.selectByUserId(user_id);
                    if (orderList.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(orderList);
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

    // 获取购买订单详情
    @RequestMapping(value = "/wnkBuyOrderDetail", method = RequestMethod.POST,params = {"user_id","order_id"})
    @ResponseBody
    public Result wnkBuyOrderDetail(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer order_id){
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
                    List<Map<Object,Object>> list = this.wnkOrdersService.selectByIdReturnMap(order_id);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    }
                    else{
                        Map<Object,Object> map = list.get(0);
                        String qrCodePhotoId = (String)map.get("order_qrcode");
                        if (qrCodePhotoId == null || qrCodePhotoId.equals("")){
                            map.put("order_qrcode", "");
                        }
                        else{
                            map.put("order_qrcode", ImageToolsController.qrcodeShowURL+"?imageid="+qrCodePhotoId);
                        }
                        List<Map<Object,Object>> commodity_list = this.wnkOrderCommodityService.selectCommodityByOrderId(order_id);
                        map.put("commodity_list",commodity_list);
                        result.setData(map);
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
}
