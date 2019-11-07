package com.springmvc.controller.wnk_business_app.goods_tag;

import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkBusinessGoodsTag;
import com.springmvc.entity.WnkStoreInformation;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商家商品标签
 * @author 杨新杰
 * @Date 2018/11/17 14:18
 */
@Controller()
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class WnkGoodsTagController {

    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;

    @Resource
    private WnkBusinessGoodsTagService wnkBusinessGoodsTagService;

    // 商家查询商品标签
    @RequestMapping(value = "/selectBusinessGoodsTag", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessGoodsTag(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer type){
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
                    List<Map<String,Object>> list =  wnkBusinessGoodsTagService.selectBusinessGoodsTagById(business_id,type);
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 新增商品标签
    @RequestMapping(value = "/insertBusinessGoodsTag", method = RequestMethod.POST)
    @ResponseBody
    public Result insertBusinessGoodsTag(HttpServletRequest request, HttpServletResponse response, Integer business_id, WnkBusinessGoodsTag wnkBusinessGoodsTag){
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
                    if(  wnkBusinessGoodsTagService.insertBusinessGoodsTag(wnkBusinessGoodsTag) > 0){
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("新增成功");
                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("新增失败");
                    }
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    // 更新商品标签
    @RequestMapping(value = "/updateBusinessGoodsTag", method = RequestMethod.POST)
    @ResponseBody
    public Result updateBusinessGoodsTag(HttpServletRequest request, HttpServletResponse response, Integer business_id, WnkBusinessGoodsTag wnkBusinessGoodsTag){
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
                    if(wnkBusinessGoodsTagService.updateBusinessGoodsTag(wnkBusinessGoodsTag) > 0){
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("修改成功");
                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("修改失败");
                    }
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


}
