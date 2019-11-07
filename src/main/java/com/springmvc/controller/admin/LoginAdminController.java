package com.springmvc.controller.admin;

import com.springmvc.entity.AdminTypes;
import com.springmvc.entity.Admins;
import com.springmvc.entity.BackstageMenus;
import com.springmvc.entity.WnkStoreInformation;
import com.springmvc.service.impl.*;
import com.springmvc.utils.CookileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springmvc.utils.Result;

import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class LoginAdminController {
    @Resource
    private AdminsService adminsService;
    @Resource
    private AdminTypesService adminTypesService;
    @Resource
    private BackstageMenusService backstageMenusService;

    @Resource
    private WnkBusinessLegalizeService wnkBusinessLegalizeService;

    @Resource
    private DoingsSpreadService doingsSpreadService;
    @Resource
    private SuggestionFeedbackService suggestionFeedbackService;

    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;


    /**
     *
     * 功能描述: 进入登录页面
     *
     * @param  request  HttpServletRequest
     * @return 返回登录页面
     * @author 刘武祥
     * @date   2018/10/22 0022 18:01
     */
    @RequestMapping(value = "")
    @ResponseBody
    public ModelAndView login(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/login");
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入首页
     *
     * @param  request  HttpServletRequest
     * @return 返回首页界面
     * @author 刘武祥
     * @date   2018/10/22 0022 17:58
     */
    @RequestMapping(value = "/home")
    @ResponseBody
    public ModelAndView home(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String userId = cookileUtil.getCookie_user_id(request,0);
        if (userId == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/index");
            Admins admins = this.adminsService.selectById(Integer.parseInt(userId));
            if (admins == null){
                modelAndView.addObject("name","无");
            }
            else{
                modelAndView.addObject("name",admins.getName());
            }
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 管理员登录
     *
     * @param request   HttpServletRequest
     * @param response  HttpServletResponse
     * @param account   登录账户
     * @param login_pwd 登录密码
     * @return 返回管理员登录状态
     * @author 刘武祥
     * @date   2018/10/22 0022 17:50
     */
    @RequestMapping(value = "/loginAction", method = RequestMethod.POST,params = {"account","login_pwd"})
    @ResponseBody
    public Result loginAction(HttpServletRequest request, HttpServletResponse response,String account,String login_pwd){
        Result result = new Result();
        try {
            Admins admins = this.adminsService.selectByMobileAndLoginPWD(account,login_pwd);
            if (admins == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("登录失败");
            }
            else{
                CookileUtil cookileUtil = new CookileUtil();
                Integer userId = admins.getId();
                cookileUtil.saveCookie(response,userId.toString(),0);
                result.setData("");
                result.setStatus(Result.SUCCESS);
                result.setMsg("登录成功");
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("登录失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 获取管理员可操作的菜单
     *
     * @param  request   HttpServletRequest
     * @param  response  HttpServletResponse
     * @return 返回管理员可操作的菜单
     * @author 刘武祥
     * @date   2018/10/22 0022 16:36
     */
    @RequestMapping(value = "/getAdminMenus", method = RequestMethod.POST)
    @ResponseBody
    public Result getAdminMenus(HttpServletRequest request, HttpServletResponse response){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Admins admins = this.adminsService.selectById(Integer.parseInt(userId));
                if (admins == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("管理员不存在");
                }
                else{
                    AdminTypes adminTypes = this.adminTypesService.selectById(admins.getAdmin_type_id());
                    if (adminTypes == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("管理员类别不存在");
                    }
                    else{
                        String[] typesOneMenusIds = adminTypes.getAccess_rights().split(",");
                        List<Map<Object,Object>> menus = new ArrayList<Map<Object,Object>>();
                        for (Integer index = 0;index < typesOneMenusIds.length; index++){
                            BackstageMenus backstageMenus = this.backstageMenusService.selectById(Integer.parseInt(typesOneMenusIds[index]));
                            List<Map<Object,Object>> twoMenus = this.backstageMenusService.selectAllTwoMenusByOneMenuId(backstageMenus.getId());
                            Map<Object,Object> menuMap = new HashMap<Object,Object>();
                            menuMap.put("one_menu_name",backstageMenus.getName());
                            menuMap.put("two_menus",twoMenus);
                            menus.add(menuMap);
                        }
                        Map<Object,Object> map = new HashMap<Object,Object>();
                        map.put("menus",menus);
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("菜单查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 获取管理员未读消息
     *
     * @param  request   HttpServletRequest
     * @param  response  HttpServletResponse
     * @return 返回管理员可操作的菜单
     * @author 刘武祥
     * @date   2018/10/22 0022 16:36
     */
    @RequestMapping(value = "/getAdminUnreadMsg", method = RequestMethod.POST)
    @ResponseBody
    public Result getAdminUnreadMsg(HttpServletRequest request, HttpServletResponse response){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Admins admins = this.adminsService.selectById(Integer.parseInt(userId));
                if (admins == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("管理员不存在");
                }
                else{
                    // 用作返回消息
                    Map<String,Object> resultMap = new HashMap<>();
                    // 先获取是否有商户认证信息
                    List<Map<String, Object>> legalizeList = this.wnkBusinessLegalizeService.selectBusinessLegalizeAll();
                    if (legalizeList.size() > 0){
                        resultMap.put("legalize",legalizeList.size());
                    }
                    // 获取推广活动审核信息
                    List<Map<String, Object>> doingsSpreadList = this.doingsSpreadService.selectNoReviewAll();
                    if (doingsSpreadList.size()>0){
                        resultMap.put("doings_spread",doingsSpreadList);
                    }
                    // 获取投诉建议
                    List<Map<String, Object>> suggestionFeedbackList = this.suggestionFeedbackService.selectNoHandleAll();
                    if (suggestionFeedbackList.size() > 0 ){
                        resultMap.put("suggestion_feedback",suggestionFeedbackList);
                    }
                    result.setData(resultMap);
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
     * 功能描述: 获取所有商家信息
     *
     * @param  request   HttpServletRequest
     * @return 返回管理员可操作的菜单
     * @author 刘武祥
     * @date   2018/10/22 0022 16:36
     */
    @RequestMapping(value = "/selectBusinessInfoAll", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessInfoAll(HttpServletRequest request){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Admins admins = this.adminsService.selectById(Integer.parseInt(userId));
                if (admins == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("管理员不存在");
                }
                else{
                    List<Map<Object, Object>> maps = this.wnkStoreInformationService.selectAllBusiness();
                    if (maps.size() > 0){
                        result.setData(maps);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    } else {
                        result.setData(maps);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("暂无数据");
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
