package com.springmvc.controller.admin;

import com.springmvc.entity.AdminTypes;
import com.springmvc.entity.BackstageMenus;
import com.springmvc.service.impl.AdminTypesService;
import com.springmvc.service.impl.BackstageMenusService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminsTypesAdminController {
    @Resource
    private AdminTypesService adminTypesService;
    @Resource
    private BackstageMenusService backstageMenusService;

    //进入管理员类别管理界面
    @RequestMapping(value = "/adminsTypes")
    @ResponseBody
    public ModelAndView adminsTypes(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/admins_types/admins_types");

        }
        return modelAndView;
    }

    //进入添加管理员类别界面
    @RequestMapping(value = "/addAdminsTypes")
    @ResponseBody
    public ModelAndView addAdminsTypes(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/admins_types/add_admins_types");
        }
        return modelAndView;
    }

    //进入修改管理员类别界面
    @RequestMapping(value = "/setAdminsTypes",method = RequestMethod.GET,params = {"record_id"})
    @ResponseBody
    public ModelAndView setAdminsTypes(HttpServletRequest request,Integer record_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/admins_types/set_admins_type");
            modelAndView.addObject("record_id",record_id);
        }
        return modelAndView;
    }

    // 获取所有管理员类别
    @RequestMapping(value = "/getAllAdminTypes", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllAdminTypes(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.adminTypesService.selectAllAdmin();
                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
                else{
                    for (Integer index = 0;index < list.size();index++){
                        Map<Object,Object> typesMap = list.get(index);
                        String oneMenus = (String)typesMap.get("access_rights");
                        String[] menus = oneMenus.split(",");
                        String oneMenuNames = "";
                        for (Integer index2 = 0;index2 < menus.length; index2++){
                            BackstageMenus backstageMenus = this.backstageMenusService.selectById(Integer.parseInt(menus[index2]));
                            if (index2 != menus.length - 1){
                                if (backstageMenus == null){
                                    oneMenuNames = oneMenuNames + ",";
                                }
                                else{
                                    oneMenuNames = oneMenuNames + backstageMenus.getName()+",";
                                }
                            }
                            else{
                                if (backstageMenus == null){
                                    oneMenuNames = oneMenuNames + "";
                                }
                                else{
                                    oneMenuNames = oneMenuNames + backstageMenus.getName();
                                }
                            }
                        }
                        typesMap.put("access_rights",oneMenuNames);
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
     * 功能描述: 根据条件查询管理员类别
     *
     * @param   request     请求
     * @param   response    响应
     * @param   limit       分页条目数
     * @param   page        分页数
     * @param   type_name   类别名称
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/12 0012 11:49
     */
    @RequestMapping(value = "/adminSearchAdminsTypeByConditions",method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchAdminsTypeByConditions(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     Integer limit,
                                                     Integer page,
                                                     String type_name
    ){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId  = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                    Map<String,Object> map = new HashMap<>();
                    map.put("statrt_index",limit * (page-1));
                    map.put("limt",limit);
                    map.put("page",page);
                    map.put("type_name","%" + type_name +"%");
                    List<Map<String,Object>> list  = this.adminTypesService.adminSearchAdminsTypeByConditions(map);
                for (Integer index = 0;index < list.size();index++){
                    Map<String,Object> typesMap = list.get(index);
                    String oneMenus = (String)typesMap.get("access_rights");
                    String[] menus = oneMenus.split(",");
                    String oneMenuNames = "";
                    for (Integer index2 = 0;index2 < menus.length; index2++){
                        BackstageMenus backstageMenus = this.backstageMenusService.selectById(Integer.parseInt(menus[index2]));
                        if (index2 != menus.length - 1){
                            if (backstageMenus == null){
                                oneMenuNames = oneMenuNames + ",";
                            }
                            else{
                                oneMenuNames = oneMenuNames + backstageMenus.getName()+",";
                            }
                        }
                        else{
                            if (backstageMenus == null){
                                oneMenuNames = oneMenuNames + "";
                            }
                            else{
                                oneMenuNames = oneMenuNames + backstageMenus.getName();
                            }
                        }
                    }
                    typesMap.put("access_rights",oneMenuNames);
                }
                    result.setData(list);
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

    // 获取所有一级菜单
    @RequestMapping(value = "/getAllAdminOneMenus", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllAdminOneMenus(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.backstageMenusService.selectAllOneMenus();
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


    // 插入管理员类别
    @RequestMapping(value = "/insertAdminType", method = RequestMethod.POST,params = {"type_name","menu_ids"})
    @ResponseBody
    public Result insertAdminType(HttpServletRequest request, HttpServletResponse response,String type_name,String menu_ids){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                AdminTypes adminTypes = new AdminTypes();
                adminTypes.setAccess_rights(menu_ids);
                adminTypes.setType_name(type_name);
                int addState = this.adminTypesService.addRecord(adminTypes);
                if (addState <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("新增失败");
                }
                else{
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

    // 查询管理员类别信息
    @RequestMapping(value = "/selectAdminsTypeById", method = RequestMethod.POST,params = {"type_id"})
    @ResponseBody
    public Result selectAdminsTypeById(HttpServletRequest request, HttpServletResponse response,Integer type_id){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                AdminTypes adminTypes = this.adminTypesService.selectById(type_id);
                if (adminTypes == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此类别不存在");
                }
                else{
                    result.setData(adminTypes);
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


    // 修改管理员类别信息
    @RequestMapping(value = "/setAdminsTypeById", method = RequestMethod.POST,params = {"type_id","type_name","menusIds"})
    @ResponseBody
    public Result setAdminsTypeById(HttpServletRequest request, HttpServletResponse response,Integer type_id,String type_name,String menusIds){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                AdminTypes adminTypes = this.adminTypesService.selectById(type_id);
                if (adminTypes == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此类别不存在");
                }
                else{
                    adminTypes.setType_name(type_name);
                    adminTypes.setAccess_rights(menusIds);
                    int updateState = this.adminTypesService.updateAdminsTypeInformation(adminTypes);
                    if (updateState <= 0){
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
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }
}
