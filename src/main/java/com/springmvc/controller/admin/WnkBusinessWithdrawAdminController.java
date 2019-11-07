package com.springmvc.controller.admin;

import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkBusinessBalanceDetail;
import com.springmvc.entity.WnkBusinessWithdrawOrder;
import com.springmvc.service.impl.WnkBusinessAccountService;
import com.springmvc.service.impl.WnkBusinessBalanceDetailService;
import com.springmvc.service.impl.WnkBusinessWithdrawOrderService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.wechat.WechatPayLineUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 商家提现订单
 * @Author: 刘武祥
 * @Date: 2019/2/19 0019 10:15
 */
@Controller
@RequestMapping(value = "/admin")
public class WnkBusinessWithdrawAdminController {
    @Resource
    private WnkBusinessWithdrawOrderService wnkBusinessWithdrawOrderService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkBusinessBalanceDetailService wnkBusinessBalanceDetailService;

    /**
     *
     * 功能描述: 进入商家提现订单管理界面
     *
     * @param   request  服务请求
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/16 0016 21:54
     */
    @RequestMapping(value = "/withdrawWnkBusinessOrdersManager")
    @ResponseBody
    public ModelAndView withdrawWnkBusinessOrdersManager(HttpServletRequest request){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk_business_withdraw_orders/withdraw_orders");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取所有万能卡商家提现订单
     *
     * @param   request, response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/16 0016 21:53
     */
    @RequestMapping(value = "/getAllWnkBusinessWithdrawOrders", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllWnkBusinessWithdrawOrders(HttpServletRequest request, HttpServletResponse response) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                List<Map<Object, Object>> list = this.wnkBusinessWithdrawOrderService.selectAllAdmin();
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
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    /**
     *
     * 功能描述: 进入万能卡商家提现订单详情界面
     *
     * @param   request     请求
     * @param   record_id   id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/16 0016 21:51
     */
    @RequestMapping(value = "/wnkBusinessWithdrawOrderDetail",method = RequestMethod.GET,params = {"record_id"})
    @ResponseBody
    public ModelAndView wnkBusinessWithdrawOrderDetail(HttpServletRequest request,Integer record_id){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk_business_withdraw_orders/withdraw_order_detail");
            WnkBusinessWithdrawOrder wnkBusinessWithdrawOrder = this.wnkBusinessWithdrawOrderService.selectById(record_id);
            if (wnkBusinessWithdrawOrder == null){
                modelAndView.addObject("record_id",-1);
                modelAndView.addObject("name","");
                modelAndView.addObject("bank_name","");
                modelAndView.addObject("bank_id_card_number","");
                modelAndView.addObject("tixianjine","");
                modelAndView.addObject("shouxufei","");
                modelAndView.addObject("daozhangamount","");
                modelAndView.addObject("submit_date","");
                modelAndView.addObject("loan_date","");
                modelAndView.addObject("state","");
                modelAndView.addObject("order_no","");
            }
            else{
                modelAndView.addObject("record_id",wnkBusinessWithdrawOrder.getId());
                modelAndView.addObject("name",wnkBusinessWithdrawOrder.getBank_card_name());
                modelAndView.addObject("bank_name",wnkBusinessWithdrawOrder.getBack_name());
                modelAndView.addObject("bank_id_card_number",wnkBusinessWithdrawOrder.getBank_card_number());
                modelAndView.addObject("tixianjine",wnkBusinessWithdrawOrder.getRmb_count_amount());
                modelAndView.addObject("shouxufei",wnkBusinessWithdrawOrder.getService_charge_amount());
                modelAndView.addObject("daozhangamount",wnkBusinessWithdrawOrder.getReal_payment_rmb_amount());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                modelAndView.addObject("submit_date",formatter.format(wnkBusinessWithdrawOrder.getApply_date()));
                if (wnkBusinessWithdrawOrder.getLoan_date() != null){
                    modelAndView.addObject("loan_date",formatter.format(wnkBusinessWithdrawOrder.getLoan_date()));
                }
                else{
                    modelAndView.addObject("loan_date","");
                }
                if (wnkBusinessWithdrawOrder.getState() == 0){
                    modelAndView.addObject("state","待审核");
                }
                else if (wnkBusinessWithdrawOrder.getState() == 1){
                    modelAndView.addObject("state","打款中");
                }
                else if (wnkBusinessWithdrawOrder.getState() == 2){
                    modelAndView.addObject("state","审核失败");
                }
                else if (wnkBusinessWithdrawOrder.getState() == 3){
                    modelAndView.addObject("state","打款失败");
                }
                else if (wnkBusinessWithdrawOrder.getState() == 4){
                    modelAndView.addObject("state","已打款");
                }
                else{
                    modelAndView.addObject("state","");
                }
                modelAndView.addObject("order_no",wnkBusinessWithdrawOrder.getOrder_no());
            }
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 万能卡商家提现放款
     *
     * @param   request     请求
     * @param   response    响应
     * @param   record_id   id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/16 0016 21:49
     */
    @RequestMapping(value = "/wnkBusinessWithdrawPassFK", method = RequestMethod.POST, params = {"record_id"})
    @ResponseBody
    public Result wnkBusinessWithdrawPassFK(HttpServletRequest request, HttpServletResponse response, Integer record_id) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                WnkBusinessWithdrawOrder wnkBusinessWithdrawOrder = this.wnkBusinessWithdrawOrderService.selectById(record_id);
                if (wnkBusinessWithdrawOrder == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此记录不存在");
                } else if (wnkBusinessWithdrawOrder.getState() != 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请勿重复处理");
                } else {
                    Map<String, Object> resultMap = WechatPayLineUtil.wechatCashCashWnkBusiness(wnkBusinessWithdrawOrder, request, response);
                    if (resultMap.get("return_code").equals("SUCCESS")) {
                        wnkBusinessWithdrawOrder.setOut_trade_no((String) resultMap.get("payment_no"));
                        wnkBusinessWithdrawOrder.setState(4);
                        wnkBusinessWithdrawOrder.setLoan_date(new Date());
                        int state = this.wnkBusinessWithdrawOrderService.updateStateFKSuccess(wnkBusinessWithdrawOrder);
                        // 跟新订单状态为成功
                        this.wnkBusinessBalanceDetailService.updateBusinessBalanceDetailState(0, record_id);
                        if (state <= 0) {
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("放款成功,但记录变更失败");
                        } else {
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("放款成功");
                        }
                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg((String) resultMap.get("return_msg"));
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

    /**
     *
     * 功能描述: 万能卡商家提现订单拒绝提现
     *
     * @param   request     请求
     * @param   response    响应
     * @param   record_id   id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/16 0016 21:47
     */
    @RequestMapping(value = "/refusingPresentActionWnkBusiness", method = RequestMethod.POST, params = {"record_id"})
    @ResponseBody
    public Result refusingPresentActionWnkBusiness(HttpServletRequest request, HttpServletResponse response, Integer record_id) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                WnkBusinessWithdrawOrder wnkBusinessWithdrawOrder = this.wnkBusinessWithdrawOrderService.selectById(record_id);
                if (wnkBusinessWithdrawOrder == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此记录不存在");
                } else if (wnkBusinessWithdrawOrder.getState() != 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请勿重复处理");
                } else {
                    int updateState = this.wnkBusinessWithdrawOrderService.updateStateNoCan(new Date(), record_id);
                    if (updateState <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("操作失败");
                    } else {
                        WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkBusinessWithdrawOrder.getBusiness_id());
                        if (wnkBusinessAccount == null) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商户不存在");
                        } else {
                            WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                            wnkBusinessBalanceDetail.setBusiness_id(wnkBusinessWithdrawOrder.getBusiness_id());
                            wnkBusinessBalanceDetail.setName("提现被拒返款");
                            wnkBusinessBalanceDetail.setTransaction_amount(wnkBusinessWithdrawOrder.getRmb_count_amount());
                            wnkBusinessBalanceDetail.setJoin_time(new Date());
                            wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance() + wnkBusinessWithdrawOrder.getRmb_count_amount());
                            wnkBusinessBalanceDetail.setType(0);
                            wnkBusinessBalanceDetail.setState(0);
                            int addState = this.wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);
                            // 跟新订单状态为失败
                            this.wnkBusinessBalanceDetailService.updateBusinessBalanceDetailState(1, record_id);
                            if (addState <= 0) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("返款失败");
                            } else {
                                int updateUserBalanceState = this.wnkBusinessAccountService.updateBalance(wnkBusinessWithdrawOrder.getBusiness_id(), wnkBusinessAccount.getBalance() + wnkBusinessWithdrawOrder.getRmb_count_amount());
                                if (updateUserBalanceState <= 0) {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("返款失败");
                                } else {
                                    result.setData("");
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("操作成功");
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

    /**
     *
     * 功能描述: 根据条件查询商家订单信息
     *
     * @param   request                 请求
     * @param   response                响应
     * @param   limit                   分页条目数
     * @param   page                    分页数
     * @param   bank_card_name          收款姓名
     * @param   back_name               收款银行
     * @param   bank_card_number        收款账号
     * @param   rmb_count_amount        提现金额
     * @param   service_charge_amount   手续费
     * @param   real_payment_rmb_amount 到账金额
     * @param   order_state             审核状态
     * @param   start_time              申请时间
     * @param   shop_time               处理时间
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/16 0016 21:34
     */
    @RequestMapping(value = "/searchBusinessWithdrawOrdersInfoByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchBusinessWithdrawOrdersInfoByConditions(HttpServletRequest request,
                                                                    HttpServletResponse response,
                                                                    Integer limit,
                                                                    Integer page,
                                                                    String bank_card_name,
                                                                    String back_name,
                                                                    String bank_card_number,
                                                                    Double rmb_count_amount,
                                                                    Double service_charge_amount,
                                                                    Double real_payment_rmb_amount,
                                                                    String order_state ,
                                                                    String start_time,
                                                                    String shop_time)
    {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("start_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                if (bank_card_name != null && !"".equals(bank_card_name)){
                    map.put("bank_card_name","%"+bank_card_name+"%");
                }
                if (back_name != null && !"".equals(back_name)){
                    map.put("back_name","%"+back_name+"%");
                }
                if (bank_card_number != null && !"".equals(bank_card_number)){
                    map.put("bank_card_number","%"+bank_card_number+"%");
                }
                if (rmb_count_amount != null && !"".equals(rmb_count_amount)){
                    map.put("rmb_count_amount","%"+rmb_count_amount+"%");
                }
                if (service_charge_amount != null && !"".equals(service_charge_amount)){
                    map.put("service_charge_amount","%"+service_charge_amount+"%");
                }
                if (real_payment_rmb_amount != null && !"".equals(real_payment_rmb_amount)){
                    map.put("real_payment_rmb_amount","%"+real_payment_rmb_amount+"%");
                }
                map.put("order_state",("".equals(order_state) ? null : order_state));
                if (start_time != null && !"".equals(start_time)){
                    map.put("start_time",start_time);
                }
                if (shop_time != null && !"".equals(shop_time)){
                    map.put("shop_time",shop_time);
                }
                List<Map<Object,Object>> list = this.wnkBusinessWithdrawOrderService.adminSearchBusinessWithdrawOrdersInfoByConditions(map);
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
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    /**
     *
     * 功能描述: 将万能卡商家提现记录设置为已提现
     *
     * @param   request     请求
     * @param   response    响应
     * @param   record_id   id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/16 0016 21:43
     */
    @RequestMapping(value = "/wankBusinessAlreadyPresentedAction", method = RequestMethod.POST, params = {"record_id"})
    @ResponseBody
    public Result wankBusinessAlreadyPresentedAction(HttpServletRequest request, HttpServletResponse response, Integer record_id) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                WnkBusinessWithdrawOrder wnkBusinessWithdrawOrder = this.wnkBusinessWithdrawOrderService.selectById(record_id);
                if (wnkBusinessWithdrawOrder == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此记录不存在");
                } else if (wnkBusinessWithdrawOrder.getState() != 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请勿重复处理");
                } else {
                    int updateState = this.wnkBusinessWithdrawOrderService.updatealReadyPresented(new Date(), record_id);
                    if (updateState <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("设置失败");
                    } else {
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("设置成功");
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
}
