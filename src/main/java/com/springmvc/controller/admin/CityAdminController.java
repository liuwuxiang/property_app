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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class CityAdminController {
    @Resource
    private ProvinceAndCityService provinceAndCityService;

    //进入城市管理界面
    @RequestMapping(value = "/cityManager",method = RequestMethod.GET,params = {"province_id"})
    @ResponseBody
    public ModelAndView cityManager(HttpServletRequest request,Integer province_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/city_manager/city_manager");
        modelAndView.addObject("province_id",province_id);
        }
        return modelAndView;
    }

    //进入添加城市界面
    @RequestMapping(value = "/addCity",method = RequestMethod.GET,params = {"province_id"})
    @ResponseBody
    public ModelAndView addCity(HttpServletRequest request,Integer province_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/city_manager/add_city");
            modelAndView.addObject("type",0);
            modelAndView.addObject("province_id",province_id);
        }
        return modelAndView;
    }

    //进入修改城市界面
    @RequestMapping(value = "/setCity",method = RequestMethod.GET,params = {"province_id","city_id"})
    @ResponseBody
    public ModelAndView setCity(HttpServletRequest request,Integer province_id,Integer city_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/city_manager/add_city");
            modelAndView.addObject("type",1);
            modelAndView.addObject("province_id",province_id);
            ProvinceAndCity provinceAndCity = this.provinceAndCityService.selectById(city_id);
            if (provinceAndCity == null){
                modelAndView.addObject("city_id",-1);
                modelAndView.addObject("city_name","");
            }
            else{
                modelAndView.addObject("city_id",provinceAndCity.getId());
                modelAndView.addObject("city_name",provinceAndCity.getName());
            }
        }
        return modelAndView;
    }

    // 获取某个省份下的所有城市
    @RequestMapping(value = "/getAllCityByProvinceId", method = RequestMethod.POST,params = {"province_id"})
    @ResponseBody
    public Result getAllCityByProvinceId(HttpServletRequest request, HttpServletResponse response,Integer province_id){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.provinceAndCityService.selectProvinceAllCity(province_id);
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

    // 新增城市
    @RequestMapping(value = "/addCityAction", method = RequestMethod.POST,params = {"province_id","city_name"})
    @ResponseBody
    public Result addCityAction(HttpServletRequest request, HttpServletResponse response,Integer province_id,String city_name){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                ProvinceAndCity proVince = this.provinceAndCityService.selectById(province_id);
                if (proVince == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此省份不存在");
                }
                else if (proVince.getType() != 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此省份不存在");
                }
                else{
                    ProvinceAndCity provinceAndCity = this.provinceAndCityService.selectCityByNameAndProvinceId(city_name, province_id);
                    if (provinceAndCity != null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此城市已存在");
                    }
                    else{
                        provinceAndCity = new ProvinceAndCity();
                        provinceAndCity.setUpper_level_id(province_id);
                        provinceAndCity.setName(city_name);
                        int addState = this.provinceAndCityService.addCity(provinceAndCity);
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

    // 修改城市
    @RequestMapping(value = "/setCityAction", method = RequestMethod.POST,params = {"city_id","city_name","province_id"})
    @ResponseBody
    public Result setCityAction(HttpServletRequest request, HttpServletResponse response,Integer city_id,String city_name,Integer province_id){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                ProvinceAndCity provinceAndCity = this.provinceAndCityService.selectCityByNameAndProvinceId(city_name,province_id);
                if (provinceAndCity != null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此城市名已存在");
                }
                else{
                    provinceAndCity = this.provinceAndCityService.selectById(city_id);
                    if (provinceAndCity == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此记录不存在");
                    }
                    else if (provinceAndCity.getType() != 1){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此记录不存在");
                    }
                    else{
                        provinceAndCity.setName(city_name);
                        int updateState = this.provinceAndCityService.updateCity(provinceAndCity);
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

}
