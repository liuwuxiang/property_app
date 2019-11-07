package com.springmvc.controller.app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
import com.springmvc.service.WnkCommoditiesExpandHotelService;
import com.springmvc.service.WnkOrdersExpandHotelService;
import com.springmvc.service.impl.*;
import com.springmvc.utils.*;
import com.springmvc.utils.qrCode.QRCode;
import com.springmvc.utils.wechat.WechatPayLineUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: zhangfan
 * @Date: 2018/12/8 17:31
 * @Description:
 */
@Controller
@RequestMapping(value = "/app/v2.0.0")
public class WnkOrderTwoAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersBankCardService usersBankCardService;
    @Resource
    private UsersService usersService;
    @Resource
    private WnkCommoditiesService wnkCommoditiesService;
    @Resource
    private WnkOrdersTwoService wnkOrdersTwoService;
    @Resource
    private WnkOrderCommodityTwoService wnkOrderCommodityTwoService;
    @Resource
    private WnkCommoditySpecificationsService wnkCommoditySpecificationsService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;
    @Resource
    private WnkBusinessTypeOpenCardService wnkBusinessTypeOpenCardService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkSendIntegralUserService wnkSendIntegralUserService;
    @Resource
    private ExtensionMaterielService extensionMaterielService;
    @Resource
    private UserCouponsService userCouponsService;
    @Resource
    private GeneralIntegralExpenditureService generalIntegralExpenditureService;
    @Resource
    private IntegralDetailService integralDetailService;
    @Resource
    private WnkIntegralUserServer wnkIntegralUserServer;
    @Resource
    private WnkIntegralIncomeService wnkIntegralIncomeService;
    @Resource
    private WnkBusinessLevelIntegralDetailService wnkBusinessLevelIntegralDetailService;
    @Resource
    private WnkBusinessConsumptionIntegralDetailService wnkBusinessConsumptionIntegralDetailService;
    @Resource
    private SystemMessagesService systemMessagesService;
    @Resource
    private DoingSpreadUserReadRecordService doingSpreadUserReadRecordService;
    @Resource
    private DoingsSpreadService doingsSpreadService;
    @Resource
    private MemberLevelsService memberLevelsService;
    @Resource
    private UserIdCardAuthenticationService userIdCardAuthenticationService;
    @Resource
    private WnkSendIntegralDetailUserService wnkSendIntegralDetailUserService;
    @Resource
    private WnkOrderRefundRecordService wnkOrderRefundRecordService;
    @Resource
    private GeneralIntegralIncomeService generalIntegralIncomeService;
    @Resource
    private WnkOrdersExpandHotelServiceImpl wnkOrdersExpandHotelService;
    @Resource
    private WnkCommoditiesExpandHotelService wnkCommoditiesExpandHotelService;

    /**
     * 功能描述: 万能卡商品积分支付
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/8 5:34 PM
     */
    @RequestMapping(value = "/wnkCommodityIntegralPay", method = RequestMethod.POST, params = {"user_id", "commodity_id", "guige_id", "pay_pwd", "commodity_number", "general_integral", "send_integral", "coupon"})
    @ResponseBody
    public Result wnkCommodityIntegralPay(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer commodity_id, Integer guige_id, String pay_pwd, Integer commodity_number, Double general_integral, Double send_integral, Integer coupon) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                general_integral = Double.valueOf(String.format("%.2f", general_integral));
                send_integral = Double.valueOf(String.format("%.2f", send_integral));
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else if (!pay_pwd.equals(users.getPay_pwd())) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                } else {
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    WnkCommoditySpecifications wnkCommoditySpecifications = this.wnkCommoditySpecificationsService.selectById(guige_id);
                    if (wnkCommodities == null || wnkCommodities.getState() != 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品不存在或已下架");
                    } else if (wnkCommoditySpecifications == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品规格不存在");
                    } else if (wnkCommoditySpecifications.getInventory() != -1 && wnkCommoditySpecifications.getInventory() < commodity_number) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("当前规格库存不足");
                    } else {
                        Double price = wnkCommoditySpecifications.getPrice() * commodity_number;
                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkCommodities.getBusiness_id());
                        if (users.getMember_card_level() != -1) {
                            if (wnkStoreInformation != null) {
                                WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                                if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 0 && wnkBusinessType.getMake_wnk_state() == 1 && wnkCommoditySpecifications.getIs_wnk() == 0) {
                                    if (wnkBusinessType.getDiscount_type() == 0) {
                                        price = wnkBusinessType.getCommodifty_price() * commodity_number;
                                    } else {
                                        Double bili = wnkBusinessType.getCommodifty_price() / 100;
                                        price = wnkCommoditySpecifications.getPrice() * commodity_number * bili;
                                        price = Double.valueOf(String.format("%.2f", price));
                                    }
                                } else if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 1 && wnkBusinessType.getMake_wnk_state() == 1 && wnkCommoditySpecifications.getIs_wnk() == 0) {
                                    WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(user_id, wnkStoreInformation.getBusiness_id());
                                    if (wnkBusinessTypeOpenCard != null) {
                                        price = 0.00;
                                    }
                                }
                            }
                        }
                        WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkCommodities.getBusiness_id());
                        if (wnkBusinessAccount == null) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商户不存在");
                        } else if (wnkBusinessAccount.getLevel_integral() < price) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("当前商户积分不足");

                            if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")) {
                                String pushTitle = "商家等级积分不足";
                                String pushContent = "您可通用的等级积分目前已不足" + price + "个，为了避免影响用户到您店里正常消费，请您抽空升级或购买积分！谢谢!";
                                GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(), pushTitle, pushContent);
                            }
                        } else {
                            // 业务开始
                            Map<String, Object> userSendIntegralmap = wnkSendIntegralUserService.getUserIntegral(wnkCommodities.getBusiness_id(), user_id);

                            if (general_integral > 0.00 && users.getGeneral_integral() < general_integral) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("通用积分余额不足");

                                return result;
                            }
                            if (send_integral > 0.00 && (userSendIntegralmap == null || (Double) userSendIntegralmap.get("integral") < send_integral)) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("商家现金劵余额不足");

                                return result;
                            }


                            List<Map<Object, Object>> materielList = this.extensionMaterielService.selectMatensionByTypeId(wnkStoreInformation.getBusiness_type_id());
                            UserCoupons userCoupons = null;
                            if (coupon > 0) {
                                if (materielList.size() <= 0) {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("此类型暂不支持优惠券");

                                    return result;
                                } else {
                                    Integer materielId = (Integer) materielList.get(0).get("id");
                                    userCoupons = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId, user_id);
                                    if (materielList.size() >= 2) {
                                        Integer materielId2 = (Integer) materielList.get(1).get("id");
                                        UserCoupons userCoupons2 = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId2, user_id);
                                        if (userCoupons2 != null && userCoupons2.getSurplus_number() > userCoupons.getSurplus_number()) {
                                            userCoupons = userCoupons2;
                                        }
                                    }
                                    if (userCoupons == null || userCoupons.getSurplus_number() < coupon) {
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("优惠券余额不足");

                                        return result;
                                    }
                                }
                            }

                            //优惠券抵扣现金
                            Double couponDKCash = coupon * 9.9;
                            couponDKCash = Double.valueOf(String.format("%.2f", couponDKCash));
                            //剩余应支付现金
                            Double spurlesPayAmount = price - general_integral - send_integral - couponDKCash;
                            if (spurlesPayAmount > 0.00) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("此订单不可只支付积分");
                            } else {
                                WnkOrdersTwo wnkOrdersTwo = new WnkOrdersTwo();
                                wnkOrdersTwo.setBusiness_id(wnkCommodities.getBusiness_id());
                                wnkOrdersTwo.setUser_id(user_id);
                                wnkOrdersTwo.setOrder_no(UUDUtil.getOrderIdByUUId());
                                wnkOrdersTwo.setSubmit_time(new Date());
                                wnkOrdersTwo.setAmount(price);
                                wnkOrdersTwo.setPay_way(0);
                                wnkOrdersTwo.setState(0);
                                wnkOrdersTwo.setGeneral_integral(general_integral);
                                wnkOrdersTwo.setSend_integral(send_integral);
                                wnkOrdersTwo.setCoupon(coupon);
                                wnkOrdersTwo.setCash_amount(0.00);

                                int state = this.wnkOrdersTwoService.insertNewOrder(wnkOrdersTwo);
                                if (state <= 0) {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("下单失败");
                                } else {
                                    WnkOrderCommodityTwo wnkOrderCommodityTwo = new WnkOrderCommodityTwo();
                                    wnkOrderCommodityTwo.setOrder_id(wnkOrdersTwo.getId());
                                    wnkOrderCommodityTwo.setCommodity_id(commodity_id);
                                    wnkOrderCommodityTwo.setBuy_number(commodity_number);
                                    wnkOrderCommodityTwo.setCount_amount(price);
                                    wnkOrderCommodityTwo.setCommodity_guige_id(guige_id);
                                    int commodityAddState = this.wnkOrderCommodityTwoService.insertNewOrderCommodity(wnkOrderCommodityTwo);
                                    if (commodityAddState <= 0) {
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("下单失败");
                                    } else {
                                        //支付结果,true-支付成功,false-支付失败
                                        Boolean pay_status = true;
                                        if (general_integral > 0.00) {
                                            users.setGeneral_integral(users.getGeneral_integral() - general_integral);
                                        }
                                        int updateBalance = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                                        if (updateBalance <= 0) {
                                            pay_status = false;
                                        }

                                        if (send_integral > 0.00) {
                                            Integer updateSendIntegralBalance = this.wnkSendIntegralUserService.updateIntegral(user_id, wnkCommodities.getBusiness_id(), send_integral);
                                            if (updateSendIntegralBalance <= 0) {
                                                pay_status = false;
                                            }
                                        }

                                        if (coupon > 0 && userCoupons != null) {
                                            userCoupons.setSurplus_number(userCoupons.getSurplus_number() - coupon);
                                            Integer updateCouponBalance = this.userCouponsService.updateSurplusNumber(userCoupons);
                                            if (updateCouponBalance <= 0) {
                                                pay_status = false;
                                            }
                                        }
                                        if (pay_status == true) {
                                            Map<Object, Object> map = new HashMap<Object, Object>();
                                            map.put("order_id", wnkOrdersTwo.getId());

                                            result.setData(map);
                                            result.setStatus(Result.SUCCESS);
                                            result.setMsg("支付成功");

                                            if (users.getGetui_appid() != null && !users.getGetui_appid().equals("")) {
                                                String pushTitle = "支付成功";
                                                String pushContent = "您已支付成功，订单号为" + wnkOrdersTwo.getOrder_no() + "不要忘记使用哟！请在猛戳-订单中心-待使用查看";
                                                GeTuiUserUtil.pushUser(users.getGetui_appid(), pushTitle, pushContent);
                                            }

                                            this.wnkOrdersTwoService.updateOrderStateByOrderNo(1, wnkOrdersTwo.getOrder_no());
                                            //订单二维码
                                            Map<Object, Object> orderQrcodeMap = new HashMap<Object, Object>();
                                            orderQrcodeMap.put("user_id", wnkOrdersTwo.getUser_id());
                                            orderQrcodeMap.put("type", 2);
                                            orderQrcodeMap.put("order_no", wnkOrdersTwo.getOrder_no());
                                            JSONObject json = JSONObject.fromObject(orderQrcodeMap);
                                            String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                                            String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(), wnkQrcodeName);
                                            this.wnkOrdersTwoService.updateOrderQrCodeByOrderNo(wnkQrcodeName + ".png", wnkOrdersTwo.getOrder_no());
                                            this.wnkOrdersTwoService.updateOrderPayDateByOrderNo(new Date(), wnkOrdersTwo.getOrder_no());
                                            //更新规格库存
                                            if (wnkCommoditySpecifications.getInventory() != -1) {
                                                wnkCommoditySpecifications.setInventory(wnkCommoditySpecifications.getInventory() - commodity_number);
                                                this.wnkCommoditySpecificationsService.updateSpecificationStock(wnkCommoditySpecifications);
                                            }

                                            if (price > 0L && (general_integral > 0L || send_integral > 0L)) {

                                                //扣除等级积分数量并且插入记录
                                                WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail = new WnkBusinessLevelIntegralDetail();
                                                wnkBusinessLevelIntegralDetail.setBusiness_id(wnkCommodities.getBusiness_id());
                                                wnkBusinessLevelIntegralDetail.setName("客户消费");
                                                wnkBusinessLevelIntegralDetail.setIntegral_number(general_integral + send_integral);
                                                wnkBusinessLevelIntegralDetail.setTransactions_date(new Date());
                                                wnkBusinessLevelIntegralDetail.setTransactions_type(2);
                                                wnkBusinessLevelIntegralDetail.setUser_id(users.getId());
                                                wnkBusinessLevelIntegralDetail.setPay_type(5);

                                                wnkBusinessAccount.setLevel_integral(wnkBusinessAccount.getLevel_integral() - general_integral);
                                                this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                                                this.wnkBusinessLevelIntegralDetailService.insertNewRecordUserXF(wnkBusinessLevelIntegralDetail);


                                                Double kcLevelIntegralNumber = general_integral + send_integral;
                                                if (wnkStoreInformation.getGetui_appid() != null && !"".equals(wnkStoreInformation.getGetui_appid())) {
                                                    String pushTitle = "商品购买";
                                                    String pushContent = "用户成功购买了" + wnkCommodities.getName() + "商品，等级积分消费支出" + kcLevelIntegralNumber + "个，注意处理，请在猛戳商家版-订单-新订单查看";
                                                    GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(), pushTitle, pushContent);
                                                }
                                                if (general_integral > 0.00) {
                                                    GeneralIntegralExpenditure generalIntegralExpenditure = new GeneralIntegralExpenditure();
                                                    generalIntegralExpenditure.setName("商品购买");
                                                    generalIntegralExpenditure.setExpenditure_date(new Date());
                                                    generalIntegralExpenditure.setExpenditure_amount(general_integral);
                                                    generalIntegralExpenditure.setExpenditure_after_balance(users.getGeneral_integral());
                                                    generalIntegralExpenditure.setUser_id(user_id);
                                                    generalIntegralExpenditure.setExpenditure_type(4);
                                                    this.generalIntegralExpenditureService.insertExchangeSilverCoinRecord(generalIntegralExpenditure);
                                                }
                                                if (send_integral > 0.00) {
                                                    this.wnkSendIntegralDetailUserService.addIntegralRecord("商品购买", send_integral, users.getId(), wnkCommodities.getBusiness_id(), 1);
                                                    if (users.getGetui_appid() != null && !"".equals(users.getGetui_appid())) {
                                                        String pushTitle = "现金劵消费";
                                                        String pushContent = "您已成功使用" + send_integral + "现金劵兑换了" + wnkCommodities.getName() + "商品，请在猛戳-我的订单查看";
                                                        GeTuiUserUtil.pushUser(users.getGetui_appid(), pushTitle, pushContent);
                                                    }
                                                }
                                            }
                                        } else {
                                            result.setData("");
                                            result.setStatus(Result.FAIL);
                                            result.setMsg("支付失败");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("下单失败");
        }
        return result;
    }


    /**
     * 方法说明:万能卡商品积分支付-酒店类商家
     *
     * @param userId                 用户ID
     * @param businessId             商家ID
     * @param payPwd                 支付密码
     * @param commodityId            商品ID
     * @param commodityNumber        购买数量(入住天数)
     * @param mobile                 联系电话
     * @param coupon                 优惠劵积分使用金额
     * @param generalIntegral        通用积分使用金额
     * @param registerPeople         入住人信息
     * @param registerTime           预计到店信息
     * @param sendIntegral           现金劵积分使用金额
     * @param registerStartTimeStamp 入住时间戳
     * @param registerEndTimeStamp   离店时间戳
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @Date 2019/1/23
     **/
    @RequestMapping(value = "/wnkCommodityIntegralPayByHotel", method = RequestMethod.POST)
    @ResponseBody
    public Result wnkCommodityIntegralPayByHotel(HttpServletRequest request, HttpServletResponse response, Integer userId, String payPwd, Integer commodityId, Integer commodityNumber, Double generalIntegral, Double sendIntegral, Double coupon, String mobile, String registerTime, String registerPeople, Long registerStartTimeStamp, Long registerEndTimeStamp, Integer businessId) {
        try {
            if (LoginSessionCheckUtil.checkIsLogin(userId, 1, request, response, this.sessionIdsService) == null) {
                return new Result(Result.NOLOGIN, "此账户暂未登录", null);
            }
            Users users = this.usersService.selectById(userId);
            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(businessId);
            WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(businessId);
            if (users == null) {
                return new Result(Result.FAIL, "此用户不存在", null);
            }
            if (!payPwd.equals(users.getPay_pwd())) {
                return new Result(Result.FAIL, "支付密码不正确", null);
            }
            // 商品查询
            WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodityId);
            if (wnkCommodities == null) {
                return new Result(Result.FAIL, "商品不存在", null);
            }
            if (0 != wnkCommodities.getState()) {
                return new Result(Result.FAIL, "商品未上架或者已删除", null);
            }
            // 查询入住时间段酒店是否有剩余库存
            Map<String, Object> objectMap = new HashMap<>(3);
            objectMap.put("commoditiesId", commodityId);
            objectMap.put("joinTime", registerStartTimeStamp);
            objectMap.put("outTime", registerEndTimeStamp);
            objectMap.put("businessId", businessId);
            Integer inventory = this.wnkCommoditySpecificationsService.selectDayInventoryNumberByCommoditiesId(objectMap);
            if (inventory != -1 && inventory <= 0) {
                return new Result(Result.FAIL, "库存不足", null);
            }
            Map<Object, Object> Specifications = this.wnkCommoditySpecificationsService.selectByCommodityId(commodityId).get(0);
            // 积分 优惠券 现金券检查
            Map<String, Object> userIntegral = wnkSendIntegralUserService.getUserIntegral(businessId, userId);


            if (generalIntegral > 0 && generalIntegral > users.getGeneral_integral()) {
                return new Result(Result.FAIL, "通用积分不足", null);
            }

            if (sendIntegral > 0 && sendIntegral > (Double) userIntegral.get("integral")) {
                return new Result(Result.FAIL, "现金券不足", null);
            }
            List<Map<Object, Object>> materielList = this.extensionMaterielService.selectMatensionByTypeId(wnkStoreInformation.getBusiness_type_id());
            UserCoupons userCoupons = null;
            if (coupon > 0) {
                if (materielList.size() <= 0) {
                    return new Result(Result.FAIL, "此类型暂不支持优惠券", null);
                }
                Integer materielId = (Integer) materielList.get(0).get("id");
                userCoupons = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId, userId);
                if (materielList.size() >= 2) {
                    Integer materielId2 = (Integer) materielList.get(1).get("id");
                    UserCoupons userCoupons2 = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId2, userId);
                    if (userCoupons2 != null && userCoupons2.getSurplus_number() > userCoupons.getSurplus_number()) {
                        userCoupons = userCoupons2;
                    }
                }
                if (userCoupons == null || coupon > userCoupons.getSurplus_number()) {
                    return new Result(Result.FAIL, "优惠券余额不足", null);
                }
            }
            // 计算入住天数
            registerStartTimeStamp = TimeUtil.delHMS(registerStartTimeStamp);
            registerEndTimeStamp = TimeUtil.delHMS(registerEndTimeStamp);
            Double checkInTime = Math.floor((registerEndTimeStamp - registerStartTimeStamp) / (24 * 3600 * 1000));
            // 计算入住人数
            Integer registerPeopleNumber = registerPeople.split(",").length;
            // 计算总价
            Double totalPric = checkInTime * registerPeopleNumber * (Double) Specifications.get("price");
            // 是否能全积分支付
            Double surplusPayAmount = totalPric - generalIntegral - sendIntegral - (coupon * 9.9);
            if (surplusPayAmount > 0.00) {
                return new Result(Result.FAIL, "此订单不可只支付积分", null);
            }
            // 生成订单
            WnkOrdersTwo wnkOrdersTwo = new WnkOrdersTwo();
            wnkOrdersTwo.setBusiness_id(wnkCommodities.getBusiness_id());
            wnkOrdersTwo.setUser_id(userId);
            wnkOrdersTwo.setOrder_no(UUDUtil.getOrderIdByUUId());
            wnkOrdersTwo.setSubmit_time(new Date());
            wnkOrdersTwo.setAmount(totalPric);
            wnkOrdersTwo.setPay_way(0);
            wnkOrdersTwo.setState(0);
            wnkOrdersTwo.setGeneral_integral(generalIntegral);
            wnkOrdersTwo.setSend_integral(sendIntegral);
            wnkOrdersTwo.setCoupon(coupon.intValue());
            wnkOrdersTwo.setCash_amount(0.00);
            wnkOrdersTwo.setOrder_type(1);
            int state = this.wnkOrdersTwoService.insertNewOrder(wnkOrdersTwo);
            if (state <= 0) {
                return new Result(Result.FAIL, "下单失败", null);
            }

            WnkOrderCommodityTwo wnkOrderCommodityTwo = new WnkOrderCommodityTwo();
            wnkOrderCommodityTwo.setOrder_id(wnkOrdersTwo.getId());
            wnkOrderCommodityTwo.setCommodity_id(commodityId);
            wnkOrderCommodityTwo.setBuy_number(checkInTime.intValue() * registerPeopleNumber);
            wnkOrderCommodityTwo.setCount_amount(totalPric);
            wnkOrderCommodityTwo.setCommodity_guige_id(Integer.valueOf(Specifications.get("id").toString()));
            int commodityAddState = this.wnkOrderCommodityTwoService.insertNewOrderCommodity(wnkOrderCommodityTwo);
            if (commodityAddState <= 0) {
                return new Result(Result.FAIL, "下单失败", null);
            }

            // 扣除用户各项积分
            if (generalIntegral > 0.00) {
                users.setGeneral_integral(users.getGeneral_integral() - generalIntegral);
                if (this.usersService.updateUserTYAndXFAndSilverCoinBalance(users) <= 0) {
                    return new Result(Result.FAIL, "支付失败", null);
                }
            }

            if (sendIntegral > 0.00) {
                if (this.wnkSendIntegralUserService.updateIntegral(userId, wnkCommodities.getBusiness_id(), sendIntegral) <= 0) {
                    return new Result(Result.FAIL, "支付失败", null);
                }
            }


            if (coupon > 0 && userCoupons != null) {
                userCoupons.setSurplus_number(userCoupons.getSurplus_number() - coupon.intValue());
                if (this.userCouponsService.updateSurplusNumber(userCoupons) <= 0) {
                    return new Result(Result.FAIL, "支付失败", null);
                }
            }



            //发送用户个推
            if (users.getGetui_appid() != null && !users.getGetui_appid().equals("")) {
                String pushTitle = "支付成功";
                String pushContent = "您已支付成功，订单号为" + wnkOrdersTwo.getOrder_no() + "不要忘记使用哟！请在猛戳-订单中心-待使用查看";
                GeTuiUserUtil.pushUser(users.getGetui_appid(), pushTitle, pushContent);
            }
            // 更新订单信息为待商家确认
            this.wnkOrdersTwoService.updateOrderStateByOrderNo(3, wnkOrdersTwo.getOrder_no());

            //订单二维码
            Map<Object, Object> orderQrcodeMap = new HashMap<Object, Object>();
            orderQrcodeMap.put("user_id", wnkOrdersTwo.getUser_id());
            orderQrcodeMap.put("type", 2);
            orderQrcodeMap.put("order_no", wnkOrdersTwo.getOrder_no());
            JSONObject json = JSONObject.fromObject(orderQrcodeMap);
            String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
            String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(), wnkQrcodeName);
            this.wnkOrdersTwoService.updateOrderQrCodeByOrderNo(wnkQrcodeName + ".png", wnkOrdersTwo.getOrder_no());
            this.wnkOrdersTwoService.updateOrderPayDateByOrderNo(new Date(), wnkOrdersTwo.getOrder_no());


            // 需要更新每天的库存 更新规格库存
            Map<String, Object> updateMap = new HashMap<>(4);
            updateMap.put("commoditiesId", commodityId);
            updateMap.put("joinTime", registerStartTimeStamp);
            updateMap.put("outTime", registerEndTimeStamp);
            updateMap.put("businessId", businessId);
            this.wnkCommoditySpecificationsService.updateDayInventoryNumberByCommoditiesIdAndJoinAndOutTimeAndBusinessId(updateMap);



            if (totalPric > 0L && (generalIntegral > 0L || sendIntegral > 0L)) {

                //扣除等级积分数量并且插入记录
                WnkBusinessLevelIntegralDetail  wnkBusinessLevelIntegralDetail = new WnkBusinessLevelIntegralDetail();
                wnkBusinessLevelIntegralDetail.setBusiness_id(wnkCommodities.getBusiness_id());
                wnkBusinessLevelIntegralDetail.setName("客户消费");
                wnkBusinessLevelIntegralDetail.setIntegral_number(generalIntegral + sendIntegral);
                wnkBusinessLevelIntegralDetail.setTransactions_date(new Date());
                wnkBusinessLevelIntegralDetail.setTransactions_type(2);
                wnkBusinessLevelIntegralDetail.setUser_id(users.getId());
                wnkBusinessLevelIntegralDetail.setPay_type(5);

                wnkBusinessAccount.setLevel_integral(wnkBusinessAccount.getLevel_integral() - generalIntegral);
                this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                this.wnkBusinessLevelIntegralDetailService.insertNewRecordUserXF(wnkBusinessLevelIntegralDetail);


                Double kcLevelIntegralNumber = generalIntegral + sendIntegral;
                if (wnkStoreInformation.getGetui_appid() != null && !"".equals(wnkStoreInformation.getGetui_appid())) {
                    String pushTitle = "商品购买";
                    String pushContent = "用户成功购买了" + wnkCommodities.getName() + "商品，等级积分消费支出" + kcLevelIntegralNumber + "个，注意处理，请在猛戳商家版-订单-新订单查看";
                    GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(), pushTitle, pushContent);
                }
                if (generalIntegral > 0.00) {
                    GeneralIntegralExpenditure generalIntegralExpenditure = new GeneralIntegralExpenditure();
                    generalIntegralExpenditure.setName("商品购买");
                    generalIntegralExpenditure.setExpenditure_date(new Date());
                    generalIntegralExpenditure.setExpenditure_amount(generalIntegral);
                    generalIntegralExpenditure.setExpenditure_after_balance(users.getGeneral_integral());
                    generalIntegralExpenditure.setUser_id(userId);
                    generalIntegralExpenditure.setExpenditure_type(4);
                    this.generalIntegralExpenditureService.insertExchangeSilverCoinRecord(generalIntegralExpenditure);
                }
                if (sendIntegral > 0.00) {
                    this.wnkSendIntegralDetailUserService.addIntegralRecord("商品购买", sendIntegral, users.getId(), wnkCommodities.getBusiness_id(), 1);
                    if (users.getGetui_appid() != null && !"".equals(users.getGetui_appid())) {
                        String pushTitle = "现金劵消费";
                        String pushContent = "您已成功使用" + sendIntegral + "现金劵兑换了" + wnkCommodities.getName() + "商品，请在猛戳-我的订单查看";
                        GeTuiUserUtil.pushUser(users.getGetui_appid(), pushTitle, pushContent);
                    }
                }


            }
            // 添加订单扩展信息 - 酒店类商家
            WnkOrdersExpandHotel hotel = new WnkOrdersExpandHotel();
            hotel.setUser_id(userId);
            hotel.setBusiness_id(businessId);
            hotel.setCommodity_id(commodityId);
            hotel.setOrder_id(wnkOrdersTwo.getId());
            hotel.setMobile(mobile);
            hotel.setGeneral_integral_number(generalIntegral);
            hotel.setSend_integral_number(sendIntegral);
            hotel.setCoupon(coupon);
            hotel.setRegister_people(registerPeople);
            hotel.setRegister_time(registerTime);
            hotel.setRegister_start_time_stamp(Double.valueOf(registerStartTimeStamp));
            hotel.setRegister_end_time_stamp(Double.valueOf(registerEndTimeStamp));
            this.wnkOrdersExpandHotelService.insertInfo(hotel);

            return new Result(Result.SUCCESS, "预定成功", null);
        } catch (Exception e) {
            return new Result(Result.FAIL, "下单失败", e.getMessage());
        }
    }


    // 通过user_id获取用户基础信息
    @RequestMapping(value = "/getUserBaseInformation", method = RequestMethod.POST, params = {"user_id", "commodity_id"})
    @ResponseBody
    public Result getUserBaseInformation(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer commodity_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                if (wnkCommodities == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商户不存在");

                    return result;
                }
                WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkCommodities.getBusiness_id());
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else if (wnkStoreInformation == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商户不存在");
                } else {
                    MemberLevels memberLevels = this.memberLevelsService.selectById(users.getMember_level_id());
                    Users recommendUsers = null;
                    WnkBusinessAccount wnkBusinessAccount = null;
                    if (users.getRecommend_user_id() != -1 && users.getRecommend_type() == 0) {
                        recommendUsers = this.usersService.selectById(users.getRecommend_user_id());
                    } else if (users.getRecommend_user_id() != -1 && users.getRecommend_type() == 1) {
                        wnkBusinessAccount = this.wnkBusinessAccountService.selectById(users.getRecommend_user_id());
                    }

                    UserIdCardAuthentication userIdCardAuthentication = this.userIdCardAuthenticationService.selectAuthenticationByUserId(user_id);
                    Map<Object, Object> userMap = new HashMap<Object, Object>();
                    userMap.put("user_id", users.getId());
                    userMap.put("header", ImageToolsController.imageShowURL + "?imageid=" + users.getHeader());
                    userMap.put("nick_name", users.getNick_name());
                    userMap.put("sex", users.getSex());
                    if (users.getRecommend_type() == 0) {
                        if (recommendUsers != null) {
                            userMap.put("recommend_mobile", users.getRecommend_user_id() == -1 ? "无推荐人" : recommendUsers.getMobile());
                        } else {
                            userMap.put("recommend_mobile", "无推荐人");
                        }
                    } else {
                        if (wnkBusinessAccount != null) {
                            userMap.put("recommend_mobile", users.getRecommend_user_id() == -1 ? "无推荐人" : wnkBusinessAccount.getMobile());
                        } else {
                            userMap.put("recommend_mobile", "无推荐人");
                        }
                    }
                    userMap.put("level_icon", memberLevels == null ? "" : ImageToolsController.imageShowURL + "?imageid=" + memberLevels.getIcon_url());
                    userMap.put("level_name", memberLevels == null ? "" : memberLevels.getName());
                    userMap.put("consumption_integral", users.getConsumption_integral());
                    userMap.put("general_integral", users.getGeneral_integral());
                    userMap.put("team_members_number", this.usersService.selectUserByRecommendUserId(user_id).size());
                    userMap.put("mobile", users.getMobile());
                    userMap.put("email", users.getEmail());
                    userMap.put("is_microfinance", users.getIs_microfinance());
                    userMap.put("user_integral", users.getUser_integral());
                    userMap.put("my_rose", users.getSilver_coin_balance());
                    // 我的积分
                    userMap.put("user_integral", users.getUser_integral());
                    if (userIdCardAuthentication == null) {
                        //未认证
                        userMap.put("id_card_state", -1);
                    } else if (userIdCardAuthentication.getState() == 0 || userIdCardAuthentication.getState() == 1) {
                        //已提交认证
                        userMap.put("id_card_state", 0);
                    }
                    if (users.getMember_card_level() == -1) {
                        userMap.put("member_card_name", "普通会员");
                        userMap.put("wnk_state", -1);
                    } else if (users.getMember_card_level() == 0) {
                        userMap.put("member_card_name", "银卡会员");
                        userMap.put("wnk_state", 0);
                    } else if (users.getMember_card_level() == 1) {
                        userMap.put("member_card_name", "金卡会员");
                        userMap.put("wnk_state", 0);
                    }
                    if (users.getPay_pwd() == null || users.getPay_pwd().equals("")) {
                        userMap.put("is_pay_state", 0);
                    } else {
                        userMap.put("is_pay_state", 1);
                    }
                    Double send_integral = this.wnkSendIntegralUserService.selectUserCountIntegralByUserId(user_id);
                    if (send_integral == null) {
                        send_integral = 0.00;
                    }
                    userMap.put("send_integral", send_integral);
                    Integer business_integral = this.wnkIntegralUserServer.selectUserCountIntegralByUserId(user_id);
                    if (business_integral == null) {
                        business_integral = 0;
                    }

                    //计算用户未读消息条数STR
                    Integer no_read_message_count = 0;
                    List<Map<Object, Object>> systemMessageList = this.systemMessagesService.selectUserAllMessage(user_id);
                    //所有会员和商家均可查看的推广消息
                    List<Map<Object, Object>> allUserCanLookList = this.doingsSpreadService.selectNotDownTextMessageAndExaminePassJurisdictionForAllUserAndBusinessOrUser();
                    //只有商家会员可查看的推广消息
                    List<Map<Object, Object>> businessMemeberCanLookList = null;
                    if (users.getRecommend_type() == 1 && users.getRecommend_user_id() != -1) {
                        businessMemeberCanLookList = this.doingsSpreadService.selectNotDownTextMessageAndExaminePassJurisdictionForBusinessMember(users.getRecommend_user_id());
                        for (Integer index = 0; index < businessMemeberCanLookList.size(); index++) {
                            allUserCanLookList.add(businessMemeberCanLookList.get(index));
                        }
                    }
                    for (Integer index = 0; index < systemMessageList.size(); index++) {
                        Map<Object, Object> map = systemMessageList.get(index);
                        Integer read_state = (Integer) map.get("read_status");
                        if (read_state == 0) {
                            no_read_message_count++;
                        }
                    }
                    for (Integer index = 0; index < allUserCanLookList.size(); index++) {
                        Map<Object, Object> map = allUserCanLookList.get(index);
                        Long message_id = (Long) map.get("id");
                        DoingSpreadUserReadRecord doingSpreadUserReadRecord = this.doingSpreadUserReadRecordService.selectByUserIdAndMessageId(user_id, message_id.intValue());
                        if (doingSpreadUserReadRecord == null) {
                            no_read_message_count++;
                        }
                    }
                    userMap.put("no_read_message_count", no_read_message_count);
                    userMap.put("business_integral", business_integral);

                    List<Map<Object, Object>> materielList = this.extensionMaterielService.selectMatensionByTypeId(wnkStoreInformation.getBusiness_type_id());
                    if (materielList.size() <= 0) {
                        userMap.put("coupon_number", 0);
                    } else {
                        Integer materielId = (Integer) materielList.get(0).get("id");
                        UserCoupons userCoupons = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId, user_id);
                        if (materielList.size() >= 2) {
                            Integer materielId2 = (Integer) materielList.get(1).get("id");
                            UserCoupons userCoupons2 = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId2, user_id);
                            if (userCoupons2 != null && userCoupons2.getSurplus_number() > userCoupons.getSurplus_number()) {
                                userCoupons = userCoupons2;
                            }
                        }
                        if (userCoupons == null) {
                            userMap.put("coupon_number", 0);
                        } else {
                            userMap.put("coupon_number", userCoupons.getSurplus_number());
                        }
                    }
                    //赠送积分余额
                    // 业务开始
                    Map<String, Object> userSendIntegralmap = wnkSendIntegralUserService.getUserIntegral(wnkCommodities.getBusiness_id(), user_id);
                    if (userSendIntegralmap == null) {
                        userMap.put("send_integral_balance", 0);
                    } else {
                        userMap.put("send_integral_balance", (Double) userSendIntegralmap.get("integral"));
                    }
                    result.setData(userMap);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
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
     * 功能描述: 万能卡商品微信支付
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/8 5:34 PM
     */
    @RequestMapping(value = "/wnkCommodityWXlPay", method = RequestMethod.POST, params = {"user_id", "commodity_id", "guige_id", "pay_pwd", "commodity_number", "general_integral", "send_integral", "coupon"})
    @ResponseBody
    public Result wnkCommodityWXlPay(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer commodity_id, Integer guige_id, String pay_pwd, Integer commodity_number, Double general_integral, Double send_integral, Integer coupon) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                general_integral = Double.valueOf(String.format("%.2f", general_integral));
                send_integral = Double.valueOf(String.format("%.2f", send_integral));
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else if (!pay_pwd.equals(users.getPay_pwd()) && (general_integral > 0.00 || send_integral > 0.00 || coupon > 0)) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                } else {
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    WnkCommoditySpecifications wnkCommoditySpecifications = this.wnkCommoditySpecificationsService.selectById(guige_id);
                    if (wnkCommodities == null || wnkCommodities.getState() != 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品不存在或已下架");
                    } else if (wnkCommoditySpecifications == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品规格不存在");
                    } else if (wnkCommoditySpecifications.getInventory() != -1 && wnkCommoditySpecifications.getInventory() < commodity_number) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("当前规格库存不足");
                    } else {
                        Double price = wnkCommoditySpecifications.getPrice() * commodity_number;
                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkCommodities.getBusiness_id());
                        if (users.getMember_card_level() != -1) {
                            if (wnkStoreInformation != null) {
                                WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                                if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 0 && wnkBusinessType.getMake_wnk_state() == 1 && wnkCommoditySpecifications.getIs_wnk() == 0) {
                                    if (wnkBusinessType.getDiscount_type() == 0) {
                                        price = wnkBusinessType.getCommodifty_price() * commodity_number;
                                    } else {
                                        Double bili = wnkBusinessType.getCommodifty_price() / 100;
                                        price = wnkCommoditySpecifications.getPrice() * commodity_number * bili;
                                        price = Double.valueOf(String.format("%.2f", price));
                                    }
                                } else if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 1 && wnkBusinessType.getMake_wnk_state() == 1 && wnkCommoditySpecifications.getIs_wnk() == 0) {
                                    WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(user_id, wnkStoreInformation.getBusiness_id());
                                    if (wnkBusinessTypeOpenCard != null) {
                                        price = 0.00;
                                    }
                                }
                            }
                        }
                        WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkCommodities.getBusiness_id());
                        if (wnkBusinessAccount == null) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商户不存在");
                        } else if (wnkBusinessAccount.getLevel_integral() < price) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("当前商户积分不足");

                            if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")) {
                                String pushTitle = "商家等级积分不足";
                                String pushContent = "您可通用的等级积分目前已不足" + price + "个，为了避免影响用户到您店里正常消费，请您抽空升级或购买积分！谢谢!";
                                GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(), pushTitle, pushContent);
                            }
                        } else {
                            // 业务开始
                            Map<String, Object> userSendIntegralmap = wnkSendIntegralUserService.getUserIntegral(wnkCommodities.getBusiness_id(), user_id);

                            if (general_integral > 0.00 && users.getGeneral_integral() < general_integral) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("通用积分余额不足");

                                return result;
                            }
                            if (send_integral > 0.00 && (userSendIntegralmap == null || (Double) userSendIntegralmap.get("integral") < send_integral)) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("商家现金劵余额不足");

                                return result;
                            }
                            List<Map<Object, Object>> materielList = this.extensionMaterielService.selectMatensionByTypeId(wnkStoreInformation.getBusiness_type_id());
                            if (coupon > 0) {
                                if (materielList.size() <= 0) {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("此类型暂不支持优惠券");

                                    return result;
                                } else {
                                    Integer materielId = (Integer) materielList.get(0).get("id");
                                    UserCoupons userCoupons = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId, user_id);
                                    if (materielList.size() >= 2) {
                                        Integer materielId2 = (Integer) materielList.get(1).get("id");
                                        UserCoupons userCoupons2 = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId2, user_id);
                                        if (userCoupons2 != null && userCoupons2.getSurplus_number() > userCoupons.getSurplus_number()) {
                                            userCoupons = userCoupons2;
                                        }
                                    }
                                    if (userCoupons == null || userCoupons.getSurplus_number() < coupon) {
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("优惠券余额不足");

                                        return result;
                                    }
                                }
                            }

                            //优惠券抵扣现金
                            Double couponDKCash = coupon * 9.9;
                            couponDKCash = Double.valueOf(String.format("%.2f", couponDKCash));
                            //剩余应支付现金
                            Double spurlesPayAmount = price - general_integral - send_integral - couponDKCash;
                            spurlesPayAmount = Double.valueOf(String.format("%.2f", spurlesPayAmount));
                            WnkOrdersTwo wnkOrdersTwo = new WnkOrdersTwo();
                            wnkOrdersTwo.setBusiness_id(wnkCommodities.getBusiness_id());
                            wnkOrdersTwo.setUser_id(user_id);
                            wnkOrdersTwo.setOrder_no(UUDUtil.getOrderIdByUUId());
                            wnkOrdersTwo.setSubmit_time(new Date());
                            wnkOrdersTwo.setAmount(price);
                            wnkOrdersTwo.setPay_way(1);
                            wnkOrdersTwo.setState(0);
                            wnkOrdersTwo.setGeneral_integral(general_integral);
                            wnkOrdersTwo.setSend_integral(send_integral);
                            wnkOrdersTwo.setCoupon(coupon);
                            wnkOrdersTwo.setCash_amount(spurlesPayAmount);

                            int state = this.wnkOrdersTwoService.insertNewOrder(wnkOrdersTwo);
                            if (state <= 0) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("下单失败");
                            } else {
                                WnkOrderCommodityTwo wnkOrderCommodityTwo = new WnkOrderCommodityTwo();
                                wnkOrderCommodityTwo.setOrder_id(wnkOrdersTwo.getId());
                                wnkOrderCommodityTwo.setCommodity_id(commodity_id);
                                wnkOrderCommodityTwo.setBuy_number(commodity_number);
                                wnkOrderCommodityTwo.setCount_amount(price);
                                wnkOrderCommodityTwo.setCommodity_guige_id(guige_id);
                                int commodityAddState = this.wnkOrderCommodityTwoService.insertNewOrderCommodity(wnkOrderCommodityTwo);
                                if (commodityAddState <= 0) {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("下单失败");
                                } else {
                                    Map<String, Object> resutMap = WechatPayLineUtil.wechatPayWnkCommodityAppTwo(wnkOrdersTwo.getOrder_no(), spurlesPayAmount, request, response);
                                    if ((Boolean) resutMap.get("status") == true) {
                                        SortedMap<String, Object> parameterMap = (SortedMap<String, Object>) resutMap.get("wx_config");
                                        JSONObject json = JSONObject.fromObject(parameterMap);
                                        Integer timestamp = (Integer) json.get("timestamp");

                                        String payData = "{\"appid\":\"" + json.get("appid") + "\",\"noncestr\":\"" + json.get("noncestr") + "\",\"package\":\"Sign=WXPay\",\"partnerid\":\"" + json.get("partnerid") + "\",\"prepayid\":\"" + json.get("prepayid") + "\",\"timestamp\":" + timestamp + ",\"sign\":\"" + json.get("sign") + "\"}";
                                        Map<String, Object> returnMap = new HashMap<String, Object>();
                                        returnMap.put("config", payData);
                                        returnMap.put("order_id", wnkOrdersTwo.getId());
                                        result.setData(returnMap);
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("下单成功");

                                        //更新规格库存
                                        if (wnkCommoditySpecifications.getInventory() != -1) {
                                            wnkCommoditySpecifications.setInventory(wnkCommoditySpecifications.getInventory() - commodity_number);
                                            this.wnkCommoditySpecificationsService.updateSpecificationStock(wnkCommoditySpecifications);
                                        }

                                    } else {
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        String msg = (String) resutMap.get("msg");
                                        if (msg == null || msg.equals("")) {
                                            result.setMsg("未知错误");
                                        } else {
                                            result.setMsg((String) resutMap.get("msg"));
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
            result.setMsg("下单失败");
        }
        return result;
    }


    /**
     * 功能描述: 万能卡商品微信支付 - 酒店类商家
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/8 5:34 PM
     */
    @RequestMapping(value = "/wnkCommodityWXlPayByHotel", method = RequestMethod.POST)
    @ResponseBody
    public Result wnkCommodityWXlPayByHotel(HttpServletRequest request, HttpServletResponse response, Integer userId, String payPwd, Integer commodityId, Integer commodityNumber, Double generalIntegral, Double sendIntegral, Double coupon, String mobile, String registerTime, String registerPeople, Long registerStartTimeStamp, Long registerEndTimeStamp, Integer businessId) {
        try {
            if (LoginSessionCheckUtil.checkIsLogin(userId, 1, request, response, this.sessionIdsService) == null) {
                return new Result(Result.NOLOGIN, "此账户暂未登录", null);
            }
            Users users = this.usersService.selectById(userId);
            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(businessId);
            WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(businessId);
            if (users == null) {
                return new Result(Result.FAIL, "此用户不存在", null);
            }
            // 商品查询
            WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodityId);
            if (wnkCommodities == null) {
                return new Result(Result.FAIL, "商品不存在", null);
            }
            if (0 != wnkCommodities.getState()) {
                return new Result(Result.FAIL, "商品未上架或者已删除", null);
            }
            // 查询入住时间段酒店是否有剩余库存
            Map<String, Object> objectMap = new HashMap<>(3);
            objectMap.put("commoditiesId", commodityId);
            objectMap.put("joinTime", registerStartTimeStamp);
            objectMap.put("outTime", registerEndTimeStamp);
            objectMap.put("businessId", businessId);
            Integer inventory = this.wnkCommoditySpecificationsService.selectDayInventoryNumberByCommoditiesId(objectMap);
            if (inventory != -1 && inventory <= 0) {
                return new Result(Result.FAIL, "库存不足", null);
            }
            Map<Object, Object> Specifications = this.wnkCommoditySpecificationsService.selectByCommodityId(commodityId).get(0);
            // 积分 优惠券 现金券检查
            Map<String, Object> userIntegral = wnkSendIntegralUserService.getUserIntegral(businessId, userId);

            if (userIntegral == null) {
                return new Result(Result.FAIL, "获取用户相关优惠券信息失败", null);
            }

            if (generalIntegral > 0 && generalIntegral > users.getGeneral_integral()) {
                return new Result(Result.FAIL, "通用积分不足", null);
            }

            if (sendIntegral > 0 && sendIntegral > (Double) userIntegral.get("integral")) {
                return new Result(Result.FAIL, "现金券不足", null);
            }
            List<Map<Object, Object>> materielList = this.extensionMaterielService.selectMatensionByTypeId(wnkStoreInformation.getBusiness_type_id());
            UserCoupons userCoupons = null;
            if (coupon > 0) {
                if (materielList.size() <= 0) {
                    return new Result(Result.FAIL, "此类型暂不支持优惠券", null);
                }
                Integer materielId = (Integer) materielList.get(0).get("id");
                userCoupons = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId, userId);
                if (materielList.size() >= 2) {
                    Integer materielId2 = (Integer) materielList.get(1).get("id");
                    UserCoupons userCoupons2 = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId2, userId);
                    if (userCoupons2 != null && userCoupons2.getSurplus_number() > userCoupons.getSurplus_number()) {
                        userCoupons = userCoupons2;
                    }
                }
                if (userCoupons == null || coupon > userCoupons.getSurplus_number()) {
                    return new Result(Result.FAIL, "优惠券余额不足", null);
                }
            }
            // 计算入住天数
            registerStartTimeStamp = TimeUtil.delHMS(registerStartTimeStamp);
            registerEndTimeStamp = TimeUtil.delHMS(registerEndTimeStamp);
            Double checkInTime = Math.floor((registerEndTimeStamp - registerStartTimeStamp) / (24 * 3600 * 1000));
            // 计算入住人数
            Integer registerPeopleNumber = registerPeople.split(",").length;
            // 计算总价
            Double totalPric = checkInTime * registerPeopleNumber * (Double) Specifications.get("price");
            // 剩余支付现金
            Double surplusPayAmount = totalPric - generalIntegral - sendIntegral - (coupon * 9.9);
            // 开始订单处理

            // 生成订单
            WnkOrdersTwo wnkOrdersTwo = new WnkOrdersTwo();
            wnkOrdersTwo.setBusiness_id(wnkCommodities.getBusiness_id());
            wnkOrdersTwo.setUser_id(userId);
            wnkOrdersTwo.setOrder_no(UUDUtil.getOrderIdByUUId());
            wnkOrdersTwo.setSubmit_time(new Date());
            wnkOrdersTwo.setAmount(totalPric);
            wnkOrdersTwo.setPay_way(0);
            wnkOrdersTwo.setState(0);
            wnkOrdersTwo.setGeneral_integral(generalIntegral);
            wnkOrdersTwo.setSend_integral(sendIntegral);
            wnkOrdersTwo.setCoupon(coupon.intValue());
            wnkOrdersTwo.setCash_amount(0.00);
            wnkOrdersTwo.setOrder_type(1);
            int state = this.wnkOrdersTwoService.insertNewOrder(wnkOrdersTwo);
            if (state <= 0) {
                return new Result(Result.FAIL, "下单失败", null);
            }

            WnkOrderCommodityTwo wnkOrderCommodityTwo = new WnkOrderCommodityTwo();
            wnkOrderCommodityTwo.setOrder_id(wnkOrdersTwo.getId());
            wnkOrderCommodityTwo.setCommodity_id(commodityId);
            wnkOrderCommodityTwo.setBuy_number(checkInTime.intValue() * registerPeopleNumber);
            wnkOrderCommodityTwo.setCount_amount(totalPric);
            wnkOrderCommodityTwo.setCommodity_guige_id(Integer.valueOf(Specifications.get("id").toString()));
            int commodityAddState = this.wnkOrderCommodityTwoService.insertNewOrderCommodity(wnkOrderCommodityTwo);
            if (commodityAddState <= 0) {
                return new Result(Result.FAIL, "下单失败", null);
            }
            // 添加订单扩展信息 - 酒店类商家
            WnkOrdersExpandHotel hotel = new WnkOrdersExpandHotel();
            hotel.setUser_id(userId);
            hotel.setBusiness_id(businessId);
            hotel.setCommodity_id(commodityId);
            hotel.setOrder_id(wnkOrdersTwo.getId());
            hotel.setMobile(mobile);
            hotel.setGeneral_integral_number(generalIntegral);
            hotel.setSend_integral_number(sendIntegral);
            hotel.setCoupon(coupon);
            hotel.setRegister_people(registerPeople);
            hotel.setRegister_time(registerTime);
            hotel.setRegister_start_time_stamp(Double.valueOf(registerStartTimeStamp));
            hotel.setRegister_end_time_stamp(Double.valueOf(registerEndTimeStamp));
            if (this.wnkOrdersExpandHotelService.insertInfo(hotel) <= 0) {
                return new Result(Result.FAIL, "下单失败", null);
            }

            Map<String, Object> resutMap = WechatPayLineUtil.wechatPayWnkCommodityAppTwo(wnkOrdersTwo.getOrder_no(), surplusPayAmount, request, response);
            if ((Boolean) resutMap.get("status") == true) {
                SortedMap<String, Object> parameterMap = (SortedMap<String, Object>) resutMap.get("wx_config");
                JSONObject json = JSONObject.fromObject(parameterMap);
                Integer timestamp = (Integer) json.get("timestamp");

                String payData = "{\"appid\":\"" + json.get("appid") + "\",\"noncestr\":\"" + json.get("noncestr") + "\",\"package\":\"Sign=WXPay\",\"partnerid\":\"" + json.get("partnerid") + "\",\"prepayid\":\"" + json.get("prepayid") + "\",\"timestamp\":" + timestamp + ",\"sign\":\"" + json.get("sign") + "\"}";
                Map<String, Object> returnMap = new HashMap<String, Object>();
                returnMap.put("config", payData);
                returnMap.put("order_id", wnkOrdersTwo.getId());
                return new Result(Result.SUCCESS, "下单成功", returnMap);
            } else {
                String msg = (String) resutMap.get("msg");
                if (msg == null || msg.equals("")) {
                    return new Result(Result.FAIL, "未知错误", null);
                } else {
                    return new Result(Result.FAIL, (String) resutMap.get("msg"), null);
                }
            }
        } catch (Exception e) {
            return new Result(Result.FAIL, "下单失败", e.getMessage());
        }
    }


    // 获取购买订单详情
    @RequestMapping(value = "/wnkBuyOrderDetail", method = RequestMethod.POST, params = {"user_id", "order_id"})
    @ResponseBody
    public Result wnkBuyOrderDetail(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer order_id) {
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


    // 获取某用户所有的购买订单
    @RequestMapping(value = "/wnkWnkMyOrder", method = RequestMethod.POST, params = {"user_id", "state"})
    @ResponseBody
    public Result wnkWnkMyOrder(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer state) {
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
                    List<Map<Object, Object>> orderList = this.wnkOrdersTwoService.userSelectUserOrder(user_id, state);
                    if (state == 3) {
                        orderList.addAll(this.wnkOrdersTwoService.userSelectUserOrder(user_id, 4));
                        orderList.addAll(this.wnkOrdersTwoService.userSelectUserOrder(user_id, 5));
                        orderList.addAll(this.wnkOrdersTwoService.userSelectUserOrder(user_id, 6));
                    }
                    if (orderList.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无订单");
                    } else {
                        Double count_amount = 0.00;
                        for (Integer index = 0; index < orderList.size(); index++) {
                            Map<Object, Object> map = orderList.get(index);
                            List<Map<Object, Object>> commodity_list = this.wnkOrderCommodityTwoService.selectCommodityByOrderId((Integer) map.get("id"));
                            map.put("commodity_list", commodity_list);
                            map.put("commodity_count", commodity_list.size());
                            count_amount = count_amount + (Double) map.get("amount");
                        }
                        Map<Object, Object> returnMap = new HashMap<Object, Object>();
                        returnMap.put("list", orderList);
                        returnMap.put("xf_cs", orderList.size());
                        returnMap.put("count_amount", count_amount);
                        result.setData(returnMap);
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


    /**
     * 功能描述: 获取订单退款基础支持信息
     *
     * @param user_id  用户ID
     * @param order_id 订单ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/10 3:02 AM
     */
    @RequestMapping(value = "/wnkOrderRefundBaseInformation", method = RequestMethod.POST, params = {"user_id", "order_id"})
    @ResponseBody
    public Result wnkOrderRefundBaseInformation(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer order_id) {
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
                    WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderId(order_id);
                    if (wnkOrdersTwo == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    } else if (wnkOrdersTwo.getUser_id() != user_id) {
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
     * @param user_id       用户ID
     * @param order_id      订单ID
     * @param refund_number 退款商品数量
     * @param refund_reason 退款原因
     * @return:
     * @author: zhangfan
     * @date: 2018/12/10 12:44 AM
     */
    @RequestMapping(value = "/wnkOrderRefund", method = RequestMethod.POST, params = {"user_id", "order_id", "refund_number", "refund_reason"})
    @ResponseBody
    public Result wnkOrderRefund(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer order_id, Integer refund_number, String refund_reason) {
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
                    } else if (wnkOrdersTwo.getUser_id() != user_id) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("你无权操作此订单");
                    } else {
                        WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkOrdersTwo.getBusiness_id());
                        if (wnkBusinessAccount == null) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商户不存在");
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
                                                Map<String, Object> userIntegralMap = this.wnkSendIntegralUserService.getUserIntegral(wnkOrdersTwo.getBusiness_id(), wnkOrdersTwo.getUser_id());
                                                int insertRecordState = 1;
                                                if (userIntegralMap == null) {
                                                    insertRecordState = 0;
                                                    insertRecordState = this.wnkSendIntegralUserService.addUserIntegral(wnkOrdersTwo.getBusiness_id(), wnkOrdersTwo.getUser_id());
                                                }
                                                if (insertRecordState > 0) {
                                                    int addState = this.wnkSendIntegralUserService.increaseUserIntegral(wnkOrdersTwo.getUser_id(), wnkOrdersTwo.getBusiness_id(), refund_send_integral);
                                                    if (addState > 0) {
                                                        // 插入积分记录 0=收入
                                                        this.wnkSendIntegralDetailUserService.addIntegralRecord("商品退款", refund_send_integral, wnkOrdersTwo.getUser_id(), wnkOrdersTwo.getBusiness_id(), 0);
                                                    }
                                                }
                                            }
                                            //进行通用积分退款
                                            if (refund_general_integral > 0.00) {
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
                                                    System.out.println("用户申请退款-微信退款申请状态：" + (Boolean) wxRefundMap.get("status"));
                                                    System.out.println("用户申请退款-微信退款申请反馈信息：" + (String) wxRefundMap.get("msg"));
                                                }
                                                //退款至支付宝
                                                else if (wnkOrdersTwo.getPay_way() == 2) {

                                                }
                                            }

                                            // 进行商户积分返还
                                            // 赠送积分 refund_send_integral
                                            // 通用积分 refund_general_integral
                                            // 现金     refund_cash
                                            // 计算商户积分返还总数
                                            Double integralTotal = refund_send_integral + refund_general_integral + refund_cash;
                                            this.wnkBusinessAccountService.updateBusinessLevelIntegral(wnkBusinessAccount.getLevel_integral() + integralTotal.intValue(), wnkBusinessAccount.getId());
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
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    /**
     * 功能描述: 查询订单详情
     */
    @RequestMapping(value = "/selectHotelBusinessOrderInfoByOrderId", method = RequestMethod.POST)
    @ResponseBody
    public Result selectHotelBusinessOrderInfoByOrderId(HttpServletRequest request, HttpServletResponse response, Integer userId, Integer orderId) {
        try {
            if (LoginSessionCheckUtil.checkIsLogin(userId, 1, request, response, this.sessionIdsService) == null) {
                return new Result(Result.NOLOGIN, "此账户暂未登录", null);
            }
            Users users = this.usersService.selectById(userId);
            if (users == null) {
                return new Result(Result.FAIL, "此用户不存在", null);
            }

            // 查询基本订单信息
            WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderId(orderId);
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
            WnkBusinessAccount wnkBusinessAccount = wnkBusinessAccountService.selectById(wnkOrdersTwo.getBusiness_id());
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
            return new Result(Result.FAIL, "操作失败", null);
        }
    }


    /**
     * 功能描述: 用户退款
     */
    @RequestMapping(value = "/hotelOrderCancel", method = RequestMethod.POST)
    @ResponseBody
    public Result hotelOrderCancel(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer order_id) {
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
                    } else if (wnkOrdersTwo.getUser_id() != user_id) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("你无权操作此订单");
                    } else {
                        WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkOrdersTwo.getBusiness_id());
                        if (wnkBusinessAccount == null) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商户不存在");
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
                                    Double refundCountAmount = commodityUnitPrice * wnkOrderCommodityTwo.getBuy_number();
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
                                            int updateOrderState = this.wnkOrdersTwoService.updateOrderStateByOrderNo(6, wnkOrdersTwo.getOrder_no());
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
                                            wnkOrderRefundRecord.setRefund_number(0);
                                            wnkOrderRefundRecord.setGeneral_integral(refund_general_integral);
                                            wnkOrderRefundRecord.setOrder_no(wnkOrdersTwo.getOrder_no());
                                            wnkOrderRefundRecord.setRefund_date(new Date());
                                            wnkOrderRefundRecord.setCoupon(refund_coupon);
                                            wnkOrderRefundRecord.setSend_integral(refund_send_integral);
                                            wnkOrderRefundRecord.setCash(refund_cash);
                                            wnkOrderRefundRecord.setRefund_reason(wnkOrderCommodityTwo.getBuy_number().toString());
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
                                                                userCoupons.setSurplus_number(wnkOrderCommodityTwo.getBuy_number());
                                                                this.userCouponsService.insertMaterielBuyRecord(userCoupons);
                                                            } else {
                                                                userCoupons.setSurplus_number(userCoupons.getSurplus_number() + wnkOrderCommodityTwo.getBuy_number());
                                                                this.userCouponsService.updateSurplusNumber(userCoupons);
                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                            //进行现金劵(赠送积分)退款
                                            if (refund_send_integral > 0.00) {
                                                //查询用户在此商家处是否已有积分记录
                                                Map<String, Object> userIntegralMap = this.wnkSendIntegralUserService.getUserIntegral(wnkOrdersTwo.getBusiness_id(), wnkOrdersTwo.getUser_id());
                                                int insertRecordState = 1;
                                                if (userIntegralMap == null) {
                                                    insertRecordState = 0;
                                                    insertRecordState = this.wnkSendIntegralUserService.addUserIntegral(wnkOrdersTwo.getBusiness_id(), wnkOrdersTwo.getUser_id());
                                                }
                                                if (insertRecordState > 0) {
                                                    int addState = this.wnkSendIntegralUserService.increaseUserIntegral(wnkOrdersTwo.getUser_id(), wnkOrdersTwo.getBusiness_id(), refund_send_integral);
                                                    if (addState > 0) {

                                                        // 插入积分记录 0=收入
                                                        this.wnkSendIntegralDetailUserService.addIntegralRecord("商品退款", refund_send_integral, wnkOrdersTwo.getUser_id(), wnkOrdersTwo.getBusiness_id(), 0);
                                                    }
                                                }
                                            }
                                            //进行通用积分退款
                                            if (refund_general_integral > 0.00) {
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
                                                    System.out.println("用户申请退款-微信退款申请状态：" + (Boolean) wxRefundMap.get("status"));
                                                    System.out.println("用户申请退款-微信退款申请反馈信息：" + (String) wxRefundMap.get("msg"));
                                                }
                                                //退款至支付宝
                                                else if (wnkOrdersTwo.getPay_way() == 2) {

                                                }
                                            }
                                            // 扣除赠送现金券
                                            //查询用户在此商家处是否已有积分记录
                                            Map<String, Object> userIntegralMap = this.wnkSendIntegralUserService.getUserIntegral(wnkOrdersTwo.getBusiness_id(), wnkOrdersTwo.getUser_id());
                                            int insertRecordState = 1;
                                            if (userIntegralMap == null) {
                                                insertRecordState = 0;
                                                insertRecordState = this.wnkSendIntegralUserService.addUserIntegral(wnkOrdersTwo.getBusiness_id(), wnkOrdersTwo.getUser_id());
                                            }
                                            if (insertRecordState > 0) {
                                                // 查询商品规格信息
                                                Integer gift_noun = (Integer) this.wnkCommoditySpecificationsService.selectByCommodityId(wnkOrderCommodityTwo.getCommodity_id()).get(0).get("gift_noun");
                                                Double recoredNumber = refundCountAmount * (gift_noun / 100);
                                                int addState = this.wnkSendIntegralUserService.updateIntegral(wnkOrdersTwo.getUser_id(), wnkOrdersTwo.getBusiness_id(), recoredNumber);
                                                if (addState > 0) {
                                                    // 插入积分记录 1=扣除
                                                    this.wnkSendIntegralDetailUserService.addIntegralRecord("消费赠送退款扣除", recoredNumber, wnkOrdersTwo.getUser_id(), wnkOrdersTwo.getBusiness_id(), 1);
                                                }
                                            }

                                            // 进行商户积分返还
                                            // 赠送积分 refund_send_integral
                                            // 通用积分 refund_general_integral
                                            // 现金     refund_cash
                                            // 计算商户积分返还总数
                                            Double integralTotal = refund_send_integral + refund_general_integral + refund_cash;
                                            this.wnkBusinessAccountService.updateBusinessLevelIntegral(wnkBusinessAccount.getLevel_integral() + integralTotal.intValue(), wnkBusinessAccount.getId());

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
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


}
