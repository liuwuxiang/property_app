package com.springmvc.controller.wnk_business_app;

import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.GeTuiBusinessUtil;
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
 * @Date: 2018/10/28 23:50
 * @Description:万能卡商家玫瑰明细接口类
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class WnkBusinessRoseDetailWnkBusinessAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkBusinessRoseDetailService wnkBusinessRoseDetailService;
    @Resource
    private AboutUsService aboutUsService;
    @Resource
    private WnkBusinessBalanceDetailService wnkBusinessBalanceDetailService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;

    /**
     *
     * 功能描述: 获取万能卡商家玫瑰明细
     *
     * @param: business_id,type(交易类型(0-收入,1-支出))
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/getWnkBusinessRoseDetail", method = RequestMethod.POST,params = {"business_id","type"})
    @ResponseBody
    public Result getWnkBusinessRoseDetail(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if(type != 0 && type != 1){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("数据不合法");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    List<Map<Object,Object>> list = this.wnkBusinessRoseDetailService.selectRecordByBusinessId(business_id,type);
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

    /**
     *
     * 功能描述: 获取万能卡商家玫瑰与人民币兑换比例(多少玫瑰兑换1元人民币)
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
            AboutUs aboutUs = this.aboutUsService.selectIntegralAbout(13);
            Map<Object,Object> map = new HashMap<Object,Object>();
            if (aboutUs == null){
                map.put("rose_rmb_proprotion",0);
            }
            else{
                map.put("rose_rmb_proprotion",Integer.valueOf(aboutUs.getContent()));
            }
            result.setData(map);
            result.setStatus(Result.SUCCESS);
            result.setMsg("查询成功");
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 万能卡商家玫瑰兑换人民币
     *
     * @param:
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/wnkBusinessRoseExchangeRMB", method = RequestMethod.POST,params = {"business_id","rose_number"})
    @ResponseBody
    public Result wnkBusinessRoseExchangeRMB(HttpServletRequest request, HttpServletResponse response,Integer business_id,Integer rose_number){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else if (wnkBusinessAccount.getRose_number() < rose_number){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("玫瑰余额不足");
                }
                else{
                    AboutUs aboutUs = this.aboutUsService.selectIntegralAbout(13);
                    if (aboutUs == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("系统当前未设置兑换比例");
                    }
                    else{
                        AboutUs aboutUsDate = this.aboutUsService.selectIntegralAbout(17);
                        AboutUs aboutUsMinNumber = this.aboutUsService.selectIntegralAbout(15);
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
                        else{
                            Double getBalance = Double.valueOf(rose_number / Double.parseDouble(aboutUs.getContent()));

                            WnkBusinessRoseDetail wnkBusinessRoseDetail = new WnkBusinessRoseDetail();
                            wnkBusinessRoseDetail.setBusiness_id(business_id);
                            wnkBusinessRoseDetail.setName("玫瑰兑换");
                            wnkBusinessRoseDetail.setIntegral_number(rose_number);
                            wnkBusinessRoseDetail.setTransactions_date(new Date());
                            wnkBusinessRoseDetail.setTransactions_type(1);

                            wnkBusinessAccount.setBalance(wnkBusinessAccount.getBalance() + getBalance);
                            wnkBusinessAccount.setRose_number(wnkBusinessAccount.getRose_number() - rose_number);
                            int balanceState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                            if (balanceState <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("兑换失败");
                            }
                            else{
                                WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                                wnkBusinessBalanceDetail.setBusiness_id(business_id);
                                wnkBusinessBalanceDetail.setName("玫瑰积分兑换");
                                wnkBusinessBalanceDetail.setTransaction_amount(getBalance);
                                wnkBusinessBalanceDetail.setJoin_time(new Date());
                                wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance());
                                wnkBusinessBalanceDetail.setType(0);
                                wnkBusinessBalanceDetail.setState(0);
                                this.wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);
                                this.wnkBusinessRoseDetailService.insertNewRecord(wnkBusinessRoseDetail);
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("兑换成功");

                                WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                                if (wnkStoreInformation != null){
                                    if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")){
                                        String pushTitle = "我的玫瑰支出";
                                        String pushContent = "您已成功兑换了"+rose_number+"枝玫瑰到我的账户中，请在猛戳-我的玫瑰查看";
                                        GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }
}
