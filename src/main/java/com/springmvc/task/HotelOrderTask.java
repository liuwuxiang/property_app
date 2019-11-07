package com.springmvc.task;

import com.springmvc.entity.*;
import com.springmvc.service.WnkOrdersExpandHotelService;
import com.springmvc.service.impl.*;
import com.springmvc.utils.Result;
import com.springmvc.utils.SpringContextUtils;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.wechat.WechatPayLineUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class HotelOrderTask {


    @Resource
    private WnkOrdersTwoService wnkOrdersTwoService;
    @Resource
    private WnkOrderCommodityTwoService wnkOrderCommodityTwoService;
    @Resource
    private WnkOrderRefundRecordService wnkOrderRefundRecordService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;
    @Resource
    private ExtensionMaterielService extensionMaterielService;
    @Resource
    private UserCouponsService userCouponsService;
    @Resource
    private WnkSendIntegralUserService wnkSendIntegralUserService;
    @Resource
    private WnkSendIntegralDetailUserService wnkSendIntegralDetailUserService;
    @Resource
    private UsersService usersService;
    @Resource
    private GeneralIntegralIncomeService generalIntegralIncomeService;
    @Resource
    private WnkCommoditySpecificationsService wnkCommoditySpecificationsService;
    @Resource
    private WnkOrdersExpandHotelService wnkOrdersExpandHotelService;



    /**
     * 方法说明: 酒店类订单处理定时任务
     *
     * @return void
     * @author 杨新杰
     * @Date 2019/1/25
     **/
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void HotelOrderProcessingTask() throws InterruptedException {
        System.out.println("定时任务启动=_=//////////////////////////////////////////////");
        // 创建线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 查询所有为
        // 3-等待商家确认的订单
        List<WnkOrdersTwo> list = this.wnkOrdersTwoService.selectHotelBusinessNotConfirm();
        System.out.println("订单数量:"+ list.size());
        // 获取当前时间戳
        Long timeStamp = new Date().getTime();
        for (WnkOrdersTwo w : list) {
            if (w.getPay_time() != null) {
                // 获取订单支付时间戳
                Long payStamp = w.getPay_time().getTime();
                // 如果当前时间戳减去支付时间戳  > 一个小时则取消订单
                Long cha = timeStamp - payStamp;
                //
                if (cha > (1000 * 60 * 60)) {
                    // 随机暂停
                    // 随机休眠一会
                    Integer numOne = new Random().nextInt(66) + 1;
                    Integer numTwo = new Random().nextInt(66) + 1;
                    Thread.sleep(numOne.longValue() * numTwo.longValue());
                    // 加入线程
                    cachedThreadPool.execute(new th(w.getId()));
                }
            }
        }

    }

    // 创建内部类 用作启动线程
    class th implements Runnable{

        private WnkOrdersTwoService wnkOrdersTwoService = (WnkOrdersTwoService)SpringContextUtils.getSpringBean("/wnkOrdersTwoService");
        private WnkOrderCommodityTwoService wnkOrderCommodityTwoService  = (WnkOrderCommodityTwoService)SpringContextUtils.getSpringBean("/wnkOrderCommodityTwoService");
        private WnkOrderRefundRecordService wnkOrderRefundRecordService = (WnkOrderRefundRecordService)SpringContextUtils.getSpringBean("/wnkOrderRefundRecordService");
        private WnkStoreInformationService wnkStoreInformationService = (WnkStoreInformationService)SpringContextUtils.getSpringBean("/wnkStoreInformationService");
        private WnkBusinessAccountService wnkBusinessAccountService = (WnkBusinessAccountService)SpringContextUtils.getSpringBean("/wnkBusinessAccountService");
        private WnkBusinessTypeService wnkBusinessTypeService  = (WnkBusinessTypeService)SpringContextUtils.getSpringBean("/wnkBusinessTypeService");
        private ExtensionMaterielService extensionMaterielService = (ExtensionMaterielService)SpringContextUtils.getSpringBean("/extensionMaterielService");
        private UserCouponsService userCouponsService = (UserCouponsService)SpringContextUtils.getSpringBean("/userCouponsService");
        private WnkSendIntegralUserService wnkSendIntegralUserService = (WnkSendIntegralUserService)SpringContextUtils.getSpringBean("/wnkSendIntegralUserService");
        private WnkSendIntegralDetailUserService wnkSendIntegralDetailUserService = (WnkSendIntegralDetailUserService)SpringContextUtils.getSpringBean("/wnkSendIntegralDetailUserService");
        private UsersService usersService = (UsersService)SpringContextUtils.getSpringBean("/usersService");
        private GeneralIntegralIncomeService generalIntegralIncomeService  = (GeneralIntegralIncomeService)SpringContextUtils.getSpringBean("/generalIntegralIncomeService");
        private WnkCommoditySpecificationsService wnkCommoditySpecificationsService  = (WnkCommoditySpecificationsService)SpringContextUtils.getSpringBean("/wnkCommoditySpecificationsService");
        private WnkOrdersExpandHotelService wnkOrdersExpandHotelService = (WnkOrdersExpandHotelService)SpringContextUtils.getSpringBean("wnkOrdersExpandHotelServiceImpl");

        private Integer orderId;

        // 线程锁obj
        Object lookObj = new Object();

        public th(Integer orderId){
            this.orderId = orderId;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            synchronized (th.class){
                WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderId(orderId);
                System.out.println(wnkOrdersTwo);
                if (wnkOrdersTwo == null || wnkOrdersTwo.getState() != 3){
                    return;
                }



                List<Map<Object, Object>> maps = wnkOrderCommodityTwoService.selectCommodityByOrderId(wnkOrdersTwo.getId());
                Map<Object, Object> wnkOrderCommodityTwoMap = maps.get(0);

                WnkOrderCommodityTwo wnkOrderCommodityTwo = wnkOrderCommodityTwoService.selectByID((Integer) wnkOrderCommodityTwoMap.get("id"));

                //应退款金额
                Double refundCountAmount = (Double) wnkOrderCommodityTwoMap.get("count_amount");
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

                        WnkOrderRefundRecord wnkOrderRefundRecord = new WnkOrderRefundRecord();
                        wnkOrderRefundRecord.setRefund_no(UUDUtil.getOrderIdByUUId());
                        wnkOrderRefundRecord.setRefund_number(wnkOrderCommodityTwo.getBuy_number());
                        wnkOrderRefundRecord.setGeneral_integral(refund_general_integral);
                        wnkOrderRefundRecord.setOrder_no(wnkOrdersTwo.getOrder_no());
                        wnkOrderRefundRecord.setRefund_date(new Date());
                        wnkOrderRefundRecord.setCoupon(refund_coupon);
                        wnkOrderRefundRecord.setSend_integral(refund_send_integral);
                        wnkOrderRefundRecord.setCash(refund_cash);
                        wnkOrderRefundRecord.setRefund_reason("超时,系统自动退款");
                        this.wnkOrderRefundRecordService.insertNewRecord(wnkOrderRefundRecord);
                        WnkBusinessAccount wnkBusinessAccount = wnkBusinessAccountService.selectById(wnkOrdersTwo.getBusiness_id());
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
                                            userCoupons.setSurplus_number(refund_coupon);
                                            this.userCouponsService.insertMaterielBuyRecord(userCoupons);
                                        } else {
                                            userCoupons.setSurplus_number(userCoupons.getSurplus_number() + refund_coupon);
                                            this.userCouponsService.updateSurplusNumber(userCoupons);
                                        }
                                    }
                                }
                            }

                        }
                        //进行现金劵(赠送积分)退款
                        if (refund_send_integral > 0.00) {
                            //查询用户在此商家处是否已有积分记录
                            Map<String, Object> userIntegralMap = this.wnkSendIntegralUserService.getUserIntegral(wnkBusinessAccount.getId(), wnkOrdersTwo.getUser_id());
                            int insertRecordState = 1;
                            if (userIntegralMap == null) {
                                insertRecordState = 0;
                                insertRecordState = this.wnkSendIntegralUserService.addUserIntegral(wnkBusinessAccount.getId(), wnkOrdersTwo.getUser_id());
                            }
                            if (insertRecordState > 0) {
                                int addState = this.wnkSendIntegralUserService.increaseUserIntegral(wnkOrdersTwo.getUser_id(), wnkBusinessAccount.getId(), refund_send_integral);
                                if (addState > 0) {
                                    // 插入积分记录 0=收入
                                    this.wnkSendIntegralDetailUserService.addIntegralRecord("商品退款", refund_send_integral, wnkOrdersTwo.getUser_id(), wnkBusinessAccount.getId(), 0);
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
                                generalIntegralIncome.setName("商品退款(超时自动退款)");
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
//                        HttpServletRequest  request   = new HttpServletRequestWrapper(null) ;
//                        HttpServletResponse response  = new HttpServletResponseWrapper(null) ;
                                Map<String, Object> wxRefundMap = WechatPayLineUtil.wechatPayWnkCommodityAppRefund(wnkOrdersTwo.getOrder_no(), wnkOrderRefundRecord.getRefund_no(), Double.valueOf(String.format("%.2f", wnkOrderRefundRecord.getCash())), wnkOrdersTwo.getCash_amount(), null, null);
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


                    }
                }
            }
        }
    }



    /**
     * 方法说明: 系统退款
     *
     * @return
     * @author 杨新杰
     * @Date 2019/1/25
     * @Param
     **/
    private synchronized void systemRefound(WnkOrdersTwo wnkOrdersTwo) {
        List<Map<Object, Object>> maps = wnkOrderCommodityTwoService.selectCommodityByOrderId(wnkOrdersTwo.getId());
        Map<Object, Object> wnkOrderCommodityTwoMap = maps.get(0);

        WnkOrderCommodityTwo wnkOrderCommodityTwo = wnkOrderCommodityTwoService.selectByID((Integer) wnkOrderCommodityTwoMap.get("id"));

        //应退款金额
        Double refundCountAmount = (Double) wnkOrderCommodityTwoMap.get("count_amount");
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

                WnkOrderRefundRecord wnkOrderRefundRecord = new WnkOrderRefundRecord();
                wnkOrderRefundRecord.setRefund_no(UUDUtil.getOrderIdByUUId());
                wnkOrderRefundRecord.setRefund_number(wnkOrderCommodityTwo.getBuy_number());
                wnkOrderRefundRecord.setGeneral_integral(refund_general_integral);
                wnkOrderRefundRecord.setOrder_no(wnkOrdersTwo.getOrder_no());
                wnkOrderRefundRecord.setRefund_date(new Date());
                wnkOrderRefundRecord.setCoupon(refund_coupon);
                wnkOrderRefundRecord.setSend_integral(refund_send_integral);
                wnkOrderRefundRecord.setCash(refund_cash);
                wnkOrderRefundRecord.setRefund_reason("超时,系统自动退款");
                this.wnkOrderRefundRecordService.insertNewRecord(wnkOrderRefundRecord);
                WnkBusinessAccount wnkBusinessAccount = wnkBusinessAccountService.selectById(wnkOrdersTwo.getBusiness_id());
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
                                    userCoupons.setSurplus_number(refund_coupon);
                                    this.userCouponsService.insertMaterielBuyRecord(userCoupons);
                                } else {
                                    userCoupons.setSurplus_number(userCoupons.getSurplus_number() + refund_coupon);
                                    this.userCouponsService.updateSurplusNumber(userCoupons);
                                }
                            }
                        }
                    }

                }
                //进行现金劵(赠送积分)退款
                if (refund_send_integral > 0.00) {
                    //查询用户在此商家处是否已有积分记录
                    Map<String, Object> userIntegralMap = this.wnkSendIntegralUserService.getUserIntegral(wnkBusinessAccount.getId(), wnkOrdersTwo.getUser_id());
                    int insertRecordState = 1;
                    if (userIntegralMap == null) {
                        insertRecordState = 0;
                        insertRecordState = this.wnkSendIntegralUserService.addUserIntegral(wnkBusinessAccount.getId(), wnkOrdersTwo.getUser_id());
                    }
                    if (insertRecordState > 0) {
                        int addState = this.wnkSendIntegralUserService.increaseUserIntegral(wnkOrdersTwo.getUser_id(), wnkBusinessAccount.getId(), refund_send_integral);
                        if (addState > 0) {
                            // 插入积分记录 0=收入
                            this.wnkSendIntegralDetailUserService.addIntegralRecord("商品退款", refund_send_integral, wnkOrdersTwo.getUser_id(), wnkBusinessAccount.getId(), 0);
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
                        generalIntegralIncome.setName("商品退款(超时自动退款)");
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
//                        HttpServletRequest  request   = new HttpServletRequestWrapper(null) ;
//                        HttpServletResponse response  = new HttpServletResponseWrapper(null) ;
                        Map<String, Object> wxRefundMap = WechatPayLineUtil.wechatPayWnkCommodityAppRefund(wnkOrdersTwo.getOrder_no(), wnkOrderRefundRecord.getRefund_no(), Double.valueOf(String.format("%.2f", wnkOrderRefundRecord.getCash())), wnkOrdersTwo.getCash_amount(), null, null);
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


            }
        }
    }

}
