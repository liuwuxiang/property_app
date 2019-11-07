package com.springmvc.controller.app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.Users;
import com.springmvc.entity.WnkBusinessType;
import com.springmvc.entity.WnkBusinessTypeOpenCard;
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
import java.util.*;

/**
 * @author: zhangfan
 * @Date: 2018/12/17 17:19
 * @Description:
 */
@Controller
@RequestMapping(value = "/app/v2.0.0")
public class SearchAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessSearchRecordService wnkBusinessSearchRecordService;
    @Resource
    private UserSearchHistoryService userSearchHistoryService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;
    @Resource
    private WnkBusinessTypeOpenCardService wnkBusinessTypeOpenCardService;
    @Resource
    private WnkBusinessLabelService wnkBusinessLabelService;
    @Resource
    private WnkCommoditySpecificationsService wnkCommoditySpecificationsService;
    @Resource
    private WnkCommoditiesService wnkCommoditiesService;

    // 获取搜索历史以及热门商家
    @RequestMapping(value = "/getSearchHistoryAndHoltBusiness", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getSearchHistoryAndHoltBusiness(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    List<Map<Object,Object>> holtBusinessList = this.wnkBusinessSearchRecordService.selectSearchMaxTenRecord();
                    List<Map<Object,Object>> userSearchHistoryList = this.userSearchHistoryService.selectUserAllSearchRecord(user_id);
                    Map<Object,Object> returnMap = new HashMap<Object,Object>();
                    returnMap.put("holt_business_list",holtBusinessList);
                    returnMap.put("search_history_list",userSearchHistoryList);

                    result.setData(returnMap);
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

    // 根据用户ID删除搜索记录
    @RequestMapping(value = "/deleteUserSearchRecordByUserId", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result deleteUserSearchRecordByUserId(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    this.userSearchHistoryService.deleteRecordByUserId(user_id);

                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("操作成功");
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
     * 功能描述: 获取万能卡商家分类下的商家带距离
     *
     * @param user_id   用户id
     * @param type_id   类别id
     * @param lat       纬度
     * @param longt     经度
     * @param user_juli 距离(1-1公里,3-3公里，5-5公里,10-10公里,-1-全城)
     * @param sort_type 排序类型(1-离我最近,2-销量最高,3-价格最低)
     * @param search_content 搜索内容
     * @return:
     * @author: zhangfan
     * @date: 2018/11/1 2:14 PM
     */
    @RequestMapping(value = "/fuzzyQueryBusiness", method = RequestMethod.POST, params = {"user_id", "type_id", "lat", "longt", "user_juli", "sort_type","search_content"})
    @ResponseBody
    public Result fuzzyQueryBusiness(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer type_id, Double lat, Double longt, Double user_juli, Integer sort_type,String search_content) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else {
                    List<Map<Object, Object>> fuzzySearchBusinesslist = this.wnkStoreInformationService.fuzzyQueryBusiness(lat, longt,search_content, sort_type);
                    List<Map<Object,Object>> fuzzyCommodityBusinessList = this.wnkCommoditiesService.selectBusinessByFuzzCommodityName(lat,longt,search_content);
                    for (Integer index = 0;index < fuzzyCommodityBusinessList.size();index++){
                        Map<Object,Object> fuzzyCommodityMap = fuzzyCommodityBusinessList.get(index);
                        Integer fuzzyCommodityBusinessId = (Integer) fuzzyCommodityMap.get("business_id");
                        Integer business_state = -1;
                        if(fuzzyCommodityMap.get("business_state") instanceof Long){
                            business_state = new Long((Long) fuzzyCommodityMap.get("business_state")).intValue();
                        }
                        else{
                            business_state = (Integer) fuzzyCommodityMap.get("business_state");
                        }
                        //是否重复(true-重复,false-不重复)
                        Boolean isCF = false;
                        if(business_state == 0){
                            for (Integer index2 = 0;index2 < fuzzySearchBusinesslist.size();index2++){
                                Map<Object,Object> fuzzyBusinessMap = fuzzyCommodityBusinessList.get(index);
                                Integer fuzzyBusinessId = (Integer) fuzzyBusinessMap.get("business_id");
                                if (fuzzyCommodityBusinessId == fuzzyBusinessId){
                                    isCF = true;
                                }
                            }
                        }
                        else{
                            isCF = true;
                        }
                        if (isCF == false){
                            fuzzySearchBusinesslist.add(fuzzyCommodityMap);
                        }
                    }
                    List<Map<Object, Object>> returnList = new ArrayList<Map<Object, Object>>();
                    if (fuzzySearchBusinesslist.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    } else {
                        WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(type_id);
                        //是否查询用户此分类开卡状态(false-不查询,true-查询)
                        Boolean isSearchUserOpenCardState = false;
                        if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 1 && wnkBusinessType.getMake_wnk_state() == 1) {
                            isSearchUserOpenCardState = true;
                        }
                        for (Integer index = 0; index < fuzzySearchBusinesslist.size(); index++) {
                            Map<Object, Object> map = fuzzySearchBusinesslist.get(index);
                            String tese_label = (String) map.get("tese_label");
                            String fuwu_label = (String) map.get("fuwu_label");
                            String fm_photo = (String) map.get("banners_photoid");
                            String cover_photo = (String) map.get("cover_photo");
                            Integer mapTypeId = -1;
                            if (map.get("business_type_id") instanceof Long){
                                mapTypeId = new Long((Long)map.get("business_type_id")).intValue();
                            }
                            else{
                                mapTypeId = (Integer)map.get("business_type_id");
                            }
                            if (cover_photo != null && !cover_photo.equals("")) {
                                fm_photo = cover_photo;
                            } else {
                                if (fm_photo == null || fm_photo.equals("")) {
                                    fm_photo = "logo.jpg";
                                } else {
                                    String[] sourceStrArray = fm_photo.split(",");
                                    if (sourceStrArray.length <= 0) {
                                        fm_photo = "logo.jpg";
                                    } else {
                                        fm_photo = sourceStrArray[0];
                                    }
                                }
                            }
                            map.put("fm_photo", ImageToolsController.imageShowURL + "?imageid=" + fm_photo);
                            Long sale = (Long) map.get("sale");
                            if (sale == null) {
                                map.put("sale", 0);
                            }
                            Double juli = (Double) map.get("juli");
                            if (juli == null) {
                                map.put("juli", "距离不详");
                            } else {
                                map.put("juli", juli + "km");
                            }
                            if (user_juli != -1) {
                                if (juli == null || juli <= user_juli) {
                                    if (type_id != -1){
                                        if(type_id == mapTypeId){
                                            returnList.add(map);
                                        }
                                    }
                                    else{
                                        returnList.add(map);
                                    }

                                }

                            } else {
                                if (type_id != -1){
                                    if(type_id == mapTypeId){
                                        returnList.add(map);
                                    }
                                }
                                else{
                                    returnList.add(map);
                                }
                            }
                            Integer mapBusinessId = -1;
                            if(map.get("business_id") instanceof Long){
                                mapBusinessId = new Long((Long) map.get("business_id")).intValue();
                            }
                            else{
                                mapBusinessId = (Integer) map.get("business_id");
                            }
                            if (isSearchUserOpenCardState == true) {
                                WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(user_id,mapBusinessId);
                                if (wnkBusinessTypeOpenCard != null) {
                                    map.put("business_card_state", 0);
                                } else {
                                    map.put("business_card_state", 1);
                                }
                            } else {
                                //用户在商家处开卡状态(0-已开卡,1-未开卡,2-无需开卡)
                                map.put("business_card_state", 2);
                            }

                            String tese_label_new = "";
                            String fuwu_label_new = "";
                            if (tese_label != null && !tese_label.equals("")) {
                                String[] tese_label_sz = tese_label.split(",");
                                if (tese_label_sz.length > 0) {
                                    for (Integer biaoqian_index = 0; biaoqian_index < tese_label_sz.length; biaoqian_index++) {
                                        String label_id = tese_label_sz[biaoqian_index];
                                        Map<String, Object> wnkBusinessLabelMap = this.wnkBusinessLabelService.selectLabel(label_id);
                                        if (wnkBusinessLabelMap != null) {
                                            tese_label_new = tese_label_new + wnkBusinessLabelMap.get("name") + " ";
                                        }
                                    }
                                }
                            }
                            if (fuwu_label != null && !fuwu_label.equals("")) {
                                String[] fuwu_label_sz = fuwu_label.split(",");
                                if (fuwu_label_sz.length > 0) {
                                    for (Integer biaoqian_index = 0; biaoqian_index < fuwu_label_sz.length; biaoqian_index++) {
                                        String label_id = fuwu_label_sz[biaoqian_index];
                                        Map<String, Object> wnkBusinessLabelMap = this.wnkBusinessLabelService.selectLabel(label_id);
                                        if (wnkBusinessLabelMap != null) {
                                            fuwu_label_new = fuwu_label_new + wnkBusinessLabelMap.get("name") + " ";
                                        }
                                    }
                                }
                            }

                            map.put("tese_label", tese_label_new);
                            map.put("fuwu_label", fuwu_label_new);
                            Double positive_price = (Double) map.get("positive_price");
                            if (positive_price == null) {
                                positive_price = 0.00;
                            }
                            if (wnkBusinessType == null){
                                Integer business_type_id = -1;
                                if(map.get("business_type_id") instanceof Long){
                                    business_type_id = new Long((Long) map.get("business_type_id")).intValue();
                                }
                                else{
                                    business_type_id = (Integer) map.get("business_type_id");
                                }
                                wnkBusinessType = this.wnkBusinessTypeService.selectById(business_type_id);
                            }
                            if (wnkBusinessType.getMake_wnk_state() == 1) {
                                Map<Object, Object> guigeMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeMinPriceByBusinessId(mapBusinessId);
                                if (guigeMap != null) {
                                    Double min_price = (Double) guigeMap.get("min_price");
                                    if (min_price == null || min_price.equals("")) {
                                        map.put("min_price", 0);
                                        min_price = 0.00;
                                    } else {
                                        map.put("min_price", min_price);
                                    }
                                    if (wnkBusinessType.getCommdity_charge_way() == 1) {
                                        map.put("member_price", "会员价:￥0起");
                                    } else {
                                        if (wnkBusinessType.getDiscount_type() == 0) {
                                            map.put("member_price", "会员价:￥" + wnkBusinessType.getCommodifty_price() + "起");
                                        } else {
                                            Double bili = wnkBusinessType.getCommodifty_price() / 100;
                                            Double price = min_price * bili;
                                            price = Double.valueOf(String.format("%.2f", price));
                                            map.put("member_price", "会员价:￥" + price + "起");
                                        }
                                    }
                                } else {
                                    map.put("min_price", positive_price);
                                    Map<Object, Object> guigeStartMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeStartByBusinessId(mapBusinessId);
                                    if (guigeStartMap != null) {
                                        Double min_price = (Double) guigeStartMap.get("min_price");
                                        if (min_price == null || min_price.equals("")) {
                                            map.put("member_price", "会员价:￥0起");
                                        } else {
                                            map.put("member_price", "会员价:￥" + min_price + "起");
                                        }
                                    } else {
                                        map.put("member_price", "会员价:￥0起");
                                    }
                                }
                            } else {
                                map.put("min_price", positive_price);
                                Map<Object, Object> guigeStartMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeStartByBusinessId(mapBusinessId);
                                if (guigeStartMap != null) {
                                    Double min_price = (Double) guigeStartMap.get("min_price");
                                    if (min_price == null || min_price.equals("")) {
                                        map.put("member_price", "会员价:￥0起");
                                    } else {
                                        map.put("member_price", "会员价:￥" + min_price + "起");
                                    }
                                } else {
                                    map.put("member_price", "会员价:￥0起");
                                }
                            }


                            Integer operate_status = -1;
                            if(map.get("operate_status") instanceof Long){
                                operate_status = new Long((Long) map.get("operate_status")).intValue();
                            }
                            else{
                                operate_status = (Integer) map.get("operate_status");
                            }
                            if (operate_status == 1) {
                                map.put("business_hours", "休息中");
                            }

                            List<Map<Object, Object>> guigeList = this.wnkCommoditySpecificationsService.selectWnkGuiGeByBusinessId(mapBusinessId);
                            if (guigeList.size() > 0 && wnkBusinessType.getMake_wnk_state() == 1) {
                                //是否显示vip图标(0-不显示,1-显示)
                                map.put("vip_icon_state", 1);
                            } else {
                                //是否显示vip图标(0-不显示,1-显示)
                                map.put("vip_icon_state", 0);
                            }


                        }

                        Collections.sort(returnList, new Comparator<Map<Object, Object>>() {
                            @Override
                            public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {
                                String map1value = (String) o1.get("juli");
                                String map2value = (String) o2.get("juli");
                                return String.valueOf(map1value).compareTo(String.valueOf(map2value));
                            }
                        });
                        if (returnList.size() <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("暂无数据");
                        }
                        else{
                            result.setData(returnList);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("获取成功");
                            this.wnkBusinessSearchRecordService.insertRecord(search_content);
                            this.userSearchHistoryService.insertSearchRecord(user_id,search_content);
                        }
                    }

                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }
}
