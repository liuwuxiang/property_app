package com.springmvc.controller.wnk_business_app;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.*;
import com.springmvc.utils.wechat.wxScanUtil.JsSignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class OrdersWnkBusinessAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkOrdersService wnkOrdersService;
    @Resource
    private WnkOrderCommodityService wnkOrderCommodityService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private UsersService usersService;
    @Resource
    private WnkQrCodeMakeRecordService wnkQrCodeMakeRecordService;
    @Resource
    private WnkBusinessBalanceDetailService wnkBusinessBalanceDetailService;
    @Resource
    private CodesService codesService;

    // 获取订单(type=0:新订单,1-已完成)
    @RequestMapping(value = "/getAllOrder",method = RequestMethod.POST,params = {"business_id","type"})
    @ResponseBody
    public Result getAllOrder(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer type){
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
                    Integer state = type == 0?1:2;
                    List<Map<Object,Object>> orderList = this.wnkOrdersService.selectOrderByBusinessIdAndState(business_id,state);
                    List<Map<Object,Object>> userNumber = this.wnkOrdersService.selectUserNumberByBusinessId(business_id,state);
                    if (orderList.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无订单");
                    }
                    else{
                        for (Integer index = 0;index < orderList.size();index++){
                            Map<Object,Object> map = orderList.get(index);
                            List<Map<Object,Object>> commodity_list = this.wnkOrderCommodityService.selectCommodityByOrderId((Integer) map.get("id"));
                            map.put("commodity_list",commodity_list);
                            map.put("commodity_count",commodity_list.size());
                        }
                        Map<Object,Object> returnMap = new HashMap<Object,Object>();
                        returnMap.put("list",orderList);
                        returnMap.put("user_number",userNumber.size());
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


    // 调用微信扫一扫配置参数
    @RequestMapping(value = "/makeWxScanSetting", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result makeWxScanSetting(HttpServletRequest request, HttpServletResponse response, Integer business_id){
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
                    String url =  "http://m.chenlankeji.cn/property_system/wnk_business/joinOrders";
                    Map<String, String> sign = JsSignUtil.sign(url);
                    result.setData(sign);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("配置成功");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("配置失败");
        }
        return result;
    }

    // 订单使用
    @RequestMapping(value = "/orderQrcodeMake", method = RequestMethod.POST,params = {"business_id","order_no","user_id"})
    @ResponseBody
    public Result orderQrcodeMake(HttpServletRequest request, HttpServletResponse response, Integer business_id,String order_no,Integer user_id){
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
                    WnkOrders wnkOrders = this.wnkOrdersService.selectByOrderNo(order_no);
                    if (wnkOrders == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    }
                    else{
                        if (wnkOrders.getState() != 1){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            if (wnkOrders.getState() == 0){
                                result.setMsg("此订单未支付");
                            }
                            else if (wnkOrders.getState() == 2){
                                result.setMsg("此订单已被使用");
                            }
                        }
                        else if (wnkOrders.getUser_id() != user_id){
                            result.setMsg("订单与二维码不符");
                        }
                        else if (wnkOrders.getBusiness_id() != business_id){
                            result.setMsg("此订单您无权操作");
                        }
                        else{
                            int state = this.wnkOrdersService.updateOrderStateByOrderNo(2,order_no);
                            if (state <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("使用失败");
                            }
                            else{
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("使用成功");
                                if (wnkOrders.getPay_way() != 4 && wnkOrders.getPay_way()!= 5){
                                    wnkBusinessAccount.setBalance(wnkBusinessAccount.getBalance() + wnkOrders.getAmount());
                                    int updateBalance = this.wnkBusinessAccountService.updateBalance(wnkBusinessAccount.getId(),wnkBusinessAccount.getBalance());
                                    if (updateBalance > 0){
                                        WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                                        wnkBusinessBalanceDetail.setBusiness_id(business_id);
                                        wnkBusinessBalanceDetail.setName("营业收入");
                                        wnkBusinessBalanceDetail.setTransaction_amount(wnkOrders.getAmount());
                                        wnkBusinessBalanceDetail.setJoin_time(new Date());
                                        wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance());
                                        wnkBusinessBalanceDetail.setType(0);
                                        wnkBusinessBalanceDetail.setState(0);
                                        this.wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);
                                    }
                                }

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


    // 万能卡会员权益使用(通过二维码免费享受服务)
    @RequestMapping(value = "/memberQYQrCodeMake", method = RequestMethod.POST,params = {"business_id","user_id","code","make_number"})
    @ResponseBody
    public Result memberQYQrCodeMake(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer user_id,String code,Integer make_number){
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
                else if (make_number <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("使用次数不可小于等于0");
                }
                else{
                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                    if (wnkStoreInformation == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商户信息不存在");
                    }
                    else{
                        WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                        if (wnkBusinessType == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商户分类不存在");
                        }
                        else{
                            Users users = this.usersService.selectById(user_id);
                            if (users == null){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("此用户不存在");
                            }
                            else if (users.getMember_card_level() == -1){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("此用户未开通万能卡");
                            }
                            else{
                                int makeNumber = this.wnkQrCodeMakeRecordService.selectCurrentMakeNumber(user_id,wnkBusinessType.getId());
                                if (makeNumber >= wnkBusinessType.getMonth_free_number()){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("使用次数已达到上限");
                                }
                                else{
                                    List<Map<Object,Object>> list = this.codesService.selectMobileCode(users.getMobile(),code,11);
                                    if (list.size() > 0){
                                        Integer code_id = -1;
                                        for (int index = 0;index < list.size();index ++){
                                            Map<Object,Object> map = list.get(index);
                                            if ((Integer)map.get("state") == 0){
                                                code_id = (Integer)map.get("id");
                                            }
                                        }
                                        if (code_id != -1){
                                            int state = this.codesService.updateCodesState(code_id);
                                            if (state >= 1){
                                                WnkQrCodeMakeRecord wnkQrCodeMakeRecord = new WnkQrCodeMakeRecord();
                                                wnkQrCodeMakeRecord.setBusiness_id(business_id);
                                                wnkQrCodeMakeRecord.setUser_id(user_id);
                                                wnkQrCodeMakeRecord.setMake_time(new Date());
                                                wnkQrCodeMakeRecord.setBusiness_type_id(wnkBusinessType.getId());
                                                wnkQrCodeMakeRecord.setOrder_no(UUDUtil.getOrderIdByUUId());
                                                wnkQrCodeMakeRecord.setMake_number(make_number);
                                                int addState = this.wnkQrCodeMakeRecordService.insertMakeRecord(wnkQrCodeMakeRecord);
                                                if (addState <= 0){
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
                                            else{
                                                result.setStatus(Result.FAIL);
                                                result.setMsg("验证失败");
                                            }
                                        }
                                        else{
                                            result.setStatus(Result.FAIL);
                                            result.setMsg("验证码无效");
                                        }
                                    }
                                    else{
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("验证码不存在");
                                    }
                                }
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


    // 按月统计所有订单
    @RequestMapping(value = "/countAllOrderByMonth", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result countAllOrderByMonth(HttpServletRequest request, HttpServletResponse response, Integer business_id){
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
                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                    if (wnkStoreInformation == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商户信息不存在");
                    }
                    else{
                        //已完成订单
                        List<Map<Object,Object>> finishOrderList = this.wnkOrdersService.countOrderByMonthAndState(2,business_id);
                        //新订单
                        List<Map<Object,Object>> newOrderList = this.wnkOrdersService.countOrderByMonthAndState(1,business_id);
                        if (finishOrderList.size() <= 0 && newOrderList.size() <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("暂无数据");
                        }
                        else{
                            Integer finishOrderCount = 0;
                            Integer newOrderCount = 0;
                            for (Integer index=0;index < finishOrderList.size();index++){
                                Map<Object,Object> map = finishOrderList.get(index);
                                Long count = (Long) map.get("count");
                                finishOrderCount = finishOrderCount + new Long(count).intValue();
                            }
                            for (Integer index=0;index < finishOrderList.size();index++){
                                Map<Object,Object> map = finishOrderList.get(index);
                                Long count = (Long) map.get("count");
                                map.put("bili",new Long(count).doubleValue()/finishOrderCount * 100);
                            }
                            for (Integer index=0;index < newOrderList.size();index++){
                                Map<Object,Object> map = newOrderList.get(index);
                                Long count = (Long) map.get("count");
                                newOrderCount = newOrderCount + new Long(count).intValue();
                            }
                            for (Integer index=0;index < newOrderList.size();index++){
                                Map<Object,Object> map = newOrderList.get(index);
                                Long count = (Long) map.get("count");
                                map.put("bili",new Long(count).doubleValue()/newOrderCount * 100);
                            }
                            if (finishOrderCount == 0 && newOrderCount == 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("暂无数据");
                            }
                            else{
                                Map<Object,Object> map = new HashMap<Object,Object>();
                                map.put("finish_order",finishOrderList);
                                map.put("new_order",newOrderList);
                                result.setData(map);
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("查询成功");
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


    // 按月统计所有收入
    @RequestMapping(value = "/countAllIncomeByMonth", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result countAllIncomeByMonth(HttpServletRequest request, HttpServletResponse response, Integer business_id){
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
                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                    if (wnkStoreInformation == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商户信息不存在");
                    }
                    else{
                        //订单统计
                        List<Map<Object,Object>> orderList = this.wnkOrdersService.countOrderByMonthAndState2(business_id);
                        //收入金额统计
                        List<Map<Object,Object>> incomAmountList = this.wnkBusinessBalanceDetailService.countAmountByMonth();
                        if (orderList.size() <= 0 && incomAmountList.size() <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("暂无数据");
                        }
                        else{
                            Integer orderCount = 0;
                            Double incomCountAmount = 0.00;
                            for (Integer index=0;index < orderList.size();index++){
                                Map<Object,Object> map = orderList.get(index);
                                Long count = (Long) map.get("count");
                                orderCount = orderCount + new Long(count).intValue();
                            }
                            for (Integer index=0;index < orderList.size();index++){
                                Map<Object,Object> map = orderList.get(index);
                                Long count = (Long) map.get("count");
                                map.put("bili",(new Long(count).doubleValue()/orderCount * 100));
                            }
                            for (Integer index=0;index < incomAmountList.size();index++){
                                Map<Object,Object> map = incomAmountList.get(index);
                                incomCountAmount = incomCountAmount + (Double) map.get("count_amount");
                            }
                            for (Integer index=0;index < incomAmountList.size();index++){
                                Map<Object,Object> map = incomAmountList.get(index);
                                Long count = (Long) map.get("count");
                                map.put("bili",(Double) map.get("count_amount")/incomCountAmount * 100);
                            }
                            if (orderCount == 0 && incomCountAmount == 0.00){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("暂无数据");
                            }
                            else{
                                Map<Object,Object> map = new HashMap<Object,Object>();
                                map.put("order",orderList);
                                map.put("count_amount",incomAmountList);
                                result.setData(map);
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("查询成功");
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


    // 获取订单统计详情(make_type=0-按订单统计详情，make_type=1-按销售统计详情),make_type=0时type=0:新订单,1-已完成,make_type=1时type=0:按量统计,1-按金额统计
    @RequestMapping(value = "/getAllOrderStaticDetail", method = RequestMethod.POST,params = {"business_id","type","month_year","make_type"})
    @ResponseBody
    public Result getAllOrderStaticDetail(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer type,String month_year,Integer make_type){
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
                    String[] yearAndMonth = month_year.split("-");
                    if (yearAndMonth.length == 2){
                        if (make_type == 0){
                            Integer state = type == 0?1:2;
                            List<Map<Object,Object>> orderList = this.wnkOrdersService.selectOrderByBusinessIdAndStateAndMonthAndYeaar(business_id,state,yearAndMonth[1],yearAndMonth[0]);
                            List<Map<Object,Object>> userNumber = this.wnkOrdersService.selectUserNumberByBusinessIdAndMonthAndYear(business_id,state,yearAndMonth[1],yearAndMonth[0]);
                            if (orderList.size() <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("暂无订单");
                            }
                            else{
                                for (Integer index = 0;index < orderList.size();index++){
                                    Map<Object,Object> map = orderList.get(index);
                                    List<Map<Object,Object>> commodity_list = this.wnkOrderCommodityService.selectCommodityByOrderId((Integer) map.get("id"));
                                    map.put("commodity_list",commodity_list);
                                    map.put("commodity_count",commodity_list.size());
                                }
                                Map<Object,Object> returnMap = new HashMap<Object,Object>();
                                returnMap.put("list",orderList);
                                returnMap.put("user_number",userNumber.size());
                                result.setData(returnMap);
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("查询成功");
                            }
                        }
                        else{
                            if (type == 0){
                                List<Map<Object,Object>> orderList = this.wnkOrdersService.selectOrderByBusinessIdAndMonthAndYeaar(business_id,yearAndMonth[1],yearAndMonth[0]);
                                List<Map<Object,Object>> userNumber = this.wnkOrdersService.selectUserNumberByBusinessIdAndMonthAndYearNoState(business_id,yearAndMonth[1],yearAndMonth[0]);
                                if (orderList.size() <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("暂无订单");
                                }
                                else{
                                    for (Integer index = 0;index < orderList.size();index++){
                                        Map<Object,Object> map = orderList.get(index);
                                        List<Map<Object,Object>> commodity_list = this.wnkOrderCommodityService.selectCommodityByOrderId((Integer) map.get("id"));
                                        map.put("commodity_list",commodity_list);
                                        map.put("commodity_count",commodity_list.size());
                                    }
                                    Map<Object,Object> returnMap = new HashMap<Object,Object>();
                                    returnMap.put("list",orderList);
                                    returnMap.put("user_number",userNumber.size());
                                    result.setData(returnMap);
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("查询成功");
                                }
                            }
                            else{
                                Integer state = 2;
                                List<Map<Object,Object>> orderList = this.wnkOrdersService.selectOrderByBusinessIdAndStateAndMonthAndYeaar(business_id,state,yearAndMonth[1],yearAndMonth[0]);
                                List<Map<Object,Object>> userNumber = this.wnkOrdersService.selectUserNumberByBusinessIdAndMonthAndYear(business_id,state,yearAndMonth[1],yearAndMonth[0]);
                                if (orderList.size() <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("暂无订单");
                                }
                                else{
                                    for (Integer index = 0;index < orderList.size();index++){
                                        Map<Object,Object> map = orderList.get(index);
                                        List<Map<Object,Object>> commodity_list = this.wnkOrderCommodityService.selectCommodityByOrderId((Integer) map.get("id"));
                                        map.put("commodity_list",commodity_list);
                                        map.put("commodity_count",commodity_list.size());
                                    }
                                    Map<Object,Object> returnMap = new HashMap<Object,Object>();
                                    returnMap.put("list",orderList);
                                    returnMap.put("user_number",userNumber.size());
                                    result.setData(returnMap);
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("查询成功");
                                }
                            }
                        }
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("数据不合法");
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


    // 获取用户基础信息
    @RequestMapping(value = "/wnkBusinessBaseInformation", method = RequestMethod.POST,params = {"business_id","user_id"})
    @ResponseBody
    public Result wnkBusinessBaseInformation(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("用户不存在");
                }
                else{
                    Map<Object,Object> map = new HashMap<Object,Object>();
                    map.put("nick_name",users.getNick_name());
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
                        map.put("member_card","未开卡");
                    }
                    else if (users.getMember_card_level() == 0){
                        map.put("member_card","银卡");
                    }
                    else if (users.getMember_card_level() == 1){
                        map.put("member_card","金卡");
                    }
                    map.put("member_star",users.getMember_star());
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");

                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    // 会员权益验证获取短信验证码
    @RequestMapping(value = "/memberQYMakeGetMobileCode", method = RequestMethod.POST,params = {"user_id","business_id"})
    @ResponseBody
    public Result memberQYMakeGetMobileCode(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer business_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    Users users = this.usersService.selectById(user_id);
                    if (users == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此用户不存在");
                    }
                    else{
                        String uuid = UUDUtil.getOrderIdByUUId();
                        String code = uuid.substring(uuid.length()-6,uuid.length());
                        Codes codes = new Codes();
                        codes.setSend_number(users.getMobile());
                        codes.setCode(code);
                        codes.setSend_time(new Date());
                        codes.setMake_type(11);
                        SendSmsResponse sendSmsResponse = MobileCodeUtil.sendSms(code,users.getMobile());
                        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")){
                            Integer state = this.codesService.addMobileCode(codes);
                            if (state >= 1){
                                Map<Object,Object> map = new HashMap<Object,Object>();
                                map.put("code",code);
                                result.setData(map);
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("验证码已发送至用户手机");
                            }
                            else{
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("发送失败");
                            }

                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg(sendSmsResponse.getMessage());
                        }
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("发送失败");
        }
        return result;
    }
}
