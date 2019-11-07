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
import java.util.*;

/**
 * @author 杨新杰
 */
@Controller
@RequestMapping(value = "/app/v1.0.0")
public class WnkBusinessTypeAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkQrCodeMakeRecordService wnkQrCodeMakeRecordService;
    @Resource
    private UserOpenCardsService userOpenCardsService;
    @Resource
    private WnkBuyMealService wnkBuyMealService;
    @Resource
    private WnkIntegralUserServer wnkIntegralUserServer;
    @Resource
    private WnkSendIntegralUserService wnkSendIntegralUserService;
    @Resource
    private WnkBusinessTypeOpenCardService wnkBusinessTypeOpenCardService;
    @Resource
    private WnkBusinessLabelService wnkBusinessLabelService;
    @Resource
    private WnkBusinessLegalizeService wnkBusinessLegalizeService;
    @Resource
    private WnkCommoditiesService wnkCommoditiesService;
    @Resource
    private WnkCommoditySpecificationsService wnkCommoditySpecificationsService;
    @Resource
    private WnkOrdersTwoService wnkOrdersTwoService;

    // 获取万能卡商家分类
    @RequestMapping(value = "/getWnkBusinessType", method = RequestMethod.POST, params = {"user_id"})
    @ResponseBody
    public Result getWnkBusinessType(HttpServletRequest request, HttpServletResponse response, Integer user_id) {
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
                    Integer month_free_number = 0;
                    if (users.getMember_card_level() != -1) {
                        UserOpenCards userOpenCards = this.userOpenCardsService.selectByUserId(user_id);
                        if (userOpenCards != null) {
                            WnkBuyMeal wnkBuyMeal = this.wnkBuyMealService.selectById(userOpenCards.getCard_type_id());
                            if (wnkBuyMeal != null) {
                                month_free_number = wnkBuyMeal.getWnk_make_number();
                            }
                        }
                    }
                    List<Map<Object, Object>> list = this.wnkBusinessTypeService.selectAllRecord();
                    if (list.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    } else {
                        for (Integer index = 0; index < list.size(); index++) {
                            Map<Object, Object> map = list.get(index);
                            map.put("logo_photo_id", ImageToolsController.imageShowURL + "?imageid=" + map.get("logo_photo_id"));
                            map.put("background_photo_id", ImageToolsController.imageShowURL + "?imageid=" + map.get("background_photo_id"));
                            if (users.getMember_card_level() != -1) {
                                Integer make_number = this.wnkQrCodeMakeRecordService.selectCurrentMakeNumber(user_id, (Integer) map.get("id"));
                                Integer sy_number = month_free_number - make_number;
                                if (month_free_number == -1) {
                                    map.put("month_free_number", "不限");
                                } else {
                                    map.put("month_free_number", sy_number);
                                }
                            } else {
                                map.put("month_free_number", 0);
                            }
                        }
                        result.setData(list);
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


    // 获取万能卡商家分类下的商家
    @RequestMapping(value = "/getWnkBusinessByTypeId", method = RequestMethod.POST, params = {"user_id", "type_id"})
    @ResponseBody
    public Result getWnkBusinessByTypeId(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer type_id) {
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
                    List<Map<Object, Object>> list = this.wnkStoreInformationService.selectByTypeId(type_id, 0.00, 0.00, 1);
                    if (list.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    } else {
                        result.setData(list);
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

    // 获取商家开卡信息
    @RequestMapping(value = "/getBusinessOpenCardInformation", method = RequestMethod.POST, params = {"user_id", "business_id"})
    @ResponseBody
    public Result getBusinessOpenCardInformation(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer business_id) {
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
                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                    if (wnkStoreInformation == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此商户不存在");
                    } else {
                        WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                        if (wnkBusinessType == null) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商户分类不存在");
                        } else if (wnkBusinessType.getCommdity_charge_way() == 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("此分类无需开会员卡");
                        } else {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("business_name", wnkStoreInformation.getStore_name());
                            map.put("pay_amount", wnkBusinessType.getCommodifty_price());
                            map.put("price_annual_card", wnkStoreInformation.getPositive_price());
                            result.setData(map);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("查询成功");
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

    /**
     * 功能描述: 获取万能卡商家分类下的商家带距离
     *
     * @param user_id   用户id
     * @param type_id   类别id
     * @param lat       纬度
     * @param longt     经度
     * @param user_juli 距离(1-1公里,3-3公里，5-5公里,10-10公里,-1-全城)
     * @param sort_type 排序类型(1-离我最近,2-销量最高,3-价格最低)
     * @return:
     * @author: zhangfan
     * @date: 2018/11/1 2:14 PM
     */
    @RequestMapping(value = "/getWnkBusinessByTypeIdAndJuLi", method = RequestMethod.POST, params = {"user_id", "type_id", "lat", "longt", "user_juli", "sort_type"})
    @ResponseBody
    public Result getWnkBusinessByTypeIdAndJuLi(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer type_id, Double lat, Double longt, Double user_juli, Integer sort_type) {
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
                    List<Map<Object, Object>> list = this.wnkStoreInformationService.selectByTypeId(type_id, lat, longt, sort_type);
                    List<Map<Object, Object>> returnList = new ArrayList<Map<Object, Object>>();
                    if (list.size() <= 0) {
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
                        for (Integer index = 0; index < list.size(); index++) {
                            Map<Object, Object> map = list.get(index);
                            String tese_label = (String) map.get("tese_label");
                            String fuwu_label = (String) map.get("fuwu_label");
                            String fm_photo = (String) map.get("banners_photoid");
                            String cover_photo = (String) map.get("cover_photo");
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
                                    returnList.add(map);
                                }

                            } else {
                                returnList.add(map);
                            }
                            if (isSearchUserOpenCardState == true) {
                                WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(user_id, (Integer) map.get("business_id"));
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
                            if (wnkBusinessType.getMake_wnk_state() == 1) {
                                Map<Object, Object> guigeMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeMinPriceByBusinessId((Integer) map.get("business_id"));
                                if (guigeMap != null) {
                                    // 有正价显示正价
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
                                    Map<Object, Object> guigeStartMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeStartByBusinessId((Integer) map.get("business_id"));
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
                                Map<Object, Object> guigeStartMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeStartByBusinessId((Integer) map.get("business_id"));
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

                            String business_hours = (String) map.get("business_hours");
                            if (business_hours != null && business_hours.equals("00时 - 00时")) {
                                map.put("business_hours", "24小时营业");
                            }
                            Integer operate_status = (Integer) map.get("operate_status");
                            if (operate_status == 1) {
                                map.put("business_hours", "休息中");
                            }

                            List<Map<Object, Object>> guigeList = this.wnkCommoditySpecificationsService.selectWnkGuiGeByBusinessId((Integer) map.get("business_id"));
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
                        result.setData(returnList);
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

    /**
     * 功能描述: 获取万能卡商家分类下的商家带距离
     *
     * @param user_id   用户id
     * @param type_id   类别id
     * @param lat       纬度
     * @param longt     经度
     * @param user_juli 距离(1-1公里,3-3公里，5-5公里,10-10公里,-1-全城)
     * @param sort_type 排序类型(1-离我最近,2-销量最高,3-价格最低)
     * @return:
     * @author: zhangfan
     * @date: 2018/11/1 2:14 PM
     */
    @RequestMapping(value = "/getWnkBusinessByTypeIdAndJuLiTwo", method = RequestMethod.POST, params = {"user_id", "type_id", "lat", "longt", "user_juli", "sort_type"})
    @ResponseBody
    public Result getWnkBusinessByTypeIdAndJuLiTwo(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer type_id, Double lat, Double longt, Double user_juli, Integer sort_type) {
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
                    List<Map<Object, Object>> list = this.wnkStoreInformationService.selectByTypeId(type_id, lat, longt, sort_type);
                    List<Map<Object, Object>> returnList = new ArrayList<Map<Object, Object>>();
                    if (list.size() <= 0) {
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
                        for (Integer index = 0; index < list.size(); index++) {
                            Map<Object, Object> map = list.get(index);
                            String tese_label = (String) map.get("tese_label");
                            String fuwu_label = (String) map.get("fuwu_label");
                            String fm_photo = (String) map.get("banners_photoid");
                            String cover_photo = (String) map.get("cover_photo");
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
                                    returnList.add(map);
                                }

                            } else {
                                returnList.add(map);
                            }
                            if (isSearchUserOpenCardState == true) {
                                WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(user_id, (Integer) map.get("business_id"));
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
                            if (wnkBusinessType.getMake_wnk_state() == 1) {
                                Map<Object, Object> guigeMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeMinPriceByBusinessId((Integer) map.get("business_id"));
                                if (guigeMap != null) {
                                    // 有正价显示正价
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
                                    Map<Object, Object> guigeStartMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeStartByBusinessId((Integer) map.get("business_id"));
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
                                Map<Object, Object> guigeStartMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeStartByBusinessId((Integer) map.get("business_id"));
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

                            String business_hours = (String) map.get("business_hours");
                            if (business_hours != null && business_hours.equals("00时 - 00时")) {
                                map.put("business_hours", "24小时营业");
                            }
                            Integer operate_status = (Integer) map.get("operate_status");
                            if (operate_status == 1) {
                                map.put("business_hours", "休息中");
                            }

                            List<Map<Object, Object>> guigeList = this.wnkCommoditySpecificationsService.selectWnkGuiGeByBusinessId((Integer) map.get("business_id"));
                            if (guigeList.size() > 0 && wnkBusinessType.getMake_wnk_state() == 1) {
                                //是否显示vip图标(0-不显示,1-显示)
                                map.put("vip_icon_state", 1);
                            } else {
                                //是否显示vip图标(0-不显示,1-显示)
                                map.put("vip_icon_state", 0);
                            }

                            // 获取商家商品中赠送消费额度最高的消费赠送
                            // 获取商家信息
                            Integer businessId = (Integer) map.get("business_id");
                            Map<String, Object> giftNoun = this.wnkCommoditiesService.selectCommoditiesSpecificationsServiceMaxGiftNounByBusinessId(businessId);
                            if(giftNoun != null){
                                map.put("gift_noun",giftNoun.get("max_gift_noun"));
                            } else {
                                map.put("gift_noun",0);
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
                        Map<Object, Object> returnMap = new HashMap<Object, Object>();
                        returnMap.put("wnk_state", users.getMember_card_level());
                        returnMap.put("list", returnList);
                        result.setData(returnMap);
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


    // 获取用户的积分商家(用户在商家处有积分的)
    @RequestMapping(value = "/getUserIntegralBusiness", method = RequestMethod.POST, params = {"user_id"})
    @ResponseBody
    public Result getUserIntegralBusiness(HttpServletRequest request, HttpServletResponse response, Integer user_id) {
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
                    List<Map<Object, Object>> list = this.wnkIntegralUserServer.selectBusinessByUserId(user_id);
                    if (list.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    } else {
                        for (Integer index = 0; index < list.size(); index++) {
                            Map<Object, Object> map = list.get(index);
                            String fm_photo = (String) map.get("banners_photoid");
                            String cover_photo = (String) map.get("cover_photo");
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
                            Long sale = (Long) map.get("sale");
                            if (sale == null) {
                                map.put("sale", 0);
                            }
                            Double min_price = (Double) map.get("min_price");
                            if (min_price == null) {
                                map.put("min_price", 0);
                            }
                            map.put("fm_photo", ImageToolsController.imageShowURL + "?imageid=" + fm_photo);
                            map.put("juli", "");
                        }
                        result.setData(list);
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


    // 获取用户的赠送积分商家(用户在商家处有赠送积分的)
    @RequestMapping(value = "/getUserSendIntegralBusiness", method = RequestMethod.POST, params = {"user_id"})
    @ResponseBody
    public Result getUserSendIntegralBusiness(HttpServletRequest request, HttpServletResponse response, Integer user_id) {
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
                    List<Map<Object, Object>> list = this.wnkSendIntegralUserService.selectBusinessByUserId(user_id);
                    if (list.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    } else {
                        for (Integer index = 0; index < list.size(); index++) {
                            Map<Object, Object> map = list.get(index);
                            String fm_photo = (String) map.get("banners_photoid");
                            String cover_photo = (String) map.get("cover_photo");
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
                            Long sale = (Long) map.get("sale");
                            if (sale == null) {
                                map.put("sale", 0);
                            }
                            Double min_price = (Double) map.get("min_price");
                            if (min_price == null) {
                                map.put("min_price", 0);
                            }
                            map.put("fm_photo", ImageToolsController.imageShowURL + "?imageid=" + fm_photo);
                            map.put("juli", "");
                        }
                        result.setData(list);
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

    // 获取附近的商家
    @RequestMapping(value = "/getNearbyWnkBusiness", method = RequestMethod.POST, params = {"user_id", "lat", "longt"})
    @ResponseBody
    public Result getNearbyWnkBusiness(HttpServletRequest request, HttpServletResponse response, Integer user_id, Double lat, Double longt) {
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
                    List<Map<Object, Object>> list = this.wnkStoreInformationService.selectNearbyBusiness(lat, longt);
                    if (list.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    } else {
                        for (Integer index = 0; index < list.size(); index++) {
                            Map<Object, Object> map = list.get(index);
                            String tese_label = (String) map.get("tese_label");
                            String fuwu_label = (String) map.get("fuwu_label");
                            String fm_photo = (String) map.get("banners_photoid");
                            String cover_photo = (String) map.get("cover_photo");
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

                            Long sale = (Long) map.get("sale");
                            if (sale == null) {
                                map.put("sale", 0);
                            }

                            map.put("fm_photo", ImageToolsController.imageShowURL + "?imageid=" + fm_photo);
                            Double juli = (Double) map.get("juli");
                            if (juli == null) {
                                map.put("juli", "距离不详");
                                map.put("jili_calculate", 0);
                            } else {
                                map.put("juli", juli + "km");
                                map.put("jili_calculate", juli);
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
                            Integer business_type_id = (Integer) map.get("business_type_id");
                            WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(business_type_id);

                            Double positive_price = (Double) map.get("positive_price");
                            if (positive_price == null) {
                                positive_price = 0.00;
                            }
                            if (wnkBusinessType.getMake_wnk_state() == 1) {
                                Map<Object, Object> guigeMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeMinPriceByBusinessId((Integer) map.get("business_id"));
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
                                    Map<Object, Object> guigeStartMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeStartByBusinessId((Integer) map.get("business_id"));
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
                                Map<Object, Object> guigeStartMap = this.wnkCommoditySpecificationsService.selectWnkGuiGeStartByBusinessId((Integer) map.get("business_id"));
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
                            String business_hours = (String) map.get("business_hours");
                            if (business_hours != null && business_hours.equals("00时 - 00时")) {
                                map.put("business_hours", "24小时营业");
                            }
                            Integer operate_status = (Integer) map.get("operate_status");
                            if (operate_status == 1) {
                                map.put("business_hours", "休息中");
                            }
                            List<Map<Object, Object>> guigeList = this.wnkCommoditySpecificationsService.selectWnkGuiGeByBusinessId((Integer) map.get("business_id"));
                            if (guigeList.size() > 0 && wnkBusinessType.getMake_wnk_state() == 1) {
                                //是否显示vip图标(0-不显示,1-显示)
                                map.put("vip_icon_state", 1);
                            } else {
                                //是否显示vip图标(0-不显示,1-显示)
                                map.put("vip_icon_state", 0);
                            }

                            // 获取商家商品中赠送消费额度最高的消费赠送
                            // 获取商家信息
                            Integer businessId = (Integer) map.get("business_id");
                            Map<String, Object> giftNoun = this.wnkCommoditiesService.selectCommoditiesSpecificationsServiceMaxGiftNounByBusinessId(businessId);
                            map.put("gift_noun",giftNoun.get("max_gift_noun"));
                        }

                        result.setData(list);
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

    // 获取推荐的商家
    @RequestMapping(value = "/getRecommendWnkBusiness", method = RequestMethod.POST, params = {"user_id"})
    @ResponseBody
    public Result getRecommendWnkBusiness(HttpServletRequest request, HttpServletResponse response, Integer user_id) {
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
                    //特别推荐商户
                    List<Map<Object, Object>> recommendList = this.wnkStoreInformationService.selectEspeciallyRecommendBusiness();
                    if (recommendList.size() < 4) {
                        List<Map<Object, Object>> list = this.wnkStoreInformationService.selectRecommendBusiness();
                        Integer shengyu = 4 - recommendList.size();
                        for (Integer index = 0; index < shengyu; index++) {
                            recommendList.add(list.get(index));
                        }
                    }

                    if (recommendList.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    } else {

                        for (Integer index = 0; index < recommendList.size(); index++) {
                            Map<Object, Object> map = recommendList.get(index);
                            // 获取商家banner图
                            String fm_photo = (String) map.get("banners_photoid");
                            // 获取商家展示图
                            String cover_photo = (String) map.get("cover_photo");

                            // 如果设置了轮播图则显示轮播图第一张 否则显示展示图
                            if (fm_photo != null && !fm_photo.equals("")) {
                                // 如果轮播图存在则取第一张,如果不存在则设置为默认logo
                                String[] sourceStrArray = fm_photo.split(",");
                                if (sourceStrArray.length <= 0) {
                                    fm_photo = "logo.jpg";
                                } else {
                                    fm_photo = sourceStrArray[0];
                                }
                            } else {
                                if (cover_photo == null || cover_photo.equals("")) {
                                    fm_photo = "logo.jpg";
                                } else {
                                    fm_photo = cover_photo;
                                }
                            }
//                             如果展示图存在则把展示图赋值给轮播图
//                            if (cover_photo != null && !cover_photo.equals("")){
//                                fm_photo = cover_photo;
//                            }
//                            else{ // 如果展示图和banner图都不存在则显示默认logo
//                                if (fm_photo == null || fm_photo.equals("")){
//                                    fm_photo = "logo.jpg";
//                                }
//                                else{
//                                    // 如果banner图存在则取第一张,如果不存在则设置为默认logo
//                                    String[] sourceStrArray = fm_photo.split(",");
//                                    if (sourceStrArray.length <= 0){
//                                        fm_photo = "logo.jpg";
//                                    }
//                                    else{
//                                        fm_photo = sourceStrArray[0];
//                                    }
//                                }
//                            }
                            map.put("fm_photo", ImageToolsController.imageShowURL + "?imageid=" + fm_photo);
                            Long sale = (Long) map.get("sale");
                            if (sale == null) {
                                map.put("sale", 0);
                            }
                            Double min_price = (Double) map.get("min_price");
                            if (min_price == null) {
                                map.put("min_price", 0);
                            }
                            Double juli = (Double) map.get("juli");
                            if (juli == null) {
                                map.put("juli", "距离不详");
                            } else {
                                map.put("juli", juli + "km");
                            }
                        }
                        result.setData(recommendList);
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

    // 模糊查询商家
    @RequestMapping(value = "/fuzzyQueryBusiness", method = RequestMethod.POST, params = {"user_id", "content", "lat", "longt"})
    @ResponseBody
    public Result fuzzyQueryBusiness(HttpServletRequest request, HttpServletResponse response, Integer user_id, String content, Double lat, Double longt) {
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
                    List<Map<Object, Object>> list = this.wnkStoreInformationService.selectByStoreName(content, lat, longt);
                    List<Map<Object, Object>> returnList = new ArrayList<Map<Object, Object>>();
                    if (list.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    } else {
                        for (Integer index = 0; index < list.size(); index++) {
                            Map<Object, Object> map = list.get(index);
                            WnkBusinessLegalize wnkBusinessLegalize = this.wnkBusinessLegalizeService.selectByBusinessId((Integer) map.get("business_id"));
                            if (wnkBusinessLegalize != null && wnkBusinessLegalize.getLegalize_status() == 2) {
                                String fm_photo = (String) map.get("banners_photoid");
                                String cover_photo = (String) map.get("cover_photo");
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
                                Double min_price = (Double) map.get("min_price");
                                if (min_price == null) {
                                    map.put("min_price", 0);
                                }
                                Double juli = (Double) map.get("juli");
                                if (juli == null) {
                                    map.put("juli", "距离不详");
                                } else {
                                    map.put("juli", juli + "km");
                                }

                                returnList.add(map);
                            }

                        }
                        if (returnList.size() <= 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("暂无数据");
                        } else {
                            result.setData(returnList);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("获取成功");
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


    /**
     * 功能描述: 查询对应商家限制信息 (暂时为电影类商家)
     *
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 11:37 2019/1/7
     */
    @RequestMapping(value = "/getTimeLimitAndUsed", method = RequestMethod.POST)
    @ResponseBody
    public Result getTimeLimitAndUsed(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer business_id) {
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
                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                    WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                    Map<String, Object> map = new HashMap<>(2);
                    map.put("time_discount_member", wnkBusinessType.getLimit_number());
                    Map<String, Object> map1 = this.wnkOrdersTwoService.selectUserToDaysBusinessOrderNumber(user_id, business_id);

                    if (map1 != null) {
                        map.put("time_consume_today", map1.get("number"));
                    } else {
                        map.put("time_consume_today", 0);
                    }

                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    /**
     * 功能描述: 查询对应商家限制信息 (暂时为电影类商家)
     *
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 11:37 2019/1/7
     */
    @RequestMapping(value = "/getWnkBusinessTypeByBusinessId", method = RequestMethod.POST)
    @ResponseBody
    public Result getWnkBusinessTypeByBusinessId(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer business_id) {
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
                    WnkStoreInformation wnkStoreInformation = wnkStoreInformationService.selectByBusinessId(business_id);
                    if (wnkStoreInformation == null) {
                        result.setData(null);
                        result.setStatus(Result.FAIL);
                        result.setMsg("此商家不存在");
                        return result;
                    }
                    WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                    if (wnkBusinessType == null) {
                        result.setData(null);
                        result.setStatus(Result.FAIL);
                        result.setMsg("此分类不存在");
                        return result;
                    }
                    result.setData(wnkBusinessType);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


}
