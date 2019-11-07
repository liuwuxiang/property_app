package com.springmvc.controller.admin;

import com.springmvc.entity.SystemNoticeMessage;
import com.springmvc.service.impl.SystemNoticeMessageService;
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
public class MessageAdminController {
    @Resource
    private SystemNoticeMessageService systemNoticeMessageService;

    /**
     *
     * 功能描述: 进入消息中心界面
     *
     * @param   request     请求
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/2/12 0012 15:58
     */
    @RequestMapping(value = "/messageCenter")
    @ResponseBody
    public ModelAndView messageCenter(HttpServletRequest request){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/message_center/message_center");
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述: 进入发布消息界面
     *
     * @param   request   请求
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/2/12 0012 15:57
     */
    @RequestMapping(value = "/sendMessage")
    @ResponseBody
    public ModelAndView sendMessage(HttpServletRequest request){

        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/message_center/send_message");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取所有通知消息
     *
     * @param   request     请求
     * @param   response    响应
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/2/12 0012 15:56
     */
    @RequestMapping(value = "/getAllNotice", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllNotice(HttpServletRequest request, HttpServletResponse response) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                List<Map<Object, Object>> list = this.systemNoticeMessageService.selectAllAdmin();
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
     * 功能描述: 根据条件查询通知消息
     *
     * @param   request             请求
     * @param   response            响应
     * @param   limit               分页条目数
     * @param   page                分页数
     * @param   receiving_object    消息对象
     * @param   title               标题
     * @param   content             内容
     * @param   send_time           时间
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/11 0011 14:47
     */
    @RequestMapping(value = "/adminSearchMessageByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchMessageByConditions(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  Integer limit,
                                                  Integer page,
                                                  Integer receiving_object,
                                                  String title,
                                                  String content,
                                                  String send_time
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
                map.put("statrt_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                map.put("receiving_object", ("".equals(receiving_object) || receiving_object == null) ? null : receiving_object);
                if (title != null && !"".equals(title)){
                    map.put("title", "%"+title+"%");
                }
                if (content != null && !"".equals(content)) {
                    map.put("content", "%" + content + "%");
                }
                map.put("send_time", send_time);
                List<Map<String, Object>> List = this.systemNoticeMessageService.adminSearchMessageByConditions(map);
                result.setData(List);
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

    /**
     *
     * 功能描述: 发布新通知
     *
     * @param   request             请求
     * @param   response            响应
     * @param   title               标题
     * @param   receiving_object    消息对象
     * @param   content             内容
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/2/12 0012 15:53
     */
    @RequestMapping(value = "/sendNewNotice", method = RequestMethod.POST,params = {"title","receiving_object","content"})
    @ResponseBody
    public Result sendNewNotice(HttpServletRequest request, HttpServletResponse response,String title,Integer receiving_object,String content){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                if (receiving_object != 0 && receiving_object!= 1 && receiving_object != 2 && receiving_object != 3 && receiving_object != 4){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("数据不合法");
                }
                else{
                    SystemNoticeMessage systemNoticeMessage = new SystemNoticeMessage();
                    systemNoticeMessage.setTitle(title);
                    systemNoticeMessage.setReceiving_object(receiving_object);
                    systemNoticeMessage.setContent(content);
                    systemNoticeMessage.setSend_time(new Date());
                    int addState = this.systemNoticeMessageService.addNotice(systemNoticeMessage);
                    if (addState <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("发送失败");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("发送成功");
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
