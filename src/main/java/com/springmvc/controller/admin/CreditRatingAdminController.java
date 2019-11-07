package com.springmvc.controller.admin;

import com.springmvc.entity.CreditRatingStandard;
import com.springmvc.service.impl.CreditRatingStandardService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述:信用评级
 * @Author: 刘武祥
 * @Date: 2019/2/18 0018 10:25
 */
@Controller
@RequestMapping(value = "/admin")
public class CreditRatingAdminController {
    @Resource
    private CreditRatingStandardService creditRatingStandardService;

    /**
     *
     * 功能描述: 进入信用评级管理界面
     *
     * @param   request 请求
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:03
     */
    @RequestMapping(value = "/creditRatingManager")
    @ResponseBody
    public ModelAndView creditRatingManager(HttpServletRequest request){
        
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/credit_rating/credit_rating");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入信用评级修改界面
     *
     * @param   request      请求
     * @param   record_id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:04
     */
    @RequestMapping(value = "/setCreditRating",method = RequestMethod.GET,params = {"record_id"})
    @ResponseBody
    public ModelAndView setCreditRating(HttpServletRequest request,Integer record_id){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/credit_rating/set_credit_rating");
            CreditRatingStandard creditRatingStandard = this.creditRatingStandardService.selectById(record_id);
            if (creditRatingStandard == null){
                modelAndView.addObject("record_id",-1);
                modelAndView.addObject("clasp_type_name","");
                modelAndView.addObject("clasp_object","");
                modelAndView.addObject("clasp_value","");
                modelAndView.addObject("state",0);
            }
            else{
                modelAndView.addObject("record_id",creditRatingStandard.getId());
                modelAndView.addObject("clasp_type_name",creditRatingStandard.getClasp_type_name());
                if (creditRatingStandard.getClasp_object() == 0){
                    modelAndView.addObject("clasp_object","会员");
                }
                else{
                    modelAndView.addObject("clasp_object","物业");
                }
                modelAndView.addObject("clasp_value",creditRatingStandard.getClasp_value());
                modelAndView.addObject("state",creditRatingStandard.getState());
            }
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取所有扣信标准
     *
     * @param   request, response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/10 0010 15:37
     */
    @RequestMapping(value = "/getAllCreditRatingStandard", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllCreditRatingStandard(HttpServletRequest request, HttpServletResponse response){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.creditRatingStandardService.selectAll();
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
     * 功能描述:根据扣信标准查询所有信用评级信息
     *
     * @param   request         请求
     * @param   response        响应
     * @param   limit           分页条目数
     * @param   page            分页数
     * @param   clasp_type_name 扣信类型
     * @param   clasp_object    扣信对象
     * @param   clasp_value     扣信额度
     * @param   state           状态
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/10 0010 15:56
     */
    @RequestMapping(value = "/adminSearchCreditRatingByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchCreditRatingByConditions(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Integer limit,
                                                               Integer page,
                                                               String clasp_type_name,
                                                               Integer clasp_object,
                                                               Integer clasp_value,
                                                               Integer state
    ){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Map<String, Object> map = new HashMap<>();
                map.put("statrt_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                if (clasp_type_name != null && !"".equals(clasp_type_name)) {
                    map.put("clasp_type_name", "%"+clasp_type_name+"%");
                }
                if (clasp_object != null && !"".equals(clasp_object)) {
                    map.put("clasp_object", clasp_object);
                }
                if (clasp_value != null && !"".equals(clasp_value)) {
                    map.put("clasp_value", clasp_value);
                }
                map.put("state",("".equals(state) || state == null) ? null : state);
                List<Map<String, Object>> List = this.creditRatingStandardService.adminSearchCreditRatingByConditions(map);
                result.setData(List);
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

    /**
     *
     * 功能描述: 修改扣信标准信息
     *
     * @param   request      请求
     * @param   response     响应
     * @param   record_id
     * @param   clasp_value
     * @param   state
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:06
     */
    @RequestMapping(value = "/setCreditRatingInformation", method = RequestMethod.POST,params = {"record_id","clasp_value","state"})
    @ResponseBody
    public Result setCreditRatingInformation(HttpServletRequest request, HttpServletResponse response,Integer record_id,Integer clasp_value,Integer state){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                if (clasp_value <= 0 || clasp_value == null || (state != 0 && state != 1)){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("数据不合法");
                }
                else{
                    CreditRatingStandard creditRatingStandard = this.creditRatingStandardService.selectById(record_id);
                    if (creditRatingStandard == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此记录不存在");
                    }
                    else{
                        creditRatingStandard.setClasp_value(clasp_value);
                        creditRatingStandard.setState(state);
                        int updateState = this.creditRatingStandardService.updateInformation(creditRatingStandard);
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
            result.setMsg("查询失败");
        }
        return result;
    }
}
