package com.springmvc.controller.wnk_business_app;

import com.fasterxml.jackson.databind.util.ObjectIdMap;
import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
import com.springmvc.service.impl.WnkBusinessAccountService;
import com.springmvc.service.impl.WnkBusinessRegisterService;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class WnkBusinessRegisterWnkAppController {
    @Resource
    private WnkBusinessRegisterService wnkBusinessRegisterService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;

    // 万能卡商家注册
    @RequestMapping(value = "/wnkBusinessRegister", method = RequestMethod.POST,params = {"recommend_mobile","store_name","type_id","area","address","login_account","contact_name","contact_mobile","miaoshu"})
    @ResponseBody
    public Result wnkBusinessRegister(HttpServletRequest request, HttpServletResponse response,String recommend_mobile, String store_name,Integer type_id,String area,String address,String login_account,String contact_name,String contact_mobile,String miaoshu){
        Result result = new Result();
        try {
            WnkBusinessAccount recommendWnkBusinessAccount = this.wnkBusinessAccountService.selectByMobile(recommend_mobile);
            if (recommendWnkBusinessAccount == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("不存在此推荐商户");
            }
            else{
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectByMobile(login_account);
                if (wnkBusinessAccount == null){
                    List<Map<Object,Object>> list = this.wnkBusinessRegisterService.selectByMobile(login_account);
                    if (list.size() <= 0){
                        WnkBusinessRegister wnkBusinessRegister = new WnkBusinessRegister();
                        wnkBusinessRegister.setStore_name(store_name);
                        wnkBusinessRegister.setType_id(type_id);
                        wnkBusinessRegister.setArea(area);
                        wnkBusinessRegister.setAddress(address);
                        wnkBusinessRegister.setLogin_account(login_account);
                        wnkBusinessRegister.setContact_name(contact_name);
                        wnkBusinessRegister.setContact_mobile(contact_mobile);
                        wnkBusinessRegister.setMiaoshu(miaoshu);
                        wnkBusinessRegister.setYingye_zhizhao_photo("");
                        wnkBusinessRegister.setMentou_photo("");
                        wnkBusinessRegister.setSubmit_date(new Date());
                        wnkBusinessRegister.setRecommend_business_id(recommendWnkBusinessAccount.getId());
                        wnkBusinessRegister.setLegal_person_id_card("");
                        int state = this.wnkBusinessRegisterService.insertBusinessRegister(wnkBusinessRegister);
                        if (state <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("注册失败");
                        }
                        else{
                            wnkBusinessRegister.setState(1);
                            int updateState = this.wnkBusinessRegisterService.updateInformation(wnkBusinessRegister);
                            if (updateState <= 0){
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("自动审核失败,请等待管理员审核");
                            }
                            else{
                                int zdRegisterState = this.wnkBusinessAccountService.addWnkBusinessZDRegister(wnkBusinessRegister,"123456");
                                if (zdRegisterState == 0){
                                    result.setMsg("注册失败,商户已存在");
                                }
                                else if (zdRegisterState == -1){
                                    result.setMsg("注册失败,创建账号失败");
                                }
                                else if (zdRegisterState == -2){
                                    result.setMsg("注册成功，但添加商户信息失败");
                                }
                                else{
                                    result.setMsg("注册成功,登录信息已发送至您手机");
                                }
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                            }
                        }
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此登录账号已存在");
                    }
                }
                else{
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此登录账号已存在");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 通过id查询商家手机号
    @RequestMapping(value = "/searchMobileById", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result searchMobileById(HttpServletRequest request, HttpServletResponse response, Integer business_id){
        Result result = new Result();
        try {
            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
            if (wnkBusinessAccount == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("此推荐人不存在");
            }
            else{
                Map<Object,Object> map = new HashMap<Object,Object>();
                map.put("mobile",wnkBusinessAccount.getMobile());
                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }
}
