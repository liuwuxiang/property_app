package com.springmvc.controller.wnk_business_wx.integral;

import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 商家积分商城-跳转管理控制器<br>
 * 负责所有连接跳转处理
 * @author 杨新杰
 * @Date 2018/10/9 16:32
 * @Description:
 */
@Controller
@RequestMapping(value = "/wnk_business")
public class JumpController {

    @Resource
    private LoginSessionIdsService sessionIdsService;

    /**
     * 功能描述:进入商品分类管理
     *
     * @param  request HttpServletRequest服务类
     * @return <br>org.springframework.web.servlet.ModelAndView<br>
     * @author 杨新杰
     * @date   2018/10/9 16:35
     */
    @RequestMapping(value = "/joinIntegralType")
    @ResponseBody
    public ModelAndView joinIntegralType(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wnk_business/integral/type");
        }
        return modelAndView;
    }

    /**
     * 功能描述:进入订单管理
     *
     * @param  request HttpServletRequest服务类
     * @return <br>org.springframework.web.servlet.ModelAndView<br>
     * @author 杨新杰
     * @date   2018/10/9 16:35
     */
    @RequestMapping(value = "/joinIntegralOrder")
    @ResponseBody
    public ModelAndView joinIntegralOrder(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wnk_business/integral/order");
        }
        return modelAndView;
    }

    /**
     * 功能描述:进入商品分类管理
     *
     * @param  request HttpServletRequest服务类
     * @return <br>org.springframework.web.servlet.ModelAndView<br>
     * @author 杨新杰
     * @date   2018/10/9 16:35
     */
    @RequestMapping(value = "/joinIntegralGoods")
    @ResponseBody
    public ModelAndView joinIntegralGoods(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wnk_business/integral/goods");
        }
        return modelAndView;
    }

    /**
     * 功能描述:进入商品分类新增/编辑页面
     *
     * @param  request HttpServletRequest服务类
     * @return <br>org.springframework.web.servlet.ModelAndView<br>
     * @author 杨新杰
     * @date   2018/10/10 10:00
     */
    @RequestMapping(value = "/joinIntegralTypeEdit")
    @ResponseBody
    public ModelAndView joinIntegralTypeEdit(HttpServletRequest request,Integer type_id){
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
            System.out.println(1111111111);
            modelAndView = new ModelAndView("wnk_business/integral/type_edit");
            modelAndView.addObject("type_id",type_id);
        }
        return modelAndView;
    }

    /**
     * 功能描述:进入商品新增/编辑页面
     *
     * @param  request HttpServletRequest服务类
     * @return <br>org.springframework.web.servlet.ModelAndView<br>
     * @author 杨新杰
     * @date   2018/10/10 10:00
     */
    @RequestMapping(value = "/joinIntegralGoodsEdit")
    @ResponseBody
    public ModelAndView joinIntegralGoodsEdit(HttpServletRequest request,Integer goods_id){
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
            modelAndView = new ModelAndView("wnk_business/integral/goods_edit");
            modelAndView.addObject("goods_id",goods_id);
        }
        return modelAndView;
    }

    /**
     * 功能描述:进入确认订单页面
     *
     * @param  request HttpServletRequest服务类
     * @return <br>org.springframework.web.servlet.ModelAndView<br>
     * @author 杨新杰
     * @date   2018/10/10 10:00
     */
    @RequestMapping(value = "/joinIntegralOrderConfirm")
    @ResponseBody
    public ModelAndView joinIntegralOrderConfirm(HttpServletRequest request,String order_id,Integer userId){
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
            modelAndView = new ModelAndView("wnk_business/integral/order_confirm");
            modelAndView.addObject("user_id" ,userId);
            modelAndView.addObject("order_id",order_id);
        }
        return modelAndView;
    }

}
