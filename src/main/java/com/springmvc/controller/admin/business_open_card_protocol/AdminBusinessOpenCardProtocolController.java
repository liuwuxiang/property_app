package com.springmvc.controller.admin.business_open_card_protocol;

import com.springmvc.dao.WnkBusinessOpenCardProtocolMapper;
import com.springmvc.entity.BusinessOpenCardProtocol;
import com.springmvc.entity.IntegralHelp;
import com.springmvc.service.impl.AdminsService;
import com.springmvc.service.impl.WnkBusinessOpenCardProtocolService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.Result;
import jdk.nashorn.internal.ir.IfNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.BatchUpdateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 功能描述: 后台管理员商家会员卡开卡协议相关接口
 * @Author: 刘武祥
 * @Date: 2019/2/20 0020 10:20
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminBusinessOpenCardProtocolController {

    @Resource
    private WnkBusinessOpenCardProtocolService wnkBusinessOpenCardProtocolService;

    /**
     *
     * 功能描述: 进入商家开卡协议管理界面
     *
     * @return  org.springframework.web.servlet.ModelAndView
     * @author  杨新杰
     * @date    14:35 2019/1/2
     */
    @RequestMapping(value = "/joinWnkBusinessTypeCardProtocol")
    @ResponseBody
    public ModelAndView joinWnkBusinessTypeCardProtocol(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk/wnk_business_open_card_protocol");
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述: 进入商家开卡协议新增or修改界面
     *
     * @return  org.springframework.web.servlet.ModelAndView
     * @author  杨新杰
     * @date    14:35 2019/1/2
     */
    @RequestMapping(value = "/joinWnkBusinessOpenCardProtocolEdit")
    @ResponseBody
    public ModelAndView joinWnkBusinessOpenCardProtocolEdit(HttpServletRequest request,Integer protocol_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/wnk/wnk_business_open_card_protocol_edit");
            modelAndView.addObject("protocol_id",protocol_id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述:  查询所有商家会员卡开卡协议(除去已经标记删除的)
     *
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:45 2019/1/2
     */
    @RequestMapping(value = "/adminSelectWnkBusinessOpenCardProtocolAll", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSelectWnkBusinessOpenCardProtocolAll(HttpServletRequest request){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                List<Map<String, Object>> list = this.wnkBusinessOpenCardProtocolService.adminSelectWnkBusinessOpenCardProtocolAll();
                if (list.size() > 0){
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
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
     * 功能描述: 根据条件查询会员卡办理和使用说明
     *
     * @param   request         请求
     * @param   response        响应
     * @param   limit           分页条目数
     * @param   page            分页数
     * @param   business_name   商家名称
     * @param   content         协议内容
     * @param   is_checked_str  状态
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/25 0025 11:28
     */
    @RequestMapping(value = "/adminSearchWnkBusinessOpenCardProtocol", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchWnkBusinessOpenCardProtocol(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         Integer limit,
                                                         Integer page,
                                                         String business_name,
                                                         String content,
                                                         Integer is_checked_str) {

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
                map.put("start_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                if (business_name != null && !"".equals(business_name)){
                    map.put("business_name", "%" + business_name + "%");
                }
                if (content != null && !"".equals(content)){
                    map.put("content","%"+content+"%");
                }
                map.put("is_checked_str", ("".equals(is_checked_str) ? null : is_checked_str));
                List<Map<String, Object>> list = this.wnkBusinessOpenCardProtocolService.adminSearchWnkBusinessOpenCardProtocol(map);
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
     * 功能描述:  插入新的商家协议
     *
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:45 2019/1/2
     */
    @RequestMapping(value = "/insertBusinessOpenCardProtocol", method = RequestMethod.POST)
    @ResponseBody
    public Result insertBusinessOpenCardProtocol(HttpServletRequest request, BusinessOpenCardProtocol businessOpenCardProtocol){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                // 设置未删除
                businessOpenCardProtocol.setIs_del(0);
                int i  = this.wnkBusinessOpenCardProtocolService.insertBusinessOpenCardProtocol(businessOpenCardProtocol);
                if (i > 0){
                    result.setData(null);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("新增成功");
                } else {
                    result.setData(null);
                    result.setStatus(Result.FAIL);
                    result.setMsg("新增失败");
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
     * 功能描述:  插入新的商家协议
     *
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:45 2019/1/2
     */
    @RequestMapping(value = "/updateBusinessOpenCardProtocol", method = RequestMethod.POST)
    @ResponseBody
    public Result updateBusinessOpenCardProtocol(HttpServletRequest request, BusinessOpenCardProtocol businessOpenCardProtocol){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                // 设置未删除
                businessOpenCardProtocol.setIs_del(0);
                int i  = this.wnkBusinessOpenCardProtocolService.updateBusinessOpenCardProtocol(businessOpenCardProtocol);
                if (i > 0){
                    result.setData(null);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("修改成功");
                } else {
                    result.setData(null);
                    result.setStatus(Result.FAIL);
                    result.setMsg("修改失败");
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
     * 功能描述:  通过ID查询商家协议内容
     *
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:45 2019/1/2
     */
    @RequestMapping(value = "/selectBusinessOpenCardProtocolById", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessOpenCardProtocolById(HttpServletRequest request, Integer protocol_id){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                // 设置未删除
                Map<String,Object> map  = this.wnkBusinessOpenCardProtocolService.selectBusinessOpenCardProtocolById(protocol_id);
                if (map  != null){
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                } else {
                    result.setData(map);
                    result.setStatus(Result.FAIL);
                    result.setMsg("查询失败");
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
