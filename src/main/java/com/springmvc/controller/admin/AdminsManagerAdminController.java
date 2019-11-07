package com.springmvc.controller.admin;

import com.springmvc.entity.Admins;
import com.springmvc.service.impl.AdminsService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminsManagerAdminController {
    @Resource
    private AdminsService adminsService;

    /**
     *
     * 功能描述: 进入管理员管理页面
     *
     * @param  request  HttpServletRequest
     * @return 返回管理员管理页面
     * @author 刘武祥
     * @date   2018/10/22 0022 16:28
     */
    @RequestMapping(value = "/adminsManager")
    @ResponseBody
    public ModelAndView adminsManager(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/admins_manager/admins_manager");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入添加管理员界面
     *
     * @param  request  HttpServletRequest
     * @return 返回添加管理员的界面
     * @author 刘武祥
     * @date   2018/10/22 0022 16:25
     */
    @RequestMapping(value = "/addAdmins")
    @ResponseBody
    public ModelAndView addAdmins(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/admins_manager/add_admins");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入修改管理员界面
     *
     * @param  request  HttpServletRequest
     * @return 返回管理员修改界面
     * @author 刘武祥
     * @date   2018/10/22 0022 16:23
     */
    @RequestMapping(value = "/setAdmins",method = RequestMethod.GET,params = {"record_id"})
    @ResponseBody
    public ModelAndView setAdmins(HttpServletRequest request,Integer record_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/admins_manager/set_admins");
            modelAndView.addObject("record_id",record_id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入管理员资料修改界面
     *
     * @param  request  HttpServletRequest
     * @return 返回管理员资料修改界面
     * @author 刘武祥
     * @date   2018/10/22 0022 16:20
     */
    @RequestMapping(value = "/setAdminInformation")
    @ResponseBody
    public ModelAndView setAdminInformation(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/admin_message/set_admin_information");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入管理员密码修改界面
     *
     * @param  request  HttpServletRequest
     * @return 返回管理员密码修改界面
     * @author 刘武祥
     * @date   2018/10/22 0022 16:17
     */
    @RequestMapping(value = "/setAdminPwd")
    @ResponseBody
    public ModelAndView setAdminPwd(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/admin_message/set_admin_pwd");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述:  获取所有管理员
     *
     * @param  request   HttpServletRequest
     * @param  response  HttpServletResponse
     * @return 返回所有管理员状态信息
     * @author 刘武祥
     * @date   2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/getAllAdmins", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllAdmins(HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.adminsService.selectAllAdmin();
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

    /**
     *
     * 功能描述: 根据条件查询管理员管理信息
     *
     * @param   request             请求
     * @param   response            响应
     * @param   limit               分页条目数
     * @param   page                分页数
     * @param   name                姓名
     * @param   mobile              手机号码/账号
     * @param   admin_types_name    管理员类别
     * @param   join_time           加入时间
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/23 0023 16:46
     */
    @RequestMapping(value = "/adminSearchAdminsByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchAdminsByConditions(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       Integer limit,
                                                       Integer page,
                                                       String name,
                                                       String mobile,
                                                       String position,
                                                       String admin_types_name,
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
                Map<String, Object> map = new HashMap<>();
                map.put("statrt_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                if (name != null && !"".equals(name)) {
                    map.put("name", "%" + name + "%");
                }
                if (mobile != null && !"".equals(mobile)) {
                    map.put("mobile", "%" + mobile + "%");
                }
                if (position != null && !"".equals(position)) {
                    map.put("position", "%" + position + "%");
                }
                if (admin_types_name != null && !"".equals(admin_types_name)) {
                    map.put("admin_types_name", "%" + admin_types_name + "%");
                }
                map.put("join_time", join_time);
                List<Map<String, Object>> list = this.adminsService.adminSearchAdminsByConditions(map);
                result.setData(list);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
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
     * 功能描述:  获取管理员信息
     *
     * @param  request   HttpServletRequest
     * @param  response  HttpServletResponse
     * @param  record_id  管理员id
     * @return 返回所有管理员状态信息
     * @author 张凡
     * @date   2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/getAdminsInformation", method = RequestMethod.POST,params = {"record_id"})
    @ResponseBody
    public Result getAdminsInformation(HttpServletRequest request, HttpServletResponse response,Integer record_id){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Admins admins = this.adminsService.selectById(record_id);
                if (admins == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此管理员不存在");
                }
                else{
                    result.setData(admins);
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
     * 功能描述:  新增管理员
     *
     * @param  request   HttpServletRequest
     * @param  response  HttpServletResponse
     * @return 返回所有管理员状态信息
     * @author 刘武祥
     * @date   2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/insertAdmin", method = RequestMethod.POST,params = {"name","mobile","position","admin_type_id"})
    @ResponseBody
    public Result insertAdmin(HttpServletRequest request, HttpServletResponse response,String name,String mobile,String position,Integer admin_type_id){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Admins admins = this.adminsService.selectByMobile(mobile);
                if (admins == null){
                    admins = new Admins();
                    admins.setName(name);
                    admins.setMobile(mobile);
                    admins.setPosition(position);
                    admins.setLogin_pwd("123456");
                    admins.setAdmin_type_id(admin_type_id);
                    admins.setJoin_time(new Date());
                    int addState = this.adminsService.addRecord(admins);
                    if (addState <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("新增失败");
                    }
                    else{
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("login_pwd",admins.getLogin_pwd());

                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("新增成功");
                    }
                }
                else{
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此手机号已存在");
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
     *
     * 功能描述:  修改管理员
     *
     * @param  request   HttpServletRequest
     * @param  response  HttpServletResponse
     * @return 返回所有管理员状态信息
     * @author 刘武祥
     * @date   2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/setAdminInformationAction", method = RequestMethod.POST,params = {"name","mobile","position","admin_type_id","record_id"})
    @ResponseBody
    public Result setAdminInformationAction(HttpServletRequest request, HttpServletResponse response,String name,String mobile,String position,Integer admin_type_id,Integer record_id){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Admins admins = this.adminsService.selectById(record_id);
                if (admins != null){
                    admins.setName(name);
                    admins.setMobile(mobile);
                    admins.setPosition(position);
                    admins.setAdmin_type_id(admin_type_id);
                    int updateState = this.adminsService.updateAdminsInformation(admins);
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
                else{
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此管理员不存在");
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
     *
     * 功能描述:  删除管理员
     *
     * @param  request   HttpServletRequest
     * @param  response  HttpServletResponse
     * @return 返回所有管理员状态信息
     * @author 刘武祥
     * @date   2018/10/22 0022 16:11
     */
    @RequestMapping(value = "/deleteAdminInformation", method = RequestMethod.POST,params = {"record_id"})
    @ResponseBody
    public Result deleteAdminInformation(HttpServletRequest request, HttpServletResponse response,Integer record_id){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Admins admins = this.adminsService.selectById(record_id);
                if (admins != null){
                    int deleteState = this.adminsService.deleteRecordByAdminId(record_id);
                    if (deleteState <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("删除失败");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("删除成功");
                    }
                }
                else{
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此管理员不存在");
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
     *
     * 功能描述: 管理员修改基本资料事件
     *
     * @param  request   HttpServletRequest
     * @param  response  HttpServletResponse
     * @param  name      姓名
     * @param  mobile    手机号
     * @return 返回管理员修改基本状态
     * @author 刘武祥
     * @date   2018/10/22 0022 16:04
     */
    @RequestMapping(value = "/setAdminInformationAction", method = RequestMethod.POST,params = {"name","mobile"})
    @ResponseBody
    public Result setAdminInformationAction(HttpServletRequest request, HttpServletResponse response,String name,String mobile){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                if (name.equals("") || name == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入姓名");
                }
                else if (mobile.equals("") || mobile == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入手机号");
                }
                else{
                    Admins admins = this.adminsService.selectByMobile(mobile);
                    if (admins != null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此手机号已存在");
                    }
                    else{
                        admins = this.adminsService.selectById(Integer.parseInt(userId));
                        if (admins == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("管理员不存在");
                        }
                        else{
                            admins.setMobile(mobile);
                            admins.setName(name);
                            int state = this.adminsService.updateAdminMobileAndName(admins);
                            if (state >= 1){
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("更新成功");
                            }
                            else{
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("更新失败");
                            }
                        }
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

    /**
     *
     * 功能描述: 管理员登录密码修改
     *
     * @param  request,response  请求/响应
     * @param  old_login_pwd     旧的登录密码
     * @param  new_login_pwd     新的登录密码
     * @return 返回修改登录的状态
     * @author 刘武祥
     * @date   2018/10/22 0022 15:37
     */
    @RequestMapping(value = "/setAdminLoginPwd", method = RequestMethod.POST,params = {"old_login_pwd","new_login_pwd"})
    @ResponseBody
    public Result setAdminLoginPwd(HttpServletRequest request, HttpServletResponse response,String old_login_pwd,String new_login_pwd){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                if (old_login_pwd.equals("") || old_login_pwd == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入原始密码");
                }
                else if (new_login_pwd.equals("") || new_login_pwd == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入新密码");
                }
                else{
                    Admins admins = this.adminsService.selectById(Integer.parseInt(userId));
                    if (admins == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("管理员不存在");
                    }
                    else{
                        if (!old_login_pwd.equals(admins.getLogin_pwd())){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("原始密码不正确");
                        }
                        else{
                            admins.setLogin_pwd(new_login_pwd);
                            int state = this.adminsService.updateAdminLoginPwd(admins);
                            if (state >= 1){
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("更新成功");
                            }
                            else{
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("更新失败");
                            }
                        }
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

    /**
     *
     * 功能描述: 管理员退出登录
     *
     * @param  request, response  请求/响应
     * @return 返回管理员退出状态
     * @author 刘武祥
     * @date   2018/10/22 0022 15:25
     */
    @RequestMapping(value = "/adminExitLogin", method = RequestMethod.POST)
    @ResponseBody
    public Result adminExitLogin(HttpServletRequest request, HttpServletResponse response){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            cookileUtil.clearCookie(0,userId,response);
            result.setData("");
            result.setStatus(Result.SUCCESS);
            result.setMsg("退出成功");
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("退出失败");
        }
        return result;
    }
}
