package com.springmvc.controller.admin.legalize;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.CookileUtil;
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
 *
 * 功能描述:     万能卡商户认证管理员操作端
 * @Author:     刘武祥
 * @CreateDate: 2019/2/15 0015 12:08
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminBusinessLegalizeController {

    @Resource
    private AdminsService adminsService;

    @Resource
    private WnkBusinessLegalizeService wnkBusinessLegalizeService;

    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;


    /**
     * 功能描述: 进入商户认证管理员操作界面
     *
     * @param request
     * @return
     * @author 杨新杰
     * @date 2018/11/10 16:02
     */
    @RequestMapping(value = "/joinBusinessLegalize", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView adminsManager(HttpServletRequest request) {
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/legalize/legalize");
        }
        return modelAndView;
    }


    /**
     * 功能描述: 进入商户认证管理员操作界面(已审核记录)
     *
     * @param request
     * @return
     * @author 杨新杰
     * @date 2018/11/10 16:02
     */
    @RequestMapping(value = "/joinBusinessAuditedLegalize", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinBusinessAuditedLegalize(HttpServletRequest request) {
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/legalize/audited_legalize");
        }
        return modelAndView;
    }

    /**
     * 功能描述: 进入商户认证管理员操作界面(已审核记录)
     *
     * @param request
     * @return
     * @author 杨新杰
     * @date 2018/11/10 16:02
     */
    @RequestMapping(value = "/adminJoinBusinessLegalizeInfo", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView adminShowBusinessLegalizeInfo(HttpServletRequest request,Integer business_id) {
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/legalize/legalize_info");
            modelAndView.addObject("business_id",business_id);
        }
        return modelAndView;
    }


    /**
     * 功能描述:获取所有非认证通过状态的商家
     *
     * @param request   请求
     * @param response  响应
     * @return 返回所有非认证的商家
     * @author 杨新杰
     * @date 2018/11/10 16:11
     */
    @RequestMapping(value = "/getWnkBusinessLegalizeAll", method = RequestMethod.POST)
    @ResponseBody
    public Result getWnkBusinessLegalizeAll(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Admins admins = this.adminsService.selectById(Integer.parseInt(userId));
                if (admins == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("管理员不存在");
                } else {
                    List<Map<String, Object>> list = this.wnkBusinessLegalizeService.selectBusinessLegalizeAll();
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("菜单查询失败");
        }
        return result;
    }

    /**
     * 功能描述:获取所有已审核认证的商家
     *
     * @param request   请求
     * @param response  响应
     * @return 返回所有已审核认证的商家
     * @author 杨新杰
     * @date 2018/11/10 16:11
     */
    @RequestMapping(value = "/getWnkBusinessAuditedLegalizeAll", method = RequestMethod.POST)
    @ResponseBody
    public Result getWnkBusinessAuditedLegalizeAll(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Admins admins = this.adminsService.selectById(Integer.parseInt(userId));
                if (admins == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("管理员不存在");
                } else {
                    List<Map<String, Object>> list = this.wnkBusinessLegalizeService.selectBusinessAuditedLegalizeAll();
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
     * 功能描述:通过条件查询符合条件的待审核认证商家信息
     *
     * @param   request             请求
     * @param   response            响应
     * @param   limit               分页条目数
     * @param   page                分页数
     * @param   business_id         商家id
     * @param   store_name          店铺名称
     * @param   phone               法人联系电话
     * @param   start_time          申请时间
     * @param   wnk_business_type   店铺分类
     * @param   legalize_status     审核情况
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/4 0004 17:44
     */
    @RequestMapping(value = "/adminSearchWnkBusinessLegalizeInfoByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchWnkBusinessLegalizeInfoByConditions(HttpServletRequest request,
                                                                        HttpServletResponse response,
                                                                        Integer limit,
                                                                        Integer page,
                                                                        Integer business_id,
                                                                        String store_name,
                                                                        String phone,
                                                                        String start_time,
                                                                        String wnk_business_type,
                                                                        Integer legalize_status) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Admins admins = this.adminsService.selectById(Integer.parseInt(userId));
                if (admins == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("管理员不存在");
                } else {
                    Map<String, Object> maps = new HashMap<>();
                    maps.put("statrt_index", limit * (page - 1));
                    maps.put("limit",limit);
                    maps.put("page", page);
                    if (business_id != null && !"".equals(business_id)) {
                        maps.put("business_id", business_id);
                    }
                    if (store_name != null && !"".equals(store_name)) {
                        maps.put("store_name", "%" + store_name + "%");
                    }
                    if (phone != null && !"".equals(phone)) {
                        maps.put("phone", phone );
                    }
                    maps.put("start_time", start_time);
                    maps.put("wnk_business_type",wnk_business_type);
                    maps.put("legalize_status", ("".equals(legalize_status) ? null : legalize_status) );
                    List<Map<String, Object>> list = this.wnkBusinessLegalizeService.adminSearchWnkBusinessLegalizeInfoByConditions(maps);
                    if (list.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("无数据");
                    } else {
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("菜单查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述:通过条件查询符合条件的已审核认证商家信息
     *
     * @param   request             请求
     * @param   response            响应
     * @param   limit               分页条目数
     * @param   page                分页数
     * @param   business_id         商家id
     * @param   store_name          店铺名称
     * @param   phone               法人联系电话
     * @param   stop_time           审核时间
     * @param   wnk_business_type   店铺分类
     * @param   legalize_status     审核情况
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/4 0004 17:44
     */
    @RequestMapping(value = "/adminSearchWnkBusinessAuditedLegalizeInfoByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchWnkBusinessAuditedLegalizeInfoByConditions(HttpServletRequest request,
                                                                 HttpServletResponse response,
                                                                 Integer limit,
                                                                 Integer page,
                                                                 Integer business_id,
                                                                 String store_name,
                                                                 String phone,
                                                                 String stop_time,
                                                                 String wnk_business_type,
                                                                 Integer legalize_status) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Admins admins = this.adminsService.selectById(Integer.parseInt(userId));
                if (admins == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("管理员不存在");
                } else {
                    Map<String, Object> maps = new HashMap<>();
                    maps.put("statrt_index", limit * (page - 1));
                    maps.put("limit",limit);
                    maps.put("page", page);
                    if (business_id != null && !"".equals(business_id)) {
                        maps.put("business_id", business_id);
                    }
                    if (store_name != null && !"".equals(store_name)) {
                        maps.put("store_name", "%" + store_name + "%");
                    }
                    if (phone != null && !"".equals(phone)) {
                        maps.put("phone", phone );
                    }
                    maps.put("stop_time", stop_time);
                    maps.put("wnk_business_type",wnk_business_type);
                    maps.put("legalize_status", ("".equals(legalize_status) ? null : legalize_status) );
                    List<Map<String, Object>> list = this.wnkBusinessLegalizeService.adminSearchWnkBusinessAuditedLegalizeInfoByConditions(maps);
                    if (list.size() <= 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("无数据");
                    } else {
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("菜单查询失败");
        }
        return result;
    }

    /**
     * 功能描述:更新用户认证状态
     *
     * @param request, response
     * @return
     * @author 杨新杰
     * @date 2018/11/10 16:11
     */
    @RequestMapping(value = "/updateBusinessLegalize", method = RequestMethod.POST)
    @ResponseBody
    public Result updateBusinessLegalize(HttpServletRequest request, Integer legalize_id, Integer business_id, Integer type, String reson) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Admins admins = this.adminsService.selectById(Integer.parseInt(userId));
                if (admins == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("管理员不存在");
                } else {
                    if (this.wnkBusinessLegalizeService.updateBusinessLegalize(legalize_id, business_id, type) > 0) {
                        WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                        if (wnkBusinessAccount != null) {
                            // 审核通过
                            if (type == 1) {
                                MobileCodeUtil.sendAccountAuthenticationPassSms(wnkBusinessAccount.getMobile());
                            } else {   // 审核失败
                                MobileCodeUtil.sendAccountAuthenticationNoPassSms(wnkBusinessAccount.getMobile(), reson);
                            }
                        }

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
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("菜单查询失败");
        }
        return result;
    }


    /**
     * 功能描述:根据商家ID查询商家认证信息
     *
     * @param request, response
     * @return
     * @author 杨新杰
     * @date 2018/11/10 16:11
     */
    @RequestMapping(value = "/selectBusinessLegalizeInfoById", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessLegalizeInfoById(HttpServletRequest request,Integer id) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                Admins admins = this.adminsService.selectById(Integer.parseInt(userId));
                if (admins == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("管理员不存在");
                } else {
                    Map<String,Object> map = this.wnkBusinessLegalizeService.selectBusinessLegalizeInfoById(id);
                    if (map == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("查询失败");
                    } else {
                        map.put("img_path",ImageToolsController.imageShowURL);
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("菜单查询失败");
        }
        return result;
    }


}
