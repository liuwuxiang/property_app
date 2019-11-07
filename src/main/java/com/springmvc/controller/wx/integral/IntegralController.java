package com.springmvc.controller.wx.integral;


import com.springmvc.entity.GeneralIntegralIncome;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.GeneralIntegralIncomeService;
import com.springmvc.service.impl.IntegralDetailService;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class IntegralController {
    private static Logger logger = Logger.getLogger(GoodsController.class);
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;

    @Resource
    private IntegralDetailService integralDetailService;

    @Resource
    private GeneralIntegralIncomeService generalIntegralIncomeService;

    /**
     *
     * 功能描述: 获取我的积分详情
     *
     * @param  request   HttpServletRequest服务类
     * @param  response  HttpServletResponse服务类
     * @param  user_id   用户ID
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:42
     */
    @RequestMapping(value = "/getMyIntegralDetail", method = RequestMethod.POST)
    @ResponseBody
    public Result getMyIntegralDetail(HttpServletRequest request, HttpServletResponse response, Integer user_id) {
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
                    // 业务开始
                    Users user = usersService.selectById(user_id);

                    Map<String, Object> map = new HashMap<>();
                    map.put("userIntegral", user.getUser_integral());

                    result.setData(map);
                    result.setMsg("获取成功");
                    result.setStatus(Result.SUCCESS);
                    // 业务结束
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
     *
     * 功能描述:
     *
     * @param  request   HttpServletRequest服务类
     * @param  response  HttpServletResponse服务类
     * @param  user_id   用户ID
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:42
     */
    @RequestMapping(value = "/getIntegralCount", method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralCount(HttpServletRequest request, HttpServletResponse response, Integer user_id) {
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
                    // 业务开始
                    List<Map<Object, Object>> maps = integralDetailService.selectUserRecordByUserId(user_id);
                    result.setData(maps);
                    result.setMsg("获取成功");
                    result.setStatus(Result.SUCCESS);
                    // 业务结束
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
     *
     * 功能描述:通过用户id以及type类型查询用户平台积分明细
     *
     * @param  request   HttpServletRequest服务类
     * @param  response  HttpServletResponse服务类
     * @param  user_id   用户ID
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:42
     */
    @RequestMapping(value = "/getIntegralCountByUserIdAndType", method = RequestMethod.POST,params = {"user_id","type"})
    @ResponseBody
    public Result getIntegralCountByUserIdAndType(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer type) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else if (type != 0 && type != 1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else {
                    // 业务开始
                    List<Map<Object, Object>> maps = integralDetailService.selectUserRecordByUserIdAndType(user_id,type);
                    result.setData(maps);
                    result.setMsg("获取成功");
                    result.setStatus(Result.SUCCESS);
                    // 业务结束
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
     *
     * 功能描述: 提现
     *
     * @param  request   HttpServletRequest服务类
     * @param  response  HttpServletResponse服务类
     * @param  user_id   用户ID
     * @param user_pay_pwd    支付密码
     * @param withdraw_number 提现积分数量
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:42
     */
    @RequestMapping(value = "/IntegralOrderCash", method = RequestMethod.POST)
    @ResponseBody
    public Result IntegralOrderCash(HttpServletRequest request, HttpServletResponse response, Integer user_id, String user_pay_pwd, Double withdraw_number) {
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
                    // 业务开始
                    if (withdraw_number % 100 != 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("提现积分必须为100的倍数");
                    } else if (users.getPay_pwd() == null || users.getPay_pwd().equals("")) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("请先设置支付密码");
                    } else if (!users.getPay_pwd().equals(user_pay_pwd)) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("支付密码不正确");
                    } else if (users.getUser_integral() < withdraw_number) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("积分余额不足");
                    } else {

                        // 用于扣除积分
                        Map<String, Object> map = new HashMap<>();
                        map.put("integral", withdraw_number);
                        map.put("user_id" , user_id);


                        // 计算到账多少积分
                        Double ArrivalsGeneralIntegral = withdraw_number * 0.01;

                        // 添加积分提现记录
                        GeneralIntegralIncome g = new GeneralIntegralIncome();
                        // 收益名称
                        g.setName("平台积分提现");
                        // 收益时间
                        g.setIncome_date(new Date());
                        // 收益金额
                        g.setIncome_amount(ArrivalsGeneralIntegral);
                        // 收入类型 5=平台积分提现
                        g.setIncome_type(5);
                        // 用户ID
                        g.setUser_id(user_id);
                        // 收入后余额 用户原来的拥有的金额 +  平台积分提现的金额
                        g.setIncome_after_balance(users.getGeneral_integral() + ArrivalsGeneralIntegral);


                        // 减去用户平台积分
                        if (usersService.updateUserIntegralById(map) > 0){
                            // 插入 通用积分收入明细
                            if (generalIntegralIncomeService.insertUserSilverCoinWithdrawRecord(g) > 0){
                                users.setGeneral_integral(users.getGeneral_integral()+ArrivalsGeneralIntegral);
                                // 更新通用积分余额
                                if (usersService.updateUserTYAndXFAndSilverCoinBalance(users) > 0){
                                    // 插入积分商城提现记录
                                    if (integralDetailService.insertIntrgralDetailRecord(user_id,"积分提现",withdraw_number,1) > 0){
                                        result.setData("");
                                        result.setMsg("提现成功");
                                        result.setStatus(Result.SUCCESS);
                                    } else {
                                        result.setData("");
                                        result.setMsg("提现失败");
                                        result.setStatus(Result.FAIL);
                                    }
                                } else {
                                    result.setData("");
                                    result.setMsg("提现失败");
                                    result.setStatus(Result.FAIL);
                                }
                            } else {
                                result.setData("");
                                result.setMsg("提现失败");
                                result.setStatus(Result.FAIL);
                            }
                        } else {
                            result.setData("");
                            result.setMsg("提现失败");
                            result.setStatus(Result.FAIL);
                        }
                    }
                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


}
