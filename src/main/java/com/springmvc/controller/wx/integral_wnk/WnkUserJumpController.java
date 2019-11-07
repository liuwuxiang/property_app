package com.springmvc.controller.wx.integral_wnk;

import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersCertificatesService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 万能卡商家积分商城 - 跳转操作控制器
 * @author 杨新杰
 * @Date 2018/10/12 09:41
 */
@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class WnkUserJumpController {

    @Resource
    private LoginSessionIdsService sessionIdsService;

    /**
     *
     * 功能描述: 进入商家积分商城首页
     *
     * @param  request     HttpServletRequest服务类
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/joinWnkIntegralIndex",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinWnkIntegralIndex(HttpServletRequest request,Integer business_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,1,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/integral_wnk/index");
            modelAndView.addObject("business_id",business_id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入商家积分-我的积分
     *
     * @param  request     HttpServletRequest服务类
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/joinMyIntegralByWnk",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinMyIntegralByWnk(HttpServletRequest request,Integer business_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,1,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/integral_wnk/count");
            modelAndView.addObject("business_id",business_id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入商家积分-我的订单
     *
     * @param  request     HttpServletRequest服务类
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/joinMyIntegralOrderByWnk",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinMyIntegralOrderByWnk(HttpServletRequest request,Integer business_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,1,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/integral_wnk/record");
            modelAndView.addObject("business_id",business_id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入商家积分-我的订单详情页
     *
     * @param  request     HttpServletRequest服务类
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/joinOrderDetailedByWnk",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinOrderDetailedByWnk(HttpServletRequest request,Integer business_id,String order_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,1,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/integral_wnk/order_detailed");
            modelAndView.addObject("business_id",business_id);
            modelAndView.addObject("order_id",order_id);
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述: 进入商家积分-进入商品分类
     *
     * @param  request     HttpServletRequest服务类
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/joinGoodsTypeByWnk",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinGoodsTypeByWnk(HttpServletRequest request,Integer business_id,Integer type_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,1,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/integral_wnk/category");
            modelAndView.addObject("business_id",business_id);
            modelAndView.addObject("type_id",type_id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入商家积分-进入商品详情页
     *
     * @param  request     HttpServletRequest服务类
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/joinIntegralDetailByWnk",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinIntegralDetailByWnk(HttpServletRequest request,Integer business_id,Integer goods_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,1,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/integral_wnk/detail");
            modelAndView.addObject("business_id",business_id);
            modelAndView.addObject("goods_id",goods_id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入商家积分-进入下单页面
     *
     * @param  request     HttpServletRequest服务类
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/joinGoodsChangeWnk",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinGoodsChangeWnk(HttpServletRequest request,Integer business_id,Integer goods_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,1,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/integral_wnk/confirm_order");
            modelAndView.addObject("business_id",business_id);
            modelAndView.addObject("goods_id",goods_id);
        }
        return modelAndView;
    }

}
