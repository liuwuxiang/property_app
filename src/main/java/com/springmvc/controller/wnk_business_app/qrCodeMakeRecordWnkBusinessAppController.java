package com.springmvc.controller.wnk_business_app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
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
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class qrCodeMakeRecordWnkBusinessAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkQrCodeMakeRecordService wnkQrCodeMakeRecordService;
    @Resource
    private UsersService usersService;
    @Resource
    private WnkOrdersService wnkOrdersService;
    @Resource
    private WnkOrderCommodityService wnkOrderCommodityService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkCommoditiesService wnkCommoditiesService;
    @Resource
    private WnkCommoditySpecificationsService wnkCommoditySpecificationsService;

    // 获取权益使用订单
    @RequestMapping(value = "/getAllWnkQyOrder", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result getAllWnkQyOrder(HttpServletRequest request, HttpServletResponse response, Integer business_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    List<Map<Object,Object>> list = this.wnkQrCodeMakeRecordService.selectByBusinessId(business_id);
                    List<Map<Object,Object>> user_number = this.wnkQrCodeMakeRecordService.selectUserNumberByBusinessId(business_id);
                    Integer make_number = this.wnkQrCodeMakeRecordService.selectCurrentMakeNumberReturnListByBusinessId(business_id);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        for (Integer index = 0;index < list.size();index++){
                            Map<Object,Object> map = list.get(index);
                            Users users = this.usersService.selectById((Integer) map.get("user_id"));
                            WnkCommoditySpecifications wnkCommoditySpecifications = this.wnkCommoditySpecificationsService.selectById((Integer) map.get("guige_id"));
                            if (users != null){
                                map.put("user_name",users.getNick_name());
                                if (users.getSex() == 0){
                                    map.put("sex","男");
                                }
                                else if (users.getSex() == 1){
                                    map.put("sex","女");
                                }
                                else{
                                    map.put("sex","保密");
                                }
                                if (users.getMember_card_level() == -1){
                                    map.put("member_card_level","未开通");
                                }
                                else if (users.getMember_card_level() == 0){
                                    map.put("member_card_level","银卡");
                                }
                                else{
                                    map.put("member_card_level","金卡");
                                }
                                map.put("star",users.getMember_star());
                            }
                            else{
                                map.put("user_name","");
                                map.put("sex","");
                                map.put("member_card_level","");
                                map.put("star","");
                            }
                            if (wnkCommoditySpecifications == null){
                                map.put("guige_name","");
                            }
                            else{
                                map.put("guige_name",wnkCommoditySpecifications.getSpecifications_name());
                            }
                        }
                        Map<Object,Object> returnMap = new HashMap<Object,Object>();
                        returnMap.put("list",list);
                        returnMap.put("people_number",user_number.size());
                        returnMap.put("make_number",make_number);
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


    // 获取购买订单详情
    @RequestMapping(value = "/wnkBuyOrderDetail", method = RequestMethod.POST,params = {"business_id","order_id"})
    @ResponseBody
    public Result wnkBuyOrderDetail(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer order_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商家不存在");
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


    // 获取万能卡订单详情
    @RequestMapping(value = "/getWnkOrderDetail", method = RequestMethod.POST,params = {"business_id","order_id"})
    @ResponseBody
    public Result getWnkOrderDetail(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer order_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    WnkQrCodeMakeRecord wnkQrCodeMakeRecord = this.wnkQrCodeMakeRecordService.selectById(order_id);
                    if (wnkQrCodeMakeRecord == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    }
                    else{
                        Map<Object,Object> map = new HashMap<Object,Object>();
                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkQrCodeMakeRecord.getBusiness_id());
                        if (wnkStoreInformation == null){
                            map.put("store_name","");
                        }
                        else{
                            map.put("store_name",wnkStoreInformation.getStore_name());
                        }
                        map.put("business_id",wnkQrCodeMakeRecord.getBusiness_id());
                        map.put("make_number",wnkQrCodeMakeRecord.getMake_number());
                        WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(wnkQrCodeMakeRecord.getCommodity_id());
                        if (wnkCommodities == null){
                            map.put("commodity_name","");
                        }
                        else{
                            map.put("commodity_name",wnkCommodities.getName());
                        }
                        map.put("qr_code",ImageToolsController.qrcodeShowURL+"?imageid="+wnkQrCodeMakeRecord.getOrder_qrcode());
                        map.put("order_no",wnkQrCodeMakeRecord.getOrder_no());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        map.put("line_date",sdf.format(wnkQrCodeMakeRecord.getLine_order_date()));
                        WnkCommoditySpecifications wnkCommoditySpecifications = this.wnkCommoditySpecificationsService.selectById(wnkQrCodeMakeRecord.getGuige_id());
                        if (wnkCommoditySpecifications == null){
                            map.put("guige_name","");
                        }
                        else{
                            map.put("guige_name",wnkCommoditySpecifications.getSpecifications_name());
                        }
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }

                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 权益订单使用
    @RequestMapping(value = "/wnkQYOrderMake", method = RequestMethod.POST,params = {"business_id","order_no","user_id"})
    @ResponseBody
    public Result wnkQYOrderMake(HttpServletRequest request, HttpServletResponse response, Integer business_id,String order_no,Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商家不存在");
                }
                else{
                    WnkQrCodeMakeRecord wnkQrCodeMakeRecord = this.wnkQrCodeMakeRecordService.selectByOrderNo(order_no);
                    if (wnkQrCodeMakeRecord == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    }
                    else if (wnkQrCodeMakeRecord.getBusiness_id() != business_id){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("您无权操作此订单");
                    }
                    else if (wnkQrCodeMakeRecord.getUser_id() != user_id){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("数据不一致");
                    }
                    else if (wnkQrCodeMakeRecord.getState() != 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此订单已使用");
                    }
                    else{
                        int updateState = this.wnkQrCodeMakeRecordService.updateOrderStateByOrderNo(1,order_no);
                        if (updateState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("使用失败");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("使用成功");
                        }
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
