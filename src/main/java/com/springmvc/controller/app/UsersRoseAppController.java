package com.springmvc.controller.app;

import com.springmvc.entity.AboutUs;
import com.springmvc.entity.GeneralIntegralIncome;
import com.springmvc.entity.Users;
import com.springmvc.entity.UsersRoseDetail;
import com.springmvc.service.impl.*;
import com.springmvc.utils.GeTuiUserUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 21:46
 * @Description:用户玫瑰(银币)接口类
 */
@Controller
@RequestMapping(value = "/app/v1.0.0")
public class UsersRoseAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersRoseDetailService usersRoseDetailService;
    @Resource
    private AboutUsService aboutUsService;
    @Resource
    private GeneralIntegralIncomeService generalIntegralIncomeService;

    /**
     *
     * 功能描述: 获取用户玫瑰与通用积分兑换比例(多少玫瑰兑换1个通用积分)
     *
     * @param:
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/getRoseAndRMBExchangeProportion", method = RequestMethod.POST)
    @ResponseBody
    public Result getRoseAndRMBExchangeProportion(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            Map<Object,Object> map = new HashMap<>();
            // 兑换比例
            AboutUs aboutUs = this.aboutUsService.selectIntegralAbout(14);
            if (aboutUs == null){
                map.put("rose_rmb_proprotion",0);
            }
            else{
                map.put("rose_rmb_proprotion",Integer.valueOf(aboutUs.getContent()));
            }
            // 最少兑换数量
            aboutUs = this.aboutUsService.selectIntegralAbout(16);
            if (aboutUs == null){
                map.put("rose_min_number",0);
            } else {
                map.put("rose_min_number",aboutUs.getContent());
            }
            // 兑换时间
            aboutUs = this.aboutUsService.selectIntegralAbout(18);
            if (aboutUs == null){
                map.put("rose_open_time",0);
            } else {
                map.put("rose_open_time",aboutUs.getContent());
            }
            result.setData(map);
            result.setStatus(Result.SUCCESS);
            result.setMsg("查询成功");
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    // 获取用户玫瑰(银币)明细(type:交易类型(0-收入,1-支出))
    @RequestMapping(value = "/getUserRoseRoseDetail", method = RequestMethod.POST,params = {"user_id","type"})
    @ResponseBody
    public Result getUserRoseRoseDetail(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (type != 0 && type != 1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    List<Map<Object,Object>> list = this.usersRoseDetailService.selectRecordByUserId(user_id,type);
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
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    // 用户玫瑰(银币)兑换
    @RequestMapping(value = "/userRoseExchange", method = RequestMethod.POST,params = {"user_id","rose_number"})
    @ResponseBody
    public Result userRoseExchange(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer rose_number){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else if (rose_number > users.getSilver_coin_balance()){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("玫瑰余额不足");
                }
                else{
                    AboutUs aboutUs = this.aboutUsService.selectIntegralAbout(14);
                    if (aboutUs == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("系统当前未设置兑换比例");
                    }
                    else{
                        AboutUs aboutUsDate = this.aboutUsService.selectIntegralAbout(18);
                        AboutUs aboutUsMinNumber = this.aboutUsService.selectIntegralAbout(16);
                        if (aboutUsMinNumber != null){
                            Integer minNumber = Integer.parseInt(aboutUsMinNumber.getContent());
                            if (minNumber > rose_number){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("兑换数量不可小于"+minNumber+"个玫瑰");

                                return result;
                            }
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("系统当前未设置最低兑换数量");

                            return result;
                        }

                        if (aboutUsDate != null){
                            String minNumber = aboutUsDate.getContent();
                            Calendar calendar = Calendar.getInstance();//可以对每个时间域单独修改
                            int currentDay = calendar.get(Calendar.DATE);
                            String[] startAndEndDay = minNumber.split("-");
                            if (startAndEndDay.length < 2){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("开放兑换时间不合法");
                            }
                            if (currentDay < Integer.parseInt(startAndEndDay[0]) || currentDay > Integer.parseInt(startAndEndDay[1])){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("兑换失败,每月"+minNumber+"号可兑换");

                                return result;
                            }
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("系统当前未设置开放时间");

                            return result;
                        }

                        Integer bili = Integer.parseInt(aboutUs.getContent());
                        if (bili == 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("当前不可兑换");
                        }
                        else if (rose_number % bili != 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("兑换数量必须为"+bili+"的整数倍");
                        }
                        else{
                            Integer getGeneralIntegral = rose_number / Integer.parseInt(aboutUs.getContent());

                            users.setGeneral_integral(users.getGeneral_integral() + getGeneralIntegral);
                            users.setSilver_coin_balance(users.getSilver_coin_balance() - rose_number);
                            int updateState = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                            if (updateState <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("兑换失败");
                            }
                            else{
                                GeneralIntegralIncome generalIntegralIncome = new GeneralIntegralIncome();
                                generalIntegralIncome.setName("玫瑰兑换");
                                generalIntegralIncome.setIncome_date(new Date());
                                generalIntegralIncome.setIncome_amount(getGeneralIntegral.doubleValue());
                                generalIntegralIncome.setIncome_after_balance(users.getGeneral_integral());
                                generalIntegralIncome.setUser_id(users.getId());
                                generalIntegralIncome.setIncome_type(6);
                                this.generalIntegralIncomeService.insertUserSilverCoinWithdrawRecord(generalIntegralIncome);

                                UsersRoseDetail usersRoseDetail = new UsersRoseDetail();
                                usersRoseDetail.setUser_id(users.getId());
                                usersRoseDetail.setName("玫瑰兑换通用积分");
                                usersRoseDetail.setIntegral_number(rose_number);
                                usersRoseDetail.setTransactions_date(new Date());
                                usersRoseDetail.setTransactions_type(1);
                                this.usersRoseDetailService.insertNewRecord(usersRoseDetail);

                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("兑换成功");

                                if (users.getGetui_appid() != null && !users.getGetui_appid().equals("")){
                                    String pushTitle = "玫瑰支出";
                                    String pushContent = "您已成功兑换了"+rose_number+"枝玫瑰到通用积分，请在猛戳-我的玫瑰查看";
                                    GeTuiUserUtil.pushUser(users.getGetui_appid(),pushTitle,pushContent);
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
}
