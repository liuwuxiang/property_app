package com.springmvc.controller.admin;

import com.springmvc.entity.ProvinceAndCity;
import com.springmvc.service.impl.ProvinceAndCityService;
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

@Controller
@RequestMapping(value = "/admin")
public class ProvinceAdminController {
    @Resource
    private ProvinceAndCityService provinceAndCityService;

    /**
     *
     * 功能描述: 进入省份管理界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/2/12 0012 14:08
     */
    @RequestMapping(value = "/provinceManager")
    @ResponseBody
    public ModelAndView provinceManager(HttpServletRequest request){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/province_manager/province_manager");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入添加省份界面
     *
     * @param   request  请求
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/2/12 0012 14:07
     */
    @RequestMapping(value = "/addProvince")
    @ResponseBody
    public ModelAndView addProvince(HttpServletRequest request){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/province_manager/add_province");
            modelAndView.addObject("type",0);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入修改省份界面
     *
     * @param   request
     * @param   record_id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/2/12 0012 14:06
     */
    @RequestMapping(value = "/setProvince",method = RequestMethod.GET,params = {"record_id"})
    @ResponseBody
    public ModelAndView setProvince(HttpServletRequest request,Integer record_id){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/province_manager/add_province");
            modelAndView.addObject("type",1);
            ProvinceAndCity provinceAndCity = this.provinceAndCityService.selectById(record_id);
            if (provinceAndCity == null){
                modelAndView.addObject("record_id",-1);
                modelAndView.addObject("province_name","");
            }
            else{
                modelAndView.addObject("record_id",provinceAndCity.getId());
                modelAndView.addObject("province_name",provinceAndCity.getName());
            }
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取所有省份
     *
     * @param   request
     * @param   response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:33
     */
    @RequestMapping(value = "/getAllProvinceAndCity", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllProvinceAndCity(HttpServletRequest request, HttpServletResponse response){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.provinceAndCityService.selectAllProvince();
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
     * 功能描述: 根据条件查询所有省份
     *
     * @param   request     请求
     * @param   response    响应
     * @param   limit       分页条目数
     * @param   page        分页数
     * @param   name        省份名称
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/10 0010 17:16
     */
    @RequestMapping(value = "/adminSearchProvinceAndCityByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchBankByConditions(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Integer limit,
                                              Integer page,
                                              String name
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
                if (name != null && !"".equals(name)) {
                    map.put("name", "%"+name+"%");
                }
                List<Map<String, Object>> List = this.provinceAndCityService.adminSearchProvinceAndCityByConditions(map);
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
     * 功能描述: 添加省份
     *
     * @param   request
     * @param   response
     * @param   province_name
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:28
     */
    @RequestMapping(value = "/addProvinceAction", method = RequestMethod.POST,params = {"province_name"})
    @ResponseBody
    public Result addProvinceAction(HttpServletRequest request, HttpServletResponse response,String province_name){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                ProvinceAndCity provinceAndCity = this.provinceAndCityService.selectByName(province_name);
                if (provinceAndCity != null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此省份已存在");
                }
                else{
                    provinceAndCity = new ProvinceAndCity();
                    provinceAndCity.setName(province_name);
                    int addState = this.provinceAndCityService.addProvince(provinceAndCity);
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
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 修改省份
     *
     * @param   request
     * @param   response
     * @param   record_id
     * @param   province_name
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:30
     */
    @RequestMapping(value = "/setProvinceAction", method = RequestMethod.POST,params = {"record_id","province_name"})
    @ResponseBody
    public Result setProvinceAction(HttpServletRequest request, HttpServletResponse response,Integer record_id,String province_name){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                ProvinceAndCity provinceAndCity = this.provinceAndCityService.selectByName(province_name);
                if (provinceAndCity != null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此省份已存在");
                }
                else{
                    provinceAndCity = this.provinceAndCityService.selectById(record_id);
                    if (provinceAndCity == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此记录不存在");
                    }
                    else{
                        provinceAndCity.setName(province_name);
                        int addState = this.provinceAndCityService.updateProvince(provinceAndCity);
                        if (addState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("修改失败");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("修改成功");
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
