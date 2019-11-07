package com.springmvc.controller.admin;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.UsersService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.Result;
import javafx.geometry.Pos;
import org.apache.http.protocol.HTTP;
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
public class MemberAdminController {
    @Resource
    private UsersService usersService;

    /**
     *
     * 功能描述: 进入会员管理界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/7 0007 11:57
     */
    @RequestMapping(value = "/memberManager")
    @ResponseBody
    public ModelAndView memberManager(HttpServletRequest request){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/members/members");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入会员信息修改界面
     *
     * @param   request
     * @param   user_id     用户id
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/1/7 0007 11:58
     */
    @RequestMapping(value = "/members_edit")
    @ResponseBody
    public ModelAndView members_edit(HttpServletRequest request,Integer user_id){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/members/members_edit");
        modelAndView.addObject("user_id",user_id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取所有会员
     *
     * @param   request     请求
     * @param   response    响应
     * @return: Result      返回所有会员信息
     * @author: 刘武祥
     * @date: 2019/1/7 0007 11:56
     */
    @RequestMapping(value = "/getAllMembers", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllMembers(HttpServletRequest request, HttpServletResponse response){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.usersService.getAllUsersAdmin();
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
     * 功能描述:管理员通过会员ID查询会员信息
     *
     * @param   request   请求
     * @param   response  响应
     * @param   user_id   用户id
     * @return: Result    返回会员ID查询到的所有会员信息
     * @author: 刘武祥
     * @date: 2019/1/7 0007 11:45
     */
    @RequestMapping(value = "/adminSelectUserInfoById", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSelectUserInfoById(HttpServletRequest request, HttpServletResponse response,Integer user_id){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Users users = usersService.selectById(user_id);
                if (users ==  null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("查询失败");
                }
                else{
                    Map<String,Object> map = new HashMap<>();
                    map.put("img_header",ImageToolsController.imageShowURL+"?imageid="+users.getHeader());
                    map.put("nick_name", users.getNick_name());
                    map.put("sex", users.getSex());
                    map.put("email", users.getEmail());
                    result.setData(map);
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
     * 功能描述:管理员通过会员id更新会员信息
     *
     * @param   request     请求
     * @param   response    响应
     * @param   users       用户实体
     * @return: Result      返回更新的会员信息
     * @author: 刘武祥
     * @date: 2019/1/8 0008 12:04
     */
    @RequestMapping(value = "/adminUpdateUserInfoById", method = RequestMethod.POST)
    @ResponseBody
    public Result adminUpdateUserInfoById(HttpServletRequest request, HttpServletResponse response,Users users){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
               if (usersService.adminUpdateUserInfoById(users) > 0){
                   result.setData("");
                   result.setStatus(Result.SUCCESS);
                   result.setMsg("更新成功");
               } else {
                   result.setData("");
                   result.setStatus(Result.FAIL);
                   result.setMsg("更新失败");
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
     * 功能描述:根据条件查询搜索会员用户
     *
     * @param   request             请求
     * @param   response            响应
     * @param   limit               分页条目数
     * @param   page                分页页码
     * @param   nick_name           昵称
     * @param   mobile              手机号
     * @param   sex                 性别
     * @param   member_card_level   开卡类型
     * @param   general_integral    通用积分余额
     * @param   silver_coin_balance 玫瑰余额
     * @param   register_time       注册时间
     * @param   recommend_user_name 推荐人
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/9 0009 16:24
     */
    @RequestMapping(value = "/adminSearchMemberByConditions",method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchMemberByConditions(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Integer limit,
                                                Integer page,
                                                String mobile,
                                                String nick_name,
                                                Integer sex,
                                                Integer member_card_level,
                                                Double general_integral,
                                                Integer silver_coin_balance,
                                                String register_time,
                                                String recommend_user_name
    ){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Map<String, Object> map = new HashMap<>();
                map.put("start_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                if (mobile != null && !"".equals(mobile)) {
                    map.put("mobile", "%" + mobile + "%");
                }
                if (nick_name != null && !"".equals(nick_name)) {
                    map.put("nick_name","%"+nick_name+"%");
                }
                map.put("sex",("".equals(sex)  ? null : sex));
                map.put("member_card_level",("".equals(member_card_level)  ? null : member_card_level));
                map.put("general_integral", ("".equals(general_integral)  ? null : general_integral));
                map.put("silver_coin_balance",("".equals(silver_coin_balance)  ? null : silver_coin_balance));
                map.put("register_time", register_time);
                if (recommend_user_name != null && !"".equals(recommend_user_name)) {
                    map.put("recommend_user_name", "%" + recommend_user_name + "%");
                }
                List<Map<String,Object>> list = this.usersService.adminSearchMemberByConditions(map);
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

}

