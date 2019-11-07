package com.springmvc.controller.admin;

import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.UUDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 充值订单
 * @Author: 刘武祥
 * @Date: 2019/2/19 0019 12:06
 */
@Controller
@RequestMapping(value = "/admin")
public class RechargeOrdersAdminController {
    @Resource
    private RechargeOrderService rechargeOrderService;
    @Resource
    private UsersService usersService;
    @Resource
    private AboutUsService aboutUsService;
    @Resource
    private ConsumptionIntegralIncomeService consumptionIntegralIncomeService;
    @Resource
    private GeneralIntegralIncomeService generalIntegralIncomeService;
    @Resource
    private IntegralDetailService integralDetailService;

    /**
     *
     * 功能描述: 进入用户充值订单管理界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:18
     */
    @RequestMapping(value = "/rechargeOrdersManager")
    @ResponseBody
    public ModelAndView rechargeOrdersManager(HttpServletRequest request) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/recharge_orders/recharge_orders");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入商家充值订单管理界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2018/12/29 0029 18:27
     */
    @RequestMapping(value = "/businessRechargeOrdersManager")
    @ResponseBody
    public ModelAndView businessRechargeOrdersManager(HttpServletRequest request) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/recharge_orders/business_recharge_orders");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入会员充值界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2018/12/29 0029 18:27
     */
    @RequestMapping(value = "/memberRecharge")
    @ResponseBody
    public ModelAndView memberRecharge(HttpServletRequest request) {
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/recharge_orders/member_recharge");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取用户所有充值订单
     *
     * @param   request     请求
     * @param   response    响应
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:48
     */
    @RequestMapping(value = "/getAllRechargeOrder", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllRechargeOrder(HttpServletRequest request, HttpServletResponse response) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                List<Map<Object, Object>> list = this.rechargeOrderService.selectAllAdmin();
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
     * 功能描述:获取商家所有充值订单
     *
     * @param   request     请求
     * @param   response    响应
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2018/12/29 0029 18:26
     */
    @RequestMapping(value = "/getAllBusinessRechargeOrder", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllBusinessRechargeOrder(HttpServletRequest request, HttpServletResponse response) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                List<Map<Object, Object>> list = this.rechargeOrderService.selectAllBusiness();
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
     * 功能描述: 根据条件获取商家充值订单
     *
     * @param   request     请求
     * @param   response    响应
     * @param   limit       分页条目数
     * @param   page        分页数
     * @param   business_id 充值商家
     * @param   order_no    订单号
     * @param   amount      充值金额
     * @param   state       充值状态
     * @param   create_time 充值时间
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2018/12/29 0029 18:28
     */
    @RequestMapping(value = "/adminBusinessSearchRechargeOrderByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminBusinessSearchRechargeOrderByConditions(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       Integer limit,
                                                       Integer page,
                                                       Integer business_id,
                                                       String order_no,
                                                       Double amount,
                                                       Integer state,
                                                       String create_time

    ) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("statrt_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                map.put("business_id",business_id);
                map.put("amount", amount);
                map.put("state", ("".equals(state) ? null : state) );
                map.put("order_no","%"+order_no+"%");
                map.put("create_time", create_time);
                List<Map<String, Object>> list = this.rechargeOrderService.adminBusinessSearchRechargeOrderByConditions(map);
                result.setData(list);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");

            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 根据条件获取充值订单
     *
     * @param   request             请求
     * @param   response            响应
     * @param   limit               分页条目数
     * @param   page                分页数
     * @param   pay_type            支付方式
     * @param   pay_recharge_type   充值方式
     * @param   pay_state           充值状态
     * @param   username            充值用户
     * @param   system_order_no     订单号
     * @param   recharge_time       充值时间
     * @return: Result              返回查询充值订单消息
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:21
     */
    @RequestMapping(value = "/adminSearchRechargeOrderByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchRechargeOrderByConditions(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       Integer limit,
                                                       Integer page,
                                                       String pay_type,
                                                       String pay_recharge_type,
                                                       String pay_state,
                                                       String username,
                                                       String system_order_no,
                                                       String recharge_time

    ) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("statrt_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                map.put("pay_type", pay_type);
                map.put("pay_recharge_type", pay_recharge_type);
                map.put("pay_state", pay_state);
                map.put("username", "%" + username + "%");
                map.put("system_order_no","%" + system_order_no + "%");
                map.put("recharge_time", recharge_time);
                List<Map<String, Object>> list = this.rechargeOrderService.adminSearchRechargeOrderByConditions(map);
                result.setData(list);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");

            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 积分线下充值
     *
     * @param   request     请求
     * @param   response    响应
     * @param   mobile      手机号
     * @param   amount      充值金额
     * @param   type
     * @return: Result      返回积分线下充值信息
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:24
     */
    @RequestMapping(value = "/integralRechargeAction", method = RequestMethod.POST, params = {"mobile", "amount"})
    @ResponseBody
    public Result integralRechargeAction(HttpServletRequest request, HttpServletResponse response, String mobile, Double amount, Integer type) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Users users = this.usersService.selectByMobile(mobile);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此手机号不存在");
                } else {
//                    AboutUs aboutUs = this.aboutUsService.selectIntegralAbout(9);
//                    if (aboutUs == null){
//                        result.setData("");
//                        result.setStatus(Result.FAIL);
//                        result.setMsg("目前不可充值");
//                    }
//                    else{
//                        if (amount >= Integer.parseInt(aboutUs.getContent()) && amount % Integer.parseInt(aboutUs.getContent()) == 0){
                    //Integer integralNumber = amount / Integer.parseInt(aboutUs.getContent());
                    if (type == 0) { // 通用积分充值
                        Double integralNumber = amount;
                        RechargeOrder rechargeOrder = new RechargeOrder();
                        rechargeOrder.setUser_id(users.getId());
                        rechargeOrder.setRecharge_amount(amount);
                        rechargeOrder.setRecharge_type(1);
                        rechargeOrder.setPay_type(2);
                        rechargeOrder.setState(2);
                        rechargeOrder.setRecharge_time(new Date());
                        rechargeOrder.setSystem_order_no(UUDUtil.getOrderIdByUUId());
                        rechargeOrder.setPay_time(new Date());
                        rechargeOrder.setIntegral_number(integralNumber);
                        int rechargeState = this.rechargeOrderService.addUnderLineRechargeOrder(rechargeOrder);
                        if (rechargeState <= 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("充值失败");
                        } else {
//                                ConsumptionIntegralIncome consumptionIntegralIncome = new ConsumptionIntegralIncome();
////                                ,,,,user_id,income_type
//                                consumptionIntegralIncome.setName("线下充值");
//                                consumptionIntegralIncome.setIncome_date(new Date());
//                                consumptionIntegralIncome.setIncome_amount(integralNumber.doubleValue());
//                                consumptionIntegralIncome.setIncome_after_balance(users.getConsumption_integral()+integralNumber.doubleValue());
//                                consumptionIntegralIncome.setUser_id(users.getId());
//                                consumptionIntegralIncome.setIncome_type(0);
//                                int addIncomeRecordState = this.consumptionIntegralIncomeService.addIncome(consumptionIntegralIncome);


                            GeneralIntegralIncome generalIntegralIncome = new GeneralIntegralIncome();
                            generalIntegralIncome.setName("线下充值");
                            generalIntegralIncome.setIncome_date(new Date());
                            generalIntegralIncome.setIncome_amount(amount);
                            generalIntegralIncome.setIncome_after_balance(users.getGeneral_integral() + amount);
                            generalIntegralIncome.setUser_id(users.getId());
                            generalIntegralIncome.setIncome_type(0);
                            int i = generalIntegralIncomeService.insertUserSilverCoinWithdrawRecord(generalIntegralIncome);

                            if (i <= 0) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("充值失败");
                            } else {
                                users.setGeneral_integral(users.getGeneral_integral() + integralNumber);
                                int updateBalanceState = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                                if (updateBalanceState <= 0) {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("充值失败");
                                } else {
                                    result.setData("");
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("充值成功,获得" + integralNumber + "通用积分");
                                }

                            }

                        }
                    } else {
                        Double integralNumber = amount;
//                        RechargeOrder rechargeOrder = new RechargeOrder();
//                        rechargeOrder.setUser_id(users.getId());
//                        rechargeOrder.setRecharge_amount(amount);
//                        rechargeOrder.setRecharge_type(1);
//                        rechargeOrder.setPay_type(2);
//                        rechargeOrder.setState(2);
//                        rechargeOrder.setRecharge_time(new Date());
//                        rechargeOrder.setSystem_order_no(UUDUtil.getOrderIdByUUId());
//                        rechargeOrder.setPay_time(new Date());
//                        rechargeOrder.setIntegral_number(integralNumber);
//
//                        int rechargeState = this.rechargeOrderService.addUnderLineRechargeOrder(rechargeOrder);
//                        if (rechargeState <= 0) {
//                            result.setData("");
//                            result.setStatus(Result.FAIL);
//                            result.setMsg("充值失败");
//                        } else {
                        int i = integralDetailService.insertIntrgralDetailRecord(users.getId(), "线下充值", amount, 0);
                        if (i <= 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("充值失败");
                        } else {
                            users.setUser_integral(users.getUser_integral() + integralNumber);
                            int updateBalanceState = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                            if (updateBalanceState <= 0) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("充值失败");
                            } else {
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("充值成功,获得" + integralNumber + "平台积分");
                            }

                        }
//                        }
                    }
//                        }
//                        else{
//                            result.setData("");
//                            result.setStatus(Result.FAIL);
//                            result.setMsg("充值金额必须为"+aboutUs.getContent()+"的整数倍");
//                        }
//                    }
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
