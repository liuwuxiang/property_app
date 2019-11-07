package com.springmvc.controller.app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.Users;
import com.springmvc.entity.WnkBusinessTag;
import com.springmvc.entity.WnkBusinessType;
import com.springmvc.entity.WnkBusinessTypeOpenCard;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.util.*;

/**
 * @author: zhangfan
 * @Date: 2018/10/20 16:26
 * @Description:万能卡商户标签App获取Controoler
 */
@Controller
@RequestMapping(value = "/app/v1.0.0")
public class WnkBusinessTagAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessTagService wnkBusinessTagService;
    @Resource
    private WnkBusinessTagChooseRecordService wnkBusinessTagChooseRecordService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;
    @Resource
    private WnkBusinessTypeOpenCardService wnkBusinessTypeOpenCardService;
    @Resource
    private WnkBusinessLabelService wnkBusinessLabelService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkCommoditySpecificationsService wnkCommoditySpecificationsService;

    @Resource
    private WnkCommoditiesService wnkCommoditiesService;

    // 获取万能卡商户一级标签
    @RequestMapping(value = "/getWnkBusinessOneTag", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getWnkBusinessOneTag(HttpServletRequest request, HttpServletResponse response, Integer user_id){
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
                    List<Map<Object,Object>> list = this.wnkBusinessTagService.selectAllOneTag();
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无商品");
                    }
                    else{
                        for (Integer index = 0;index < list.size();index++){
                            Map<Object,Object> map = list.get(index);
                            map.put("photo_url",ImageToolsController.imageShowURL+"?imageid="+map.get("photo_id"));

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
            result.setMsg("获取失败");
        }
        return result;
    }


    // 获取万能卡商户一级标签(用户端附近页面使用)
    @RequestMapping(value = "/getWnkBusinessOneTagNearby", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getWnkBusinessOneTagNearby(HttpServletRequest request, HttpServletResponse response, Integer user_id){
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
                    List<Map<Object,Object>> typeList = this.wnkBusinessTypeService.selectAllRecord();
                    List<Map<Object,Object>> returnList = new ArrayList<Map<Object,Object>>();
                    List<Map<Object,Object>> list = this.wnkBusinessTagService.selectAllOneTag();
                    for (Integer index = 0;index < typeList.size();index++){
                        Map<Object,Object> typeMap = typeList.get(index);
                        Integer typeId = (Integer) typeMap.get("id");
                        Boolean adddState = true;
                        for (Integer index2 = 0;index2 < list.size();index2++){
                            Map<Object,Object> tagMap = list.get(index2);
                            Integer relation_business_type_id = (Integer) tagMap.get("relation_business_type_id");
                            if (typeId == relation_business_type_id){
                                adddState = false;
                            }
                        }
                        if (adddState == true){
                            typeMap.put("type",0);
                            returnList.add(typeMap);
                        }
                    }

                    for (Integer index = 0;index < list.size();index++){
                        Map<Object,Object> tagMap = list.get(index);
                        tagMap.put("type",1);
                        returnList.add(tagMap);
                    }

                    if (returnList.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无商品");
                    }
                    else{
                        result.setData(returnList);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
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

    // 获取某个一级标签下的所有二级标签
    @RequestMapping(value = "/getWnkBusinessTwoTag", method = RequestMethod.POST,params = {"user_id","one_tag_id"})
    @ResponseBody
    public Result getWnkBusinessTwoTag(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer one_tag_id){
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
                    List<Map<Object,Object>> list = this.wnkBusinessTagService.selectTwoTagByOneTagId(one_tag_id);
                    for (Integer index = 0;index < list.size();index++){
                        Map<Object,Object> map = list.get(index);
                        map.put("photo_id",ImageToolsController.imageShowURL+"?imageid="+map.get("photo_id"));
                    }
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 获取某个标签下的所有商家
     *
     * @param user_id 用户id
     * @param tag_id 标签id
     * @param lat 纬度
     * @param longt 经度
     * @param user_juli 距离(1-1公里,3-3公里，5-5公里,10-10公里,-1-全城)
     * @param sort_type 排序类型(1-离我最近,2-销量最高,3-价格最低)
     * @return:
     * @author: zhangfan
     * @date: 2018/11/1 2:14 PM
     */
    @RequestMapping(value = "/getWnkBusinessByTagId", method = RequestMethod.POST,params = {"user_id","tag_id","lat","longt","user_juli","sort_type"})
    @ResponseBody
    public Result getWnkBusinessByTagId(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer tag_id,Double lat,Double longt,Double user_juli,Integer sort_type){
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
                    List<Map<Object,Object>> list = this.wnkBusinessTagChooseRecordService.selectByTagId(tag_id,lat,longt,sort_type);
                    List<Map<Object,Object>> returnList = new ArrayList<Map<Object,Object>>();
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        for (Integer index = 0;index < list.size();index++){
                            Map<Object,Object> map = list.get(index);
                            String tese_label = (String)map.get("tese_label");
                            String fuwu_label = (String)map.get("fuwu_label");
                            String fm_photo = (String)map.get("banners_photoid");
                            String cover_photo = (String)map.get("cover_photo");
                            if (cover_photo != null && !cover_photo.equals("")){
                                fm_photo = cover_photo;
                            }
                            else{
                                if (fm_photo == null || fm_photo.equals("")){
                                    fm_photo = "logo.jpg";
                                }
                                else{
                                    String[] sourceStrArray = fm_photo.split(",");
                                    if (sourceStrArray.length <= 0){
                                        fm_photo = "logo.jpg";
                                    }
                                    else{
                                        fm_photo = sourceStrArray[0];
                                    }
                                }
                            }
                            Long sale = (Long) map.get("sale");
                            if (sale == null){
                                map.put("sale",0);
                            }

                            map.put("fm_photo",ImageToolsController.imageShowURL+"?imageid="+fm_photo);
                            Double juli = (Double)map.get("juli");
                            if (juli == null){
                                map.put("juli","距离不详");
                            }
                            else{
                                map.put("juli",juli+"km");
                            }
                            if (user_juli != -1){
                                if (juli == null || juli <= user_juli){
                                    returnList.add(map);
                                }

                            }
                            else{
                                returnList.add(map);
                            }
                            Long business_type_id = (Long) map.get("business_type_id");
                            WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(business_type_id.intValue());
                            if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 1 && wnkBusinessType.getMake_wnk_state() == 1){
                                WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(user_id,(Integer) map.get("business_id"));
                                if (wnkBusinessTypeOpenCard != null){
                                    map.put("business_card_state",0);
                                }
                                else{
                                    map.put("business_card_state",1);
                                }
                            }
                            else{
                                //用户在商家处开卡状态(0-已开卡,1-未开卡,2-无需开卡)
                                map.put("business_card_state",2);
                            }

                            String tese_label_new = "";
                            String fuwu_label_new = "";
                            if (tese_label != null && !tese_label.equals("")){
                                String[] tese_label_sz =tese_label.split(",");
                                if (tese_label_sz.length > 0){
                                    for (Integer biaoqian_index = 0;biaoqian_index < tese_label_sz.length;biaoqian_index++){
                                        String label_id = tese_label_sz[biaoqian_index];
                                        Map<String, Object> wnkBusinessLabelMap = this.wnkBusinessLabelService.selectLabel(label_id);
                                        if (wnkBusinessLabelMap != null){
                                            tese_label_new = tese_label_new + wnkBusinessLabelMap.get("name") + " ";
                                        }
                                    }
                                }
                            }
                            if (fuwu_label != null && !fuwu_label.equals("")){
                                String[] fuwu_label_sz =fuwu_label.split(",");
                                if (fuwu_label_sz.length > 0){
                                    for (Integer biaoqian_index = 0;biaoqian_index < fuwu_label_sz.length;biaoqian_index++){
                                        String label_id = fuwu_label_sz[biaoqian_index];
                                        Map<String, Object> wnkBusinessLabelMap = this.wnkBusinessLabelService.selectLabel(label_id);
                                        if (wnkBusinessLabelMap != null){
                                            fuwu_label_new = fuwu_label_new + wnkBusinessLabelMap.get("name") + " ";
                                        }
                                    }
                                }
                            }

                            map.put("tese_label",tese_label_new);
                            map.put("fuwu_label",fuwu_label_new);

                            Double positive_price = (Double)map.get("positive_price");
                            if (positive_price == null){
                                positive_price = 0.00;
                            }
                            if (wnkBusinessType.getMake_wnk_state() == 1){
                                Map<Object, Object> guigeMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeMinPriceByBusinessId((Integer) map.get("business_id"));
                                if (guigeMap != null){
                                    Double min_price = (Double)guigeMap.get("min_price");
                                    if (min_price == null || min_price.equals("")){
                                        map.put("min_price",0);
                                        min_price = 0.00;
                                    }
                                    else{
                                        map.put("min_price",min_price);
                                    }
                                    if (wnkBusinessType.getCommdity_charge_way() == 1){
                                        map.put("member_price","会员价:￥0起");
                                    }
                                    else{
                                        if (wnkBusinessType.getDiscount_type() == 0){
                                            map.put("member_price","会员价:￥"+wnkBusinessType.getCommodifty_price()+"起");
                                        }
                                        else{
                                            Double bili = wnkBusinessType.getCommodifty_price() / 100;
                                            Double price = min_price * bili;
                                            price = Double.valueOf(String.format("%.2f", price));
                                            map.put("member_price","会员价:￥"+price+"起");
                                        }
                                    }
                                }
                                else{
                                    map.put("min_price",positive_price);
                                    Map<Object,Object> guigeStartMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeStartByBusinessId((Integer) map.get("business_id"));
                                    if (guigeStartMap != null){
                                        Double min_price = (Double)guigeStartMap.get("min_price");
                                        if (min_price == null || min_price.equals("")){
                                            map.put("member_price","会员价:￥0起");
                                        }
                                        else{
                                            map.put("member_price","会员价:￥"+min_price+"起");
                                        }
                                    }
                                    else{
                                        map.put("member_price","会员价:￥0起");
                                    }
                                }
                            }
                            else{
                                map.put("min_price",positive_price);
                                Map<Object,Object> guigeStartMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeStartByBusinessId((Integer) map.get("business_id"));
                                if (guigeStartMap != null){
                                    Double min_price = (Double)guigeStartMap.get("min_price");
                                    if (min_price == null || min_price.equals("")){
                                        map.put("member_price","会员价:￥0起");
                                    }
                                    else{
                                        map.put("member_price","会员价:￥"+min_price+"起");
                                    }
                                }
                                else{
                                    map.put("member_price","会员价:￥0起");
                                }
                            }

                            String business_hours = (String)map.get("business_hours");
                            if (business_hours != null && business_hours.equals("00时 - 00时")){
                                map.put("business_hours", "24小时营业");
                            }
                            Long operate_status = (Long) map.get("operate_status");
                            if (operate_status == 1){
                                map.put("business_hours","休息中");
                            }

                            List<Map<Object,Object>> guigeList = this.wnkCommoditySpecificationsService.selectWnkGuiGeByBusinessId((Integer) map.get("business_id"));
                            if (guigeList.size() > 0 && wnkBusinessType.getMake_wnk_state() == 1){
                                //是否显示vip图标(0-不显示,1-显示)
                                map.put("vip_icon_state",1);
                            }
                            else{
                                //是否显示vip图标(0-不显示,1-显示)
                                map.put("vip_icon_state",0);
                            }
                            // 查询消费赠送比例
                            // 获取商家商品中赠送消费额度最高的消费赠送
                            Integer businessId = (Integer) map.get("business_id");
                            Map<String, Object> giftNoun = this.wnkCommoditiesService.selectCommoditiesSpecificationsServiceMaxGiftNounByBusinessId(businessId);
                            if(giftNoun == null){
                                map.put("gift_noun",0);
                            } else {
                                map.put("gift_noun",giftNoun.get("max_gift_noun"));
                            }
                        }
                        result.setData(returnList);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }



    /**
     *
     * 功能描述: 获取某个标签下的推荐商家
     *
     * @param user_id 用户id
     * @param tag_id 标签id
     * @param lat 纬度
     * @param longt 经度
     * @return:
     * @author: zhangfan
     * @date: 2018/11/1 2:14 PM
     */
    @RequestMapping(value = "/getWnkRecommendBusinessByTagId", method = RequestMethod.POST,params = {"user_id","tag_id","lat","longt"})
    @ResponseBody
    public Result getWnkRecommendBusinessByTagId(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer tag_id,Double lat,Double longt){
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
                    WnkBusinessTag wnkBusinessTag = this.wnkBusinessTagService.selectById(tag_id);
                    if (wnkBusinessTag == null || wnkBusinessTag.getRelation_business_type_id() == -1){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        List<Map<Object,Object>> list = this.wnkStoreInformationService.selectByTypeId(wnkBusinessTag.getRelation_business_type_id(),lat,longt,1);
                        if (list.size() <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("暂无数据");
                        }
                        else{
                            WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkBusinessTag.getRelation_business_type_id());
                            //是否查询用户此分类开卡状态(false-不查询,true-查询)
                            Boolean isSearchUserOpenCardState = false;
                            if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 1 && wnkBusinessType.getMake_wnk_state() == 1){
                                isSearchUserOpenCardState = true;
                            }
                            for (Integer index = 0;index < list.size();index++){
                                Map<Object,Object> map = list.get(index);
                                String tese_label = (String)map.get("tese_label");
                                String fuwu_label = (String)map.get("fuwu_label");
                                String fm_photo = (String)map.get("banners_photoid");
                                String cover_photo = (String)map.get("cover_photo");
                                if (cover_photo != null && !cover_photo.equals("")){
                                    fm_photo = cover_photo;
                                }
                                else{
                                    if (fm_photo == null || fm_photo.equals("")){
                                        fm_photo = "logo.jpg";
                                    }
                                    else{
                                        String[] sourceStrArray = fm_photo.split(",");
                                        if (sourceStrArray.length <= 0){
                                            fm_photo = "logo.jpg";
                                        }
                                        else{
                                            fm_photo = sourceStrArray[0];
                                        }
                                    }
                                }
                                map.put("fm_photo",ImageToolsController.imageShowURL+"?imageid="+fm_photo);
                                Long sale = (Long) map.get("sale");
                                if (sale == null){
                                    map.put("sale",0);
                                }
                                Double juli = (Double)map.get("juli");
                                if (juli == null){
                                    map.put("juli","距离不详");
                                }
                                else{
                                    map.put("juli",juli+"km");
                                }
//                                returnList.add(map);
                                if (isSearchUserOpenCardState == true){
                                    WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(user_id,(Integer) map.get("business_id"));
                                    if (wnkBusinessTypeOpenCard != null){
                                        map.put("business_card_state",0);
                                    }
                                    else{
                                        map.put("business_card_state",1);
                                    }
                                }
                                else{
                                    //用户在商家处开卡状态(0-已开卡,1-未开卡,2-无需开卡)
                                    map.put("business_card_state",2);
                                }

                                String tese_label_new = "";
                                String fuwu_label_new = "";
                                if (tese_label != null && !tese_label.equals("")){
                                    String[] tese_label_sz =tese_label.split(",");
                                    if (tese_label_sz.length > 0){
                                        for (Integer biaoqian_index = 0;biaoqian_index < tese_label_sz.length;biaoqian_index++){
                                            String label_id = tese_label_sz[biaoqian_index];
                                            Map<String, Object> wnkBusinessLabelMap = this.wnkBusinessLabelService.selectLabel(label_id);
                                            if (wnkBusinessLabelMap != null){
                                                tese_label_new = tese_label_new + wnkBusinessLabelMap.get("name") + " ";
                                            }
                                        }
                                    }
                                }
                                if (fuwu_label != null && !fuwu_label.equals("")){
                                    String[] fuwu_label_sz =fuwu_label.split(",");
                                    if (fuwu_label_sz.length > 0){
                                        for (Integer biaoqian_index = 0;biaoqian_index < fuwu_label_sz.length;biaoqian_index++){
                                            String label_id = fuwu_label_sz[biaoqian_index];
                                            Map<String, Object> wnkBusinessLabelMap = this.wnkBusinessLabelService.selectLabel(label_id);
                                            if (wnkBusinessLabelMap != null){
                                                fuwu_label_new = fuwu_label_new + wnkBusinessLabelMap.get("name") + " ";
                                            }
                                        }
                                    }
                                }

                                map.put("tese_label",tese_label_new);
                                map.put("fuwu_label",fuwu_label_new);
                                Double positive_price = (Double)map.get("positive_price");
                                if (positive_price == null){
                                    positive_price = 0.00;
                                }
                                if (wnkBusinessType.getMake_wnk_state() == 1){
                                    Map<Object, Object> guigeMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeMinPriceByBusinessId((Integer) map.get("business_id"));
                                    if (guigeMap != null){
                                        Double min_price = (Double)guigeMap.get("min_price");
                                        if (min_price == null || min_price.equals("")){
                                            map.put("min_price",0);
                                            min_price = 0.00;
                                        }
                                        else{
                                            map.put("min_price",min_price);
                                        }
                                        if (wnkBusinessType.getCommdity_charge_way() == 1){
                                            map.put("member_price","会员价:￥0起");
                                        }
                                        else{
                                            if (wnkBusinessType.getDiscount_type() == 0){
                                                map.put("member_price","会员价:￥"+wnkBusinessType.getCommodifty_price()+"起");
                                            }
                                            else{
                                                Double bili = wnkBusinessType.getCommodifty_price() / 100;
                                                Double price = min_price * bili;
                                                price = Double.valueOf(String.format("%.2f", price));
                                                map.put("member_price","会员价:￥"+price+"起");
                                            }
                                        }
                                    }
                                    else{
                                        map.put("min_price",positive_price);
                                        Map<Object,Object> guigeStartMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeStartByBusinessId((Integer) map.get("business_id"));
                                        if (guigeStartMap != null){
                                            Double min_price = (Double)guigeStartMap.get("min_price");
                                            if (min_price == null || min_price.equals("")){
                                                map.put("member_price","会员价:￥0起");
                                            }
                                            else{
                                                map.put("member_price","会员价:￥"+min_price+"起");
                                            }
                                        }
                                        else{
                                            map.put("member_price","会员价:￥0起");
                                        }
                                    }
                                }
                                else{
                                    map.put("min_price",positive_price);
                                    Map<Object,Object> guigeStartMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeStartByBusinessId((Integer) map.get("business_id"));
                                    if (guigeStartMap != null){
                                        Double min_price = (Double)guigeStartMap.get("min_price");
                                        if (min_price == null || min_price.equals("")){
                                            map.put("member_price","会员价:￥0起");
                                        }
                                        else{
                                            map.put("member_price","会员价:￥"+min_price+"起");
                                        }
                                    }
                                    else{
                                        map.put("member_price","会员价:￥0起");
                                    }
                                }


                                String business_hours = (String)map.get("business_hours");
                                if (business_hours != null && business_hours.equals("00时 - 00时")){
                                    map.put("business_hours", "24小时营业");
                                }
                                Integer operate_status = (Integer) map.get("operate_status");
                                if (operate_status == 1){
                                    map.put("business_hours","休息中");
                                }

                                List<Map<Object,Object>> guigeList = this.wnkCommoditySpecificationsService.selectWnkGuiGeByBusinessId((Integer) map.get("business_id"));
                                if (guigeList.size() > 0 && wnkBusinessType.getMake_wnk_state() == 1){
                                    //是否显示vip图标(0-不显示,1-显示)
                                    map.put("vip_icon_state",1);
                                }
                                else{
                                    //是否显示vip图标(0-不显示,1-显示)
                                    map.put("vip_icon_state",0);
                                }


                            }
                            result.setData(list);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("获取成功");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }
}
