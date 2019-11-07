package com.springmvc.controller.wnk_business_app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.service.impl.*;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author 杨新杰
 * @date 2018/12/30 16:26
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class BusinessWnkBusinessRegionConroller {

    @Resource
    private LoginSessionIdsService sessionIdsService;

    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;

    @Resource
    private WnkBusinessRegionService wnkBusinessRegionService;

    @RequestMapping(value = "/selectBusinessWnkBusinessRegionAll", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessWnkBusinessRegionAll(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer sort_type, Integer gender){
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
                    List<Map<String,Object>> list = this.wnkBusinessRegionService.selectWnkBusinessRegionAll();
                    if (list.size() > 0){
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
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
