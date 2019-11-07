package com.springmvc.controller.wnk_business_app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
import com.springmvc.service.WnkCommoditiesExpandHotelService;
import com.springmvc.service.WnkOrdersExpandHotelService;
import com.springmvc.service.impl.*;
import com.springmvc.utils.*;
import com.springmvc.utils.wechat.WechatPayLineUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author: zhangfan
 * @Date: 2018/12/9 19:57
 * @Description:
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v2.0.0")
public class WnkOrdersBusinessAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkOrdersTwoService wnkOrdersTwoService;
    @Resource
    private WnkOrderCommodityTwoService wnkOrderCommodityTwoService;
    @Resource
    private WnkOrderMakeRecordService wnkOrderMakeRecordService;
    @Resource
    private WnkOrderRefundRecordService wnkOrderRefundRecordService;
    @Resource
    private WnkBusinessBalanceDetailService wnkBusinessBalanceDetailService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;
    @Resource
    private ExtensionMaterielService extensionMaterielService;
    @Resource
    private UserCouponsService userCouponsService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkSendIntegralDetailUserService wnkSendIntegralDetailUserService;
    @Resource
    private WnkSendIntegralUserService wnkSendIntegralUserService;
    @Resource
    private UsersService usersService;
    @Resource
    private GeneralIntegralIncomeService generalIntegralIncomeService;
    @Resource
    private IntegralDetailService integralDetailService;
    @Resource
    private WnkIntegralUserServer wnkIntegralUserServer;
    @Resource
    private WnkIntegralIncomeService wnkIntegralIncomeService;
    @Resource
    private WnkBusinessConsumptionIntegralDetailService wnkBusinessConsumptionIntegralDetailService;

    @Resource
    private WnkOrdersExpandHotelService wnkOrdersExpandHotelService;
    @Resource
    private WnkCommoditiesService wnkCommoditiesService;
    @Resource
    private WnkCommoditySpecificationsService wnkCommoditySpecificationsService;
    @Resource
    private WnkCommoditiesExpandHotelService wnkCommoditiesExpandHotelService;

    @Resource
    private WnkBusinessLevelIntegralDetailService wnkBusinessLevelIntegralDetailService;

    // 获取订单(type=0:新订单,1-已完成)
    @RequestMapping(value = "/getAllOrder", method = RequestMethod.POST, params = {"business_id", "type"})
    @ResponseBody
    public Result getAllOrder(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer type) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    Integer state = type == 0 ? 1 : 2;
                    List<Map<Object, Object>> orderList = new LinkedList<>();
                    WnkStoreInformation wnkStoreInformation = wnkStoreInformationService.selectByBusinessId(business_id);
                    WnkBusinessType wnkBusinessType = wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                    if ("酒店".equals(wnkBusinessType.getName())) {
                        if (state == 2) {
                            orderList.addAll(this.wnkOrdersTwoService.selectOrderByBusinessIdAndState(business_id, 5));
                            orderList.addAll(this.wnkOrdersTwoService.selectOrderByBusinessIdAndState(business_id, 6));
                        } else {
                            orderList.addAll(this.wnkOrdersTwoService.selectOrderByBusinessIdAndState(business_id, 3));
                            orderList.addAll(this.wnkOrdersTwoService.selectOrderByBusinessIdAndState(business_id, 4));
                        }
                    }
                    orderList.addAll(this.wnkOrdersTwoService.selectOrderByBusinessIdAndState(business_id, state));
                    List<Map<Object, Object>> userNumber = this.wnkOrdersTwoService.selectUserNumberByBusinessId(business_id, state);
                    if (orderList.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无订单");
                    } else {
                        for (Integer index = 0; index < orderList.size(); index++) {
                            Map<Object, Object> map = orderList.get(index);
                            List<Map<Object, Object>> commodity_list = this.wnkOrderCommodityTwoService.selectCommodityByOrderId((Integer) map.get("id"));
                            map.put("commodity_list", commodity_list);
                            map.put("commodity_count", commodity_list.size());
                        }
                        Map<Object, Object> returnMap = new HashMap<Object, Object>();
                        returnMap.put("list", orderList);
                        returnMap.put("user_number", userNumber.size());
                        result.setData(returnMap);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }

                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    // 通过订单ID 查询记录ID
    @RequestMapping(value = "/getWnkOrderIdByOrderId", method = RequestMethod.POST)
    @ResponseBody
    public Result getWnkOrderIdByOrderId(HttpServletRequest request, String order_no) {
        Result result = new Result();
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request, 3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null) {
            isLogin = false;
        } else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id, 3, this.sessionIdsService) == null) {
                isLogin = false;
            } else {
                isLogin = true;
            }
        }
        if (!isLogin) {
            result.setData("");
            result.setMsg("未登录");
            result.setStatus(Result.NOLOGIN);
        } else {
            WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderNo(order_no);
            if (wnkOrdersTwo != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("order_id", wnkOrdersTwo.getId());
                map.put("user_id", wnkOrdersTwo.getUser_id());
                result.setData(map);
                result.setMsg("查询成功");
                result.setStatus(Result.SUCCESS);
            } else {
                result.setData("");
                result.setMsg("订单不存在");
                result.setStatus(Result.FAIL);
            }

        }
        return result;
    }

    // 获取购买订单详情
    @RequestMapping(value = "/wnkBuyOrderDetail", method = RequestMethod.POST, params = {"business_id", "order_id"})
    @ResponseBody
    public Result wnkBuyOrderDetail(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer order_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商家不存在");
                } else {
                    List<Map<Object, Object>> list = this.wnkOrdersTwoService.selectByIdReturnMap(order_id);
                    if (list.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    } else {
                        Map<Object, Object> map = list.get(0);
                        String qrCodePhotoId = (String) map.get("order_qrcode");
                        if (qrCodePhotoId == null || qrCodePhotoId.equals("")) {
                            map.put("order_qrcode", "");
                        } else {
                            map.put("order_qrcode", ImageToolsController.qrcodeShowURL + "?imageid=" + qrCodePhotoId);
                        }
                        List<Map<Object, Object>> commodity_list = this.wnkOrderCommodityTwoService.selectCommodityByOrderId(order_id);
                        map.put("commodity_list", commodity_list);
                        Integer surplus_available = 0;
                        if (commodity_list.size() > 0) {
                            Map<Object, Object> orderCommodityMap = commodity_list.get(0);
                            Integer buy_number = (Integer) orderCommodityMap.get("buy_number");
                            Integer already_used_number = (Integer) orderCommodityMap.get("already_used_number");
                            Integer refunded_number = (Integer) orderCommodityMap.get("refunded_number");
                            surplus_available = buy_number - already_used_number - refunded_number;
                        }
                        map.put("surplus_available", surplus_available);
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    // 获取订单使用记录
    @RequestMapping(value = "/wnkOrderMakeRecord", method = RequestMethod.POST, params = {"business_id", "order_id"})
    @ResponseBody
    public Result wnkOrderMakeRecord(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer order_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商家不存在");
                } else {
                    WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderId(order_id);
                    if (wnkOrdersTwo == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    } else {
                        List<Map<Object, Object>> list = this.wnkOrderMakeRecordService.selectByOrderNo(wnkOrdersTwo.getOrder_no());
                        if (list.size() <= 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("暂无数据");
                        } else {
                            result.setData(list);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("查询成功");
                        }
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    // 获取订单退款记录
    @RequestMapping(value = "/wnkOrderRefundRecord", method = RequestMethod.POST, params = {"business_id", "order_id"})
    @ResponseBody
    public Result wnkOrderRefundRecord(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer order_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商家不存在");
                } else {
                    WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderId(order_id);
                    if (wnkOrdersTwo == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    } else {
                        List<Map<Object, Object>> list = this.wnkOrderRefundRecordService.selectByOrderNo(wnkOrdersTwo.getOrder_no());
                        if (list.size() <= 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("暂无数据");
                        } else {
                            result.setData(list);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("查询成功");
                        }
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     * 功能描述: 万能卡订单使用
     *
     * @param business_id 商家ID
     * @param order_id    订单ID
     * @param make_number 使用数量
     * @return:
     * @author: zhangfan
     * @date: 2018/12/9 11:28 PM
     */
    @RequestMapping(value = "/wnkOrderMake", method = RequestMethod.POST, params = {"business_id", "order_id", "make_number"})
    @ResponseBody
    public Result wnkOrderMake(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer order_id, Integer make_number) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商家不存在");
                } else {
                    WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderId(order_id);
                    if (wnkOrdersTwo == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    } else if (wnkOrdersTwo.getBusiness_id() != business_id) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("你无权操作此订单");
                    } else if (wnkOrdersTwo.getState() == 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此订单未支付");
                    } else if (wnkOrdersTwo.getState() == 2) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此订单已失效");
                    } else {
                        List<Map<Object, Object>> commodity_list = this.wnkOrderCommodityTwoService.selectCommodityByOrderId(order_id);
                        Integer surplus_available = 0;
                        Map<Object, Object> orderCommodityMap = null;
                        if (commodity_list.size() > 0) {
                            orderCommodityMap = commodity_list.get(0);
                            Integer buy_number = (Integer) orderCommodityMap.get("buy_number");
                            Integer already_used_number = (Integer) orderCommodityMap.get("already_used_number");
                            Integer refunded_number = (Integer) orderCommodityMap.get("refunded_number");
                            surplus_available = buy_number - already_used_number - refunded_number;
                        }
                        if (surplus_available <= 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("此订单剩余使用数量为0");
                        } else if (surplus_available < make_number) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("此订单剩余使用数量为" + surplus_available);
                        } else {
                            if (orderCommodityMap == null) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("此订单下无商品");
                            } else {
                                Integer orderCommodityId = (Integer) orderCommodityMap.get("id");
                                WnkOrderCommodityTwo wnkOrderCommodityTwo = this.wnkOrderCommodityTwoService.selectByID(orderCommodityId);
                                if (wnkOrderCommodityTwo == null) {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("订单商品不存在");
                                } else {
                                    wnkOrderCommodityTwo.setAlready_used_number(wnkOrderCommodityTwo.getAlready_used_number() + make_number);
                                    int updateMakeNumberState = this.wnkOrderCommodityTwoService.updateCommodityMakeNumberAndRefundNumber(wnkOrderCommodityTwo);
                                    if (updateMakeNumberState <= 0) {
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("使用失败");
                                    } else {
                                        result.setData("");
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("使用成功");

                                        // 查看商品规格是否需要扣除对应比例的等级积分并且补贴对应比例的等级积分给用户
                                        WnkCommoditySpecifications specifications = wnkCommoditySpecificationsService.selectById(wnkOrderCommodityTwo.getCommodity_guige_id());
                                        // 如果是禁用了万能卡权益,则需要扣除对应比例等级积分
                                        if (specifications.getIs_wnk() == 1){
                                            Double lPrice = wnkOrdersTwo.getAmount() * (specifications.getGift_noun() / 100);
                                            wnkBusinessAccount.setLevel_integral(wnkBusinessAccount.getLevel_integral() - lPrice);
                                            this.wnkBusinessAccountService.updateBusinessLevelIntegral(wnkBusinessAccount.getLevel_integral(),wnkBusinessAccount.getId());
                                            // 增加用户在此商家的现金券
                                            Map<String, Object> userIntegral = this.wnkSendIntegralUserService.getUserIntegral(business_id, wnkOrdersTwo.getUser_id());
                                            if (userIntegral == null ){
                                                // 增加积分记录后更新积分余额
                                                this.wnkSendIntegralUserService.addUserIntegral(business_id,wnkOrdersTwo.getUser_id());
                                                this.wnkSendIntegralUserService.updateIntegral(wnkOrdersTwo.getUser_id(),business_id,lPrice);
                                            } else {
                                                Double integralDouble = (Double) userIntegral.get("integral");
                                                integralDouble += lPrice;
                                                this.wnkSendIntegralUserService.updateIntegral(wnkOrdersTwo.getUser_id(),business_id,integralDouble);
                                            }
                                            // 插入商家等级积分扣除记录
                                            //扣除等级积分数量并且插入记录
                                            WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail = new WnkBusinessLevelIntegralDetail();
                                            wnkBusinessLevelIntegralDetail.setBusiness_id(business_id);
                                            wnkBusinessLevelIntegralDetail.setName("消费赠送");
                                            wnkBusinessLevelIntegralDetail.setIntegral_number(lPrice);
                                            wnkBusinessLevelIntegralDetail.setTransactions_date(new Date());
                                            wnkBusinessLevelIntegralDetail.setTransactions_type(2);
                                            wnkBusinessLevelIntegralDetail.setUser_id(wnkOrdersTwo.getUser_id());
                                            wnkBusinessLevelIntegralDetail.setPay_type(5);
                                            this.wnkBusinessLevelIntegralDetailService.insertNewRecordUserXF(wnkBusinessLevelIntegralDetail);
                                            // 插入用户现金券收入记录
                                            wnkSendIntegralDetailUserService.addIntegralRecord("消费赠送",lPrice,wnkOrdersTwo.getUser_id(),business_id,0);
                                        }

                                        WnkOrderMakeRecord wnkOrderMakeRecord = new WnkOrderMakeRecord();
                                        wnkOrderMakeRecord.setOrder_no(wnkOrdersTwo.getOrder_no());
                                        wnkOrderMakeRecord.setMake_date(new Date());
                                        wnkOrderMakeRecord.setMake_number(make_number);

                                        this.wnkOrderMakeRecordService.insertNewRecord(wnkOrderMakeRecord);
                                        surplus_available = wnkOrderCommodityTwo.getBuy_number() - wnkOrderCommodityTwo.getAlready_used_number() - wnkOrderCommodityTwo.getRefunded_number();
                                        if (surplus_available <= 0) {
                                            this.wnkOrdersTwoService.updateOrderStateByOrderNo(2, wnkOrdersTwo.getOrder_no());
                                            Integer refund_number_count = 0;
                                            Double general_integral_count = 0.00;
                                            Integer coupon_count = 0;
                                            Double cash_count = 0.00;
                                            Double send_integral_count = 0.00;
                                            Map<Object, Object> refundCountMap = this.wnkOrderRefundRecordService.selectRefundSumByOrderNo(wnkOrdersTwo.getOrder_no());
                                            if (refundCountMap != null) {
                                                refund_number_count = ((BigDecimal) refundCountMap.get("refund_number_count")).intValue();
                                                general_integral_count = (Double) refundCountMap.get("general_integral_count");
                                                coupon_count = ((BigDecimal) refundCountMap.get("coupon_count")).intValue();
                                                cash_count = (Double) refundCountMap.get("cash_count");
                                                send_integral_count = (Double) refundCountMap.get("send_integral_count");
                                            }
                                            //商家剩余可得
                                            Double businessIncomeMoney = (wnkOrdersTwo.getGeneral_integral() - general_integral_count) + (wnkOrdersTwo.getCash_amount() - cash_count);
                                            if (businessIncomeMoney > 0.00) {
                                                wnkBusinessAccount.setBalance(wnkBusinessAccount.getBalance() + businessIncomeMoney);
                                                int updateBalance = this.wnkBusinessAccountService.updateBalance(wnkBusinessAccount.getId(), wnkBusinessAccount.getBalance());
                                                if (updateBalance > 0) {
                                                    WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                                                    wnkBusinessBalanceDetail.setBusiness_id(business_id);
                                                    wnkBusinessBalanceDetail.setName("营业收入");
                                                    wnkBusinessBalanceDetail.setTransaction_amount(businessIncomeMoney);
                                                    wnkBusinessBalanceDetail.setJoin_time(new Date());
                                                    wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance());
                                                    wnkBusinessBalanceDetail.setType(0);
                                                    wnkBusinessBalanceDetail.setState(0);
                                                    this.wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);
                                                }
                                                Users users = this.usersService.selectById(wnkOrdersTwo.getUser_id());
                                                if (users != null) {
                                                    this.integralDetailService.insertIntrgralDetailRecord(wnkOrdersTwo.getUser_id(), "商品购买", wnkOrdersTwo.getGeneral_integral() + wnkOrdersTwo.getSend_integral() + wnkOrdersTwo.getCash_amount(), 0);

                                                    Map<String, Object> wnkIntegralMap = this.wnkIntegralUserServer.getUserIntegral(wnkOrdersTwo.getBusiness_id(), users.getId());
                                                    Integer addWnkIntegralUserState = 1;
                                                    if (wnkIntegralMap == null) {
                                                        addWnkIntegralUserState = this.wnkIntegralUserServer.addUserIntegral(wnkOrdersTwo.getBusiness_id(), users.getId());
                                                    }
                                                    if (addWnkIntegralUserState > 0) {
                                                        Map<String, Object> recommendIntegralMap = this.wnkIntegralUserServer.getUserIntegral(wnkOrdersTwo.getBusiness_id(), wnkOrdersTwo.getUser_id());
                                                        if (recommendIntegralMap == null) {
                                                            this.wnkIntegralUserServer.addUserIntegral(wnkOrdersTwo.getBusiness_id(), wnkOrdersTwo.getUser_id());
                                                        }
                                                        // 增加用户在此商户的积分余额
                                                        this.wnkIntegralUserServer.increaseUserIntegral(wnkOrdersTwo.getUser_id(), wnkOrdersTwo.getBusiness_id(), wnkOrdersTwo.getGeneral_integral() + wnkOrdersTwo.getSend_integral() + wnkOrdersTwo.getCash_amount());
                                                        // 插入积分记录 0=收入
                                                        this.wnkIntegralIncomeService.addIntegralRecord("商品购买", wnkOrdersTwo.getGeneral_integral() + wnkOrdersTwo.getSend_integral() + wnkOrdersTwo.getCash_amount(), wnkOrdersTwo.getUser_id(), wnkOrdersTwo.getBusiness_id(), 0);
                                                        if (users.getGetui_appid() != null && !users.getGetui_appid().equals("")) {
                                                            String pushTitle = "订单完成";
                                                            Integer getIntegralAmount = wnkOrdersTwo.getGeneral_integral().intValue() + wnkOrdersTwo.getSend_integral().intValue() + wnkOrdersTwo.getCash_amount().intValue();
                                                            String pushContent = "您已完成本次单号为" + wnkOrdersTwo.getOrder_no() + "消费，同时获得了" + getIntegralAmount + "个平台积分和商家兑换积分，请在猛戳-订单中心-已完成查看";
                                                            GeTuiUserUtil.pushUser(users.getGetui_appid(), pushTitle, pushContent);
                                                        }
                                                    }
                                                    if (users.getRecommend_user_id() != -1 && (wnkOrdersTwo.getGeneral_integral() > 0.00 || wnkOrdersTwo.getSend_integral() > 0.00)) {
                                                        if (users.getRecommend_type() == 0) {
                                                            Users recommendUser = this.usersService.selectById(users.getRecommend_user_id());
                                                            if (recommendUser.getMember_card_level() != -1) {
                                                                //                                        Map<String,Object> recommendIntegralMap = this.wnkIntegralUserServer.getUserIntegral(wnkOrdersTwo.getBusiness_id(),recommendUser.getId());
                                                                //                                        if (recommendIntegralMap == null){
                                                                //                                            this.wnkIntegralUserServer.addUserIntegral(wnkOrdersTwo.getBusiness_id(),recommendUser.getId());
                                                                //                                        }
                                                                this.integralDetailService.insertIntrgralDetailRecord(users.getRecommend_user_id(), "朋友购买商品", wnkOrdersTwo.getGeneral_integral() + wnkOrdersTwo.getSend_integral() + wnkOrdersTwo.getCash_amount(), 0);
                                                                // 增加用户下级在此商户的积分余额
                                                                //                                        this.wnkIntegralUserServer.increaseUserIntegral(users.getRecommend_user_id(),wnkOrdersTwo.getBusiness_id(),wnkOrdersTwo.getGeneral_integral().intValue());
                                                                //                                        this.wnkIntegralIncomeService.addIntegralRecord("朋友购买商品",wnkOrdersTwo.getGeneral_integral().intValue(),users.getRecommend_user_id(),wnkOrdersTwo.getBusiness_id(),0);
                                                                if (recommendUser.getGetui_appid() != null && !recommendUser.getGetui_appid().equals("")) {
                                                                    String pushTitle = "平台积分收入";
                                                                    Integer getIntegralAmount = wnkOrdersTwo.getGeneral_integral().intValue() + wnkOrdersTwo.getSend_integral().intValue() + wnkOrdersTwo.getCash_amount().intValue();
                                                                    String pushContent = "团队队员已成功消费，您获得了" + getIntegralAmount + "个平台积分,请在猛戳-我的积分查看";
                                                                    GeTuiUserUtil.pushUser(recommendUser.getGetui_appid(), pushTitle, pushContent);
                                                                }
                                                            }
                                                        } else {
                                                            WnkBusinessAccount wnkBusinessAccount2 = this.wnkBusinessAccountService.selectById(users.getRecommend_user_id());
                                                            if (wnkBusinessAccount2 != null) {
                                                                WnkBusinessConsumptionIntegralDetail wnkBusinessConsumptionIntegralDetail = new WnkBusinessConsumptionIntegralDetail();
                                                                wnkBusinessConsumptionIntegralDetail.setBusiness_id(users.getRecommend_user_id());
                                                                wnkBusinessConsumptionIntegralDetail.setName("会员购买商品");
                                                                wnkBusinessConsumptionIntegralDetail.setIntegral_number(wnkOrdersTwo.getGeneral_integral() + wnkOrdersTwo.getSend_integral() + wnkOrdersTwo.getCash_amount());
                                                                wnkBusinessConsumptionIntegralDetail.setTransactions_date(new Date());
                                                                wnkBusinessConsumptionIntegralDetail.setTransactions_type(0);
                                                                //获得积分
                                                                Integer getIntegralNumber = wnkOrdersTwo.getGeneral_integral().intValue() + wnkOrdersTwo.getSend_integral().intValue() + wnkOrdersTwo.getCash_amount().intValue();
                                                                wnkBusinessAccount2.setConsumption_integral(wnkBusinessAccount2.getConsumption_integral() + getIntegralNumber);
                                                                int businessIntegralUpdateState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount2);
                                                                if (businessIntegralUpdateState > 0) {
                                                                    this.wnkBusinessConsumptionIntegralDetailService.insertNewRecord(wnkBusinessConsumptionIntegralDetail);
                                                                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkBusinessAccount2.getId());
                                                                    if (wnkStoreInformation != null) {
                                                                        if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")) {
                                                                            String pushTitle = "推广积分收入";
                                                                            String pushContent = "团队队员已成功消费，您获得" + getIntegralNumber + "个推广积分,请在猛戳-我的积分-收入明细查看";
                                                                            GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(), pushTitle, pushContent);
                                                                        }
                                                                    }

                                                                }
                                                            }
                                                        }


                                                    }
                                                }
                                            }

                                        }

                                    }

                                }

                            }


                        }
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 获取订单退款基础支持信息
    @RequestMapping(value = "/wnkOrderRefundBaseInformation", method = RequestMethod.POST, params = {"business_id", "order_id"})
    @ResponseBody
    public Result wnkOrderRefundBaseInformation(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer order_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商家不存在");
                } else {
                    WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderId(order_id);
                    if (wnkOrdersTwo == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    } else if (wnkOrdersTwo.getBusiness_id() != business_id) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("你无权操作此订单");
                    } else if (wnkOrdersTwo.getState() == 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此订单未支付");
                    } else if (wnkOrdersTwo.getState() == 2) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此订单已失效");
                    } else if (wnkOrdersTwo.getAmount() <= 0.00) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此订单总价为0,不支持退款");
                    } else {
                        List<Map<Object, Object>> commodity_list = this.wnkOrderCommodityTwoService.selectCommodityByOrderId(order_id);
                        Map<Object, Object> orderCommodityMap = null;
                        if (commodity_list.size() > 0) {
                            orderCommodityMap = commodity_list.get(0);
                        }
                        if (orderCommodityMap == null) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("订单商品不存在");
                        } else {
                            Integer orderCommodityId = (Integer) orderCommodityMap.get("id");
                            WnkOrderCommodityTwo wnkOrderCommodityTwo = this.wnkOrderCommodityTwoService.selectByID(orderCommodityId);
                            if (wnkOrderCommodityTwo == null) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("订单商品不存在");
                            } else if (wnkOrderCommodityTwo.getBuy_number() - wnkOrderCommodityTwo.getRefunded_number() - wnkOrderCommodityTwo.getAlready_used_number() <= 0) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("此订单已无可退款商品");
                            } else {
                                //商品单价
                                Double commodityUnitPrice = wnkOrderCommodityTwo.getCount_amount() / wnkOrderCommodityTwo.getBuy_number();
                                //最大可退款数量
                                Integer max_refund_number = wnkOrderCommodityTwo.getBuy_number() - wnkOrderCommodityTwo.getRefunded_number() - wnkOrderCommodityTwo.getAlready_used_number();
                                Map<Object, Object> retunMap = new HashMap<Object, Object>();
                                retunMap.put("commodity_unit_price", commodityUnitPrice);
                                retunMap.put("max_refund_number", max_refund_number);

                                result.setData(retunMap);
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("查询成功");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     * 功能描述: 万能卡订单退款
     *
     * @param business_id   商家ID
     * @param order_id      订单ID
     * @param refund_number 退款商品数量
     * @param refund_reason 退款原因
     * @return:
     * @author: zhangfan
     * @date: 2018/12/10 12:44 AM
     */
    @RequestMapping(value = "/wnkOrderRefund", method = RequestMethod.POST, params = {"business_id", "order_id", "refund_number", "refund_reason"})
    @ResponseBody
    public Result wnkOrderRefund(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer order_id, Integer refund_number, String refund_reason) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商家不存在");
                } else {
                    WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderId(order_id);
                    if (wnkOrdersTwo == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    } else if (wnkOrdersTwo.getState() == 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此订单未支付");
                    } else if (wnkOrdersTwo.getState() == 2) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此订单已失效");
                    } else if (wnkOrdersTwo.getAmount() <= 0.00) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此订单总价为0,不支持退款");
                    } else if (wnkOrdersTwo.getBusiness_id() != business_id) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("你无权操作此订单");
                    } else {
                        List<Map<Object, Object>> commodity_list = this.wnkOrderCommodityTwoService.selectCommodityByOrderId(order_id);
                        Map<Object, Object> orderCommodityMap = null;
                        if (commodity_list.size() > 0) {
                            orderCommodityMap = commodity_list.get(0);
                        }
                        if (orderCommodityMap == null) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("订单商品不存在");
                        } else {
                            Integer orderCommodityId = (Integer) orderCommodityMap.get("id");
                            WnkOrderCommodityTwo wnkOrderCommodityTwo = this.wnkOrderCommodityTwoService.selectByID(orderCommodityId);
                            if (wnkOrderCommodityTwo == null) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("订单商品不存在");
                            } else if (refund_number > wnkOrderCommodityTwo.getBuy_number() - wnkOrderCommodityTwo.getRefunded_number() - wnkOrderCommodityTwo.getAlready_used_number()) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                //最大退款数量
                                Integer maxRefundNumber = wnkOrderCommodityTwo.getBuy_number() - wnkOrderCommodityTwo.getRefunded_number() - wnkOrderCommodityTwo.getAlready_used_number();
                                result.setMsg("此订单退款数量不可超过" + maxRefundNumber + "件");
                            } else {
                                //商品单价
                                Double commodityUnitPrice = wnkOrderCommodityTwo.getCount_amount() / wnkOrderCommodityTwo.getBuy_number();
                                //应退款金额
                                Double refundCountAmount = commodityUnitPrice * refund_number;
                                refundCountAmount = Double.valueOf(String.format("%.2f", refundCountAmount));

                                //已退款商品数量
                                Integer refund_number_count = 0;
                                //已退款通用积分额度
                                Double general_integral_count = 0.00;
                                //已退款优惠券数量
                                Integer coupon_count = 0;
                                //已退款现金
                                Double cash_count = 0.00;
                                //已退款现金劵(赠送积分)
                                Double send_integral_count = 0.00;
                                Map<Object, Object> refundCountMap = this.wnkOrderRefundRecordService.selectRefundSumByOrderNo(wnkOrdersTwo.getOrder_no());
                                if (refundCountMap != null) {
                                    refund_number_count = ((BigDecimal) refundCountMap.get("refund_number_count")).intValue();
                                    general_integral_count = (Double) refundCountMap.get("general_integral_count");
                                    coupon_count = ((BigDecimal) refundCountMap.get("coupon_count")).intValue();
                                    cash_count = (Double) refundCountMap.get("cash_count");
                                    send_integral_count = (Double) refundCountMap.get("send_integral_count");
                                }
                                //退款优先级：优惠券-现金券-通用积分-第三方支付
                                //退款优惠券数量
                                Integer refund_coupon = 0;
                                //退款现金劵(赠送积分)
                                Double refund_send_integral = 0.00;
                                //退款通用积分额度
                                Double refund_general_integral = 0.00;
                                //退款现金
                                Double refund_cash = 0.00;

                                //计算优惠券退款数量
                                if (refundCountAmount > 0.00 && coupon_count < wnkOrdersTwo.getCoupon()) {
                                    //计算总额需要多少张优惠券
                                    Double couponDKAmount = refundCountAmount / 9.9;
                                    refund_coupon = couponDKAmount.intValue();
                                    if (refund_coupon > wnkOrdersTwo.getCoupon() - coupon_count) {
                                        refund_coupon = wnkOrdersTwo.getCoupon() - coupon_count;
                                    }
                                    refundCountAmount = refundCountAmount - refund_coupon * 9.9;
                                }
                                //计算现金劵(赠送积分退款数量)
                                if (refundCountAmount > 0.00 && send_integral_count < wnkOrdersTwo.getSend_integral()) {
                                    if (refundCountAmount > wnkOrdersTwo.getSend_integral() - send_integral_count) {
                                        refund_send_integral = wnkOrdersTwo.getSend_integral() - send_integral_count;
                                    } else {
                                        refund_send_integral = refundCountAmount;
                                    }
                                    refundCountAmount = refundCountAmount - refund_send_integral;
                                }
                                //计算通用积分退款数量
                                if (refundCountAmount > 0.00 && general_integral_count < wnkOrdersTwo.getGeneral_integral()) {
                                    if (refundCountAmount > wnkOrdersTwo.getGeneral_integral() - general_integral_count) {
                                        refund_general_integral = wnkOrdersTwo.getGeneral_integral() - general_integral_count;
                                    } else {
                                        refund_general_integral = refundCountAmount;
                                    }
                                    refundCountAmount = refundCountAmount - refund_general_integral;
                                }
                                //计算现金退款数量
                                if (refundCountAmount > 0.00 && cash_count < wnkOrdersTwo.getCash_amount()) {
                                    if (refundCountAmount > wnkOrdersTwo.getCash_amount() - cash_count) {
                                        refund_cash = wnkOrdersTwo.getCash_amount() - cash_count;
                                    } else {
                                        refund_cash = refundCountAmount;
                                    }
                                    refundCountAmount = refundCountAmount - refund_cash;
                                }

                                //更新退款数量
                                wnkOrderCommodityTwo.setRefunded_number(wnkOrderCommodityTwo.getRefunded_number() + refund_number);
                                int updateRefundNumberState = this.wnkOrderCommodityTwoService.updateCommodityMakeNumberAndRefundNumber(wnkOrderCommodityTwo);
                                if (updateRefundNumberState > 0) {
                                    //订单状态更新结果(true-成功,false-失败)
                                    boolean orderStateUpdateState = true;
                                    if (wnkOrderCommodityTwo.getAlready_used_number() + wnkOrderCommodityTwo.getRefunded_number() >= wnkOrderCommodityTwo.getBuy_number()) {
                                        int updateOrderState = this.wnkOrdersTwoService.updateOrderStateByOrderNo(2, wnkOrdersTwo.getOrder_no());
                                        if (updateOrderState <= 0) {
                                            orderStateUpdateState = false;
                                        }
                                    }
                                    if (orderStateUpdateState == true) {
                                        result.setData("");
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("退款成功");

                                        WnkOrderRefundRecord wnkOrderRefundRecord = new WnkOrderRefundRecord();
                                        wnkOrderRefundRecord.setRefund_no(UUDUtil.getOrderIdByUUId());
                                        wnkOrderRefundRecord.setRefund_number(refund_number);
                                        wnkOrderRefundRecord.setGeneral_integral(refund_general_integral);
                                        wnkOrderRefundRecord.setOrder_no(wnkOrdersTwo.getOrder_no());
                                        wnkOrderRefundRecord.setRefund_date(new Date());
                                        wnkOrderRefundRecord.setCoupon(refund_coupon);
                                        wnkOrderRefundRecord.setSend_integral(refund_send_integral);
                                        wnkOrderRefundRecord.setCash(refund_cash);
                                        wnkOrderRefundRecord.setRefund_reason(refund_reason);
                                        this.wnkOrderRefundRecordService.insertNewRecord(wnkOrderRefundRecord);

                                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkBusinessAccount.getId());
                                        //进行优惠券退款
                                        if (refund_coupon > 0) {
                                            if (wnkStoreInformation != null) {
                                                WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                                                if (wnkBusinessType != null) {
                                                    List<Map<Object, Object>> extensionList = this.extensionMaterielService.selectMatensionByTypeId(wnkBusinessType.getId());
                                                    if (extensionList.size() > 0) {
                                                        Map<Object, Object> extensionMap = extensionList.get(0);
                                                        Integer materielId = (Integer) extensionMap.get("id");
                                                        UserCoupons userCoupons = this.userCouponsService.selectByMaterielIdAndUserId(wnkOrdersTwo.getUser_id(), materielId);

                                                        if (userCoupons == null) {
                                                            userCoupons = new UserCoupons();
                                                            userCoupons.setUser_id(wnkOrdersTwo.getUser_id());
                                                            userCoupons.setMateriel_id(materielId);
                                                            userCoupons.setSurplus_number(refund_number);
                                                            this.userCouponsService.insertMaterielBuyRecord(userCoupons);
                                                        } else {
                                                            userCoupons.setSurplus_number(userCoupons.getSurplus_number() + refund_number);
                                                            this.userCouponsService.updateSurplusNumber(userCoupons);
                                                        }
                                                    }
                                                }
                                            }

                                        }
                                        //进行现金劵(赠送积分)退款
                                        if (refund_send_integral > 0.00) {
                                            //查询用户在此商家处是否已有积分记录
                                            Map<String, Object> userIntegralMap = this.wnkSendIntegralUserService.getUserIntegral(business_id, wnkOrdersTwo.getUser_id());
                                            int insertRecordState = 1;
                                            if (userIntegralMap == null) {
                                                insertRecordState = 0;
                                                insertRecordState = this.wnkSendIntegralUserService.addUserIntegral(business_id, wnkOrdersTwo.getUser_id());
                                            }
                                            if (insertRecordState > 0) {
                                                int addState = this.wnkSendIntegralUserService.increaseUserIntegral(wnkOrdersTwo.getUser_id(), business_id, refund_send_integral);
                                                if (addState > 0) {
                                                    // 插入积分记录 0=收入
                                                    this.wnkSendIntegralDetailUserService.addIntegralRecord("商品退款", refund_send_integral, wnkOrdersTwo.getUser_id(), business_id, 0);
                                                }
                                            }
                                        }
                                        //进行通用积分退款
                                        if (refund_general_integral > 0.00) {
                                            Users users = this.usersService.selectById(wnkOrdersTwo.getUser_id());
                                            if (users != null) {
                                                users.setGeneral_integral(users.getGeneral_integral() + refund_general_integral);
                                                this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);

                                                GeneralIntegralIncome generalIntegralIncome = new GeneralIntegralIncome();
                                                generalIntegralIncome.setName("商品退款");
                                                generalIntegralIncome.setIncome_date(new Date());
                                                generalIntegralIncome.setIncome_amount(refund_general_integral);
                                                generalIntegralIncome.setIncome_after_balance(users.getGeneral_integral());
                                                generalIntegralIncome.setUser_id(users.getId());
                                                generalIntegralIncome.setIncome_type(0);

                                                this.generalIntegralIncomeService.insertUserSilverCoinWithdrawRecord(generalIntegralIncome);
                                            }
                                        }
                                        //进行现金退款
                                        if (refund_cash > 0.00) {
                                            //退款至微信
                                            if (wnkOrdersTwo.getPay_way() == 1) {
                                                Map<String, Object> wxRefundMap = WechatPayLineUtil.wechatPayWnkCommodityAppRefund(wnkOrdersTwo.getOrder_no(), wnkOrderRefundRecord.getRefund_no(), Double.valueOf(String.format("%.2f", wnkOrderRefundRecord.getCash())), wnkOrdersTwo.getCash_amount(), request, response);
                                                System.out.println("商户申请退款-微信退款申请状态：" + (Boolean) wxRefundMap.get("status"));
                                                System.out.println("商户申请退款-微信退款申请反馈信息：" + (String) wxRefundMap.get("msg"));
                                            }
                                            //退款至支付宝
                                            else if (wnkOrdersTwo.getPay_way() == 2) {

                                            }
                                        }
                                    } else {
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("退款失败");
                                    }
                                } else {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("退款失败");
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    /**
     * 功能描述: 万能卡商家获取商品详情 - 酒店类
     *
     * @param businessId 商家ID
     * @param orderId    订单ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/10 12:44 AM
     */
    @RequestMapping(value = "/wnkBuyOrderDetailByHotel", method = RequestMethod.POST)
    @ResponseBody
    public Result wnkBuyOrderDetailByHotel(HttpServletRequest request, HttpServletResponse response, Integer businessId, Integer orderId) {
        try {
            if (LoginSessionCheckUtil.checkIsLogin(businessId, 3, request, response, this.sessionIdsService) == null) {
                return new Result(Result.NOLOGIN, "此账户暂未登录", null);
            }
            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(businessId);
            if (wnkBusinessAccount == null) {
                return new Result(Result.FAIL, "商家不存在", null);
            }
            // 查询基本订单信息
            WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderId(orderId);
            if (wnkOrdersTwo == null) {
                return new Result(Result.FAIL, "订单不存在", null);
            }
            // 查询退款信息
            List<Map<Object, Object>> maps = this.wnkOrderRefundRecordService.selectByOrderNo(wnkOrdersTwo.getOrder_no());
            // 查询扩展信息
            WnkOrdersExpandHotel wnkOrdersExpandHotel = this.wnkOrdersExpandHotelService.selectExpandInfoByOrderId(orderId);
            // 规格信息
            List<Map<Object, Object>> maps1 = this.wnkCommoditySpecificationsService.selectByCommodityId(wnkOrdersExpandHotel.getCommodity_id());
            // 扩展规格信息
            WnkCommoditiesExpandHotel wnkCommoditiesExpandHotel = this.wnkCommoditiesExpandHotelService.selectCommoditiesExpandHotelByCommoditiesId(wnkOrdersExpandHotel.getCommodity_id());
            // 商品信息
            WnkCommodities commodity = this.wnkCommoditiesService.selectById((Integer) maps1.get(0).get("commodity_id"));
            // 商家信息
            Map<String, Object> businessInfoMap = new HashMap<>(16);
            WnkStoreInformation wnkStoreInformation = wnkStoreInformationService.selectByBusinessId(wnkOrdersTwo.getBusiness_id());
            businessInfoMap.put("mobile", wnkBusinessAccount.getMobile());
            businessInfoMap.put("address", wnkStoreInformation.getAddress());
            businessInfoMap.put("name", wnkStoreInformation.getStore_name());

            // 组装
            List<Map<String, Object>> list = new LinkedList<>();
            Map<String, Object> map = new HashMap<>(2);
            map.put("basic", wnkOrdersTwo);
            map.put("expand", wnkOrdersExpandHotel);
            map.put("refund", maps);
            map.put("specification", maps1);
            map.put("specification_expand", wnkCommoditiesExpandHotel);
            map.put("commodity", commodity);
            map.put("business", businessInfoMap);
            list.add(map);
            return new Result(Result.SUCCESS, "查询成功", list);
        } catch (Exception e) {
            return new Result(Result.FAIL, "操作失败", e.getMessage());
        }
    }


    /**
     * 功能描述: 万能卡订单退款 - 酒店类商家
     *
     * @param business_id   商家ID
     * @param order_id      订单ID
     * @param refund_number 退款商品数量
     * @param refund_reason 退款原因
     * @return:
     * @author: zhangfan
     * @date: 2018/12/10 12:44 AM
     */
    @RequestMapping(value = "/wnkOrderRefundByHotel", method = RequestMethod.POST)
    @ResponseBody
    public Result wnkOrderRefundByHotel(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer order_id, Integer refund_number, String refund_reason) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商家不存在");
                } else {
                    WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderId(order_id);
                    if (wnkOrdersTwo == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    } else if (wnkOrdersTwo.getState() == 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此订单未支付");
                    } else if (wnkOrdersTwo.getState() == 2) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此订单已失效");
                    } else if (wnkOrdersTwo.getAmount() <= 0.00) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此订单总价为0,不支持退款");
                    } else if (wnkOrdersTwo.getBusiness_id() != business_id) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("你无权操作此订单");
                    } else {
                        List<Map<Object, Object>> commodity_list = this.wnkOrderCommodityTwoService.selectCommodityByOrderId(order_id);
                        Map<Object, Object> orderCommodityMap = null;
                        if (commodity_list.size() > 0) {
                            orderCommodityMap = commodity_list.get(0);
                        }
                        if (orderCommodityMap == null) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("订单商品不存在");
                        } else {
                            Integer orderCommodityId = (Integer) orderCommodityMap.get("id");
                            WnkOrderCommodityTwo wnkOrderCommodityTwo = this.wnkOrderCommodityTwoService.selectByID(orderCommodityId);
                            if (wnkOrderCommodityTwo == null) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("订单商品不存在");
                            } else {
                                //商品单价
                                Double commodityUnitPrice = wnkOrderCommodityTwo.getCount_amount() / wnkOrderCommodityTwo.getBuy_number();
                                //应退款金额
                                Double refundCountAmount = commodityUnitPrice * refund_number;
                                refundCountAmount = Double.valueOf(String.format("%.2f", refundCountAmount));

                                //已退款商品数量
                                Integer refund_number_count = 0;
                                //已退款通用积分额度
                                Double general_integral_count = 0.00;
                                //已退款优惠券数量
                                Integer coupon_count = 0;
                                //已退款现金
                                Double cash_count = 0.00;
                                //已退款现金劵(赠送积分)
                                Double send_integral_count = 0.00;
                                Map<Object, Object> refundCountMap = this.wnkOrderRefundRecordService.selectRefundSumByOrderNo(wnkOrdersTwo.getOrder_no());
                                if (refundCountMap != null) {
                                    refund_number_count = ((BigDecimal) refundCountMap.get("refund_number_count")).intValue();
                                    general_integral_count = (Double) refundCountMap.get("general_integral_count");
                                    coupon_count = ((BigDecimal) refundCountMap.get("coupon_count")).intValue();
                                    cash_count = (Double) refundCountMap.get("cash_count");
                                    send_integral_count = (Double) refundCountMap.get("send_integral_count");
                                }
                                //退款优先级：优惠券-现金券-通用积分-第三方支付
                                //退款优惠券数量
                                Integer refund_coupon = 0;
                                //退款现金劵(赠送积分)
                                Double refund_send_integral = 0.00;
                                //退款通用积分额度
                                Double refund_general_integral = 0.00;
                                //退款现金
                                Double refund_cash = 0.00;

                                //计算优惠券退款数量
                                if (refundCountAmount > 0.00 && coupon_count < wnkOrdersTwo.getCoupon()) {
                                    //计算总额需要多少张优惠券
                                    Double couponDKAmount = refundCountAmount / 9.9;
                                    refund_coupon = couponDKAmount.intValue();
                                    if (refund_coupon > wnkOrdersTwo.getCoupon() - coupon_count) {
                                        refund_coupon = wnkOrdersTwo.getCoupon() - coupon_count;
                                    }
                                    refundCountAmount = refundCountAmount - refund_coupon * 9.9;
                                }
                                //计算现金劵(赠送积分退款数量)
                                if (refundCountAmount > 0.00 && send_integral_count < wnkOrdersTwo.getSend_integral()) {
                                    if (refundCountAmount > wnkOrdersTwo.getSend_integral() - send_integral_count) {
                                        refund_send_integral = wnkOrdersTwo.getSend_integral() - send_integral_count;
                                    } else {
                                        refund_send_integral = refundCountAmount;
                                    }
                                    refundCountAmount = refundCountAmount - refund_send_integral;
                                }
                                //计算通用积分退款数量
                                if (refundCountAmount > 0.00 && general_integral_count < wnkOrdersTwo.getGeneral_integral()) {
                                    if (refundCountAmount > wnkOrdersTwo.getGeneral_integral() - general_integral_count) {
                                        refund_general_integral = wnkOrdersTwo.getGeneral_integral() - general_integral_count;
                                    } else {
                                        refund_general_integral = refundCountAmount;
                                    }
                                    refundCountAmount = refundCountAmount - refund_general_integral;
                                }
                                //计算现金退款数量
                                if (refundCountAmount > 0.00 && cash_count < wnkOrdersTwo.getCash_amount()) {
                                    if (refundCountAmount > wnkOrdersTwo.getCash_amount() - cash_count) {
                                        refund_cash = wnkOrdersTwo.getCash_amount() - cash_count;
                                    } else {
                                        refund_cash = refundCountAmount;
                                    }
                                    refundCountAmount = refundCountAmount - refund_cash;
                                }

                                //更新退款数量
                                wnkOrderCommodityTwo.setRefunded_number(wnkOrderCommodityTwo.getBuy_number());
                                int updateRefundNumberState = this.wnkOrderCommodityTwoService.updateCommodityMakeNumberAndRefundNumber(wnkOrderCommodityTwo);
                                if (updateRefundNumberState > 0) {
                                    //订单状态更新结果(true-成功,false-失败)
                                    boolean orderStateUpdateState = true;
                                    if (wnkOrderCommodityTwo.getAlready_used_number() + wnkOrderCommodityTwo.getRefunded_number() >= wnkOrderCommodityTwo.getBuy_number()) {
                                        int updateOrderState = this.wnkOrdersTwoService.updateOrderStateByOrderNo(5, wnkOrdersTwo.getOrder_no());
                                        if (updateOrderState <= 0) {
                                            orderStateUpdateState = false;
                                        }
                                    }
                                    if (orderStateUpdateState == true) {
                                        result.setData("");
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("退款成功");

                                        WnkOrderRefundRecord wnkOrderRefundRecord = new WnkOrderRefundRecord();
                                        wnkOrderRefundRecord.setRefund_no(UUDUtil.getOrderIdByUUId());
                                        wnkOrderRefundRecord.setRefund_number(refund_number);
                                        wnkOrderRefundRecord.setGeneral_integral(refund_general_integral);
                                        wnkOrderRefundRecord.setOrder_no(wnkOrdersTwo.getOrder_no());
                                        wnkOrderRefundRecord.setRefund_date(new Date());
                                        wnkOrderRefundRecord.setCoupon(refund_coupon);
                                        wnkOrderRefundRecord.setSend_integral(refund_send_integral);
                                        wnkOrderRefundRecord.setCash(refund_cash);
                                        wnkOrderRefundRecord.setRefund_reason(refund_reason);
                                        this.wnkOrderRefundRecordService.insertNewRecord(wnkOrderRefundRecord);

                                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkBusinessAccount.getId());
                                        //进行优惠券退款
                                        if (refund_coupon > 0) {
                                            if (wnkStoreInformation != null) {
                                                WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                                                if (wnkBusinessType != null) {
                                                    List<Map<Object, Object>> extensionList = this.extensionMaterielService.selectMatensionByTypeId(wnkBusinessType.getId());
                                                    if (extensionList.size() > 0) {
                                                        Map<Object, Object> extensionMap = extensionList.get(0);
                                                        Integer materielId = (Integer) extensionMap.get("id");
                                                        UserCoupons userCoupons = this.userCouponsService.selectByMaterielIdAndUserId(wnkOrdersTwo.getUser_id(), materielId);

                                                        if (userCoupons == null) {
                                                            userCoupons = new UserCoupons();
                                                            userCoupons.setUser_id(wnkOrdersTwo.getUser_id());
                                                            userCoupons.setMateriel_id(materielId);
                                                            userCoupons.setSurplus_number(refund_number);
                                                            this.userCouponsService.insertMaterielBuyRecord(userCoupons);
                                                        } else {
                                                            userCoupons.setSurplus_number(userCoupons.getSurplus_number() + refund_number);
                                                            this.userCouponsService.updateSurplusNumber(userCoupons);
                                                        }
                                                    }
                                                }
                                            }

                                        }
                                        //进行现金劵(赠送积分)退款
                                        if (refund_send_integral > 0.00) {
                                            //查询用户在此商家处是否已有积分记录
                                            Map<String, Object> userIntegralMap = this.wnkSendIntegralUserService.getUserIntegral(business_id, wnkOrdersTwo.getUser_id());
                                            int insertRecordState = 1;
                                            if (userIntegralMap == null) {
                                                insertRecordState = 0;
                                                insertRecordState = this.wnkSendIntegralUserService.addUserIntegral(business_id, wnkOrdersTwo.getUser_id());
                                            }
                                            if (insertRecordState > 0) {
                                                int addState = this.wnkSendIntegralUserService.increaseUserIntegral(wnkOrdersTwo.getUser_id(), business_id, refund_send_integral);
                                                if (addState > 0) {
                                                    // 插入积分记录 0=收入
                                                    this.wnkSendIntegralDetailUserService.addIntegralRecord("商品退款", refund_send_integral, wnkOrdersTwo.getUser_id(), business_id, 0);
                                                }
                                            }
                                        }
                                        //进行通用积分退款
                                        if (refund_general_integral > 0.00) {
                                            Users users = this.usersService.selectById(wnkOrdersTwo.getUser_id());
                                            if (users != null) {
                                                users.setGeneral_integral(users.getGeneral_integral() + refund_general_integral);
                                                this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);

                                                GeneralIntegralIncome generalIntegralIncome = new GeneralIntegralIncome();
                                                generalIntegralIncome.setName("商品退款");
                                                generalIntegralIncome.setIncome_date(new Date());
                                                generalIntegralIncome.setIncome_amount(refund_general_integral);
                                                generalIntegralIncome.setIncome_after_balance(users.getGeneral_integral());
                                                generalIntegralIncome.setUser_id(users.getId());
                                                generalIntegralIncome.setIncome_type(0);

                                                this.generalIntegralIncomeService.insertUserSilverCoinWithdrawRecord(generalIntegralIncome);
                                            }
                                        }
                                        //进行现金退款
                                        if (refund_cash > 0.00) {
                                            //退款至微信
                                            if (wnkOrdersTwo.getPay_way() == 1) {
                                                Map<String, Object> wxRefundMap = WechatPayLineUtil.wechatPayWnkCommodityAppRefund(wnkOrdersTwo.getOrder_no(), wnkOrderRefundRecord.getRefund_no(), Double.valueOf(String.format("%.2f", wnkOrderRefundRecord.getCash())), wnkOrdersTwo.getCash_amount(), request, response);
                                                System.out.println("商户申请退款-微信退款申请状态：" + (Boolean) wxRefundMap.get("status"));
                                                System.out.println("商户申请退款-微信退款申请反馈信息：" + (String) wxRefundMap.get("msg"));
                                            }
                                            //退款至支付宝
                                            else if (wnkOrdersTwo.getPay_way() == 2) {

                                            }

                                        }


                                        // 库存返还
                                        // 需要更新每天的库存 更新规格库存
                                        Map<String, Object> updateMap = new HashMap<>(4);
                                        WnkOrdersExpandHotel wnkOrdersExpandHotel = this.wnkOrdersExpandHotelService.selectExpandInfoByOrderId(wnkOrdersTwo.getId());
                                        if (wnkOrdersExpandHotel != null) {
                                            updateMap.put("commoditiesId", wnkOrdersExpandHotel.getCommodity_id());
                                            updateMap.put("joinTime", wnkOrdersExpandHotel.getRegister_start_time_stamp().longValue());
                                            updateMap.put("outTime", wnkOrdersExpandHotel.getRegister_end_time_stamp().longValue());
                                            updateMap.put("businessId", wnkBusinessAccount.getId());
                                            this.wnkCommoditySpecificationsService.updateDayInventoryNumberByCommoditiesIdAndJoinAndOutTimeAndBusinessIdAndUp(updateMap);
                                        }

                                    } else {
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("退款失败");
                                    }
                                } else {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("退款失败");
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    /**
     * 功能描述: 万能卡订单接受 - 酒店类商家
     *
     * @param business_id 商家ID
     * @param order_id    订单ID
     * @date: 2018/12/10 12:44 AM
     */
    @RequestMapping(value = "/wnkOrderAccetedByHotel", method = RequestMethod.POST)
    @ResponseBody
    public Result wnkOrderAccetedByHotel(HttpServletRequest request, HttpServletResponse response, Integer businessId, Integer orderId) {
        try {
            if (LoginSessionCheckUtil.checkIsLogin(businessId, 3, request, response, this.sessionIdsService) == null) {
                return new Result(Result.NOLOGIN, "此账户暂未登陆", null);
            }
            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(businessId);
            if (wnkBusinessAccount == null) {
                return new Result(Result.FAIL, "此商户不存在", null);
            }
            WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderId(orderId);
            if (wnkOrdersTwo == null) {
                return new Result(Result.FAIL, "订单不存在", null);
            }
            if (wnkOrdersTwo.getState() == 0) {
                return new Result(Result.FAIL, "此订单未支付", null);
            }
            if (wnkOrdersTwo.getState() == 2) {
                return new Result(Result.FAIL, "此订单已失效", null);
            }
            // 赠送用户现金券
            // 赠送数量
            List<Map<Object, Object>> maps = this.wnkOrderCommodityTwoService.selectCommodityByOrderId(wnkOrdersTwo.getId());
            if (maps.size() < 0){
                return new Result(Result.FAIL, "获取必要信息失败", null);
            }

            Map<Object, Object> objectObjectMap = maps.get(0);
            Integer commodity_id = (Integer)objectObjectMap.get("commodity_id");
            List<Map<Object, Object>> maps1 = this.wnkCommoditySpecificationsService.selectByCommodityId(commodity_id);
            if (maps1.size() < 0){
                return new Result(Result.FAIL, "获取必要信息失败", null);
            }
            Map<Object, Object> objectObjectMap1 = maps1.get(0);
            Double giftBl = (Integer) objectObjectMap1.get("gift_noun") / 100.0;
            Double giftDouble = wnkOrdersTwo.getAmount().doubleValue() * giftBl;
            Map<String, Object> userIntegral = wnkSendIntegralUserService.getUserIntegral(businessId, wnkOrdersTwo.getUser_id());
            if (userIntegral != null){
                if(this.wnkSendIntegralUserService.updateIntegralUp(wnkOrdersTwo.getUser_id(),businessId,giftDouble) <= 0){
                    return new Result(Result.FAIL,"消费赠送赠送失败",null);
                }
            } else {
                this.wnkSendIntegralUserService.addUserIntegral(businessId,wnkOrdersTwo.getUser_id());
                if(this.wnkSendIntegralUserService.updateIntegralUp(wnkOrdersTwo.getUser_id(),businessId,giftDouble) <= 0){
                    return new Result(Result.FAIL,"消费赠送赠送失败",null);
                }
            }
            // 增加商家等级积分
            if (this.wnkBusinessAccountService.updateBalance(businessId,wnkBusinessAccount.getBalance() + wnkOrdersTwo.getAmount()) <= 0){
                return new Result(Result.FAIL,"增加商家余额失败",null);
            }

            // 更新订单状态
            // 0-待支付, 1-待使用, 2-完成, 3-等待商家确认, 4-商家已确认, 5-商家已拒绝, 6-用户取消订单
            if (this.wnkOrdersTwoService.updateOrderStateByOrderNo(4,wnkOrdersTwo.getOrder_no()) <= 0){
                return new Result(Result.SUCCESS, "更新订单状态失败", null);
            }
            return new Result(Result.SUCCESS, "操作成功", null);
        } catch (Exception e) {
            return new Result(Result.FAIL, "操作失败", e.getMessage());
        }
    }


}
