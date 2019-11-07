package com.springmvc.controller.wnk_business_app.maintain;

import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkMaintain;
import com.springmvc.entity.WnkStoreInformation;
import com.springmvc.service.impl.*;
import com.springmvc.utils.BusinessUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统维护员 - 商家端
 * @author 杨新杰
 * @Date 2018/11/14 14:05
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class WnkMaintainController {

    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;

    @Resource
    private WnkMaintainService wnkMaintainService;

    /**
     *
     * 功能描述: 根据商家类别获取对应维护员信息
     *
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/14 14:38
     */
    @RequestMapping(value = "/selectMaintainInfo", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result selectMaintainInfo(HttpServletRequest request, HttpServletResponse response, Integer business_id){
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
                    // 查询商家信息
                    WnkStoreInformation wnkStoreInformation = wnkStoreInformationService.selectByBusinessId(business_id);
                    // 获取商家类别ID
                    Integer business_type_id = wnkStoreInformation.getBusiness_type_id();
                    // 根据商家类别ID查询分类维护员信息
                    WnkMaintain wnkMaintain = this.wnkMaintainService.selectMaintainInfoByBusinessTypeId(business_type_id);
                    if (wnkMaintain == null){
                        wnkMaintain = new WnkMaintain();
                        wnkMaintain.setMaintain_name("客服");
                        wnkMaintain.setMaintain_phone("0874-3959696");
                    }
                    result.setData(wnkMaintain);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
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
