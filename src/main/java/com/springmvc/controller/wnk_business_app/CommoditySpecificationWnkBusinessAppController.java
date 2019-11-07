package com.springmvc.controller.wnk_business_app;

import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkCommodities;
import com.springmvc.entity.WnkCommoditySpecifications;
import com.springmvc.entity.WnkCommodityTypes;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class CommoditySpecificationWnkBusinessAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkCommoditySpecificationsService wnkCommoditySpecificationsService;
    @Resource
    private WnkCommoditiesService wnkCommoditiesService;

    // 添加商品规格
    @RequestMapping(value = "/addCommodityGuiGe", method = RequestMethod.POST,params = {"business_id","commodity_id","guige_name"})
    @ResponseBody
    public Result addCommodityGuiGe(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer commodity_id,String guige_name){
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
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    if (wnkCommodities == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此商品不存在");
                    }
                    else{
                        WnkCommoditySpecifications wnkCommoditySpecifications = new WnkCommoditySpecifications();
                        wnkCommoditySpecifications.setCommodity_id(commodity_id);
                        wnkCommoditySpecifications.setSpecifications_name(guige_name);
                        int addState = this.wnkCommoditySpecificationsService.insertNewRecord(wnkCommoditySpecifications);
                        if (addState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("添加失败");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("添加成功");
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


    // 获取商品规格信息
    @RequestMapping(value = "/getCommodityGuiGe", method = RequestMethod.POST,params = {"business_id","commodity_id","state"})
    @ResponseBody
    public Result getCommodityGuiGe(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer commodity_id,Integer state){
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
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    if (wnkCommodities == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此商品不存在");
                    }
                    else{
                        List<Map<Object,Object>> list = this.wnkCommoditySpecificationsService.selectByCommodityIdAndState(commodity_id, state);
                        if (list.size() <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("暂无规格数据");
                        }
                        else{
                            result.setData(list);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("查询成功");
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

    // 更改规格信息状态
    @RequestMapping(value = "/updateGuiGeState", method = RequestMethod.POST,params = {"business_id","guige_id","state"})
    @ResponseBody
    public Result updateGuiGeState(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer guige_id,Integer state){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (state != 0 && state != 1){
                result.setData("");
                result.setStatus(Result.FAIL);
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
                    WnkCommoditySpecifications wnkCommoditySpecifications = this.wnkCommoditySpecificationsService.selectById(guige_id);
                    if (guige_id == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("规格不存在");
                    }
                    else{
                        int updateState = this.wnkCommoditySpecificationsService.updateStateByRecordId(state,guige_id);
                        if (updateState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("更新失败");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("更新成功");
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


    // 更改规格信息名称
    @RequestMapping(value = "/updateGuiGeName", method = RequestMethod.POST,params = {"business_id","guige_id","name"})
    @ResponseBody
    public Result updateGuiGeName(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer guige_id,String name){
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
                    WnkCommoditySpecifications wnkCommoditySpecifications = this.wnkCommoditySpecificationsService.selectById(guige_id);
                    if (guige_id == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("规格不存在");
                    }
                    else{
                        int updateState = this.wnkCommoditySpecificationsService.updateNameByRecordId(name,guige_id);
                        if (updateState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("更新失败");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("更新成功");
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

    // 更新规格或者插入新规格
    @RequestMapping(value = "/updateAndInsertGuige", method = RequestMethod.POST)
    @ResponseBody
    public Result updateAndInsertGuige(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Integer business_id,
                                       WnkCommoditySpecifications wnkCommoditySpecifications){
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
                    return result;
                }

                WnkCommoditySpecifications w = this.wnkCommoditySpecificationsService.selectById(wnkCommoditySpecifications.getId());
                if (w == null){
                    this.wnkCommoditySpecificationsService.insertNewRecord(wnkCommoditySpecifications);
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("插入成功");

                } else {
                    if (this.wnkCommoditySpecificationsService.updateCommoditySpecificationsInfo(wnkCommoditySpecifications) > 0){
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("更新成功");
                    } else {
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("更新失败");
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
