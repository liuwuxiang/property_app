package com.springmvc.controller.wnk_business_app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.Users;
import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkCommodityTypes;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.WnkBusinessAccountService;
import com.springmvc.service.impl.WnkCommoditiesService;
import com.springmvc.service.impl.WnkCommodityTypesService;
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

@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class CommodityTypeWnkBusinessAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkCommodityTypesService wnkCommodityTypesService;
    @Resource
    private WnkCommoditiesService wnkCommoditiesService;

    // 添加商品分类
    @RequestMapping(value = "/addCommodityType", method = RequestMethod.POST,params = {"business_id","type_name"})
    @ResponseBody
    public Result addCommodityType(HttpServletRequest request, HttpServletResponse response, Integer business_id,String type_name){
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
                    WnkCommodityTypes wnkCommodityTypes = this.wnkCommodityTypesService.selectByNameAndBusinessId(type_name, business_id);
                    if (wnkCommodityTypes == null){
                        wnkCommodityTypes = new WnkCommodityTypes();
                        wnkCommodityTypes.setType_name(type_name);
                        wnkCommodityTypes.setBusiness_id(business_id);
                        int state = this.wnkCommodityTypesService.addType(wnkCommodityTypes);
                        if (state >= 1){
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("添加成功");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("添加失败");
                        }
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此分类已存在");
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

    // 获取所有商品分类
    @RequestMapping(value = "/getAllCommodityType", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result getAllCommodityType(HttpServletRequest request, HttpServletResponse response, Integer business_id){
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
                    List<Map<Object,Object>> list = this.wnkCommodityTypesService.selectAllTypeByBusinessId(business_id);
//                    if (list.size() <= 0){
//                        result.setData("");
//                        result.setStatus(Result.FAIL);
//                        result.setMsg("暂无分类");
//                    }
//                    else{
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
//                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    // 修改商品分类名称
    @RequestMapping(value = "/setCommodityTypeName", method = RequestMethod.POST,params = {"business_id","type_id","new_type_name"})
    @ResponseBody
    public Result setCommodityTypeName(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer type_id,String new_type_name){
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
                    WnkCommodityTypes wnkCommodityTypes = this.wnkCommodityTypesService.selectById(type_id);
                    if (wnkCommodityTypes == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("分类不存在");
                    }
                    else{
                        WnkCommodityTypes wnkCommodityTypes2 = this.wnkCommodityTypesService.selectByNameAndBusinessId(new_type_name, business_id);
                        if(wnkCommodityTypes2 != null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("此分类名称已存在");
                        }
                        else{
                            wnkCommodityTypes.setType_name(new_type_name);
                            int state = this.wnkCommodityTypesService.updateType(wnkCommodityTypes);
                            if (state >= 1){
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("修改成功");
                            }
                            else{
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("修改失败");
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

    /**
     *
     * 功能描述: 删除商品分类
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/20 10:16 PM
     */
    @RequestMapping(value = "/deleteCommodityType", method = RequestMethod.POST,params = {"business_id","type_id"})
    @ResponseBody
    public Result deleteCommodityType(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer type_id){
        Result result = new Result();
        result.setData("");
        result.setStatus(Result.FAIL);
        result.setMsg("分类不支持删除");
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
                    WnkCommodityTypes wnkCommodityTypes = this.wnkCommodityTypesService.selectById(type_id);
                    if (wnkCommodityTypes == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("分类不存在");
                    }
                    else{
                        int deleteState = this.wnkCommodityTypesService.updateTypeDeleteState(1,type_id);
                        if (deleteState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("删除失败");
                        }
                        else{
                            this.wnkCommoditiesService.updateDeleteStateByTypeId(1,type_id);
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("删除成功");
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
