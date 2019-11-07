package com.springmvc.controller.wnk_business_app;

import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.MobileCodeUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.UUDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class WnkBusinessWithdrawAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private AboutUsService aboutUsService;
    @Resource
    private WithdrawSupportBankService withdrawSupportBankService;
    @Resource
    private WnkBusinessWithdrawOrderService wnkBusinessWithdrawOrderService;
    @Resource
    private WnkBusinessBalanceDetailService wnkBusinessBalanceDetailService;
    @Resource
    private WithdrawSettingService withdrawSettingService;

    /**
     * 功能描述: 商家余额提现申请
     *
     * @param withdraw_amount  提现数量
     * @param bank_card_name   银行名称
     * @param bank_card_number 银行卡号
     * @param bank_id          银行ID
     * @param business_id      商家ID
     * @param user_pay_pwd     支付密码
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 17:46 2019/1/5
     */
    @RequestMapping(value = "/businessWithdrawApply", method = RequestMethod.POST, params = {"withdraw_amount", "bank_id", "bank_card_name", "bank_card_number", "business_id", "user_pay_pwd"})
    @ResponseBody
    public Result userWithdrawApply(HttpServletRequest request, HttpServletResponse response, Double withdraw_amount, Integer bank_id, String bank_card_name, String bank_card_number, Integer business_id, String user_pay_pwd) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else if (withdraw_amount <= 0.00) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("提现金额必须大于0");
            } else {
                // 获取必要信息
                // 查询提现限制信息
                WithdrawSetting withdrawSetting = withdrawSettingService.adminSelectWithdrawSetting("0");
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);

                // 未设置提现限制
                if (withdrawSetting == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请联系管理员设置提现条件!");
                    return result;
                }
                // 不满足最低提现金额
                if (withdraw_amount < withdrawSetting.getMin_number()) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("提现金额不可小于" + withdrawSetting.getMin_number() + "元");
                    return result;
                }
                // 查询商家是否存在
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商户不存在");
                    return result;
                }
                // 未设置支付密码
                if (wnkBusinessAccount.getPay_pwd() == null || wnkBusinessAccount.getPay_pwd().equals("")) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请先设置支付密码");
                    return result;
                }
                // 支付密码不正确
                if (!wnkBusinessAccount.getPay_pwd().equals(user_pay_pwd)) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                    return result;
                }
                // 商家余额不足
                if (wnkBusinessAccount.getBalance() < withdraw_amount) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("余额不足");
                    return result;
                }

                //允许提现时间段
                String start = withdrawSetting.getWithdraw_start_time();
                String end = withdrawSetting.getWithdraw_end_time();

                // 判断是否处于提现时间内
                Calendar c = Calendar.getInstance();
                if (c.get(Calendar.DATE) < Integer.valueOf(start) || c.get(Calendar.DATE) > Integer.valueOf(end)) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("当前时间段不可提现,提现时间段为:每月" + start + "日至" + end + "日");
                    return result;
                }

                //通用积分与人民币兑换比例
                AboutUs aboutUsTYAndRMB = this.aboutUsService.selectIntegralAbout(10);
                //通用积分提现手续费率
                AboutUs aboutUsTYTXFL = this.aboutUsService.selectIntegralAbout(11);
                // 银行信息
                WithdrawSupportBank withdrawSupportBank = this.withdrawSupportBankService.selectById(bank_id);
                // 未设置通用积分与人民币兑换比例
                if (aboutUsTYAndRMB == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("当前不可提现");
                    return result;
                }
                // 未设置通用积分提现手续费率
                if (aboutUsTYTXFL == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("当前不可提现");
                    return result;
                }
                // 未设置银行信息
                if (withdrawSupportBank == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("选择的银行不存在");
                    return result;
                }

                Date nowDate = new Date();
                // 添加订单信息
                WnkBusinessWithdrawOrder wnkBusinessWithdrawOrder = new WnkBusinessWithdrawOrder();
                wnkBusinessWithdrawOrder.setRmb_count_amount(withdraw_amount);
                //手续费
                Double service_charge_amount = wnkBusinessWithdrawOrder.getRmb_count_amount() * withdrawSetting.getService_charge_proportion() / 100.00;
                wnkBusinessWithdrawOrder.setReal_payment_rmb_amount(wnkBusinessWithdrawOrder.getRmb_count_amount() - service_charge_amount);
                wnkBusinessWithdrawOrder.setBack_name(withdrawSupportBank.getName());
                wnkBusinessWithdrawOrder.setBack_code(withdrawSupportBank.getCode());
                wnkBusinessWithdrawOrder.setBank_card_number(bank_card_number);
                wnkBusinessWithdrawOrder.setBank_card_name(bank_card_name);
                wnkBusinessWithdrawOrder.setFormality_rate(withdrawSetting.getService_charge_proportion());
                wnkBusinessWithdrawOrder.setService_charge_amount(service_charge_amount);
                wnkBusinessWithdrawOrder.setApply_date(nowDate);
                wnkBusinessWithdrawOrder.setBusiness_id(business_id);
                wnkBusinessWithdrawOrder.setState(0);
                wnkBusinessWithdrawOrder.setOrder_no(UUDUtil.getOrderIdByUUId());

                int addWithawOrderState = this.wnkBusinessWithdrawOrderService.insertWithdrawRecord(wnkBusinessWithdrawOrder);
                if (addWithawOrderState >= 1) {
                    int updateUserBalanceState = this.wnkBusinessAccountService.updateBalance(business_id, wnkBusinessAccount.getBalance() - withdraw_amount);
                    if (updateUserBalanceState >= 1) {

                        this.wnkBusinessAccountService.selectById(business_id);
                        // 插入支出记录
                        WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                        wnkBusinessBalanceDetail.setBusiness_id(wnkBusinessAccount.getId());
                        wnkBusinessBalanceDetail.setName("余额提现");
                        // 交易金额
                        wnkBusinessBalanceDetail.setTransaction_amount(withdraw_amount);
                        wnkBusinessBalanceDetail.setJoin_time(new Date());
                        //交易后余额
                        wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance() - withdraw_amount);
                        wnkBusinessBalanceDetail.setType(1);
                        wnkBusinessBalanceDetail.setState(2);
                        // 标记为提现订单
                        wnkBusinessBalanceDetail.setIs_withdraw(0);
                        // 提现订单ID
                        wnkBusinessBalanceDetail.setWithdraw_id(wnkBusinessWithdrawOrder.getId());

                        if (this.wnkBusinessBalanceDetailService.insertNewRecordWithdraw(wnkBusinessBalanceDetail) >= 1) {
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("申请成功,待管理员审核");

                            //发送提现短信通知
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(nowDate);
                            Integer year = calendar.get(Calendar.YEAR);
                            Integer month = calendar.get(Calendar.MONTH) + 1;
                            Integer day = calendar.get(Calendar.DAY_OF_MONTH);
                            MobileCodeUtil.sendBusinessAccountWithdrawSms(wnkBusinessAccount.getMobile(), String.valueOf(year), String.valueOf(month), String.valueOf(day), bank_card_number.substring(bank_card_number.length() - 4), withdraw_amount, wnkBusinessWithdrawOrder.getReal_payment_rmb_amount(), wnkBusinessWithdrawOrder.getService_charge_amount());
                        }
                    } else {
                        this.wnkBusinessWithdrawOrderService.deleteRecordById(wnkBusinessWithdrawOrder.getId());
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("提现失败");
                    }
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("提现失败");
                }


            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("提现失败");
        }
        return result;
    }


    // 获取商家提现明细()
    @RequestMapping(value = "/getBusinessWithdrawRecord", method = RequestMethod.POST, params = {"business_id"})
    @ResponseBody
    public Result getBusinessWithdrawRecord(HttpServletRequest request, HttpServletResponse response, Integer business_id) {
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
                    Map<Object, Object> map = new HashMap<Object, Object>();
                    map.put("balance", wnkBusinessAccount.getBalance());
                    map.put("detail", this.wnkBusinessWithdrawOrderService.selectRecordByBusinessId(business_id));
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }
}
