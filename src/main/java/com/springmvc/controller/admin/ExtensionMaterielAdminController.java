package com.springmvc.controller.admin;

import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.GeTuiBusinessUtil;
import com.springmvc.utils.MobileCodeUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Author zhangfan
 * @Date 2018/11/17 19:05
 * @Description 后端物料管理控制器
 */
@Controller
@RequestMapping(value = "/admin")
public class ExtensionMaterielAdminController {
    @Resource
    private ExtensionMaterielService extensionMaterielService;
    @Resource
    private MyMaterielService myMaterielService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkBusinessMaterielGiftRecordService wnkBusinessMaterielGiftRecordService;

    /**
     *
     * 功能描述: 进入物料管理页面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/25 0025 15:32
     */
    @RequestMapping(value = "/joinExtensionMaterielManagerPage", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinExtensionMaterielManagerPage(HttpServletRequest request) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/extension_materiel/extension_materiel");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入商家物料剩余情况
     *
     * @return  org.springframework.web.servlet.ModelAndView
     * @author  杨新杰
     * @date    16:20 2019/1/5
     */
    @RequestMapping(value = "/joinBusinessMaterielInfo", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinBusinessMaterielInfo(HttpServletRequest request) {
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/extension_materiel/business_materiel_info");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入物料修改页面
     *
     * @param   request
     * @param   record_id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/25 0025 15:33
     */
    @RequestMapping(value = "/setExtensionMaterielPage", method = RequestMethod.GET, params = {"record_id"})
    @ResponseBody
    public ModelAndView setExtensionMaterielPage(HttpServletRequest request, Integer record_id) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/extension_materiel/set_extension_materiel");
            modelAndView.addObject("record_id", record_id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入物料赠送页面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/25 0025 15:33
     */
    @RequestMapping(value = "/giftExtensionMateriel", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView giftExtensionMateriel(HttpServletRequest request) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/extension_materiel/gift_extension_materiel");
        }
        return modelAndView;
    }

    /**
     * 功能描述: 获取所有物料信息
     *
     * @param request  请求
     * @param response 响应
     * @return: Result      返回所有物料信息
     * @author: 刘武祥
     * @date: 2019/1/4 0004 16:49
     */
    @RequestMapping(value = "/getAllExtensionMaterielInformation", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllExtensionMaterielInformation(HttpServletRequest request, HttpServletResponse response) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                List<Map<String, Object>> list = this.extensionMaterielService.selectAdminWnkInformation();

                if (list.size() <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                } else {
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 根据条件查询物料管理信息
     *
     * @param   request     请求
     * @param   response    响应
     * @param   limit       分页条目数
     * @param   page        分页页码
     * @param   name        物料类型
     * @param   limit_times 限制购买次数
     * @param   buy_number  限制次数
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/18 0018 14:42
     */
    @RequestMapping(value = "/adminSearchExtensionMateriel", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchExtensionMateriel(HttpServletRequest request,
                                             HttpServletResponse response,
                                             Integer limit,
                                             Integer page,
                                             String name,
                                             Integer limit_times,
                                             Integer buy_number){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("start_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                if (name != null && !"".equals(name)) {
                    map.put("name", "%" + name + "%");
                }
                map.put("limit_times", ("".equals(limit_times) ? null : limit_times) );
                if (buy_number != null && !"".equals(buy_number)){
                    map.put("buy_number",buy_number);
                }
                List<Map<Object,Object>> list = this.extensionMaterielService.adminSearchExtensionMateriel(map);

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
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    /**
     *
     * 功能描述：通过物料id获取物料信息
     *
     * @param   request
     * @param   response
     * @param   record_id
     * @return: com.springmvc.utils.Result
     * @auther: 刘武祥
     * @date：  2019/2/26 10:28
     */
    @RequestMapping(value = "/getMaterielInformationById", method = RequestMethod.POST, params = {"record_id"})
    @ResponseBody
    public Result getMaterielInformationById(HttpServletRequest request, HttpServletResponse response, Integer record_id) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                ExtensionMateriel extensionMateriel = this.extensionMaterielService.selectById(record_id);

                if (extensionMateriel == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此物料不存在");
                } else {
                    extensionMateriel.setBackground_photo(extensionMateriel.getBackground_photo());
                    extensionMateriel.setLogo_photo_id(extensionMateriel.getLogo_photo_id());
                    result.setData(extensionMateriel);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    /**
     *
     * 功能描述：通过物料id修改物料信息
     *
     * @param   request
     * @param   response
     * @param   record_id
     * @param   logo_photo_id
     * @param   background_photo
     * @param   buy_number
     * @param   limit_times
     * @return: com.springmvc.utils.Result
     * @auther: 刘武祥
     * @date：  2019/2/26 10:29
     */
    @RequestMapping(value = "/materielInformationSetById", method = RequestMethod.POST, params = {"record_id", "logo_photo_id", "background_photo", "buy_number", "limit_times"})
    @ResponseBody
    public Result materielInformationSetById(HttpServletRequest request, HttpServletResponse response, Integer record_id, String logo_photo_id, String background_photo, Integer buy_number, Integer limit_times) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                ExtensionMateriel extensionMateriel = this.extensionMaterielService.selectById(record_id);

                if (extensionMateriel == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此物料不存在");
                } else {
                    extensionMateriel.setLogo_photo_id(logo_photo_id);
                    extensionMateriel.setBackground_photo(background_photo);
                    extensionMateriel.setBuy_number(buy_number);
                    extensionMateriel.setLimit_times(limit_times);
                    int updateState = this.extensionMaterielService.updateMaterielById(extensionMateriel);

                    if (updateState <= 0) {
                        result.setData(extensionMateriel);
                        result.setStatus(Result.FAIL);
                        result.setMsg("更新失败");
                    } else {
                        result.setData(extensionMateriel);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("更新成功");
                    }

                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("更新失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 后台管理员赠送物料
     *
     * @param   mobile 商家手机号码
     * @param   amount 赠送数量
     * @param   extension_id 物料ID
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:53 2019/1/5
     */
    @RequestMapping(value = "/giftExtensionByAdmin", method = RequestMethod.POST)
    @ResponseBody
    public Result giftExtensionByAdmin(HttpServletRequest request, String mobile, String amount, String extension_id) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectByMobile(mobile);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    ExtensionMateriel extensionMateriel = this.extensionMaterielService.selectById(Integer.valueOf(extension_id));

                    if (extensionMateriel == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此物料不存在");
                    } else {
                        MyMateriel myMateriel = this.myMaterielService.selectByMaterielIdAndBusinessId(wnkBusinessAccount.getId(), Integer.valueOf(extension_id));
                        Integer i , before_number;
                        if (myMateriel == null) {
                            before_number = 0;
                            myMateriel.setBusiness_id(wnkBusinessAccount.getId());
                            myMateriel.setMateriel_id(Integer.valueOf(extension_id));
                            myMateriel.setSurplus_number(Integer.valueOf(amount));
                            i = this.myMaterielService.insertMaterielBuyRecord(myMateriel);
                        } else {
                            before_number = myMateriel.getSurplus_number();
                            myMateriel.setSurplus_number(myMateriel.getSurplus_number() + Integer.valueOf(amount));
                            i = this.myMaterielService.updateSurplusNumber(myMateriel);
                        }

                        // 判断是否赠送成功
                        if (i > 0){
                            // 发送短信
                            MobileCodeUtil.sendGiftExtensionSms(wnkBusinessAccount.getMobile(), extensionMateriel.getName(), Integer.valueOf(extension_id));
                            // 发送个推
                            WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkBusinessAccount.getId());
                            if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")) {
                                String pushTitle = "物料赠送";
                                String pushContent = "平台赠送您" + amount + "个" + extensionMateriel.getName() + "物料,请在猛戳商家版-推广系统-我的物料查看。";
                                GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(), pushTitle, pushContent);
                            }

                            // 插入赠送记录
                            WnkBusinessMaterielGiftRecord wbmgr = new WnkBusinessMaterielGiftRecord();
                            // 管理员ID
                            wbmgr.setAdmin_id(Integer.valueOf(userId));
                            // 插入时间
                            wbmgr.setGift_time(new Date());
                            // 商家ID
                            wbmgr.setBusiness_id(wnkBusinessAccount.getId());
                            // 插入前数量
                            wbmgr.setGift_before_number(before_number);
                            // 插入后数量
                            wbmgr.setGift_after_number(before_number + Integer.valueOf(amount));
                            // 赠送物料的物料ID
                            wbmgr.setMateriel_id(Integer.valueOf(extension_id));
                            // 赠送数量
                            wbmgr.setGift_number(Integer.valueOf(amount));
                            int wbmgrI = this.wnkBusinessMaterielGiftRecordService.insertMaterielGiftRecord(wbmgr);
                            if (wbmgrI > 0){
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("赠送成功");
                            } else {
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("赠送成功,但是赠送记录插入失败");
                            }
                        }

                    }
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    /**
     *
     * 功能描述: 查询所有物料,用于初始化表头
     *
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:53 2019/1/5
     */
    @RequestMapping(value = "/selectMaterielInfoAll", method = RequestMethod.POST)
    @ResponseBody
    public Result selectMaterielInfoAll(HttpServletRequest request) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                List<Map<Object, Object>> maps = this.extensionMaterielService.selectAllRecord();
                if (maps.size() > 0 ){
                    result.setData(maps);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("查询失败");
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    /**
     *
     * 功能描述: 查询商家物料剩余情况
     *
     * @param page  分页查询第几页
     * @param limit 分页查询查询多少条
     * @param store_name 按照商家名称搜索的商家名称
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:53 2019/1/5
     */
    @RequestMapping(value = "/selectBusinessMaterielLastInfoAll", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessMaterielLastInfoAll(HttpServletRequest request,Integer page ,Integer limit,String store_name) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                // 用作返回的list
                List<Map<String,Object>> retList = new LinkedList<>();
                // 先获取物料
                List<Map<Object, Object>> materielList = this.extensionMaterielService.selectAllRecord();
                // 分页计算
                int total = (page - 1) * limit;
                // 获取所有商家信息
                List<Map<String, Object>> businessAccountList = this.wnkBusinessAccountService.selectBusinessAllExcludeDelete(total,limit,store_name);
                // 遍历商家信息获取剩余物料信息
                for (Map<String, Object> businessAccountMap : businessAccountList){
                    Map<String, Object> addMap = new HashMap<>(16);
                    // 通过商家ID获取到商家名称
                    Integer businessId = (Integer) businessAccountMap.get("id");
                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(businessId);
                    addMap.put("business_name",wnkStoreInformation.getStore_name());
                    if (wnkStoreInformation.getIs_lower() == 0){
                        addMap.put("business_lower_str","启用");
                    } else {
                        addMap.put("business_lower_str","停用");
                    }
                    // 遍历查询商家所有物料信息
                    for (Map<Object,Object> materielMap : materielList){
                        Integer materielId = (Integer) materielMap.get("id");
                        MyMateriel myMateriel = this.myMaterielService.selectByMaterielIdAndBusinessId(businessId,materielId);
                        // 如果商家有此物料则添加对应数量,如果没有则添加为0
                        if (myMateriel == null){
                            addMap.put(materielId.toString(),0);
                        } else {
                            addMap.put(materielId.toString(),myMateriel.getSurplus_number());
                        }
                    }
                    retList.add(addMap);
                }
                // 给返回的总条目数赋值
                result.setCount(businessAccountList.get(0).get("count"));
                result.setData(retList);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");

            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }



}
