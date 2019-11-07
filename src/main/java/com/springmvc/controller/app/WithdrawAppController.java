package com.springmvc.controller.app;

import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class WithdrawAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private WithdrawSupportBankService withdrawSupportBankService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private AboutUsService aboutUsService;
    @Resource
    private UsersWithdrawOrderService usersWithdrawOrderService;
    @Resource
    private GeneralIntegralExpenditureService generalIntegralExpenditureService;

    @Resource
    private WithdrawSettingService withdrawSettingService;

    // 获取提现支持的所有银行
    @RequestMapping(value = "/getAllWithdrawSupportBank", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllWithdrawSupportBank(HttpServletRequest request, HttpServletResponse response, Integer user_id) {
        Result result = new Result();
        try {
            List<Map<Object, Object>> list = this.withdrawSupportBankService.selectAllRecord();
            if (list.size() <= 0) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂无提现支持银行");
            } else {
                Map<Object, Object> map = new HashMap<Object, Object>();
                map.put("list", list);
                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("获取成功");
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    // 用户提现申请
    @RequestMapping(value = "/userWithdrawApply", method = RequestMethod.POST, params = {"expenditure_integral", "bank_id", "bank_card_name", "bank_card_number", "user_id", "user_pay_pwd"})
    @ResponseBody
    public Result userWithdrawApply(HttpServletRequest request, HttpServletResponse response, Integer expenditure_integral, Integer bank_id, String bank_card_name, String bank_card_number, Integer user_id, String user_pay_pwd) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else if (expenditure_integral <= 0) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("提现积分个数必须大于0并且为整数");
            } else {
                AboutUs aboutUs = this.aboutUsService.selectIntegralAbout(9);
                if (expenditure_integral < Integer.parseInt(aboutUs.getContent())) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("提现积分需大于等于" + aboutUs.getContent());
                } else if (expenditure_integral % Integer.parseInt(aboutUs.getContent()) != 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("提现积分需为" + aboutUs.getContent() + "的整数倍");
                } else {
                    Users users = this.usersService.selectById(user_id);
                    if (users == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此用户不存在");
                    } else if (users.getPay_pwd() == null || users.getPay_pwd().equals("")) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("请先设置支付密码");
                    } else if (!users.getPay_pwd().equals(user_pay_pwd)) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("支付密码不正确");
                    } else if (users.getGeneral_integral() < expenditure_integral) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("通用积分余额不足");
                    } else {
                        //允许提现时间段
                        // AboutUs aboutUsTimeSlot = this.aboutUsService.selectIntegralAbout(12);
                        //标记是否可提现(true:可提现,false:不可提现)
                        boolean is_can_withdraw = true;

                        WithdrawSetting withdrawSetting = withdrawSettingService.adminSelectWithdrawSetting("1");
                        String start = withdrawSetting.getWithdraw_start_time();
                        String end = withdrawSetting.getWithdraw_end_time();

                        if (withdrawSetting != null) {
                            Calendar c = Calendar.getInstance();
                            // 当前时间小于开始时间
                            if (c.get(Calendar.DATE) < Integer.valueOf(start) ||
                                c.get(Calendar.DATE) > Integer.valueOf(end)) {
                                result.setData("0");
                                result.setStatus(Result.FAIL);
                                String timeSlot = "每月" + start + "日至" + end + "日";
                                result.setMsg("当前时间段不可提现,提现时间段为:" + timeSlot);
                                is_can_withdraw = false;
                            }
                            if (withdrawSetting.getIs_any_time() == 1){
                                is_can_withdraw = true;
                            }
                        } else {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("请联系管理员设置提现条件");
                            is_can_withdraw = false;
                        }
                        // 判断当前时间段是否可以提现
                        if (is_can_withdraw == true) {
                            //通用积分与人民币兑换比例
                            AboutUs aboutUsTYAndRMB = this.aboutUsService.selectIntegralAbout(10);
                            //通用积分提现手续费率
                            AboutUs aboutUsTYTXFL = this.aboutUsService.selectIntegralAbout(11);
                            WithdrawSupportBank withdrawSupportBank = this.withdrawSupportBankService.selectById(bank_id);
                            // if (aboutUsTYAndRMB == null){
                            if (withdrawSetting.getWithdraw_proportion() == null) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("当前不可提现");
                            }
                            //else if (aboutUsTYTXFL == null){
                            else if (withdrawSetting.getService_charge_proportion() == null) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("当前不可提现");
                            } else if (withdrawSupportBank == null) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("选择的银行不存在");
                            } else {
                                Date nowDate = new Date();

                                UsersWithdrawOrder usersWithdrawOrder = new UsersWithdrawOrder();
                                usersWithdrawOrder.setExpenditure_integral(expenditure_integral);
//                                usersWithdrawOrder.setRmb_count_amount(expenditure_integral / Double.parseDouble(aboutUsTYAndRMB.getContent()));
                                // 兑换比例
                                usersWithdrawOrder.setRmb_count_amount(expenditure_integral / withdrawSetting.getWithdraw_proportion().doubleValue());
                                //手续费
//                                Double service_charge_amount = usersWithdrawOrder.getRmb_count_amount() * Double.parseDouble(aboutUsTYTXFL.getContent()) / 100.00;
                                Double service_charge_amount = usersWithdrawOrder.getRmb_count_amount() * withdrawSetting.getService_charge_proportion() / 100.00;
                                usersWithdrawOrder.setReal_payment_rmb_amount(usersWithdrawOrder.getRmb_count_amount() - service_charge_amount);
                                usersWithdrawOrder.setBack_name(withdrawSupportBank.getName());
                                usersWithdrawOrder.setBack_code(withdrawSupportBank.getCode());
                                usersWithdrawOrder.setBank_card_number(bank_card_number);
                                usersWithdrawOrder.setBank_card_name(bank_card_name);
//                                usersWithdrawOrder.setFormality_rate(Integer.parseInt(aboutUsTYTXFL.getContent()));
                                usersWithdrawOrder.setFormality_rate(withdrawSetting.getWithdraw_proportion());
                                usersWithdrawOrder.setService_charge_amount(service_charge_amount);
                                usersWithdrawOrder.setApply_date(nowDate);
                                usersWithdrawOrder.setUser_id(user_id);
                                usersWithdrawOrder.setState(0);
                                usersWithdrawOrder.setOrder_no(UUDUtil.getOrderIdByUUId());
                                int addWithawOrderState = this.usersWithdrawOrderService.insertWithdrawRecord(usersWithdrawOrder);
                                if (addWithawOrderState >= 1) {
                                    GeneralIntegralExpenditure generalIntegralExpenditure = new GeneralIntegralExpenditure();
                                    generalIntegralExpenditure.setName("提现");
                                    generalIntegralExpenditure.setExpenditure_date(nowDate);
                                    generalIntegralExpenditure.setExpenditure_amount(expenditure_integral.doubleValue());
                                    generalIntegralExpenditure.setExpenditure_after_balance(users.getGeneral_integral() - expenditure_integral);
                                    generalIntegralExpenditure.setUser_id(user_id);
                                    generalIntegralExpenditure.setExpenditure_type(1);
                                    generalIntegralExpenditure.setWithdraw_order_no(usersWithdrawOrder.getOrder_no());
                                    int addGeneralInteralExpenditureState = this.generalIntegralExpenditureService.insertWithdrawRecord(generalIntegralExpenditure);
                                    if (addGeneralInteralExpenditureState >= 1) {
                                        users.setGeneral_integral(users.getGeneral_integral() - expenditure_integral);
                                        int updateUserBalanceState = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                                        if (updateUserBalanceState >= 1) {
                                            result.setData("");
                                            result.setStatus(Result.SUCCESS);
                                            result.setMsg("申请成功,待管理员审核");

                                            //发送提现短信通知
                                            Calendar calendar = Calendar.getInstance();
                                            calendar.setTime(nowDate);
                                            Integer year = calendar.get(Calendar.YEAR);
                                            Integer month = calendar.get(Calendar.MONTH) + 1;
                                            Integer day = calendar.get(Calendar.DAY_OF_MONTH);
                                            MobileCodeUtil.sendUserGeneralIntegralWithdrawSms(users.getMobile(), String.valueOf(year), String.valueOf(month), String.valueOf(day), bank_card_number.substring(bank_card_number.length() - 4), expenditure_integral.doubleValue(), usersWithdrawOrder.getReal_payment_rmb_amount(), usersWithdrawOrder.getService_charge_amount());
                                        } else {
                                            this.generalIntegralExpenditureService.deleteRecordById(generalIntegralExpenditure.getId());
                                            this.usersWithdrawOrderService.deleteRecordById(usersWithdrawOrder.getId());
                                            result.setData("");
                                            result.setStatus(Result.FAIL);
                                            result.setMsg("提现失败");
                                        }
                                    } else {
                                        this.usersWithdrawOrderService.deleteRecordById(usersWithdrawOrder.getId());
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
                        } else {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            String timeSlot = "每月" + start + "日至" + end + "日";
                            result.setMsg("当前时间段不可提现,提现时间段为:" + timeSlot);
                        }

                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("提现失败");
        }
        return result;
    }
}
