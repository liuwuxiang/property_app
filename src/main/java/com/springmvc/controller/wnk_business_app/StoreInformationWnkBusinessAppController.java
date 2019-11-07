package com.springmvc.controller.wnk_business_app;

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
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class StoreInformationWnkBusinessAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private UsersService usersService;
    @Resource
    private WnkBusinessLevelService wnkBusinessLevelService;
    @Resource
    private WnkBusinessLabelService wnkBusinessLabelService;
    @Resource
    private WnkBusinessLevelIntegralDetailService wnkBusinessLevelIntegralDetailService;
    @Resource
    private WnkBusinessTagService wnkBusinessTagService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;

    // 获取店铺信息
    @RequestMapping(value = "/getStoreInformation", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result getStoreInformation(HttpServletRequest request, HttpServletResponse response, Integer business_id){
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
                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                    if (wnkStoreInformation == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("无店铺信息");
                    }
                    else{
                        Map<Object,Object> map = new HashMap<Object,Object>();
                        map.put("name",wnkStoreInformation.getStore_name());
                        map.put("address",wnkStoreInformation.getAddress());
                        map.put("mobile",wnkStoreInformation.getContact_mobile());
                        map.put("store_describe",wnkStoreInformation.getStore_describe());

                        map.put("fuwu_label",wnkStoreInformation.getFuwu_label());
                        map.put("tese_label",wnkStoreInformation.getTese_label());

                        map.put("business_hours",wnkStoreInformation.getBusiness_hours());

                        map.put("lat",wnkStoreInformation.getLat());
                        map.put("longt",wnkStoreInformation.getLongt());
                        map.put("position_name",wnkStoreInformation.getPosition_name());
                        if (wnkStoreInformation.getArea() == null){
                            map.put("area","");
                        }
                        else{
                            map.put("area",wnkStoreInformation.getArea());
                        }
                        String cover_photo = wnkStoreInformation.getCover_photo();
                        if (cover_photo == null || cover_photo.equals("")){
                            map.put("cover_photo","");
                        }
                        else{
                            map.put("cover_photo",ImageToolsController.imageShowURL+"?imageid="+cover_photo);
                        }
                        Double positive_price = wnkStoreInformation.getPositive_price();
                        if (positive_price == null){
                            map.put("positive_price","");
                        }
                        else{
                            map.put("positive_price",positive_price);
                        }


                        List<String> photoList = new ArrayList<String>();
                        if ( wnkStoreInformation.getBanners_photoid() != null && !wnkStoreInformation.getBanners_photoid().equals("")){
                            String[] photoIds = wnkStoreInformation.getBanners_photoid().split(",");
                            for (Integer index = 0;index < photoIds.length;index++){
                                photoList.add(ImageToolsController.imageShowURL+"?imageid="+photoIds[index]);
                            }
                        }
                        map.put("photos",photoList);
                        if (photoList.size() >= 1){
                            map.put("header",photoList.get(0));
                        }
                        else {
                            map.put("header","");
                        }
                        WnkBusinessLevel wnkBusinessLevel = this.wnkBusinessLevelService.selectById(wnkStoreInformation.getBusiness_level_id());
                        if (wnkBusinessLevel == null){
                            map.put("business_level_name","");
                        }
                        else{
                            map.put("business_level_name",wnkBusinessLevel.getLevel_name());
                        }
                        WnkBusinessTag wnkBusinessTag = this.wnkBusinessTagService.selectByBusinessTypeId(wnkStoreInformation.getBusiness_type_id());
                        if (wnkBusinessTag == null){
                            map.put("one_tag_name","");
                            map.put("set_tag_state",-1);
                        }
                        else{
                            map.put("one_tag_name",wnkBusinessTag.getName());
                            map.put("set_tag_state",0);
                        }

                        map.put("recommend_qr_code",ImageToolsController.qrcodeShowURL+"?imageid="+wnkStoreInformation.getRecommend_qr_code());
                        map.put("pay_qr_code",ImageToolsController.qrcodeShowURL+"?imageid="+wnkStoreInformation.getPay_qr_code());
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
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

    // 修改店铺信息
    @RequestMapping(value = "/setStoreInformation", method = RequestMethod.POST,params = {"business_id","store_name","address","mobile","store_describe","photo_ids"})
    @ResponseBody
    public Result setStoreInformation(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Integer business_id,
                                      String store_name,
                                      String address,
                                      String mobile,
                                      String store_describe,
                                      String photo_ids,
                                      String business_hours,
                                      String position_name,
                                      Double lat,
                                      Double longt,
                                      String area,
                                      String cover_photo,
                                      Double positive_price
    ){
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
                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                    if (wnkStoreInformation == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("无店铺信息");
                    } else{
                        cover_photo = cover_photo.replace(ImageToolsController.imageShowURL+"?imageid=","");
                        wnkStoreInformation.setStore_name(store_name);
                        wnkStoreInformation.setAddress(address);
                        wnkStoreInformation.setContact_mobile(mobile);
                        wnkStoreInformation.setStore_describe(store_describe);
                        wnkStoreInformation.setBanners_photoid(photo_ids);
                        wnkStoreInformation.setBusiness_hours(business_hours);

                        wnkStoreInformation.setLat(lat);
                        wnkStoreInformation.setLongt(longt);
                        wnkStoreInformation.setPosition_name(position_name);

                        wnkStoreInformation.setArea(area);
                        wnkStoreInformation.setCover_photo(cover_photo);
                        wnkStoreInformation.setPositive_price(positive_price);

                        int state = this.wnkStoreInformationService.updateStoreInformation(wnkStoreInformation);
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
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 获取账户余额
    @RequestMapping(value = "/getBalance", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result getBalance(HttpServletRequest request, HttpServletResponse response, Integer business_id){
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

                    Map<Object,Object> map = new HashMap<Object,Object>();
                    map.put("balance",wnkBusinessAccount.getBalance());
                    map.put("consumption_integral",wnkBusinessAccount.getConsumption_integral());
                    map.put("level_integral",wnkBusinessAccount.getLevel_integral());
                    map.put("rose_number",wnkBusinessAccount.getRose_number());

                    List<Map<Object,Object>> levelIntegralIncomeList = this.wnkBusinessLevelIntegralDetailService.selectRecordByBusinessId(business_id,0);
                    List<Map<Object,Object>> levelIntegralSendList = this.wnkBusinessLevelIntegralDetailService.selectRecordByBusinessId(business_id,1);
                    List<Map<Object,Object>> levelIntegralXfzcList= this.wnkBusinessLevelIntegralDetailService.selectRecordByBusinessId(business_id,2);
                    Double levelIntegralIncomeInt = 0D;
                    Double levelIntegralSendInt = 0D;
                    Double levelIntegralXfzcInte = 0D;
                    for (Integer index = 0;index < levelIntegralIncomeList.size();index++){
                        Map<Object,Object> integralMap = levelIntegralIncomeList.get(index);
                        levelIntegralIncomeInt = levelIntegralIncomeInt + (Double) integralMap.get("integral_number");
                    }
                    for (Integer index = 0;index < levelIntegralSendList.size();index++){
                        Map<Object,Object> integralMap = levelIntegralSendList.get(index);
                        levelIntegralSendInt = levelIntegralSendInt + (Double) integralMap.get("integral_number");
                    }
                    for (Integer index = 0;index < levelIntegralXfzcList.size();index++){
                        Map<Object,Object> integralMap = levelIntegralXfzcList.get(index);
                        levelIntegralXfzcInte = levelIntegralXfzcInte + (Double) integralMap.get("integral_number");
                    }
                    map.put("level_integral_count_income",levelIntegralIncomeInt);
                    map.put("level_integral_count_send",levelIntegralSendInt);
                    map.put("level_integral_count_xfzc",levelIntegralXfzcInte);



                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);

                    WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                    map.put("type_name",wnkBusinessType.getName());

                    if (wnkStoreInformation == null){
                        map.put("level_name","");
                        map.put("level_term_date","");
                    }
                    else{
                        map.put("cover_photo",wnkStoreInformation.getCover_photo());
                        WnkBusinessLevel wnkBusinessLevel = this.wnkBusinessLevelService.selectById(wnkStoreInformation.getBusiness_level_id());
                        if (wnkBusinessLevel == null){
                            map.put("level_name","");
                        }
                        else{
                            map.put("store_name",wnkStoreInformation.getStore_name());
                            map.put("level_name",wnkBusinessLevel.getLevel_name());
                            map.put("img_path",ImageToolsController.imageShowURL+"?imageid=");
                        }

                        Date levelTermDate = wnkStoreInformation.getLevel_term_validity();
                        if (levelTermDate == null){
                            map.put("level_term_date","永久有效");
                        }
                        else{
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            String formatStr =formatter.format(levelTermDate);
                            map.put("level_term_date",formatStr);
                        }

                    }
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 获取商家推广的所有会员
    @RequestMapping(value = "/getBusinessExtensionMember", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result getBusinessExtensionMember(HttpServletRequest request, HttpServletResponse response, Integer business_id){
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
                    List<Map<Object,Object>> list = this.usersService.selectUserByBusinessId(business_id);
                    for (Integer index = 0;index < list.size();index++){
                        Map<Object,Object> objMap = list.get(index);
                        if ((Integer)objMap.get("member_card_level") == 0){
                            objMap.put("card_name","-银卡会员");
                        }
                        else if ((Integer)objMap.get("member_card_level") == 1){
                            objMap.put("card_name","-金卡会员");
                        }
                        else{
                            objMap.put("card_name","");
                        }
                    }
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        for (Integer index = 0;index < list.size();index++) {
                            Map<Object, Object> objectMap = list.get(index);
                            objectMap.put("header", ImageToolsController.imageShowURL + "?imageid=" + objectMap.get("header"));
                        }
                            result.setData(list);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("查询成功");
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


    // 获取商家推广的所有商家
    @RequestMapping(value = "/getBusinessExtensionBusiness", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result getBusinessExtensionBusiness(HttpServletRequest request, HttpServletResponse response, Integer business_id){
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
                    List<Map<Object,Object>> list = this.wnkStoreInformationService.selectBusinessByRecommendBusinessId(business_id);
                    for (Integer index = 0;index < list.size();index++){
                        Map<Object,Object> map = list.get(index);
                        String banners_photoid = (String)map.get("banners_photoid");
                        List<String> photoList = new ArrayList<String>();
                        if ( banners_photoid != null && !banners_photoid.equals("")){
                            String[] photoIds = banners_photoid.split(",");
                            if (photoIds.length >= 1){
                                map.put("header",ImageToolsController.imageShowURL+"?imageid="+photoIds[0]);
                            }
                            else {
                                map.put("header","");
                            }
                        }
                    }
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
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    // 获取商家特色/服务内容
    @RequestMapping(value = "/getBusinessLabel", method = RequestMethod.POST,params = {"business_id","type"})
    @ResponseBody
    public Result getBusinessLabel(HttpServletRequest request, HttpServletResponse response, Integer business_id,String type){
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
                    // 业务开始
                    List<Map<String,Object>> list = wnkBusinessLabelService.getBusinessLabel(type);
                    list.size();
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


    // 根据商家特色/服务内容id 获取名称
    @RequestMapping(value = "/selectLabel", method = RequestMethod.POST,params = {"business_id","type","id"})
    @ResponseBody
    public Result selectLabel(HttpServletRequest request, HttpServletResponse response, Integer business_id,String type,String id){
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
                    // 业务开始
                    List<Map<String,Object>> list = new ArrayList<>();
                    String[] idArr = id.split(",");
                    for (int i = 0;i<idArr.length;i++){
                        Map<String,Object> labelMap = wnkBusinessLabelService.selectLabel(idArr[i]);
                        if (labelMap != null){
                            list.add(labelMap);
                        }
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


    // 更新商家营业状态
    @RequestMapping(value = "/updateBusinessOperateStatus", method = RequestMethod.POST,params = {"business_id","type"})
    @ResponseBody
    public Result updateBusinessOperateStatus(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Integer business_id,
                                              Integer type){
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
                    // 业务开始
                    if (wnkStoreInformationService.updateBusinessOperateStatus(type,business_id) > 0){
                        if (type == 0){
                            result.setMsg("开始营业");
                        } else {
                            result.setMsg("暂停营业");
                        }
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("更新营业状态失败");
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


    // 更新商家营业状态
    @RequestMapping(value = "/selectBusinessOperateStatus", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result selectBusinessOperateStatus(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Integer business_id){
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
                    // 业务开始
                    Map<String,Object> map = wnkStoreInformationService.selectBusinessOperateStatus(business_id);
                    if (map == null){
                        this.wnkStoreInformationService.updateBusinessOperateStatus(0,business_id);
                        if (wnkStoreInformationService.updateBusinessOperateStatus(0,business_id) > 0 ){
                            map =  wnkStoreInformationService.selectBusinessOperateStatus(business_id);
                            result.setData(map);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("查询成功");
                        } else {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("查询失败");
                        }
                    } else {
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }



}
