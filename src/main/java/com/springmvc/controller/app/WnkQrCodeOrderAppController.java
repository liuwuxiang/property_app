package com.springmvc.controller.app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.qrCode.QRCode;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class WnkQrCodeOrderAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkQrCodeMakeRecordService wnkQrCodeMakeRecordService;
    @Resource
    private UserOpenCardsService userOpenCardsService;
    @Resource
    private WnkBuyMealService wnkBuyMealService;
    @Resource
    private WnkCommoditiesService wnkCommoditiesService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkCommoditySpecificationsService wnkCommoditySpecificationsService;

    // 万能卡商品万能卡权益下单
    @RequestMapping(value = "/wnkCommodityQYLineOrder", method = RequestMethod.POST,params = {"user_id","commodity_id","guige_id","commodity_number"})
    @ResponseBody
    public Result wnkCommodityQYLineOrder(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer commodity_id,Integer guige_id,Integer commodity_number){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
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
                    Integer month_free_number = 0;
                    if (users.getMember_card_level() != -1){
                        UserOpenCards userOpenCards = this.userOpenCardsService.selectByUserId(user_id);
                        if (userOpenCards != null){
                            WnkBuyMeal wnkBuyMeal = this.wnkBuyMealService.selectById(userOpenCards.getCard_type_id());
                            if (wnkBuyMeal != null){
                                month_free_number = wnkBuyMeal.getWnk_make_number();
                            }
                        }
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂未开通万能卡");

                        return result;
                    }
                    Integer make_number = this.wnkQrCodeMakeRecordService.selectCurrentMakeNumber(user_id,1);
                    if (make_number >= month_free_number && month_free_number != -1){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("当年使用次数已达到上限");
                    }
                    else if (month_free_number - make_number < commodity_number && month_free_number != -1){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("次数余额不足");
                    }
                    else{
                        WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkCommodities.getBusiness_id());
                        WnkCommoditySpecifications wnkCommoditySpecifications = this.wnkCommoditySpecificationsService.selectById(guige_id);
                        if (wnkCommodities == null || wnkCommodities.getState() != 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商品不存在或已下架");
                        }
                        else if (wnkStoreInformation == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商户不存在");
                        }
                        else if (wnkCommoditySpecifications == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商品规格不存在");
                        }
                        else{
                            WnkQrCodeMakeRecord wnkQrCodeMakeRecord = new WnkQrCodeMakeRecord();
                            wnkQrCodeMakeRecord.setBusiness_id(wnkCommodities.getBusiness_id());
                            wnkQrCodeMakeRecord.setUser_id(user_id);
                            wnkQrCodeMakeRecord.setBusiness_type_id(wnkStoreInformation.getBusiness_type_id());
                            wnkQrCodeMakeRecord.setOrder_no(UUDUtil.getOrderIdByUUId());
                            wnkQrCodeMakeRecord.setMake_number(commodity_number);
                            wnkQrCodeMakeRecord.setCommodity_id(wnkCommodities.getId());
                            wnkQrCodeMakeRecord.setLine_order_date(new Date());
                            wnkQrCodeMakeRecord.setState(0);
                            wnkQrCodeMakeRecord.setGuige_id(guige_id);
                            //订单二维码
                            Map<Object,Object> orderQrcodeMap = new HashMap<Object,Object>();
                            orderQrcodeMap.put("user_id",wnkQrCodeMakeRecord.getUser_id());
                            orderQrcodeMap.put("type",1);
                            orderQrcodeMap.put("order_no",wnkQrCodeMakeRecord.getOrder_no());
                            JSONObject json = JSONObject.fromObject(orderQrcodeMap);
                            String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                            String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(),wnkQrcodeName);
                            wnkQrCodeMakeRecord.setOrder_qrcode(wnkQrcodeName+".png");

                            int addState = this.wnkQrCodeMakeRecordService.insertMakeRecord(wnkQrCodeMakeRecord);
                            if (addState <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("支付失败");
                            }
                            else{
                                Map<Object,Object> map = new HashMap<Object,Object>();
                                map.put("order_id",wnkQrCodeMakeRecord.getId());
                                result.setData(map);
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("支付成功");
                            }
                        }
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


    // 获取万能卡订单详情
    @RequestMapping(value = "/getWnkOrderDetail", method = RequestMethod.POST,params = {"user_id","order_id"})
    @ResponseBody
    public Result getWnkOrderDetail(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer order_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
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
}
