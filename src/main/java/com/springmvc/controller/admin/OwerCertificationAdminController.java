package com.springmvc.controller.admin;

import com.springmvc.entity.UserOwnerAuthentication;
import com.springmvc.service.impl.UserOwnerAuthenticationService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 认证管理
 * @Author: 刘武祥
 * @Date: 2019/2/15 0015 16:31
 */
@Controller
@RequestMapping(value = "/admin")
public class OwerCertificationAdminController {
    @Resource
    private UserOwnerAuthenticationService userOwnerAuthenticationService;

    /**
     *
     * 功能描述: 进入业主认证管理界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/10 0010 10:07
     */
    @RequestMapping(value = "/owerCertificationManager")
    @ResponseBody
    public ModelAndView owerCertificationManager(HttpServletRequest request){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/owner_certification/ower_certification");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取所有业主认证信息
     *
     * @param   request, response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/10 0010 10:15
     */
    @RequestMapping(value = "/getAllOwnerCertification", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllOwnerCertification(HttpServletRequest request, HttpServletResponse response){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.userOwnerAuthenticationService.getAllOwnerAuthenticationAdmin();
                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
                else{
                    for (Integer index = 0;index < list.size();index++){
                        Map<Object,Object> map = list.get(index);
                        map.put("city",map.get("province_name")+"-"+map.get("city_name"));
                        map.put("house_number",map.get("residential_building")+"-"+map.get("residential_unit")+"-"+map.get("residential_houses_number"));
                    }
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
     * 功能描述:根据条件查询业主认证信息
     *
     * @param   request             请求
     * @param   response            响应
     * @param   limit               分页条目数
     * @param   page                分页数
     * @param   buy_house_mobile    购房电话
     * @param   buy_house_name      购房姓名
     * @param   city                城市
     * @param   residential_name
     * @param   house_number
     * @param   state
     * @param   submit_date_str
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/10 0010 10:50
     */
    @RequestMapping(value = "/adminSearchOwnerCertificationByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchOwnerCertificationByConditions(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            Integer limit,
                                                            Integer page,
                                                            String buy_house_mobile,
                                                            String buy_house_name,
                                                            Integer city,
                                                            String residential_name,
                                                            String house_number,
                                                            Integer state,
                                                            String submit_date_str
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
                    if (buy_house_mobile != null && !"".equals(buy_house_mobile)) {
                        map.put("buy_house_mobile", "%"+buy_house_mobile+"%");
                    }
                    if (buy_house_name != null && !"".equals(buy_house_name)) {
                        map.put("buy_house_name", "%" + buy_house_name + "%");
                    }
                    if (city != null && !"".equals(city)) {
                        map.put("city", "%"+city+"%");
                    }
                    if (residential_name != null && !"".equals(residential_name)) {
                        map.put("residential_name", "%"+residential_name+"%");
                    }
                    if (house_number != null && !"".equals(house_number)) {
                        map.put("house_number","%"+ house_number+"%");
                    }
                    map.put("state", ("".equals(state) ? null : state));
                    map.put("submit_date_str", submit_date_str);
                    List<Map<String, Object>> mapList = this.userOwnerAuthenticationService.adminSearchOwnerCertificationByConditions(map);
                    for (Integer index = 0;index < mapList.size();index++){
                        Map<String,Object> mp = mapList.get(index);
                        mp.put("city",mp.get("province_name")+"-"+mp.get("city_name"));
                        mp.put("house_number",mp.get("residential_building")+"-"+mp.get("residential_unit")+"-"+mp.get("residential_houses_number"));
                    }
                    result.setData(mapList);
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
     * 功能描述: 业主认证信息审核通过/不通过(state=1认证通过,state=2认证不通过)
     *
     * @param   request, response, record_id, state
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/10 0010 10:32
     */
    @RequestMapping(value = "/setOwnerCertificationPass", method = RequestMethod.POST,params = {"record_id","state"})
    @ResponseBody
    public Result setOwnerCertificationPass(HttpServletRequest request, HttpServletResponse response,Integer record_id,Integer state){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                if (state != 1 && state != 2){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("数据不合法");
                }
                else{
                    UserOwnerAuthentication userOwnerAuthentication = this.userOwnerAuthenticationService.selectById(record_id);
                    if (userOwnerAuthentication == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此记录不存在");
                    }
                    else{
                        if (userOwnerAuthentication.getState() != 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("此记录已审核");
                        }
                        else{
                            userOwnerAuthentication.setState(state);
                            userOwnerAuthentication.setAuthentication_finish_date(new Date());
                            int updateState = this.userOwnerAuthenticationService.updateState(userOwnerAuthentication);
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

            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }
}
