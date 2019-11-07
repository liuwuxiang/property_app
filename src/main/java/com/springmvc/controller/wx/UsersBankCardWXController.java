package com.springmvc.controller.wx;

import com.springmvc.entity.Users;
import com.springmvc.entity.UsersBankCard;
import com.springmvc.entity.WithdrawSupportBank;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersBankCardService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.service.impl.WithdrawSupportBankService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class UsersBankCardWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersBankCardService usersBankCardService;
    @Resource
    private WithdrawSupportBankService withdrawSupportBankService;
    @Resource
    private UsersService usersService;

    //进入银行卡信息设置页面
    @RequestMapping(value = "/joinBankCardSetting")
    @ResponseBody
    public ModelAndView joinBankCardSetting(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,1,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/user_bank_card_setting");
            UsersBankCard usersBankCard = this.usersBankCardService.selectRecordByUserId(Integer.parseInt(user_id_str));
            if (usersBankCard == null){
                modelAndView.addObject("bank_id",-1);
                modelAndView.addObject("bank_name","");
                modelAndView.addObject("bank_card_number","");
                modelAndView.addObject("real_name","");
            }
            else{
                WithdrawSupportBank withdrawSupportBank = this.withdrawSupportBankService.selectById(usersBankCard.getBank_id());
                if (withdrawSupportBank == null){
                    modelAndView.addObject("bank_id",-1);
                    modelAndView.addObject("bank_name","");
                    modelAndView.addObject("bank_card_number","");
                    modelAndView.addObject("real_name","");
                }
                else{
                    modelAndView.addObject("bank_id",usersBankCard.getBank_id());
                    modelAndView.addObject("bank_name",withdrawSupportBank.getName());
                    modelAndView.addObject("bank_card_number",usersBankCard.getBank_number());
                    modelAndView.addObject("real_name",usersBankCard.getBank_card_name());
                }
            }
        }
        return modelAndView;

    }


    // 获取用户银行卡信息
    @RequestMapping(value = "/getUserBankInformation", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserBankInformation(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
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
                else{
                    UsersBankCard usersBankCard = this.usersBankCardService.selectRecordByUserId(user_id);
                    Map<Object,Object> map = new HashMap<Object,Object>();
                    if (usersBankCard == null){
                        map.put("bank_id",-1);
                        map.put("bank_name","");
                        map.put("bank_card_number","");
                        map.put("real_name","");
                    }
                    else{
                        WithdrawSupportBank withdrawSupportBank = this.withdrawSupportBankService.selectById(usersBankCard.getBank_id());
                        if (withdrawSupportBank == null){
                            map.put("bank_id",-1);
                            map.put("bank_name","");
                            map.put("bank_card_number","");
                            map.put("real_name","");
                        }
                        else{
                            map.put("bank_id",usersBankCard.getBank_id());
                            map.put("bank_name",withdrawSupportBank.getName());
                            map.put("bank_card_number",usersBankCard.getBank_number());
                            map.put("real_name",usersBankCard.getBank_card_name());
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
            result.setMsg("操作失败");
        }
        return result;
    }

    // 设置银行卡信息事件
    @RequestMapping(value = "/setUserBankCardMessage", method = RequestMethod.POST,params = {"user_id","bank_id","bank_card_number","real_name"})
    @ResponseBody
    public Result setUserBankCardMessage(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer bank_id,String bank_card_number,String real_name){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
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
                else{
                    WithdrawSupportBank withdrawSupportBank = this.withdrawSupportBankService.selectById(bank_id);
                    if (withdrawSupportBank == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("银行不存咋");
                    }
                    else{
                        Integer state = 0;
                        UsersBankCard usersBankCard = this.usersBankCardService.selectRecordByUserId(users.getId());
                        if (usersBankCard == null){
                            usersBankCard = new UsersBankCard();
                            usersBankCard.setBank_id(bank_id);
                            usersBankCard.setBank_number(bank_card_number);
                            usersBankCard.setBank_card_name(real_name);
                            usersBankCard.setUser_id(user_id);
                            state = this.usersBankCardService.insertUserBankCard(usersBankCard);
                        }
                        else{
                            usersBankCard.setBank_id(bank_id);
                            usersBankCard.setBank_card_name(real_name);
                            usersBankCard.setBank_number(bank_card_number);
                            usersBankCard.setUser_id(user_id);
                            state = this.usersBankCardService.updateBankCard(usersBankCard);
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
            result.setMsg("操作失败");
        }
        return result;
    }

    // 获取用户是否已设置银行卡
    @RequestMapping(value = "/getUserBankCard", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserBankCard(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
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
                else{
                        Map<Object,Object> map = new HashMap<Object,Object>();
                        UsersBankCard usersBankCard = this.usersBankCardService.selectRecordByUserId(users.getId());
                        if (usersBankCard == null){
                            map.put("bank_state",0);
                            result.setData(map);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("请先设置银行卡");
                        }
                        else{
                            map.put("bank_state",1);
                            result.setData(map);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("已设置");
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
