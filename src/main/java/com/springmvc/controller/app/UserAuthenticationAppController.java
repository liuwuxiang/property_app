package com.springmvc.controller.app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class UserAuthenticationAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private UserOwnerAuthenticationService userOwnerAuthenticationService;
    @Resource
    private UserCardAuthenticationService userCardAuthenticationService;
    @Resource
    private UserIdCardAuthenticationService userIdCardAuthenticationService;
    @Resource
    private ProvinceAndCityService provinceAndCityService;
    @Resource
    private ResidentialsService residentialsService;
    @Resource
    private ResidentialBuildingService residentialBuildingService;
    @Resource
    private ResidentialUnitService residentialUnitService;
    @Resource
    private ResidentialHousesNumberService residentialHousesNumberService;
    @Resource
    private LoginSessionIdsService sessionIdsService;

    // 获取用户三种认证的认证状态
    @RequestMapping(value = "/getUserAuthenticationState", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserAuthenticationState(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    Map<Object,Object> map = new HashMap<Object,Object>();
                    Map<Object,Object> userOwner = new HashMap<Object,Object>();
                    Map<Object,Object> userCard = new HashMap<Object,Object>();
                    Map<Object,Object> userIdCard = new HashMap<Object,Object>();
                    map.put("userOwner",userOwner);
                    map.put("userCard",userCard);
                    map.put("userIdCard",userIdCard);
                    UserOwnerAuthentication userOwnerAuthentication = this.userOwnerAuthenticationService.selectAuthenticationByUserId(user_id);
                    UserCardAuthentication userCardAuthentication = this.userCardAuthenticationService.selectAuthenticationByUserId(user_id);
                    UserIdCardAuthentication userIdCardAuthentication = this.userIdCardAuthenticationService.selectAuthenticationByUserId(user_id);
                    if (userOwnerAuthentication == null){
                        userOwner.put("authentication_result","未认证");
                        userOwner.put("state",-1);
                    }
                    else if (userOwnerAuthentication.getState() == 0){
                        userOwner.put("authentication_result","待审核");
                        userOwner.put("state",0);
                    }
                    else if (userOwnerAuthentication.getState() == 1){
                        userOwner.put("authentication_result","已认证");
                        userOwner.put("state",1);
                    }
                    else if (userOwnerAuthentication.getState() == 2){
                        userOwner.put("authentication_result","认证失败");
                        userOwner.put("state",2);
                    }

                    if (userCardAuthentication == null){
                        userCard.put("authentication_result","未认证");
                        userCard.put("state",-1);
                    }
                    else if (userCardAuthentication.getState() == 0){
                        userCard.put("authentication_result","待审核");
                        userCard.put("state",0);
                    }
                    else if (userCardAuthentication.getState() == 1){
                        userCard.put("authentication_result","已认证");
                        userCard.put("state",1);
                    }
                    else if (userCardAuthentication.getState() == 2){
                        userCard.put("authentication_result","认证失败");
                        userCard.put("state",2);
                    }

                    if (userIdCardAuthentication == null){
                        userIdCard.put("authentication_result","未认证");
                        userIdCard.put("state",-1);
                    }
                    else if (userIdCardAuthentication.getState() == 0){
                        userIdCard.put("authentication_result","待审核");
                        userIdCard.put("state",0);
                    }
                    else if (userIdCardAuthentication.getState() == 1){
                        userIdCard.put("authentication_result","已认证");
                        userIdCard.put("state",1);
                    }
                    else if (userIdCardAuthentication.getState() == 2){
                        userIdCard.put("authentication_result","认证失败");
                        userIdCard.put("state",2);
                    }

                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");

                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 获取所有省份
    @RequestMapping(value = "/getAllProvince", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllProvince(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(null,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Map<Object,Object> map = new HashMap<Object,Object>();
                List<Map<Object,Object>> provinceList = this.provinceAndCityService.selectAllProvince();
                if (provinceList.size() > 0){
                    map.put("list",provinceList);
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
                else{
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 获取某个省份下的所有城市
    @RequestMapping(value = "/getProvinceAllCity", method = RequestMethod.POST,params = {"province_id"})
    @ResponseBody
    public Result getProvinceAllCity(HttpServletRequest request, HttpServletResponse response,Integer province_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(null,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Map<Object,Object> map = new HashMap<Object,Object>();
                List<Map<Object,Object>> provinceList = this.provinceAndCityService.selectProvinceAllCity(province_id);
                if (provinceList.size() > 0){
                    map.put("list",provinceList);
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
                else{
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 获取某个城市下的所有小区
    @RequestMapping(value = "/getResidentialsByCityId", method = RequestMethod.POST,params = {"city_id"})
    @ResponseBody
    public Result getResidentialsByCityId(HttpServletRequest request, HttpServletResponse response,Integer city_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(null,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Map<Object,Object> map = new HashMap<Object,Object>();
                List<Map<Object,Object>> provinceList = this.residentialsService.selectResidentialsByCityId(city_id);
                if (provinceList.size() > 0){
                    map.put("list",provinceList);
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
                else{
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 获取某个小区下的所有栋号
    @RequestMapping(value = "/getResidentialsAllBuilding", method = RequestMethod.POST,params = {"residential_id"})
    @ResponseBody
    public Result getResidentialsAllBuilding(HttpServletRequest request, HttpServletResponse response,Integer residential_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(null,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Map<Object,Object> map = new HashMap<Object,Object>();
                List<Map<Object,Object>> provinceList = this.residentialBuildingService.selectAllBuildingByResidentialId(residential_id);
                if (provinceList.size() > 0){
                    map.put("list",provinceList);
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
                else{
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 获取某个栋号下所有的单元号
    @RequestMapping(value = "/getAllUnitByBuildingId", method = RequestMethod.POST,params = {"building_id"})
    @ResponseBody
    public Result getAllUnitByBuildingId(HttpServletRequest request, HttpServletResponse response,Integer building_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(null,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Map<Object,Object> map = new HashMap<Object,Object>();
                List<Map<Object,Object>> provinceList = this.residentialUnitService.selectAllUnitByBuildingId(building_id);
                if (provinceList.size() > 0){
                    map.put("list",provinceList);
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
                else{
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 获取某个单元号下所有的房间
    @RequestMapping(value = "/getAllHouseByUnitId", method = RequestMethod.POST,params = {"unit_id"})
    @ResponseBody
    public Result getAllHouseByUnitId(HttpServletRequest request, HttpServletResponse response,Integer unit_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(null,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Map<Object,Object> map = new HashMap<Object,Object>();
                List<Map<Object,Object>> provinceList = this.residentialHousesNumberService.selectAllHouseByUnitId(unit_id);
                if (provinceList.size() > 0){
                    map.put("list",provinceList);
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
                else{
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 提交业主认证信息
    @RequestMapping(value = "/submitOwnerAuthentication", method = RequestMethod.POST,params = {"mobile","name","residential_id","house_id","user_id"})
    @ResponseBody
    public Result submitOwnerAuthentication(HttpServletRequest request, HttpServletResponse response,String mobile,String name,Integer residential_id,Integer house_id,Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    Residentials residentials = this.residentialsService.selectById(residential_id);
                    ResidentialHousesNumber residentialHousesNumber = this.residentialHousesNumberService.selectById(house_id);
                    if (residentials == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此小区不存在");
                    }
                    else if (residentialHousesNumber == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此房间不存在");
                    }
                    else{
                        UserOwnerAuthentication userOwnerAuthentication = this.userOwnerAuthenticationService.selectAuthenticationByUserId(user_id);
                        //操作类型(0-新增,1-修改)
                        Integer operationType = 1;
                        if (userOwnerAuthentication == null){
                            userOwnerAuthentication = new UserOwnerAuthentication();
                            operationType = 0;
                        }
                        userOwnerAuthentication.setBuy_house_mobile(mobile);
                        userOwnerAuthentication.setBuy_house_name(name);
                        userOwnerAuthentication.setProvince_id(residentials.getProvince_id());
                        userOwnerAuthentication.setCity_id(residentials.getCity_id());
                        userOwnerAuthentication.setResidential_id(residentials.getId());
                        userOwnerAuthentication.setResidential_building_id(residentialHousesNumber.getResidential_building_id());
                        userOwnerAuthentication.setResidential_unit_id(residentialHousesNumber.getResidential_unit_id());
                        userOwnerAuthentication.setHouse_number_id(residentialHousesNumber.getId());
                        userOwnerAuthentication.setUser_id(user_id);
                        userOwnerAuthentication.setSubmit_date(new Date());
                        userOwnerAuthentication.setState(0);
                        int  operationState = 0;
                        if (operationType == 0){
                            operationState = this.userOwnerAuthenticationService.addUserOwnerAuthentication(userOwnerAuthentication);
                        }
                        else{
                            operationState = this.userOwnerAuthenticationService.updateUserOwnerAuthentication(userOwnerAuthentication);
                        }
                        result.setData("");
                        result.setStatus(operationState >= 1?Result.SUCCESS:Result.FAIL);
                        result.setMsg(operationState >= 1?"提交成功":"提交失败");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("提交失败");
        }
        return result;
    }


    // 提交车主认证信息
    @RequestMapping(value = "/submitCardAuthentication", method = RequestMethod.POST,params = {"card_number","mobile","real_name","user_id"})
    @ResponseBody
    public Result submitCardAuthentication(HttpServletRequest request, HttpServletResponse response,String card_number,String mobile,String real_name,Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    UserCardAuthentication userCardAuthentication = this.userCardAuthenticationService.selectAuthenticationByUserId(user_id);
                    //操作类型(0-新增,1-修改)
                    Integer operationType = 1;
                    if (userCardAuthentication == null){
                        userCardAuthentication = new UserCardAuthentication();
                        operationType = 0;
                    }
                    userCardAuthentication.setCard_number(card_number);
                    userCardAuthentication.setMobile(mobile);
                    userCardAuthentication.setReal_name(real_name);
                    userCardAuthentication.setUser_id(user_id);
                    userCardAuthentication.setSubmit_date(new Date());
                    userCardAuthentication.setState(0);
                    int  operationState = 0;
                    if (operationType == 0){
                        operationState = this.userCardAuthenticationService.addUserCardAuthentication(userCardAuthentication);
                    }
                    else{
                        operationState = this.userCardAuthenticationService.updateUserCardAuthentication(userCardAuthentication);
                    }
                    result.setData("");
                    result.setStatus(operationState >= 1?Result.SUCCESS:Result.FAIL);
                    result.setMsg(operationState >= 1?"提交成功":"提交失败");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("提交失败");
        }
        return result;
    }


    // 提交身份证认证信息
    @RequestMapping(value = "/submitIdCardAuthentication", method = RequestMethod.POST,params = {"mobile","real_name","id_card_number","handheld_identity_card_photo_id","card_effective_deadline","user_id"})
    @ResponseBody
    public Result submitIdCardAuthentication(HttpServletRequest request, HttpServletResponse response,String mobile,String real_name,String id_card_number,String handheld_identity_card_photo_id,String card_effective_deadline,Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
//                    card_effective_deadline = card_effective_deadline.replaceAll("/","-");
//                    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//                    Date card_effective_deadline_date = format1.parse(card_effective_deadline);
                    UserIdCardAuthentication userIdCardAuthentication = this.userIdCardAuthenticationService.selectAuthenticationByUserId(user_id);
                    //操作类型(0-新增,1-修改)
                    Integer operationType = 1;
                    if (userIdCardAuthentication == null){
                        userIdCardAuthentication = new UserIdCardAuthentication();
                        operationType = 0;
                    }
                    userIdCardAuthentication.setMobile(mobile);
                    userIdCardAuthentication.setReal_name(real_name);
                    userIdCardAuthentication.setId_card_number(id_card_number);
                    userIdCardAuthentication.setCard_effective_deadline(null);
                    userIdCardAuthentication.setHandheld_identity_card_photo(handheld_identity_card_photo_id);
                    userIdCardAuthentication.setUser_id(user_id);
                    userIdCardAuthentication.setSubmit_date(new Date());
                    userIdCardAuthentication.setState(0);
                    int  operationState = 0;
                    if (operationType == 0){
                        operationState = this.userIdCardAuthenticationService.addUserIdCardAuthentication(userIdCardAuthentication);
                    }
                    else{
                        operationState = this.userIdCardAuthenticationService.updateUserIdCardAuthentication(userIdCardAuthentication);
                    }
                    result.setData("");
                    result.setStatus(operationState >= 1?Result.SUCCESS:Result.FAIL);
                    result.setMsg(operationState >= 1?"提交成功":"提交失败");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("提交失败");
        }
        return result;
    }


    // 获取业主认证信息
    @RequestMapping(value = "/getOwnerAuthenticationInformation", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getOwnerAuthenticationInformation(HttpServletRequest request, HttpServletResponse response,Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    UserOwnerAuthentication userOwnerAuthentication = this.userOwnerAuthenticationService.selectAuthenticationByUserId(user_id);
                    if (userOwnerAuthentication == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂未进行业主认证");
                    }
                    else{
                        Map<Object,Object> authenticationMessageMap = this.userOwnerAuthenticationService.getOwnerAuthenticationInformation(user_id);
                        if (authenticationMessageMap == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("获取失败");
                        }
                        else{
                            result.setData(authenticationMessageMap);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("获取成功");
                        }
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 获取车主认证信息
    @RequestMapping(value = "/getCardAuthenticationInformation", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getCardAuthenticationInformation(HttpServletRequest request, HttpServletResponse response,Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    UserCardAuthentication userCardAuthentication = this.userCardAuthenticationService.selectAuthenticationByUserId(user_id);
                    if (userCardAuthentication == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂未进行车主认证");
                    }
                    else{
                        Map<Object,Object> authenticationMessageMap = new HashMap<Object,Object>();
                        authenticationMessageMap.put("card_number",userCardAuthentication.getCard_number());
                        authenticationMessageMap.put("mobile",userCardAuthentication.getMobile());
                        authenticationMessageMap.put("real_name",userCardAuthentication.getReal_name());
                        result.setData(authenticationMessageMap);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 获取身份证认证信息
    @RequestMapping(value = "/getIdCardAuthenticationInformation", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getIdCardAuthenticationInformation(HttpServletRequest request, HttpServletResponse response,Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    UserIdCardAuthentication userIdCardAuthentication = this.userIdCardAuthenticationService.selectAuthenticationByUserId(user_id);
                    if (userIdCardAuthentication == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂未进行身份证认证");
                    }
                    else{
                        Map<Object,Object> authenticationMessageMap = new HashMap<Object,Object>();
                        authenticationMessageMap.put("mobile",userIdCardAuthentication.getMobile());
                        authenticationMessageMap.put("real_name",userIdCardAuthentication.getReal_name());
                        authenticationMessageMap.put("id_card_number",userIdCardAuthentication.getId_card_number());
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//                        String card_effective_deadline = formatter.format(userIdCardAuthentication.getCard_effective_deadline());
//                        authenticationMessageMap.put("card_effective_deadline",card_effective_deadline);
                        authenticationMessageMap.put("handheld_identity_card_photo_url", ImageToolsController.imageShowURL+"?imageid="+userIdCardAuthentication.getHandheld_identity_card_photo());
                        authenticationMessageMap.put("handheld_identity_card_photo_id",userIdCardAuthentication.getHandheld_identity_card_photo());
                        result.setData(authenticationMessageMap);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }
}
