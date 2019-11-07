package com.springmvc.controller.wnk_business_app.tag;

import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkBusinessTag;
import com.springmvc.entity.WnkStoreInformation;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author 杨新杰
 * @Date 2018/11/5 14:53
 * @Description: 万能卡商家标签-商家操作Controller
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class WnkBusinessTagController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkBusinessTagService wnkBusinessTagService;
    @Resource
    private WnkBusinessTagChooseRecordService wnkBusinessTagChooseRecordService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;

    // 获取万能卡商户一级标签
    @RequestMapping(value = "/getWnkBusinessOneTag", method = RequestMethod.POST, params = {"business_id"})
    @ResponseBody
    public Result getWnkBusinessOneTag(HttpServletRequest request, HttpServletResponse response, Integer business_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    // 业务开始
                    List<Map<Object, Object>> list = this.wnkBusinessTagService.selectAllOneTag();
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 获取万能卡商户二级标签
    @RequestMapping(value = "/getWnkBusinessTowTag", method = RequestMethod.POST, params = {"business_id"})
    @ResponseBody
    public Result getWnkBusinessTowTag(HttpServletRequest request, HttpServletResponse response, Integer business_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                if (wnkBusinessAccount == null || wnkStoreInformation == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    WnkBusinessTag wnkBusinessTag = this.wnkBusinessTagService.selectByBusinessTypeId(wnkStoreInformation.getBusiness_type_id());
                    if (wnkBusinessTag == null){
                        // 业务开始
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此商户不可选择标签");
                    }
                    else{
                        // 业务开始
                        List<Map<Object, Object>> list = this.wnkBusinessTagService.selectTwoTagByOneTagId(wnkBusinessTag.getId());
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }

                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    // 获取用户已经选择的标签
    @RequestMapping(value = "/getWnkBusinessIfTrue", method = RequestMethod.POST, params = {"business_id"})
    @ResponseBody
    public Result getWnkBusinessIfTrue(HttpServletRequest request, HttpServletResponse response, Integer business_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    // 业务开始
                    List<Map<Object, Object>> list = this.wnkBusinessTagChooseRecordService.selectTowByBusinessId(business_id);
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 用户已经选择的标签
    @RequestMapping(value = "/saveBusinessTag", method = RequestMethod.POST)
    @ResponseBody
    public Result saveBusinessTag(HttpServletRequest request, HttpServletResponse response, Integer business_id, String arr) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    // 业务开始
                    Map<String, Object> map = JSONObject.fromObject(arr);
                    Integer towTag;
                    boolean towTagStatus;
                    // 遍历更新数据
//                    for (Map.Entry<String, Object> entry : map.entrySet()) {
//                        towTag = Integer.valueOf(entry.getKey());
//                        towTagStatus = String.valueOf(entry.getValue());
//                        System.out.println(towTag + "|" + towTagStatus);
//                        this.wnkBusinessTagChooseRecordService.updateBusinessTagStatus(business_id, towTag, towTagStatus);
//                    }
                    for(String key : map.keySet()){
                        if (!key.equals("") && key != null){
                            towTag = Integer.valueOf(key);
                            towTagStatus = (boolean) map.get(key);
                            System.out.println(towTag + "|" + towTagStatus);
                            this.wnkBusinessTagChooseRecordService.updateBusinessTagStatus(business_id, towTag, towTagStatus);
                        }

                    }

                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("更新成功");
                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

}
