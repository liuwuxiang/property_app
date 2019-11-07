package com.springmvc.controller.wnk_business_wx;

import com.springmvc.entity.WnkOrders;
import com.springmvc.entity.WnkQrCodeMakeRecord;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.WnkOrdersService;
import com.springmvc.service.impl.WnkQrCodeMakeRecordService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/wnk_business")
public class OrderWnkBusinessWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkOrdersService wnkOrdersService;
    @Resource
    private WnkQrCodeMakeRecordService wnkQrCodeMakeRecordService;

    //进入商家订单页面
    @RequestMapping(value = "/joinOrders")
    @ResponseBody
    public ModelAndView joinOrders(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,3,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wnk_business/login");
        }
        else{
            modelAndView = new ModelAndView("wnk_business/orders");
        }
        return modelAndView;
    }

    //进入商家订单统计详情页面(make_type=0-按订单统计详情，make_type=1-按销售统计详情)
    @RequestMapping(value = "/joinOrdersStatisticsDetail",method = RequestMethod.GET,params = {"year_month","state","make_type"})
    @ResponseBody
    public ModelAndView joinOrdersStatisticsDetail(HttpServletRequest request,String year_month,Integer state,Integer make_type){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,3,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wnk_business/login");
        }
        else{
            modelAndView = new ModelAndView("wnk_business/order_static_detail");
            modelAndView.addObject("year_month",year_month);
            modelAndView.addObject("state",state);
            modelAndView.addObject("make_type",make_type);
        }
        return modelAndView;
    }

    //进入会员权益订单确认使用页面
    @RequestMapping(value = "/joinMemberQYMake",method = RequestMethod.GET,params = {"user_id"})
    @ResponseBody
    public ModelAndView joinMemberQYMake(HttpServletRequest request,Integer user_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id_integer = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id_integer,3,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wnk_business/login");
        }
        else{
            modelAndView = new ModelAndView("wnk_business/member_qy_make");
            modelAndView.addObject("user_id",user_id);
        }
        return modelAndView;
    }


    //进入订单详情页(type=0-正常订单，type=1-权益订单)
    @RequestMapping(value = "/getWnkOrderDetail",method = RequestMethod.GET,params = {"order_no","type"})
    @ResponseBody
    public ModelAndView getWnkOrderDetail(HttpServletRequest request,String order_no,Integer type){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,3,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wnk_business/login");
        }
        else{
            modelAndView = new ModelAndView("wnk_business/order_detail");
            modelAndView.addObject("order_id",-1);
            modelAndView.addObject("type",type);
            if (type == 0){
                WnkOrders wnkOrders = this.wnkOrdersService.selectByOrderNo(order_no);
                if (wnkOrders != null){
                    modelAndView.addObject("order_id",wnkOrders.getId());
                }
            }
            else{
                WnkQrCodeMakeRecord wnkQrCodeMakeRecord = this.wnkQrCodeMakeRecordService.selectByOrderNo(order_no);
                if (wnkQrCodeMakeRecord != null){
                    modelAndView.addObject("order_id",wnkQrCodeMakeRecord.getId());
                }
            }
        }
        return modelAndView;
    }


    // 通过订单ID 查询记录ID
    @RequestMapping(value = "/getWnkOrderIdByOrderId",method = RequestMethod.POST)
    @ResponseBody
    public Result getWnkOrderIdByOrderId(HttpServletRequest request,String order_no,Integer type){
       Result result = new Result();
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,3,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (!isLogin){
            result.setData("");
            result.setMsg("未登录");
            result.setStatus(Result.NOLOGIN);
        }
        else{
            if (type == 0){
                WnkOrders wnkOrders = this.wnkOrdersService.selectByOrderNo(order_no);
                if (wnkOrders != null){
                    Map<String,Object> map = new HashMap<>();
                    map.put("order_id",wnkOrders.getId());
                    map.put("user_id",wnkOrders.getUser_id());
                    result.setData(map);
                    result.setMsg("查询成功");
                    result.setStatus(Result.SUCCESS);
                }
            }
            else{
                WnkQrCodeMakeRecord wnkQrCodeMakeRecord = this.wnkQrCodeMakeRecordService.selectByOrderNo(order_no);
                if (wnkQrCodeMakeRecord != null){
                    result.setData(wnkQrCodeMakeRecord.getId());
                    result.setMsg("查询成功");
                    result.setStatus(Result.SUCCESS);
                }
            }

        }
        return result;
    }



}
