package com.springmvc.controller.admin;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.*;
import com.springmvc.utils.qrCode.QRCode;
import net.sf.json.JSONObject;
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

@Controller
@RequestMapping(value = "/admin")
public class WnkBusinessManagerAdminController {
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkBusinessRegisterService wnkBusinessRegisterService;
    @Resource
    private WnkBusinessLevelService wnkBusinessLevelService;
    @Resource
    private WnkBusinessLevelIntegralDetailService wnkBusinessLevelIntegralDetailService;
    @Resource
    private ExtensionMaterielService extensionMaterielService;
    @Resource
    private MyMaterielService myMaterielService;
    @Resource
    private WnkBusinessLegalizeService wnkBusinessLegalizeService;
    @Resource
    private WnkBusinessLabelService wnkBusinessLabelService;
    @Resource
    private WnkBusinessRechargeOrderService wnkBusinessRechargeOrderService;
    @Resource
    private WnkBusinessBalanceDetailService wnkBusinessBalanceDetailService;
    @Resource
    private WnkBusinessTagChooseRecordService wnkBusinessTagChooseRecordService;
    @Resource
    private WnkBusinessTagService wnkBusinessTagService;

    /**
     *
     * 功能描述: 进入万能卡商户管理界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:02
     */
    @RequestMapping(value = "/joinWnkBusinessManager")
    @ResponseBody
    public ModelAndView joinWnkBusinessManager(HttpServletRequest request) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/wnk/wnk_business_manager");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入万能卡已下架商户管理界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:02
     */
    @RequestMapping(value = "/joinWnkLowerBusinessManager")
    @ResponseBody
    public ModelAndView joinWnkLowerBusinessManager(HttpServletRequest request) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/wnk/lower_business");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入特别推荐万能卡商户管理界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:03
     */
    @RequestMapping(value = "/joinEspciallyRecommendWnkBusinessManager")
    @ResponseBody
    public ModelAndView joinEspciallyRecommendWnkBusinessManager(HttpServletRequest request) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/wnk/especially_recommend_business");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入添加商户界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:04
     */
    @RequestMapping(value = "/joinAddWnkBusinessManager")
    @ResponseBody
    public ModelAndView joinAddWnkBusinessManager(HttpServletRequest request) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/wnk/add_wnk_business");
            modelAndView.addObject("type", 0);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入修改商户界面
     *
     * @param   request, business_id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:04
     */
    @RequestMapping(value = "/joinSetWnkBusinessManager", method = RequestMethod.GET, params = {"business_id"})
    @ResponseBody
    public ModelAndView joinSetWnkBusinessManager(HttpServletRequest request, Integer business_id) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/wnk/add_wnk_business");
            modelAndView.addObject("type", 1);
            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
            if (wnkBusinessAccount != null) {
                modelAndView.addObject("account", wnkBusinessAccount.getMobile());
                modelAndView.addObject("login_pwd", wnkBusinessAccount.getLogin_pwd());
                modelAndView.addObject("state", wnkBusinessAccount.getState());
                modelAndView.addObject("business_id", business_id);
                WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                if (wnkStoreInformation != null) {
                    modelAndView.addObject("store_name", wnkStoreInformation.getStore_name());
                    modelAndView.addObject("address", wnkStoreInformation.getAddress());
                    modelAndView.addObject("store_describe", wnkStoreInformation.getStore_describe());
                    modelAndView.addObject("contact_mobile", wnkStoreInformation.getContact_mobile());
                    modelAndView.addObject("area", wnkStoreInformation.getArea());
                    modelAndView.addObject("business_type_id", wnkStoreInformation.getBusiness_type_id());
                }
            }
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述: 获取万能卡商户分类
     *
     * @param   request, response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:04
     */
    @RequestMapping(value = "/getWnkBusinessType", method = RequestMethod.POST)
    @ResponseBody
    public Result getWnkBusinessType(HttpServletRequest request, HttpServletResponse response) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                List<Map<Object, Object>> list = this.wnkBusinessTypeService.selectAllRecord();
                if (list.size() <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无商户分类");
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
     * 功能描述: 获取所有商家信息
     *
     * @param   request, response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:05
     */
    @RequestMapping(value = "/getAllWnkBusiness", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllWnkBusiness(HttpServletRequest request, HttpServletResponse response) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                List<Map<Object, Object>> list = this.wnkBusinessAccountService.selectAllRecord();
                if (list.size() <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无商户");
                } else {

                    for (Integer index = 0; index < list.size(); index++) {
                        Map<Object, Object> map = list.get(index);
                        Integer type_id = (Integer) map.get("business_type_id");
                        WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(type_id);

                        // 获取审核通过时间
                        Map<String, Object> stopTimeMap = this.wnkBusinessLegalizeService.selectStopTimeByBusinessId((Integer) map.get("id"));
                        if (null != stopTimeMap) {
                            map.put("legalize_stop_time", stopTimeMap.get("stop_time_str"));
                        }

                        // 获取商家特色内容和服务内容标签
                        String fuwu = (String) map.get("fuwu_label");
                        String tese = (String) map.get("tese_label");

                        if (fuwu != null) {
                            String[] fuwuArr = fuwu.split(",");
                            StringBuilder fuwu2 = new StringBuilder();

                            for (int i = 0; i < fuwuArr.length; i++) {
                                Map<String, Object> objectMap = this.wnkBusinessLabelService.selectLabel(fuwuArr[i]);
                                if (objectMap != null) {
                                    fuwu2.append(objectMap.get("name")).append(",");
                                }

                            }
                            if (fuwu2.length() > 0) {
                                map.put("fuwu_label", fuwu2.substring(0, fuwu2.length() - 1));
                            }

                        }

                        if (tese != null) {
                            String[] teseArr = tese.split(",");
                            StringBuffer tese2 = new StringBuffer();

                            for (int i = 0; i < teseArr.length; i++) {
                                Map<String, Object> objectMap = this.wnkBusinessLabelService.selectLabel(teseArr[i]);
                                if (objectMap != null) {
                                    tese2.append(objectMap.get("name")).append(",");
                                }

                            }
                            if (tese2.length() > 0) {
                                map.put("tese_label", tese2.substring(0, tese2.length() - 1));
                            }

                        }

                        if (wnkBusinessType == null) {
                            map.put("type_name", "");
                        } else {
                            map.put("type_name", wnkBusinessType.getName());
                        }
                    }
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    /**
     *
     * 功能描述: 获取所有已下架的商家信息
     *
     * @param   request
     * @param   response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:05
     */
    @RequestMapping(value = "/getAllLowerWnkBusiness", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllLowerWnkBusiness(HttpServletRequest request, HttpServletResponse response) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                List<Map<Object, Object>> list = this.wnkBusinessAccountService.selectAllLowerRecord();
                if (list.size() <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无商户");
                } else {

                    for (Integer index = 0; index < list.size(); index++) {
                        Map<Object, Object> map = list.get(index);
                        Integer type_id = (Integer) map.get("business_type_id");
                        WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(type_id);

                        // 获取审核通过时间
                        Map<String, Object> stopTimeMap = this.wnkBusinessLegalizeService.selectStopTimeByBusinessId((Integer) map.get("id"));
                        if (null != stopTimeMap) {
                            map.put("legalize_stop_time", stopTimeMap.get("stop_time_str"));
                        }

                        // 获取商家特色内容和服务内容标签
                        String fuwu = (String) map.get("fuwu_label");
                        String tese = (String) map.get("tese_label");

                        if (fuwu != null) {
                            String[] fuwuArr = fuwu.split(",");
                            StringBuilder fuwu2 = new StringBuilder();

                            for (int i = 0; i < fuwuArr.length; i++) {
                                Map<String, Object> objectMap = this.wnkBusinessLabelService.selectLabel(fuwuArr[i]);
                                if (objectMap != null) {
                                    fuwu2.append(objectMap.get("name")).append(",");
                                }

                            }
                            if (fuwu2.length() > 0) {
                                map.put("fuwu_label", fuwu2.substring(0, fuwu2.length() - 1));
                            }

                        }

                        if (tese != null) {
                            String[] teseArr = tese.split(",");
                            StringBuffer tese2 = new StringBuffer();

                            for (int i = 0; i < teseArr.length; i++) {
                                Map<String, Object> objectMap = this.wnkBusinessLabelService.selectLabel(teseArr[i]);
                                if (objectMap != null) {
                                    tese2.append(objectMap.get("name")).append(",");
                                }

                            }
                            if (tese2.length() > 0) {
                                map.put("tese_label", tese2.substring(0, tese2.length() - 1));
                            }

                        }

                        if (wnkBusinessType == null) {
                            map.put("type_name", "");
                        } else {
                            map.put("type_name", wnkBusinessType.getName());
                        }
                    }
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 根据条件获取商家信息
     *
     * @param   request                     请求
     * @param   response                    响应
     * @param   limit                       分页条目数
     * @param   page                        分页数
     * @param   store_name                  店铺名称
     * @param   contact_mobile              联系电话
     * @param   balance                     余额
     * @param   wnk_business_type           分类
     * @param   wnk_business_store_state    状态
     * @param   type
     * @param   join_time                   加入时间
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/12 0012 16:15
     */
    @RequestMapping(value = "/adminSearchBusinessInfoByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchBusinessInfoByConditions(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      Integer limit,
                                                      Integer page,
                                                      String store_name,
                                                      String contact_mobile,
                                                      Double balance,
                                                      String wnk_business_type,
                                                      String wnk_business_store_state,
                                                      String type,
                                                      String join_time) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> maps = new HashMap<>();
                maps.put("start_index", limit * (page - 1));
                maps.put("limit",limit);
                maps.put("page", page);
                maps.put("type", type);
                if (store_name != null && !"".equals(store_name)) {
                    maps.put("store_name", "%" + store_name + "%");
                }
                if (contact_mobile != null && !"".equals(contact_mobile)) {
                    maps.put("contact_mobile", "%" + contact_mobile + "%");
                }
                if (balance != null && !"".equals(balance)) {
                    maps.put("balance","%"+ balance+"%");
                }
                maps.put("wnk_business_type", wnk_business_type);
                maps.put("wnk_business_store_state", wnk_business_store_state);
                maps.put("join_time",join_time);
                List<Map<Object, Object>> list = this.wnkBusinessAccountService.adminSearchBusinessInfoByConditions(maps);
                if (list.size() <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无商户");
                } else {

                    for (Integer index = 0; index < list.size(); index++) {
                        Map<Object, Object> map = list.get(index);
                        Integer type_id = (Integer) map.get("business_type_id");
                        WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(type_id);

                        // 获取审核通过时间
                        Map<String, Object> stopTimeMap = this.wnkBusinessLegalizeService.selectStopTimeByBusinessId((Integer) map.get("id"));
                        if (null != stopTimeMap) {
                            map.put("legalize_stop_time", stopTimeMap.get("stop_time_str"));
                        }

                        // 获取商家特色内容和服务内容标签
                        String fuwu = (String) map.get("fuwu_label");
                        String tese = (String) map.get("tese_label");

                        if (fuwu != null) {
                            String[] fuwuArr = fuwu.split(",");
                            StringBuilder fuwu2 = new StringBuilder();

                            for (int i = 0; i < fuwuArr.length; i++) {
                                Map<String, Object> objectMap = this.wnkBusinessLabelService.selectLabel(fuwuArr[i]);
                                if (objectMap != null) {
                                    fuwu2.append(objectMap.get("name")).append(",");
                                }

                            }
                            if (fuwu2.length() > 0) {
                                map.put("fuwu_label", fuwu2.substring(0, fuwu2.length() - 1));
                            }

                        }

                        if (tese != null) {
                            String[] teseArr = tese.split(",");
                            StringBuffer tese2 = new StringBuffer();

                            for (int i = 0; i < teseArr.length; i++) {
                                Map<String, Object> objectMap = this.wnkBusinessLabelService.selectLabel(teseArr[i]);
                                if (objectMap != null) {
                                    tese2.append(objectMap.get("name")).append(",");
                                }

                            }
                            if (tese2.length() > 0) {
                                map.put("tese_label", tese2.substring(0, tese2.length() - 1));
                            }

                        }

                        if (wnkBusinessType == null) {
                            map.put("type_name", "");
                        } else {
                            map.put("type_name", wnkBusinessType.getName());
                        }
                    }
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }


    /**
     *
     * 功能描述: 获取所有特别推荐商户信息
     *
     * @param   request, response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:08
     */
    @RequestMapping(value = "/getAllEspeciallyRecommendWnkBusiness", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllEspeciallyRecommendWnkBusiness(HttpServletRequest request, HttpServletResponse response) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                List<Map<Object, Object>> list = this.wnkBusinessAccountService.selectAllEspciallyRecommendRecord();
                if (list.size() <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无商户");
                } else {

                    for (Integer index = 0; index < list.size(); index++) {
                        Map<Object, Object> map = list.get(index);
                        Integer type_id = (Integer) map.get("business_type_id");
                        WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(type_id);

                        // 获取商家特色内容和服务内容标签
                        String fuwu = (String) map.get("fuwu_label");
                        String tese = (String) map.get("tese_label");

                        if (fuwu != null) {
                            String[] fuwuArr = fuwu.split(",");
                            StringBuilder fuwu2 = new StringBuilder();

                            for (int i = 0; i < fuwuArr.length; i++) {
                                Map<String, Object> objectMap = this.wnkBusinessLabelService.selectLabel(fuwuArr[i]);
                                if (objectMap != null) {
                                    fuwu2.append(objectMap.get("name")).append(",");
                                }

                            }
                            if (fuwu2.length() > 0) {
                                map.put("fuwu_label", fuwu2.substring(0, fuwu2.length() - 1));
                            }

                        }

                        if (tese != null) {
                            String[] teseArr = tese.split(",");
                            StringBuffer tese2 = new StringBuffer();

                            for (int i = 0; i < teseArr.length; i++) {
                                Map<String, Object> objectMap = this.wnkBusinessLabelService.selectLabel(teseArr[i]);
                                if (objectMap != null) {
                                    tese2.append(objectMap.get("name")).append(",");
                                }

                            }
                            if (tese2.length() > 0) {
                                map.put("tese_label", tese2.substring(0, tese2.length() - 1));
                            }

                        }

                        if (wnkBusinessType == null) {
                            map.put("type_name", "");
                        } else {
                            map.put("type_name", wnkBusinessType.getName());
                        }
                    }
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 根据条件查询特别推荐商户信息
     *
     * @param   request, response, limit, page, store_name, contact_mobile, wnk_business_type, state_str, balance, join_time, type
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/14 0014 14:58
     */
    @RequestMapping(value = "/adminSearchEspeciallyRecommendWnkBusiness", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchEspeciallyRecommendWnkBusiness(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      Integer limit,
                                                      Integer page,
                                                      String store_name,
                                                      String contact_mobile,
                                                      String wnk_business_type,
                                                      String state_str,
                                                      Double balance,
                                                      String join_time,
                                                      String type
    ) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> maps = new HashMap<>();
                maps.put("statrt_index", limit * (page - 1));
                maps.put("limit", limit);
                maps.put("page", page);
                maps.put("type", type);
                if (store_name != null && !"".equals(store_name)) {
                    maps.put("store_name", "%" + store_name + "%");
                }
                if (contact_mobile != null && !"".equals(contact_mobile)) {
                    maps.put("contact_mobile", "%" + contact_mobile + "%");
                }
                maps.put("wnk_business_type", wnk_business_type);
                maps.put("state_str", ("".equals(state_str) ? null : state_str) );
                if (balance != null && !"".equals(balance)) {
                    maps.put("balance", balance);
                }
                maps.put("join_time", join_time);
                List<Map<Object, Object>> list = this.wnkBusinessAccountService.adminSearchEspeciallyRecommendWnkBusiness(maps);
                if (list.size() <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无商户");
                } else {

                    for (Integer index = 0; index < list.size(); index++) {
                        Map<Object, Object> map = list.get(index);
                        Integer type_id = (Integer) map.get("business_type_id");
                        WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(type_id);


                        // 获取商家特色内容和服务内容标签
                        String fuwu = (String) map.get("fuwu_label");
                        String tese = (String) map.get("tese_label");

                        if (fuwu != null) {
                            String[] fuwuArr = fuwu.split(",");
                            StringBuilder fuwu2 = new StringBuilder();

                            for (int i = 0; i < fuwuArr.length; i++) {
                                Map<String, Object> objectMap = this.wnkBusinessLabelService.selectLabel(fuwuArr[i]);
                                if (objectMap != null) {
                                    fuwu2.append(objectMap.get("name")).append(",");
                                }

                            }
                            if (fuwu2.length() > 0) {
                                map.put("fuwu_label", fuwu2.substring(0, fuwu2.length() - 1));
                            }

                        }

                        if (tese != null) {
                            String[] teseArr = tese.split(",");
                            StringBuffer tese2 = new StringBuffer();

                            for (int i = 0; i < teseArr.length; i++) {
                                Map<String, Object> objectMap = this.wnkBusinessLabelService.selectLabel(teseArr[i]);
                                if (objectMap != null) {
                                    tese2.append(objectMap.get("name")).append(",");
                                }

                            }
                            if (tese2.length() > 0) {
                                map.put("tese_label", tese2.substring(0, tese2.length() - 1));
                            }

                        }

                        if (wnkBusinessType == null) {
                            map.put("type_name", "");
                        } else {
                            map.put("type_name", wnkBusinessType.getName());
                        }
                    }
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     * 功能描述: 商户特别推荐/取消特别推荐
     *
     * @param business_id 商户id
     * @param type        推荐状态(0-不进行特别推荐,1-进行特别推荐)
     * @return:
     * @author: zhangfan
     * @date: 2018/11/17 9:49 PM
     */
    @RequestMapping(value = "/cancelBusinessEspciallyRecommend", method = RequestMethod.POST, params = {"business_id", "type"})
    @ResponseBody
    public Result cancelBusinessEspciallyRecommend(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer type) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else if (type != 0 && type != 1) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            } else {
                WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                if (wnkStoreInformation == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商户不存在");
                } else {
                    //是否可继续操作(-1为不可继续,0为可继续)
                    Integer isOption = 0;
                    if (type == 1) {
                        List<Map<Object, Object>> list = this.wnkBusinessAccountService.selectAllEspciallyRecommendRecord();
                        if (list.size() >= 4) {
                            isOption = -1;
                        } else {
                            isOption = 0;
                        }
                    }
                    if (isOption == -1) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("特别推荐商户仅可设置4个，目前已有4个");
                    } else {
                        wnkStoreInformation.setEspecially_recommend_state(type);
                        int updateState = this.wnkStoreInformationService.updateBusinessEspeciallyRecommendState(wnkStoreInformation);
                        if (updateState > 0) {
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("操作成功");
                        } else {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("操作失败");
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
     * 功能描述: 添加商户信息
     *
     * @param   request, response, account, login_pwd, state, business_type_id, store_name, address, store_describe, contact_mobile, area, lunbo, recommend_id
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:09
     */
    @RequestMapping(value = "/addWnkBusiness", method = RequestMethod.POST, params = {"account", "login_pwd", "state", "business_type_id", "store_name", "address", "store_describe", "contact_mobile", "area", "lunbo", "recommend_id"})
    @ResponseBody
    public Result addWnkBusiness(HttpServletRequest request, HttpServletResponse response, String account, String login_pwd, Integer state, Integer business_type_id, String store_name, String address, String store_describe, String contact_mobile, String area, String lunbo, Integer recommend_id) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectByMobile(account);
                if (wnkBusinessAccount != null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商户账号已存在");
                } else {
                    wnkBusinessAccount = new WnkBusinessAccount();
                    wnkBusinessAccount.setMobile(account);
                    wnkBusinessAccount.setLogin_pwd(login_pwd);
                    wnkBusinessAccount.setJoin_time(new Date());
                    wnkBusinessAccount.setState(state);
                    int addBusinessState = this.wnkBusinessAccountService.insertBusiness(wnkBusinessAccount);
                    if (addBusinessState <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("创建账号失败");
                    } else {
                        WnkStoreInformation wnkStoreInformation = new WnkStoreInformation();
                        wnkStoreInformation.setBusiness_id(wnkBusinessAccount.getId());
                        wnkStoreInformation.setStore_name(store_name);
                        wnkStoreInformation.setAddress(address);
                        wnkStoreInformation.setStore_describe(store_describe);
                        wnkStoreInformation.setContact_mobile(contact_mobile);
                        wnkStoreInformation.setBusiness_type_id(business_type_id);
                        wnkStoreInformation.setState(state);
                        wnkStoreInformation.setArea(area);
                        wnkStoreInformation.setBanners_photoid(lunbo);
                        wnkStoreInformation.setRecommend_business_id(recommend_id);

                        //商户推广二维码
                        String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                        String wnkQrcodeUrl = QRCode.generateQRCode("http://m.chenlankeji.cn/property_system/wnk_business/joinBusinessRecommendRegister?business_id=" + wnkBusinessAccount.getId(), wnkQrcodeName);
                        wnkStoreInformation.setRecommend_qr_code(wnkQrcodeName + ".png");

                        //商户支付二维码
                        String wnkQrcodeNamePay = UUDUtil.getOrderIdByUUId();
                        Map<Object, Object> orderQrcodeMap = new HashMap<Object, Object>();
                        orderQrcodeMap.put("business_id", wnkBusinessAccount.getId());
                        JSONObject json = JSONObject.fromObject(orderQrcodeMap);
                        String wnkPayQrcodeName = UUDUtil.getOrderIdByUUId();
                        String wnkQrcodeUrlPay = QRCode.generateQRCode(json.toString(), wnkPayQrcodeName);
                        wnkStoreInformation.setPay_qr_code(wnkPayQrcodeName + ".png");

                        WnkBusinessLevel wnkBusinessLevel = this.wnkBusinessLevelService.selectDefaultLevel();
                        if (wnkBusinessLevel != null) {
                            wnkStoreInformation.setBusiness_level_id(wnkBusinessLevel.getId());
                        }
                        int addStoreState = this.wnkStoreInformationService.insertBusinessInformation(wnkStoreInformation);
                        if (addStoreState <= 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("添加商户信息失败");
                        } else {
                            // 添加未认证信息
                            WnkBusinessLegalize wnkBusinessLegalize = new WnkBusinessLegalize();
                            wnkBusinessLegalize.setBusiness_id(wnkBusinessAccount.getId());
                            // 未上传认证信息 认证状态 -1:未上传认证信息 0 : 未认证 1:认证
                            wnkBusinessLegalize.setLegalize_status(-1);
                            wnkBusinessLegalize.setPhone(wnkBusinessAccount.getMobile());
                            if (this.wnkBusinessLegalizeService.insertLegalizeInfo(wnkBusinessLegalize) > 0) {
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("添加成功");
                                //赠送1000积分给商户
                                WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail = new WnkBusinessLevelIntegralDetail();
                                wnkBusinessLevelIntegralDetail.setBusiness_id(wnkBusinessAccount.getId());
                                wnkBusinessLevelIntegralDetail.setName("系统赠送积分");
                                wnkBusinessLevelIntegralDetail.setIntegral_number(1000.00);
                                wnkBusinessLevelIntegralDetail.setTransactions_date(new Date());
                                wnkBusinessLevelIntegralDetail.setTransactions_type(0);
                                wnkBusinessAccount.setLevel_integral(wnkBusinessLevelIntegralDetail.getIntegral_number());
                                wnkBusinessAccount.setBalance(0.00);
                                wnkBusinessAccount.setConsumption_integral(0);
                                wnkBusinessAccount.setRose_number(0);
                                int updateBusinessIntegralState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                                if (updateBusinessIntegralState > 0) {
                                    this.wnkBusinessLevelIntegralDetailService.insertNewRecord(wnkBusinessLevelIntegralDetail);
                                }
                                //赠送以及5张银卡给商户
                                ExtensionMateriel extensionMateriel = this.extensionMaterielService.selectWnkInformation();
                                if (extensionMateriel != null) {
                                    MyMateriel myMateriel = new MyMateriel();
                                    myMateriel.setBusiness_id(wnkBusinessAccount.getId());
                                    myMateriel.setMateriel_id(extensionMateriel.getId());
                                    myMateriel.setSurplus_number(2);
                                    this.myMaterielService.insertMaterielBuyRecord(myMateriel);
                                }
                                MobileCodeUtil.sendAccountRegisterPassSms(account, login_pwd);

                                if (recommend_id != -1) {
                                    WnkStoreInformation wnkStoreInformationRecommend = this.wnkStoreInformationService.selectByBusinessId(recommend_id);
                                    if (wnkStoreInformationRecommend != null) {
                                        if (wnkStoreInformationRecommend.getGetui_appid() != null && !wnkStoreInformationRecommend.getGetui_appid().equals("")) {
                                            String pushTitle = "团队新成员加入通知";
                                            String pushContent = wnkStoreInformation.getStore_name() + "注册为商家，您的团队又增加一位成员啦！";
                                            GeTuiBusinessUtil.pushUser(wnkStoreInformationRecommend.getGetui_appid(), pushTitle, pushContent);
                                        }
                                    }
                                }
                            } else {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("添加未认证信息失败");
                            }

                        }
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 修改商户信息
     *
     * @param   request, business_id, account, login_pwd, state, business_type_id, store_name, address, store_describe, contact_mobile, area, business_tag_one, business_tag_two
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:09
     */
    @RequestMapping(value = "/setWnkBusiness", method = RequestMethod.POST, params = {"business_id", "account", "login_pwd", "state", "business_type_id", "store_name", "address", "store_describe", "contact_mobile", "area", "business_tag_one", "business_tag_two"})
    @ResponseBody
    public Result setWnkBusiness(HttpServletRequest request, Integer business_id, String account, String login_pwd, Integer state, Integer business_type_id, String store_name, String address, String store_describe, String contact_mobile, String area, Integer business_tag_one, Integer business_tag_two) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商户不已存在");
                } else {
                    WnkBusinessAccount wnkBusinessAccount1 = this.wnkBusinessAccountService.selectByMobile(account);
                    if (wnkBusinessAccount1.getId() != wnkBusinessAccount.getId() && account.equals(wnkBusinessAccount1.getMobile())) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此账号已存在");
                    } else {
                        wnkBusinessAccount.setMobile(account);
                        wnkBusinessAccount.setLogin_pwd(login_pwd);
                        wnkBusinessAccount.setState(state);
                        int updateBusinessState = this.wnkBusinessAccountService.updateAccountInformation(wnkBusinessAccount);
                        if (updateBusinessState <= 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("更新账号失败");
                        } else {
                            WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                            wnkStoreInformation.setStore_name(store_name);
                            wnkStoreInformation.setAddress(address);
                            wnkStoreInformation.setStore_describe(store_describe);
                            wnkStoreInformation.setContact_mobile(contact_mobile);
                            wnkStoreInformation.setBusiness_type_id(business_type_id);
                            wnkStoreInformation.setState(state);
                            wnkStoreInformation.setArea(area);
                            int updateStoreState = this.wnkStoreInformationService.adminUpdateStoreInformation(wnkStoreInformation);
                            if (updateStoreState <= 0) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("更新商户信息失败");
                            } else {
//                                // 查询商户类型关联的商户标签ID (商户一级标签ID)
//                                WnkBusinessTag wnkBusinessTag = this.wnkBusinessTagService.selectByBusinessTypeId(wnkStoreInformation.getBusiness_type_id());
//                                if (wnkBusinessTag != null) {
//                                    // 删除原来的商户标签
//                                    this.wnkBusinessTagChooseRecordService.deleteBusinessOneTag(business_id);
//                                    // 新增当前的ID
//                                    this.wnkBusinessTagChooseRecordService.insertBusinessTag(business_id, wnkBusinessTag.getId(), -1);
//                                    result.setData("");
//                                    result.setStatus(Result.SUCCESS);
//                                    result.setMsg("更新成功");
//                                } else {
//                                    // 删除原来的商户标签
//                                    this.wnkBusinessTagChooseRecordService.deleteBusinessOneTag(business_id);
//                                    result.setData("");
//                                    result.setStatus(Result.SUCCESS);
//                                    result.setMsg("更新成功");
//                                }
                                if (business_tag_one == null) {
                                    // 删除原来的商户标签
                                    this.wnkBusinessTagChooseRecordService.deleteBusinessOneTag(business_id);
                                    result.setData("");
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("更新成功");
                                } else {
                                    // 删除原来的商户标签
                                    this.wnkBusinessTagChooseRecordService.deleteBusinessOneTag(business_id);
                                    if (business_tag_two == null){
                                        business_tag_two = -1;
                                    }
                                    // 新增当前的ID
                                    this.wnkBusinessTagChooseRecordService.insertBusinessTag(business_id, business_tag_one, business_tag_two);
                                    result.setData("");
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("更新成功");
                                }


                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     * 功能描述: 上架/下架商户
     *
     * @param business_id 商家ID
     * @param is_lower    是否已下架(0-未下架,1-已下架)
     * @return:
     * @author: zhangfan
     * @date: 2018/12/20 9:14 PM
     */
    @RequestMapping(value = "/lowerBusinessStore", method = RequestMethod.POST, params = {"business_id", "is_lower"})
    @ResponseBody
    public Result lowerBusinessStore(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer is_lower) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商户不已存在");
                } else {
                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                    if (wnkStoreInformation == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商户信息不存在");
                    } else {
                        String resultStr = "";
                        if (is_lower == 0) {
                            resultStr = "上架";
                        } else {
                            resultStr = "下架";
                        }
                        Integer updateState = this.wnkStoreInformationService.upperOrLowerBusiness(is_lower, business_id);
                        if (updateState <= 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg(resultStr + "失败");
                        } else {
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg(resultStr + "成功");
                        }
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 获取所有待审核商家信息
     *
     * @param   request, response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:09
     */
    @RequestMapping(value = "/getAllNoAuditedWnkBusiness", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllNoAuditedWnkBusiness(HttpServletRequest request, HttpServletResponse response) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                List<Map<Object, Object>> list = this.wnkBusinessRegisterService.getAllNoAuditedBusiness();
                if (list.size() <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无申请");
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
     * 功能描述: 根据条件查询待审核商家信息
     *
     * @param   request             请求
     * @param   response            响应
     * @param   limit               分页条目数
     * @param   page                分页总数
     * @param   store_name          店铺名称
     * @param   wnk_business_type   店铺分类
     * @param   contact_name        联系人
     * @param   contact_mobile      联系电话
     * @param   state_str           审核状态
     * @param   submit_date_str     申请时间
     * @param   type
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/12 0012 16:34
     */
    @RequestMapping(value = "/adminSearchNoAuditedWnkBusiness", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchNoAuditedWnkBusiness(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      Integer limit,
                                                      Integer page,
                                                      String store_name,
                                                      String wnk_business_type,
                                                      String contact_name,
                                                      String contact_mobile,
                                                      Integer state_str,
                                                      String submit_date_str,
                                                      String type
    ) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Map<String, Object> maps = new HashMap<>();
                maps.put("statrt_index", limit * (page - 1));
                maps.put("limit", limit);
                maps.put("page", page);
                maps.put("type", type);
                if (store_name != null && !"".equals(store_name)) {
                    maps.put("store_name", "%" + store_name + "%");
                }
                if (contact_name != null && !"".equals(contact_name)) {
                    maps.put("contact_name", "%" + contact_name + "%");
                }
                if (contact_mobile != null && !"".equals(contact_mobile)) {
                    maps.put("contact_mobile", contact_mobile);
                }
                maps.put("wnk_business_type", ("".equals(wnk_business_type) ? null : wnk_business_type) );
                maps.put("state_str",("".equals(state_str) ? null : state_str));
                maps.put("submit_date_str", submit_date_str);
                List<Map<Object, Object>> list = this.wnkBusinessRegisterService.adminSearchNoAuditedWnkBusiness(maps);
                if (list.size() <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无商户");
                } else {

                    for (Integer index = 0; index < list.size(); index++) {
                        Map<Object, Object> map = list.get(index);
                        Integer type_id = (Integer) map.get("business_type_id");
                        WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(type_id);

                        if (wnkBusinessType == null) {
                            map.put("type_name", "");
                        } else {
                            map.put("type_name", wnkBusinessType.getName());
                        }
                    }
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 进入待审核商户页面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:12
     */
    @RequestMapping(value = "/joinNoAuditedManager")
    @ResponseBody
    public ModelAndView joinNoAuditedManager(HttpServletRequest request) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/wnk/no_audited_wnk_business_manager");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入待审核详情页面
     *
     * @param   request, record_id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:12
     */
    @RequestMapping(value = "/joinNoAuditedDetail", method = RequestMethod.GET, params = {"record_id"})
    @ResponseBody
    public ModelAndView joinNoAuditedDetail(HttpServletRequest request, Integer record_id) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/wnk/audited_detail");
            modelAndView.addObject("record_id", record_id);
            WnkBusinessRegister wnkBusinessRegister = this.wnkBusinessRegisterService.selectById(record_id);
            if (wnkBusinessRegister == null) {
                modelAndView.addObject("store_name", "");
                modelAndView.addObject("area", "");
                modelAndView.addObject("address", "");
                modelAndView.addObject("login_account", "");
                modelAndView.addObject("contact_name", "");
                modelAndView.addObject("contact_mobile", "");
                modelAndView.addObject("store_describe", "");
                modelAndView.addObject("yingyezhizhao", "");
                modelAndView.addObject("type_id", "");
                modelAndView.addObject("lunbo", "");
                modelAndView.addObject("recommend_id", "");
                modelAndView.addObject("legal_person_id_card", "");
            } else {
                modelAndView.addObject("store_name", wnkBusinessRegister.getStore_name());
                modelAndView.addObject("area", wnkBusinessRegister.getArea());
                modelAndView.addObject("address", wnkBusinessRegister.getAddress());
                modelAndView.addObject("login_account", wnkBusinessRegister.getLogin_account());
                modelAndView.addObject("contact_name", wnkBusinessRegister.getContact_name());
                modelAndView.addObject("contact_mobile", wnkBusinessRegister.getContact_mobile());
                modelAndView.addObject("store_describe", wnkBusinessRegister.getMiaoshu());
                modelAndView.addObject("yingyezhizhao", ImageToolsController.imageShowURL + "?imageid=" + wnkBusinessRegister.getYingye_zhizhao_photo());
                modelAndView.addObject("type_id", wnkBusinessRegister.getType_id());
                modelAndView.addObject("lunbo", wnkBusinessRegister.getMentou_photo());
                modelAndView.addObject("recommend_id", wnkBusinessRegister.getRecommend_business_id());
                modelAndView.addObject("legal_person_id_card", wnkBusinessRegister.getLegal_person_id_card());
            }
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 更新万能卡商家申请信息
     *
     * @param   request, response, record_id, store_name, type_id, area, address, login_account, contact_name, contact_mobile, miaoshu, state, reason
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:12
     */
    @RequestMapping(value = "/updateWnkBusinessAuditedInformation", method = RequestMethod.POST, params = {"record_id", "store_name", "type_id", "area", "address", "login_account", "contact_name", "contact_mobile", "miaoshu", "state", "reason"})
    @ResponseBody
    public Result updateWnkBusinessAuditedInformation(HttpServletRequest request, HttpServletResponse response, Integer record_id, String store_name, Integer type_id, String area, String address, String login_account, String contact_name, String contact_mobile, String miaoshu, Integer state, String reason) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                WnkBusinessRegister wnkBusinessRegister = this.wnkBusinessRegisterService.selectById(record_id);
                if (wnkBusinessRegister == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("记录不存在");
                } else {
                    if (wnkBusinessRegister.getState() != 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此记录已审核,请勿重复操作");
                    } else {
                        if (state != 2) {
                            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectByMobile(login_account);
                            if (wnkBusinessAccount != null) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("此账号已存在");
                                return result;
                            }
                        }


                        wnkBusinessRegister.setStore_name(store_name);
                        wnkBusinessRegister.setType_id(type_id);
                        wnkBusinessRegister.setArea(area);
                        wnkBusinessRegister.setAddress(address);
                        wnkBusinessRegister.setLogin_account(login_account);
                        wnkBusinessRegister.setContact_name(contact_name);
                        wnkBusinessRegister.setContact_mobile(contact_mobile);
                        wnkBusinessRegister.setMiaoshu(miaoshu);
                        wnkBusinessRegister.setState(state);
                        wnkBusinessRegister.setId(record_id);
                        int updateState = this.wnkBusinessRegisterService.updateInformation(wnkBusinessRegister);
                        if (updateState <= 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("操作失败");
                        } else {
                            // 更新到认证表

                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("操作成功");
                            if (state == 2) {
                                MobileCodeUtil.sendAccountRegisterNoPassSms(login_account, reason);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 进入商户充值
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:13
     */
    @RequestMapping(value = "/joinWnkBusinessRecharge", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinWnkBusinessRecharge(HttpServletRequest request) {

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/wnk/wnk_business_recharge");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 商户线下充值
     *
     * @param   request, response, mobile, amount, type
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/14 0014 10:13
     */
    @RequestMapping(value = "/BusinessRechargeAction", method = RequestMethod.POST)
    @ResponseBody
    public Result integralRechargeAction(HttpServletRequest request, HttpServletResponse response, String mobile, Double amount, Integer type) {

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
                    result.setMsg("此手机号不存在");
                } else {
                    // 获取商家信息
                    wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkBusinessAccount.getId());
                    if (type == 0) { // 余额充值
                        // 创建充值订单记录
                        WnkBusinessRechargeOrder wnkBusinessRechargeOrder = new WnkBusinessRechargeOrder();
                        wnkBusinessRechargeOrder.setState(1);
                        wnkBusinessRechargeOrder.setOrder_no(UUDUtil.getOrderIdByUUId());
                        wnkBusinessRechargeOrder.setBusiness_id(wnkBusinessAccount.getId());
                        wnkBusinessRechargeOrder.setCreate_time(new Date());
                        wnkBusinessRechargeOrder.setAmount(amount);
                        // 插入充值订单记录
                        int rechargeState = this.wnkBusinessRechargeOrderService.insertBusinessRechargeOrder(wnkBusinessRechargeOrder);
                        if (rechargeState <= 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("充值失败");
                        } else {
                            // 插入商家余额收入记录
                            WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                            wnkBusinessBalanceDetail.setBusiness_id(wnkBusinessAccount.getId());
                            wnkBusinessBalanceDetail.setName("线下充值");
                            wnkBusinessBalanceDetail.setTransaction_amount(amount);
                            wnkBusinessBalanceDetail.setJoin_time(new Date());
                            wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance() + amount);
                            wnkBusinessBalanceDetail.setType(0);
                            wnkBusinessBalanceDetail.setState(0);
                            int i = wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);
                            if (i <= 0) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("充值失败");
                            } else {
                                // 更新商户余额
                                wnkBusinessAccount.setBalance(wnkBusinessAccount.getBalance() + amount);
                                int updateBalanceState = wnkBusinessAccountService.updateBalance(wnkBusinessAccount.getId(), wnkBusinessAccount.getBalance());
                                if (updateBalanceState <= 0) {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("充值失败");
                                } else {
                                    result.setData("");
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("充值成功,获得" + amount + "余额");
                                }
                            }
                        }

                    } else { // 等级积分
                        // 创建商家充值订单记录
                        WnkBusinessRechargeOrder wnkBusinessRechargeOrder = new WnkBusinessRechargeOrder();
                        wnkBusinessRechargeOrder.setState(1);
                        wnkBusinessRechargeOrder.setOrder_no(UUDUtil.getOrderIdByUUId());
                        wnkBusinessRechargeOrder.setBusiness_id(wnkBusinessAccount.getId());
                        wnkBusinessRechargeOrder.setCreate_time(new Date());
                        wnkBusinessRechargeOrder.setAmount(amount);
                        // 插入充值订单记录
                        int rechargeState = this.wnkBusinessRechargeOrderService.insertBusinessRechargeOrder(wnkBusinessRechargeOrder);
                        if (rechargeState <= 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("充值失败");
                        } else {
                            // 插入商家等级积分收入记录
                            WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail = new WnkBusinessLevelIntegralDetail();
                            wnkBusinessLevelIntegralDetail.setBusiness_id(wnkBusinessAccount.getId());
                            wnkBusinessLevelIntegralDetail.setName("线下充值");
                            wnkBusinessLevelIntegralDetail.setIntegral_number(amount);
                            wnkBusinessLevelIntegralDetail.setTransactions_date(new Date());
                            wnkBusinessLevelIntegralDetail.setTransactions_type(0);
                            wnkBusinessLevelIntegralDetail.setUser_id(-1);
                            int i = wnkBusinessLevelIntegralDetailService.insertNewRecord(wnkBusinessLevelIntegralDetail);
                            if (i <= 0) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("充值失败");
                            } else {
                                // 更新商户等级积分
                                int updateBalanceState = wnkBusinessAccountService.updateBusinessLevelIntegral(wnkBusinessAccount.getLevel_integral() + amount, wnkBusinessAccount.getId());
                                if (updateBalanceState <= 0) {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("充值失败");
                                } else {
                                    result.setData("");
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("充值成功,获得" + amount + "等级积分");
                                }
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
}
