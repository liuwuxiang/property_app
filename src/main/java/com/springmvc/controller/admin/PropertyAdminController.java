package com.springmvc.controller.admin;

import com.springmvc.entity.PropertyAccount;
import com.springmvc.entity.Residentials;
import com.springmvc.service.impl.PropertyAccountService;
import com.springmvc.service.impl.ProvinceAndCityService;
import com.springmvc.service.impl.ResidentialsService;
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
 * 功能描述: 物业管理
 *
 * @author 刘武祥
 * @date: 2019/1/19 0019 14:41
 */
@Controller
@RequestMapping(value = "/admin")
public class PropertyAdminController {

    @Resource
    private ResidentialsService residentialsService;
    @Resource
    private ProvinceAndCityService provinceAndCityService;
    @Resource
    private PropertyAccountService propertyAccountService;

    /**
     *
     * 功能描述: 进入物业管理界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/19 0019 14:34
     */
    @RequestMapping(value = "/propertyManager")
    @ResponseBody
    public ModelAndView propertyManager(HttpServletRequest request){
        
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/property_manager/property_manager");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入添加物业界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/19 0019 14:35
     */
    @RequestMapping(value = "/addProperty")
    @ResponseBody
    public ModelAndView addProperty(HttpServletRequest request) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/property_manager/add_property");
            modelAndView.addObject("type", 0);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入修改物业界面
     *
     * @param   request
     * @param   record_id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/19 0019 14:35
     */
    @RequestMapping(value = "/setProperty", method = RequestMethod.GET, params = {"record_id"})
    @ResponseBody
    public ModelAndView setProperty(HttpServletRequest request, Integer record_id) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/property_manager/add_property");
            modelAndView.addObject("type", 1);
            Residentials residentials = this.residentialsService.selectById(record_id);
            if (residentials == null) {
                modelAndView.addObject("record_id", -1);
                modelAndView.addObject("proviince_id", "");
                modelAndView.addObject("city_id", "");
                modelAndView.addObject("residentials_name", "");
                modelAndView.addObject("address", "");
                modelAndView.addObject("contact_name", "");
                modelAndView.addObject("contact_mobile", "");

            } else {
                modelAndView.addObject("record_id", residentials.getId());
                modelAndView.addObject("proviince_id", residentials.getProvince_id());
                modelAndView.addObject("city_id", residentials.getCity_id());
                modelAndView.addObject("residentials_name", residentials.getResidential_name());
                modelAndView.addObject("address", residentials.getAddress());
                PropertyAccount propertyAccount = this.propertyAccountService.selectByResidentialsId(residentials.getId());
                if (propertyAccount == null) {
                    modelAndView.addObject("contact_name", "");
                    modelAndView.addObject("contact_mobile", "");
                } else {
                    modelAndView.addObject("contact_name", propertyAccount.getContact_name());
                    modelAndView.addObject("contact_mobile", propertyAccount.getContact_mobile());
                }
            }
        }
        return modelAndView;
    }



    /**
     *
     * 功能描述: 添加物业
     *
     * @param   request, response, account, province_id, city_id, residentials_name, address, contact_name, contact_mobile
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/19 0019 14:37
     */
    @RequestMapping(value = "/addPropertyAction", method = RequestMethod.POST,params = {"account","province_id","city_id","residentials_name","address","contact_name","contact_mobile"})
    @ResponseBody
    public Result addPropertyAction(HttpServletRequest request, HttpServletResponse response,String account,Integer province_id,Integer city_id,String residentials_name,String address,String contact_name,String contact_mobile){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                PropertyAccount propertyAccount = this.propertyAccountService.selectByAccount(account);
                if (propertyAccount != null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此账号已存在");
                }
                else{
                    propertyAccount = new PropertyAccount();
                    propertyAccount.setAccount(account);
                    propertyAccount.setLogin_pwd("123456");
                    propertyAccount.setContact_name(contact_name);
                    propertyAccount.setContact_mobile(contact_mobile);
                    propertyAccount.setCreate_time(new Date());

                    Residentials residentials = new Residentials();
                    residentials.setProvince_id(province_id);
                    residentials.setCity_id(city_id);
                    residentials.setResidential_name(residentials_name);
                    residentials.setCreate_time(new Date());
                    residentials.setAddress(address);
                    int addPropertyState = this.residentialsService.addProperty(residentials);
                    if (addPropertyState <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("添加物业失败");
                    }
                    else{
                        propertyAccount.setResidentials_id(residentials.getId());
                        int addAccountState = this.propertyAccountService.addPropertyPrimaryAccount(propertyAccount);
                        if (addAccountState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("添加物业账号失败");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("成功,账号默认密码为:"+propertyAccount.getLogin_pwd());
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
     * 功能描述: 获取所有物业中心
     *
     * @param   request
     * @param   response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/19 0019 14:33
     */
    @RequestMapping(value = "/getAllProperty", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllProperty(HttpServletRequest request, HttpServletResponse response){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.residentialsService.selectAllAdmin();
                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
                else{
                    for (Integer index = 0;index < list.size();index++){
                        Map<Object,Object> map = list.get(index);
                        map.put("city",map.get("province_name")+"-"+map.get("city_name"));
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
     * 功能描述: 根据条件查询物业中心信息
     *
     * @param   request             请求
     * @param   response            响应
     * @param   limit               分页条目数
     * @param   page                分页数
     * @param   account             主账号
     * @param   city                城市
     * @param   residential_name    物业名称
     * @param   address             详细地址
     * @param   contact_name        联系人
     * @param   contact_mobile      联系电话
     * @param   create_time         入住时间
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/11 0011 11:42
     */
    @RequestMapping(value = "/adminSearchPropertyByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchPropertyByConditions(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      Integer limit,
                                                      Integer page,
                                                      String account,
                                                      String city,
                                                      String residential_name,
                                                      String address,
                                                      String contact_name,
                                                      String contact_mobile,
                                                      String create_time
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
                if (account != null && !"".equals(account)) {
                    map.put("account", account);
                }
                if (city != null && !"".equals(city)) {
                    map.put("province_name", "%" + city + "%");
                    map.put("city_name", "%" + city + "%");
                }
                if (residential_name != null && !"".equals(residential_name)) {
                    map.put("residential_name", "%" + residential_name + "%");
                }
                if (address != null && !"".equals(address)) {
                    map.put("address", "%" + address + "%");
                }
                if (contact_name != null && !"".equals(contact_name)) {
                    map.put("contact_name", "%" + contact_name + "%");
                }
                if (contact_mobile != null && !"".equals(contact_mobile)) {
                    map.put("contact_mobile", contact_mobile);
                }
                map.put("create_time", create_time);
                List<Map<String, Object>> List = this.residentialsService.adminSearchPropertyByConditions(map);
                if (List.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }else {
                    for (Integer index = 0; index < List.size(); index++) {
                        Map<String, Object> m = List.get(index);
                        m.put("city", m.get("province_name") + "-" + m.get("city_name"));
                    }
                }
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
     * 功能描述: 获取所有省份
     *
     * @param   request, response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/19 0019 14:38
     */
    @RequestMapping(value = "/getAllProvinceAction", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllProvinceAction(HttpServletRequest request, HttpServletResponse response){

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
     * 功能描述: 修改物业
     *
     * @param   request, response, record_id, province_id, city_id, residentials_name, address, contact_name, contact_mobile
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/19 0019 14:38
     */
    @RequestMapping(value = "/setPropertyAction", method = RequestMethod.POST,params = {"record_id","province_id","city_id","residentials_name","address","contact_name","contact_mobile"})
    @ResponseBody
    public Result setPropertyAction(HttpServletRequest request, HttpServletResponse response,Integer record_id,Integer province_id,Integer city_id,String residentials_name,String address,String contact_name,String contact_mobile){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Residentials residentials = this.residentialsService.selectById(record_id);
                if (residentials == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此记录不存在");
                }
                else{
                    residentials.setProvince_id(province_id);
                    residentials.setCity_id(city_id);
                    residentials.setResidential_name(residentials_name);
                    residentials.setAddress(address);
                    int residentialsUpdateState = this.residentialsService.updateResidentialsInformation(residentials);
                    if (residentialsUpdateState <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("物业信息更新失败");
                    }
                    else{
                        PropertyAccount propertyAccount = this.propertyAccountService.selectByResidentialsId(residentials.getId());
                        if (propertyAccount == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("此物业无主账号信息");
                        }
                        else{
                            propertyAccount.setContact_name(contact_name);
                            propertyAccount.setContact_mobile(contact_mobile);
                            int updateAccountState = this.propertyAccountService.updateNameAndMobile(propertyAccount);
                            if (updateAccountState <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("联系人信息更新失败");
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
