package com.springmvc.controller.admin;

import com.springmvc.entity.GeneralIntegralIncome;
import com.springmvc.entity.Users;
import com.springmvc.entity.UsersWithdrawOrder;
import com.springmvc.service.impl.GeneralIntegralIncomeService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.service.impl.UsersWithdrawOrderService;
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
 * 功能描述: 用户提现订单
 * @Author: 刘武祥
 * @Date: 2019/2/18 0018 14:44
 */
@Controller
@RequestMapping(value = "/admin")
public class WithdrawOrdersAdminController {
    @Resource
    private UsersWithdrawOrderService usersWithdrawOrderService;
    @Resource
    private UsersService usersService;
    @Resource
    private GeneralIntegralIncomeService generalIntegralIncomeService;

    /**
     *
     * 功能描述: 进入提现订单管理界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/19 0019 17:27
     */
    @RequestMapping(value = "/withdrawOrdersManager")
    @ResponseBody
    public ModelAndView withdrawOrdersManager(HttpServletRequest request){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/withdraw_orders/withdraw_orders");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入提现订单详情界面
     *
     * @param   request
     * @param   record_id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/2/18 0018 14:45
     */
    @RequestMapping(value = "/withdrawOrderDetail",method = RequestMethod.GET,params = {"record_id"})
    @ResponseBody
    public ModelAndView withdrawOrderDetail(HttpServletRequest request,Integer record_id){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/withdraw_orders/withdraw_order_detail");
            UsersWithdrawOrder usersWithdrawOrder = this.usersWithdrawOrderService.selectById(record_id);
            if (usersWithdrawOrder == null){
                modelAndView.addObject("record_id",-1);
                modelAndView.addObject("name","");
                modelAndView.addObject("bank_name","");
                modelAndView.addObject("bank_id_card_number","");
                modelAndView.addObject("tixianjine","");
                modelAndView.addObject("shouxufei","");
                modelAndView.addObject("daozhangamount","");
                modelAndView.addObject("xiaohaojifen","");
                modelAndView.addObject("submit_date","");
                modelAndView.addObject("loan_date","");
                modelAndView.addObject("state","");
                modelAndView.addObject("order_no","");
            }
            else{
                modelAndView.addObject("record_id",usersWithdrawOrder.getId());
                modelAndView.addObject("name",usersWithdrawOrder.getBank_card_name());
                modelAndView.addObject("bank_name",usersWithdrawOrder.getBack_name());
                modelAndView.addObject("bank_id_card_number",usersWithdrawOrder.getBank_card_number());
                modelAndView.addObject("tixianjine",usersWithdrawOrder.getRmb_count_amount());
                modelAndView.addObject("shouxufei",usersWithdrawOrder.getService_charge_amount());
                modelAndView.addObject("daozhangamount",usersWithdrawOrder.getReal_payment_rmb_amount());
                modelAndView.addObject("xiaohaojifen",usersWithdrawOrder.getExpenditure_integral());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                modelAndView.addObject("submit_date",formatter.format(usersWithdrawOrder.getApply_date()));
                if (usersWithdrawOrder.getLoan_date() != null){
                    modelAndView.addObject("loan_date",formatter.format(usersWithdrawOrder.getLoan_date()));
                }
                else{
                    modelAndView.addObject("loan_date","");
                }
                if (usersWithdrawOrder.getState() == 0){
                    modelAndView.addObject("state","待审核");
                }
                else if (usersWithdrawOrder.getState() == 1){
                    modelAndView.addObject("state","打款中");
                }
                else if (usersWithdrawOrder.getState() == 2){
                    modelAndView.addObject("state","审核失败");
                }
                else if (usersWithdrawOrder.getState() == 3){
                    modelAndView.addObject("state","打款失败");
                }
                else if (usersWithdrawOrder.getState() == 4){
                    modelAndView.addObject("state","已打款");
                }
                else{
                    modelAndView.addObject("state","");
                }
                modelAndView.addObject("order_no",usersWithdrawOrder.getOrder_no());
            }
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取所有用户提现订单
     *
     * @param   request
     * @param   response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/2/18 0018 14:46
     */
    @RequestMapping(value = "/getAllUserWithdrawOrders", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllUserWithdrawOrders(HttpServletRequest request, HttpServletResponse response){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.usersWithdrawOrderService.selectAllAdmin();
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
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 根据条件查询用户订单信息
     *
     * @param   request                     请求
     * @param   response                    响应
     * @param   limit                       分页条目数
     * @param   page                        分页数
     * @param   bank_card_name              收款银行卡姓名(银行卡开户名)
     * @param   back_name                   收款银行名称
     * @param   bank_card_number            收款银行卡卡号
     * @param   rmb_count_amount            提现人民币总金额
     * @param   service_charge_amount       提现手续费(以人民币元为单位)
     * @param   real_payment_rmb_amount     实付人民币总金额(扣除手续费之类实际打款给用户的金额)
     * @param   order_state                 提现状态(0-待审核,1-打款中,2-审核失败,3-打款失败，4-已打款)
     * @param   start_time                  提现申请时间
     * @param   shop_time                   放款时间
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/19 0019 17:08
     */
    @RequestMapping(value = "/adminSearchUserWithdrawOrdersInfoByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchUserWithdrawOrdersInfoByConditions(HttpServletRequest request,
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
                if (bank_card_name != null && !"".equals(bank_card_number)){
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
                List<Map<Object,Object>> list = this.usersWithdrawOrderService.adminSearchUserWithdrawOrdersInfoByConditions(map);
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
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 拒绝提现
     *
     * @param   request
     * @param   response
     * @param   record_id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/2/18 0018 14:47
     */
    @RequestMapping(value = "/refusingPresentAction", method = RequestMethod.POST,params = {"record_id"})
    @ResponseBody
    public Result refusingPresentAction(HttpServletRequest request, HttpServletResponse response,Integer record_id){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                UsersWithdrawOrder usersWithdrawOrder = this.usersWithdrawOrderService.selectById(record_id);
                if (usersWithdrawOrder == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此记录不存在");
                }
                else if (usersWithdrawOrder.getState() != 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请勿重复处理");
                }
                else{
                    int updateState = this.usersWithdrawOrderService.updateStateNoCan(new Date(),record_id);
                    if (updateState <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("操作失败");
                    }
                    else{
                        Users users = this.usersService.selectById(usersWithdrawOrder.getUser_id());
                        if (users == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("用户不存在");
                        }
                        else{
                            GeneralIntegralIncome generalIntegralIncome = new GeneralIntegralIncome();
                            generalIntegralIncome.setName("积分提现被拒");
                            generalIntegralIncome.setIncome_date(new Date());
                            generalIntegralIncome.setIncome_amount(usersWithdrawOrder.getExpenditure_integral().doubleValue());
                            generalIntegralIncome.setIncome_after_balance(usersWithdrawOrder.getExpenditure_integral() + users.getGeneral_integral());
                            generalIntegralIncome.setUser_id(usersWithdrawOrder.getUser_id());
                            generalIntegralIncome.setIncome_type(3);
                            int addState = this.generalIntegralIncomeService.withdrawFK(generalIntegralIncome);
                            if (addState <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("返款失败");
                            }
                            else{
                                users.setGeneral_integral(users.getGeneral_integral() + usersWithdrawOrder.getExpenditure_integral());
                                int updateUserBalanceState = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                                if (updateUserBalanceState <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("返款失败");
                                }
                                else{
                                    result.setData("");
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("操作成功");
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

    /**
     *
     * 功能描述: 提现放款
     *
     * @param   request
     * @param   response
     * @param   record_id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/2/18 0018 14:47
     */
    @RequestMapping(value = "/withdrawPassFK", method = RequestMethod.POST,params = {"record_id"})
    @ResponseBody
    public Result withdrawPassFK(HttpServletRequest request, HttpServletResponse response,Integer record_id){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                UsersWithdrawOrder usersWithdrawOrder = this.usersWithdrawOrderService.selectById(record_id);
                if (usersWithdrawOrder == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此记录不存在");
                }
                else if (usersWithdrawOrder.getState() != 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请勿重复处理");
                }
                else{
                    Map<String,Object> resultMap = WechatPayLineUtil.wechatCashCash(usersWithdrawOrder,request,response);
                    if (resultMap.get("return_code").equals("SUCCESS")){
                        usersWithdrawOrder.setOut_trade_no((String)resultMap.get("payment_no"));
                        usersWithdrawOrder.setState(4);
                        usersWithdrawOrder.setLoan_date(new Date());
                        int state = this.usersWithdrawOrderService.updateStateFKSuccess(usersWithdrawOrder);
                        if (state <= 0){
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("放款成功,但记录变更失败");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("放款成功");
                        }
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg((String)resultMap.get("return_msg"));
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

    /**
     *
     * 功能描述: 将提现记录设置为已提现
     *
     * @param   request
     * @param   response
     * @param   record_id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/2/18 0018 14:48
     */
    @RequestMapping(value = "/alreadyPresentedAction", method = RequestMethod.POST,params = {"record_id"})
    @ResponseBody
    public Result alreadyPresentedAction(HttpServletRequest request, HttpServletResponse response,Integer record_id){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                UsersWithdrawOrder usersWithdrawOrder = this.usersWithdrawOrderService.selectById(record_id);
                if (usersWithdrawOrder == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此记录不存在");
                }
                else if (usersWithdrawOrder.getState() != 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请勿重复处理");
                }
                else{
                    int updateState = this.usersWithdrawOrderService.updatealReadyPresented(new Date(),record_id);
                    if (updateState <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("设置失败");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("设置成功");
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
