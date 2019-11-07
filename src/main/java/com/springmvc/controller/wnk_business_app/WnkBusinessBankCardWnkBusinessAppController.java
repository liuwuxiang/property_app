package com.springmvc.controller.wnk_business_app;

import com.springmvc.entity.UsersBankCard;
import com.springmvc.entity.WithdrawSupportBank;
import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkBusinessBankCard;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.WithdrawSupportBankService;
import com.springmvc.service.impl.WnkBusinessAccountService;
import com.springmvc.service.impl.WnkBusinessBankCardService;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/29 00:53
 * @Description:万能卡商家银行卡信息接口类
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class WnkBusinessBankCardWnkBusinessAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkBusinessBankCardService wnkBusinessBankCardService;
    @Resource
    private WithdrawSupportBankService withdrawSupportBankService;

    /**
     *
     * 功能描述: 获取万能卡商家银行卡信息
     *
     * @param: business_id
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/getWnkBusinessBankCard", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result getWnkBusinessBankCard(HttpServletRequest request, HttpServletResponse response, Integer business_id){
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
                else{
                    WnkBusinessBankCard wnkBusinessBankCard = this.wnkBusinessBankCardService.selectRecordByBusinessId(business_id);
                    Map<Object,Object> map = new HashMap<Object,Object>();
                    if (wnkBusinessBankCard == null){
                        map.put("bank_id",-1);
                        map.put("bank_name","");
                        map.put("bank_card_number","");
                        map.put("real_name","");
                    }
                    else{
                        WithdrawSupportBank withdrawSupportBank = this.withdrawSupportBankService.selectById(wnkBusinessBankCard.getBank_id());
                        if (withdrawSupportBank == null){
                            map.put("bank_id",-1);
                            map.put("bank_name","");
                            map.put("bank_card_number","");
                            map.put("real_name","");
                        }
                        else{
                            map.put("bank_id",wnkBusinessBankCard.getBank_id());
                            map.put("bank_name",withdrawSupportBank.getName());
                            map.put("bank_card_number",wnkBusinessBankCard.getBank_number());
                            map.put("real_name",wnkBusinessBankCard.getBank_card_name());
                        }
                    }
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
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
     * 功能描述: 设置万能卡商家银行卡信息
     *
     * @param: business_id
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/setWnkBusinessBankCard", method = RequestMethod.POST,params = {"business_id","bank_id","bank_card_number","real_name"})
    @ResponseBody
    public Result setWnkBusinessBankCard(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer bank_id,String bank_card_number,String real_name){
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
                else{
                    WithdrawSupportBank withdrawSupportBank = this.withdrawSupportBankService.selectById(bank_id);
                    if (withdrawSupportBank == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("银行不存咋");
                    }
                    else{
                        Integer state = 0;
                        WnkBusinessBankCard wnkBusinessBankCard = this.wnkBusinessBankCardService.selectRecordByBusinessId(business_id);
                        if (wnkBusinessBankCard == null){
                            wnkBusinessBankCard = new WnkBusinessBankCard();
                            wnkBusinessBankCard.setBank_id(bank_id);
                            wnkBusinessBankCard.setBank_number(bank_card_number);
                            wnkBusinessBankCard.setBank_card_name(real_name);
                            wnkBusinessBankCard.setBusiness_id(business_id);
                            state = this.wnkBusinessBankCardService.insertBusinessBankCard(wnkBusinessBankCard);
                        }
                        else{
                            wnkBusinessBankCard.setBank_id(bank_id);
                            wnkBusinessBankCard.setBank_card_name(real_name);
                            wnkBusinessBankCard.setBank_number(bank_card_number);
                            wnkBusinessBankCard.setBusiness_id(business_id);
                            state = this.wnkBusinessBankCardService.updateBankCard(wnkBusinessBankCard);
                        }
                        if (state <= 0){
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
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }
}
