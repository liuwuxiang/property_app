package com.springmvc.controller.admin;

import com.springmvc.entity.AdminTypes;
import com.springmvc.entity.Admins;
import com.springmvc.entity.BackstageMenus;
import com.springmvc.entity.MemberLevels;
import com.springmvc.service.impl.MemberLevelsService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 会员等级管理
 * @Author: 刘武祥
 * @Date: 2019/2/22 0022 11:29
 */
@Controller
@RequestMapping(value = "/admin")
public class MemberLevelAdminController {
    @Resource
    private MemberLevelsService memberLevelsService;

    /**
     *
     * 功能描述: 进入会员等级管理界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/9 0009 14:07
     */
    @RequestMapping(value = "/memberLevelManager")
    @ResponseBody
    public ModelAndView memberLevelManager(HttpServletRequest request){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/member_level/member_level");
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述: 进入会员等级修改界面
     *
     * @param   request, level_id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/9 0009 14:07
     */
    @RequestMapping(value = "/setMemberLevel",method = RequestMethod.GET,params = {"level_id"})
    @ResponseBody
    public ModelAndView setMemberLevel(HttpServletRequest request,Integer level_id){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/member_level/set_member_level");
            MemberLevels memberLevels = this.memberLevelsService.selectById(level_id);
            if (memberLevels == null){
                modelAndView.addObject("level_name","");
                modelAndView.addObject("recharge_consumption_integral","");
                modelAndView.addObject("brief_introduction","");
                modelAndView.addObject("level_id",-1);
            }
            modelAndView.addObject("level_name",memberLevels.getName());
            modelAndView.addObject("recharge_consumption_integral",memberLevels.getRecharge_consumption_integral());
            modelAndView.addObject("brief_introduction",memberLevels.getBrief_introduction());
            modelAndView.addObject("level_id",memberLevels.getId());
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取所有会员等级
     *
     * @param   request, response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:43
     */
    @RequestMapping(value = "/getAllMemberLevel", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllMemberLevel(HttpServletRequest request, HttpServletResponse response){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.memberLevelsService.selectAllLevelAdmin();
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
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 修改会员等级的升级条件及介绍
     *
     * @param   request, response, level_id, recharge_consumption_integral, brief_introduction
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/9 0009 11:23
     */
    @RequestMapping(value = "/setMemberLevel", method = RequestMethod.POST,params = {"level_id","recharge_consumption_integral","brief_introduction"})
    @ResponseBody
    public Result setMemberLevel(HttpServletRequest request, HttpServletResponse response,Integer level_id,Integer recharge_consumption_integral,String brief_introduction){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                MemberLevels memberLevels = this.memberLevelsService.selectById(level_id);
                if (memberLevels == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此等级不存在");
                }
                else{
                    memberLevels.setRecharge_consumption_integral(recharge_consumption_integral);
                    memberLevels.setBrief_introduction(brief_introduction);
                    int state = this.memberLevelsService.updateMemberLevelById(memberLevels);
                    if (state <= 0){
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
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 根据条件查询搜索会员等级
     *
     * @param   request                         请求
     * @param   response                        响应
     * @param   limit                           分页条目数
     * @param   page                            分页数
     * @param   name                            等级名称
     * @param   is_default_level                默认等级
     * @param   recharge_consumption_integral   消耗积分兑换币(多少积分)
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/9 0009 11:31
     */
    @RequestMapping(value = "/adminSearchMemberLevelByConditions",method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchMemberLevelByConditions(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     Integer limit,
                                                     Integer page,
                                                     String name,
                                                     Integer is_default_level,
                                                     Integer recharge_consumption_integral
    ){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId  = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("start_index",limit * (page-1));
                map.put("limit",limit);
                map.put("page",page);
                if (name != null && !"".equals(name)) {
                    map.put("name", "%" + name + "%");
                }
                map.put("is_default_level",("".equals(is_default_level) ? null : is_default_level));
                if (recharge_consumption_integral != null && !"".equals(recharge_consumption_integral)) {
                    map.put("recharge_consumption_integral", "%" + recharge_consumption_integral + "%");
                }
                List<Map<String,Object>> list  = this.memberLevelsService.adminSearchMemberLevelByConditions(map);
                result.setData(list);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

}
