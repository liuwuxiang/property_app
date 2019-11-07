package com.springmvc.controller.app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
import com.springmvc.service.WnkCommoditiesExpandHotelService;
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

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class WnkCommodityAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkCommodityTypesService wnkCommodityTypesService;
    @Resource
    private WnkCommoditiesService wnkCommoditiesService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkCommoditySpecificationsService wnkCommoditySpecificationsService;
    @Resource
    private UserCollectionService userCollectionService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;
    @Resource
    private WnkBusinessTypeOpenCardService wnkBusinessTypeOpenCardService;
    @Resource
    private WnkBusinessLabelService wnkBusinessLabelService;
    @Resource
    private WnkBusinessLegalizeService wnkBusinessLegalizeService;
    @Resource
    private WnkBusinessGoodsTagService wnkBusinessGoodsTagService;
    @Resource
    private WnkCommoditiesExpandHotelService wnkCommoditiesExpandHotelService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;

    // 获取万能卡商品
    @RequestMapping(value = "/getWnkCommodity", method = RequestMethod.POST, params = {"user_id", "business_id"})
    @ResponseBody
    public Result getWnkCommodity(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer business_id) {
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
                    List<Map<Object, Object>> list = this.wnkCommodityTypesService.selectAllTypeByBusinessId(business_id);
                    if (list.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无商品");
                    } else {
                        for (Integer index = 0; index < list.size(); index++) {
                            Map<Object, Object> map = list.get(index);
                            List<Map<Object, Object>> commoditieList = this.wnkCommoditiesService.selectAllCommodityByTypeId((Integer) map.get("id"));
                            for (Integer index2 = 0; index2 < commoditieList.size(); index2++) {
                                Map<Object, Object> commodityMap = commoditieList.get(index2);
                                commodityMap.put("photo", ImageToolsController.imageShowURL + "?imageid=" + commodityMap.get("photo"));
                            }
                            map.put("commodities", commoditieList);
                        }
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
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

    // 获取万能卡商品分类
    @RequestMapping(value = "/getWnkCommodityAllType", method = RequestMethod.POST, params = {"user_id", "business_id"})
    @ResponseBody
    public Result getWnkCommodityAllType(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer business_id) {
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
                    List<Map<Object, Object>> list = this.wnkCommodityTypesService.selectAllTypeByBusinessId(business_id);
                    if (list.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无商品");
                    } else {
                        List<Map<Object, Object>> retList = new LinkedList<>();
                        for (Map<Object, Object> map : list) {
                            List<Map<Object, Object>> goodsList = this.wnkCommoditiesService.userSelectAllCommodityByTypeId((Integer) map.get("id"));
                            if (goodsList.size() > 0) {
                                retList.add(map);
                            }
                        }
                        result.setData(retList);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
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

    // 获取万能卡商品分类下的商品
    @RequestMapping(value = "/getWnkCommodityTypeGoods", method = RequestMethod.POST, params = {"user_id", "business_id", "goods_type_id"})
    @ResponseBody
    public Result getWnkCommodityTypeGoods(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer business_id, Integer goods_type_id) {
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
                    List<Map<Object, Object>> commoditieList = this.wnkCommoditiesService.userSelectAllCommodityByTypeId(goods_type_id);
                    String wnk_price = "";
                    if (commoditieList.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无商品");
                    } else {
                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                        WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                        if (wnkStoreInformation != null) {
                            if (wnkBusinessType != null && wnkBusinessType.getMake_wnk_state() == 1 && wnkBusinessType.getCommdity_charge_way() == 0) {
                                wnk_price = "会员价:￥" + wnkBusinessType.getCommodifty_price();
                            } else if (wnkBusinessType != null && wnkBusinessType.getMake_wnk_state() == 1 && wnkBusinessType.getCommdity_charge_way() == 1) {
                                wnk_price = "会员价:￥0";
                            }
                        }
                        for (Integer index2 = 0; index2 < commoditieList.size(); index2++) {
                            Map<Object, Object> commodityMap = commoditieList.get(index2);
                            Double price = (Double) commodityMap.get("price");
                            Integer commodity_id = (Integer) commodityMap.get("id");
                            if (price == null) {
                                price = 0.00;
                            }
                            commodityMap.put("price", price);
                            commodityMap.put("photo", ImageToolsController.imageShowURL + "?imageid=" + commodityMap.get("photo"));

                            List<Map<Object, Object>> guigeList = this.wnkCommoditySpecificationsService.selectWnkGuiGeByCommodity(commodity_id);
                            if (guigeList.size() > 0) {
                                if (wnkBusinessType.getDiscount_type() == 0) {
                                    commodityMap.put("wnk_price", wnk_price);
                                } else {
                                    Double bili = wnkBusinessType.getCommodifty_price() / 100;
                                    price = price * bili;
                                    commodityMap.put("wnk_price", "会员价:￥" + String.format("%.2f", price));
                                }

                            } else {
                                commodityMap.put("wnk_price", "");
                            }
                            // 获取商品标签
                            String goodsTagStr = (String) commodityMap.get("goods_tag");
                            commodityMap.put("goods_tag_str", goodsTagStr);

                            // 获取商品消费比例
                            List<Map<Object, Object>> specifications = this.wnkCommoditySpecificationsService.selectByCommodityId((Integer) commodityMap.get("id"));
                            if(specifications.size() <= 0){
                                commodityMap.put("gift_noun", 0);
                            } else {
                                commodityMap.put("gift_noun", specifications.get(0).get("gift_noun"));
                            }

                        }
                        result.setData(commoditieList);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    /**
     * 方法说明: 酒店类商家根据商品分类获取商品信息
     *
     * @param user_id       用户ID
     * @param business_id   商家ID
     * @param goods_type_id 商品分类ID
     * @param joinTime      入住时间
     * @param outTime       离店时间
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @Date 2018
     **/
    @RequestMapping(value = "/getWnkCommodityTypeGoodsByHotel", method = RequestMethod.POST)
    @ResponseBody
    public Result getWnkCommodityTypeGoodsByHotel(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer business_id, Integer goods_type_id, Long joinTime, Long outTime) {
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, sessionIdsService) == null) {
                return new Result(Result.NOLOGIN, "此账户暂未登录", null);
            }
            Users users = this.usersService.selectById(user_id);
            if (users == null) {
                return new Result(Result.FAIL, "此用户不存在", null);
            }

            List<Map<String, Object>> returnList = new LinkedList<>();

            // 先获取所有商品信息
            List<Map<Object, Object>> commoditieList = this.wnkCommoditiesService.userSelectAllCommodityByTypeId(goods_type_id);
            // 获取商家信息
            WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
            WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());


            // 循环遍历每个商品 并获取所需要的商品信息
            for (Map<Object, Object> map : commoditieList) {
                // 获取规格信息 // 酒店类商家商品只能有一个规格

                List<Map<Object, Object>> commoditySpecificationsList = this.wnkCommoditySpecificationsService.selectByCommodityId((Integer) map.get("id"));
                if (commoditySpecificationsList.size() > 0) {
                    Map<Object, Object> commoditySpecifications = commoditySpecificationsList.get(0);
                    Integer inventory = (Integer) commoditySpecifications.get("inventory");
                    // 查询此商品对应用户选取的时间内是否都是有库存的
                    if(!"".equals(joinTime) && joinTime != null  &&  !"".equals(outTime) && outTime != null ){
                        Map<String,Object> objectMap = new HashMap<>(3);
                        objectMap.put("commoditiesId",map.get("id"));
                        objectMap.put("joinTime",joinTime);
                        objectMap.put("outTime" , outTime);
                        objectMap.put("businessId",business_id);
                        inventory = this.wnkCommoditySpecificationsService.selectDayInventoryNumberByCommoditiesId(objectMap);
                    }
                    // 只显示有库存的
                    if (inventory == -1 || inventory > 0) {
                        // 用作存储商品信息
                        Map<String, Object> addMap = new HashMap<>(16);
                        addMap.put("id", map.get("id"));
                        addMap.put("name", map.get("name"));
                        addMap.put("commodity_describe", map.get("commodity_describe"));
                        addMap.put("goods_tag_str", map.get("goods_tag_str"));
                        addMap.put("price", commoditySpecifications.get("price"));
                        addMap.put("inventory",inventory);

                        // 查询会员价
                        if (wnkBusinessType.getDiscount_type() == 0) {
                            addMap.put("wnk_price", commoditySpecifications.get("price"));
                        } else {
                            Double bili = wnkBusinessType.getCommodifty_price() / 100;
                            addMap.put("wnk_price", "会员价:￥" + String.format("%.2f", ((Double) commoditySpecifications.get("price") * bili)));
                        }
                        // 查询消费赠送比例
                        addMap.put("gift_noun", commoditySpecifications.get("gift_noun"));


                        // 获取酒店类扩展信息
                        WnkCommoditiesExpandHotel expand = wnkCommoditiesExpandHotelService.selectCommoditiesExpandHotelByCommoditiesId((Integer) map.get("id"));

                        if (expand != null){
                            // 早餐
                            if(expand.getBreakfast() != null  && !"-".equals(expand.getBreakfast())){
                                Integer breakfast = Integer.valueOf(expand.getBreakfast());
                                if (breakfast == 0) {
                                    addMap.put("breakfast", "不含早");
                                }
                                if (breakfast == 2) {
                                    addMap.put("breakfast", "含双早");
                                }
                                if (breakfast != 0 && breakfast != 2) {
                                    addMap.put("breakfast", "含" + breakfast + "份早餐");
                                }
                            } else {
                                addMap.put("breakfast", " ");
                            }

                            // 床型
                            if (expand.getNum_bed() != null  && !"-".equals(expand.getNum_bed())){
                                Integer numBed = Integer.valueOf(expand.getNum_bed());
                                if (numBed == 1) {
                                    if (!"-".equals(expand.getType_bed())) {
                                        addMap.put("type_bed", expand.getType_bed());
                                    } else {
                                        addMap.put("type_bed", "单床");
                                    }
                                }
                                if (numBed == 2) {
                                    addMap.put("type_bed", "双床");
                                }
                                if (numBed > 2) {
                                    if (!"-".equals(expand.getType_bed())) {
                                        addMap.put("type_bed", numBed + "张" + expand.getType_bed());
                                    } else {
                                        addMap.put("type_bed", numBed + "张床");
                                    }
                                }
                            } else {
                                addMap.put("type_bed", " ");
                            }

                            // 窗户
                            if (expand.getWindows() != null  && !"-".equals(expand.getWindows())){
                                if (!"-".equals(expand.getWindows())) {
                                    if ("有".equals(expand.getWindows())) {
                                        addMap.put("windows", "有窗");
                                    } else {
                                        addMap.put("windows", "无窗");
                                    }
                                } else {
                                    addMap.put("windows", "无窗");
                                }
                            } else {
                                addMap.put("windows", " ");
                            }

                            // 是否可以取消
                            if (expand.getCancel_reserve() != null  && Integer.valueOf(expand.getCancel_reserve()) == 0) {
                                addMap.put("cancel_reserve", "入住当天" + expand.getTime_cancel_reserve() + "点之前免费取消");
                            } else {
                                addMap.put("cancel_reserve", "不可取消");
                            }
                        } else {
                            addMap.put("windows", " ");
                            addMap.put("type_bed", " ");
                            addMap.put("breakfast", " ");
                            addMap.put("cancel_reserve", " ");
                        }
                        returnList.add(addMap);
                    }
                }

            }
            return new Result(Result.SUCCESS, "获取成功", returnList);
        } catch (Exception e) {
            return new Result(Result.FAIL, "获取失败", e.getMessage());
        }
    }


    // 通过id查询万能卡商家信息
    @RequestMapping(value = "/selectBusinesssDetail", method = RequestMethod.POST, params = {"user_id", "business_id"})
    @ResponseBody
    public Result selectBusinesssDetail(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer business_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
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
                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                    Map<Object, Object> map = new HashMap<Object, Object>();
                    if (wnkStoreInformation == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此商家不存在");
                    } else {
                        List<String> photoList = new ArrayList<String>();
                        if (wnkStoreInformation.getBanners_photoid() != null && !wnkStoreInformation.getBanners_photoid().equals("")) {
                            String[] photoIds = wnkStoreInformation.getBanners_photoid().split(",");
                            for (Integer index = 0; index < photoIds.length; index++) {
                                photoList.add(ImageToolsController.imageShowURL + "?imageid=" + photoIds[index]);
                            }
                        }
                        map.put("banners", photoList);
                        map.put("brief", wnkStoreInformation.getStore_describe());
                        map.put("address", wnkStoreInformation.getAddress());
                        map.put("store_name", wnkStoreInformation.getStore_name());
                        map.put("contact_mobile", wnkStoreInformation.getContact_mobile());
                        map.put("lat", wnkStoreInformation.getLat());
                        map.put("longt", wnkStoreInformation.getLongt());
                        List<Map<Object, Object>> collectionList = this.userCollectionService.selectCollectionByUserIdAndBusinessId(user_id, business_id);
                        if (collectionList.size() <= 0) {
                            map.put("collection_state", 0);
                        } else {
                            map.put("collection_state", 1);
                        }
                        String tese_label = wnkStoreInformation.getTese_label();
                        String fuwu_label = wnkStoreInformation.getFuwu_label();
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
                        String business_hours = wnkStoreInformation.getBusiness_hours();
                        if (wnkStoreInformation.getOperate_status() == 1) {
                            map.put("business_hours", "休息中");
                        } else {
                            if (business_hours == null || business_hours.equals("")) {
                                map.put("business_hours", "未知");
                            } else {
                                String[] hoursArray = business_hours.split(" - ");
                                if (hoursArray.length == 2) {
                                    String startTime = hoursArray[0].replaceAll("时", "");
                                    String endTime = hoursArray[1].replaceAll("时", "");
                                    Integer startInt = Integer.parseInt(startTime);
                                    Integer endInt = Integer.parseInt(endTime);
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(new Date());
                                    Integer currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                                    if (startInt == endInt) {
                                        map.put("business_hours", "24小时营业");
                                    } else {
                                        map.put("business_hours", business_hours);
                                    }
                                    if (startInt == 0) {
                                        startInt = 24;
                                    }
                                    if (endInt == 0) {
                                        endInt = 24;
                                    }

                                    if (startInt > endInt) {
                                        if (currentHour < startInt && currentHour > endInt) {
                                            map.put("business_hours", "休息中");
                                        }
                                    } else if (startInt < endInt) {
                                        if (currentHour > startInt && currentHour > endInt) {
                                            map.put("business_hours", "休息中");
                                        }
                                    }
//                                if (currentHour <= startInt || currentHour >= endInt){
//                                    map.put("business_hours","休息中");
//                                }
                                } else {
                                    map.put("business_hours", "未知");
                                }
                            }
                        }

                        WnkBusinessLegalize wnkBusinessLegalize = this.wnkBusinessLegalizeService.selectByBusinessId(business_id);
                        if (wnkBusinessLegalize == null) {
                            map.put("store_kaiye_state", "");
                        } else {
                            Date renzhengDate = wnkBusinessLegalize.getStart_time();
                            if (renzhengDate == null) {
                                map.put("store_kaiye_state", "");
                            } else {
                                long dateDiff = System.currentTimeMillis() - renzhengDate.getTime();
                                if (dateDiff < 0) {
                                    map.put("store_kaiye_state", "");
                                } else {
                                    long dateTemp1 = dateDiff / 1000; // 秒
                                    long dateTemp2 = dateTemp1 / 60; // 分钟
                                    long dateTemp3 = dateTemp2 / 60; // 小时
                                    long dateTemp4 = dateTemp3 / 24; // 天数
                                    long dateTemp5 = dateTemp4 / 30; // 月数
                                    if (dateTemp5 <= 3) {
                                        map.put("store_kaiye_state", "新店开业");
                                    } else {
                                        map.put("store_kaiye_state", "");
                                    }
                                }
                            }

                        }
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
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

    // 获取商品详情信息
    @RequestMapping(value = "/getCommodityDetail", method = RequestMethod.POST, params = {"user_id", "commodity_id"})
    @ResponseBody
    public Result getCommodityDetail(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer commodity_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
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
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    if (wnkCommodities == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品不存在");
                    } else {
                        Map<Object, Object> map = new HashMap<Object, Object>();
                        map.put("name", wnkCommodities.getName());
                        map.put("commodity_describe", wnkCommodities.getCommodity_describe());
                        map.put("price", wnkCommodities.getPrice());
                        map.put("is_make_wnk", wnkCommodities.getIs_make_wnk());
                        List<String> photoList = new ArrayList<String>();
                        if (wnkCommodities.getPhoto() != null && !wnkCommodities.getPhoto().equals("")) {
                            photoList.add(ImageToolsController.imageShowURL + "?imageid=" + wnkCommodities.getPhoto());
                        }
                        map.put("banners", photoList);
                        String photoStr = wnkCommodities.getPhoto();

                        map.put("photo", ImageToolsController.imageShowURL + "?imageid=" + photoStr.split("|")[0]);

                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkCommodities.getBusiness_id());
                        if (wnkStoreInformation != null) {
                            WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                            if (wnkBusinessType.getMake_wnk_state() == 1) {
                                map.put("member_price", wnkBusinessType.getCommodifty_price());
                            } else {
                                map.put("member_price", -1);
                            }
                        } else {
                            map.put("member_price", -1);
                        }

                        if (users.getMember_card_level() == -1) {
                            //会员是否已开卡(0-未开卡,1-已开卡)
                            map.put("member_open_card_state", 0);
                        } else {
                            //会员是否已开卡(0-未开卡,1-已开卡)
                            map.put("member_open_card_state", 1);
                        }
                        WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                        if (wnkBusinessType == null) {
                            //优惠方式(0-定价,1-折扣)
                            map.put("discount_type", 0);
                        } else {
                            //优惠方式(0-定价,1-折扣)
                            map.put("discount_type", wnkBusinessType.getDiscount_type());
                        }


                        // 获取商家商品中赠送消费额度最高的消费赠送
                        // 获取商家信息
                        Integer businessId = wnkCommodities.getBusiness_id();
                        Map<String, Object> giftNoun = this.wnkCommoditiesService.selectCommoditiesSpecificationsServiceMaxGiftNounByBusinessId(businessId);
                        if(giftNoun != null){
                            map.put("gift_noun", giftNoun.get("max_gift_noun"));
                        } else {
                            map.put("gift_noun", 0);
                        }


                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");

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


    // 获取万能卡商品规格数据
    @RequestMapping(value = "/getWnkCommoditySpecification", method = RequestMethod.POST, params = {"user_id", "commodity_id"})
    @ResponseBody
    public Result getWnkCommoditySpecification(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer commodity_id) {
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
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    if (wnkCommodities == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品不存在");
                    } else {
                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkCommodities.getBusiness_id());
                        if (wnkStoreInformation == null) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商户不存在");
                        } else {
                            WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                            if (wnkBusinessType == null) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("商户分类不存在");
                            } else {
                                List<Map<Object, Object>> list = this.wnkCommoditySpecificationsService.selectByCommodityId(commodity_id);
                                if (list.size() <= 0) {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("暂无规格信息");
                                } else {
                                    if (wnkBusinessType.getMake_wnk_state() == 0) {
                                        for (Integer index = 0; index < list.size(); index++) {
                                            Map<Object, Object> map = list.get(0);
                                            map.put("is_wnk", 1);
                                        }
                                    }
                                    result.setData(list);
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("查询成功");
                                }
                            }
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


    // 获取商品详情及规格信息信息
    @RequestMapping(value = "/getCommodityDetailAndGuige", method = RequestMethod.POST, params = {"user_id", "commodity_id", "guige_id"})
    @ResponseBody
    public Result getCommodityDetailAndGuige(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer commodity_id, Integer guige_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
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
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    WnkCommoditySpecifications wnkCommoditySpecifications = this.wnkCommoditySpecificationsService.selectById(guige_id);

                    if (wnkCommodities == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品不存在");
                    } else if (wnkCommoditySpecifications == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("规格信息不存在");
                    } else if (wnkCommoditySpecifications.getInventory() != null && wnkCommoditySpecifications.getInventory() != -1 && wnkCommoditySpecifications.getInventory() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("当前规格库存不足");
                    } else {
                        Map<Object, Object> map = new HashMap<Object, Object>();
                        map.put("name", wnkCommodities.getName());
                        map.put("commodity_describe", wnkCommodities.getCommodity_describe());
                        map.put("price", wnkCommoditySpecifications.getPrice());
                        map.put("is_make_wnk", wnkCommodities.getIs_make_wnk());
                        map.put("guige_name", wnkCommoditySpecifications.getSpecifications_name());
                        List<String> photoList = new ArrayList<String>();
                        if (wnkCommodities.getPhoto() != null && !wnkCommodities.getPhoto().equals("")) {
                            photoList.add(ImageToolsController.imageShowURL + "?imageid=" + wnkCommodities.getPhoto());
                        }
                        map.put("banners", photoList);
                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkCommodities.getBusiness_id());
                        if (wnkStoreInformation != null) {
                            WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                            if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 0) {
                                if (wnkBusinessType.getMake_wnk_state() == 1 && wnkCommoditySpecifications.getIs_wnk() == 0) {
                                    if (users.getMember_card_level() != -1) {
                                        map.put("price", wnkCommoditySpecifications.getPrice());
                                        if (wnkBusinessType.getDiscount_type() == 0) {
                                            map.put("wnk_price", wnkBusinessType.getCommodifty_price());
                                        } else {
                                            Double bili = wnkBusinessType.getCommodifty_price() / 100;
                                            map.put("wnk_price", String.format("%.2f", wnkCommoditySpecifications.getPrice() * bili));
                                        }
                                        //用户是否已开卡(0-未开卡,1-已开卡)
                                        map.put("user_open_card_state", 1);

                                    } else {
                                        map.put("price", wnkCommoditySpecifications.getPrice());
                                        if (wnkBusinessType.getDiscount_type() == 0) {
                                            map.put("wnk_price", wnkBusinessType.getCommodifty_price());
                                        } else {
                                            Double bili = wnkBusinessType.getCommodifty_price() / 100;
                                            map.put("wnk_price", String.format("%.2f", wnkCommoditySpecifications.getPrice() * bili));
                                        }
                                        //用户是否已开卡(0-未开卡,1-已开卡)
                                        map.put("user_open_card_state", 0);
                                    }
                                    //是否执行万能卡权益(0-不执行,1-执行)
                                    map.put("make_wnk_state", 1);
                                } else {
                                    map.put("price", wnkCommoditySpecifications.getPrice());
                                    map.put("wnk_price", wnkCommoditySpecifications.getPrice());
                                    //是否执行万能卡权益(0-不执行,1-执行)
                                    map.put("make_wnk_state", 0);
                                    //用户是否已开卡(0-未开卡,1-已开卡)
                                    map.put("user_open_card_state", 0);
                                }

                                //是否需要开卡(0-不需要,1-需要)
                                map.put("is_open_card", 0);
                                //是否调用微信支付(0-不调用,1-调用)
                                map.put("wx_pay_state", 1);
                            } else if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 1) {
                                if (wnkBusinessType.getMake_wnk_state() == 1 && wnkCommoditySpecifications.getIs_wnk() == 0) {
                                    WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(user_id, wnkStoreInformation.getBusiness_id());
                                    if (users.getMember_card_level() != -1 && wnkBusinessTypeOpenCard != null) {
                                        map.put("price", wnkCommoditySpecifications.getPrice());
                                        map.put("wnk_price", 0);
                                        //是否调用微信支付(0-不调用,1-调用)
                                        map.put("wx_pay_state", 0);
                                        //用户是否已开卡(0-未开卡,1-已开卡)
                                        map.put("user_open_card_state", 1);
                                    } else {
                                        map.put("price", wnkCommoditySpecifications.getPrice());
                                        map.put("wnk_price", 0);
                                        //是否调用微信支付(0-不调用,1-调用)
                                        map.put("wx_pay_state", 1);
                                        //用户是否已开卡(0-未开卡,1-已开卡)
                                        map.put("user_open_card_state", 0);
                                    }
                                    //是否执行万能卡权益(0-不执行,1-执行)
                                    map.put("make_wnk_state", 1);
                                } else {
                                    map.put("price", wnkCommoditySpecifications.getPrice());
                                    map.put("wnk_price", wnkCommoditySpecifications.getPrice());
                                    //是否执行万能卡权益(0-不执行,1-执行)
                                    map.put("make_wnk_state", 0);
                                    //是否调用微信支付(0-不调用,1-调用)
                                    map.put("wx_pay_state", 1);
                                    //用户是否已开卡(0-未开卡,1-已开卡)
                                    map.put("user_open_card_state", 0);
                                }
                                //是否需要开卡(0-不需要,1-需要)
                                map.put("is_open_card", 1);
                            } else {
                                map.put("price", wnkCommoditySpecifications.getPrice());
                                map.put("wnk_price", wnkCommoditySpecifications.getPrice());
                                //是否需要开卡(0-不需要,1-需要)
                                map.put("is_open_card", 0);
                                //是否执行万能卡权益(0-不执行,1-执行)
                                map.put("make_wnk_state", 0);
                                //是否调用微信支付(0-不调用,1-调用)
                                map.put("wx_pay_state", 1);
                                //用户是否已开卡(0-未开卡,1-已开卡)
                                map.put("user_open_card_state", 0);
                            }
                        } else {
                            map.put("price", wnkCommoditySpecifications.getPrice());
                            map.put("wnk_price", wnkCommoditySpecifications.getPrice());
                            //是否需要开卡(0-不需要,1-需要)
                            map.put("is_open_card", 0);
                            //是否执行万能卡权益(0-不执行,1-执行)
                            map.put("make_wnk_state", 0);
                            //是否调用微信支付(0-不调用,1-调用)
                            map.put("wx_pay_state", 1);
                            //用户是否已开卡(0-未开卡,1-已开卡)
                            map.put("user_open_card_state", 0);
                        }

                        map.put("gift_noun", wnkCommoditySpecifications.getGift_noun());

                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");

                    }

                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 获取商家分类是否执行万能卡权益
    @RequestMapping(value = "/getBusinessTypeIsWnk", method = RequestMethod.POST)
    @ResponseBody
    public Result getBusinessTypeIsWnk(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer business_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
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
                    WnkStoreInformation wnkStoreInformation = wnkStoreInformationService.selectByBusinessId(business_id);
                    WnkBusinessType wnkBusinessType = wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                    result.setData(wnkBusinessType.getMake_wnk_state());
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    /**
     * 功能描述: 获取商品详情和商品对应的规格,扩展信息详情(酒店)
     *
     * @param business_id 商家ID
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 11:57 2019/1/2
     */
    @RequestMapping(value = "/selectCommodityInfoAndHotelExpandInfoById", method = RequestMethod.POST)
    @ResponseBody
    public Result selectCommodityInfoAndHotelExpandInfoById(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer business_id, Integer commodity_id) {
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                return new Result(Result.NOLOGIN, "此账户暂未登录", null);
            }
            Users users = this.usersService.selectById(user_id);
            if (users == null) {
                return new Result(Result.FAIL, "此用户不存在", null);
            }

            Map<Object, Object> resultMap = new HashMap<>(16);
            WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
            List<Map<Object, Object>> SpecificationsMap = this.wnkCommoditySpecificationsService.selectByCommodityId(commodity_id);
            WnkCommoditiesExpandHotel wnkCommoditiesExpandHotel = this.wnkCommoditiesExpandHotelService.selectCommoditiesExpandHotelByCommoditiesId(commodity_id);

            // 商家电话
            resultMap.put("mobile", this.wnkBusinessAccountService.selectById(wnkCommodities.getBusiness_id()).getMobile());
            // 商家名称
            resultMap.put("business_name", this.wnkStoreInformationService.selectByBusinessId(wnkCommodities.getBusiness_id()).getStore_name());
            // 商品信息
            resultMap.put("id", wnkCommodities.getId());
            resultMap.put("name", wnkCommodities.getName());
            resultMap.put("commodity_describe", wnkCommodities.getCommodity_describe());
            resultMap.put("photo", wnkCommodities.getPhoto());
            resultMap.put("photo_prefix", ImageToolsController.imageShowURL + "?imageid=");
            // 规格信息(酒店类商品只能有一个规格)
            resultMap.put("specifications_name", SpecificationsMap.get(0).get("specifications_name"));
            resultMap.put("price", SpecificationsMap.get(0).get("price"));
            resultMap.put("inventory", SpecificationsMap.get(0).get("inventory"));
            resultMap.put("gift_noun", SpecificationsMap.get(0).get("gift_noun"));
            // 扩展信息
            resultMap.put("expand", wnkCommoditiesExpandHotel);
            return new Result(Result.SUCCESS, "查询成功", resultMap);
        } catch (Exception e) {
            return new Result(Result.FAIL, "获取失败", e.getMessage());
        }
    }


}
