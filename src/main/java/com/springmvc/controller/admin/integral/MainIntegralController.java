package com.springmvc.controller.admin.integral;

import com.springmvc.utils.CookileUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 积分商城 所有进入页面的处理
 */
@RequestMapping("/admin")
@Controller
public class MainIntegralController {

    /**
     *
     * 功能描述: 进入商品类别管理界面
     *
     * @param  request  HttpServletRequest服务类
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>登陆了返回积分商品分类管理页面, 没登录返回登陆页面
     * @author 杨新杰
     * @date   2018/10/8 10:26
     */
    @RequestMapping(value = "/JoinIntegralType")
    @ResponseBody
    public ModelAndView JoinIntegralType(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/integral/integralType");
        }
        return modelAndView;
    }

   /**
     *
     * 功能描述: 进入用户平台积分提现设置
     *
     * @param  request  HttpServletRequest服务类
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>登陆了返回积分商品分类管理页面, 没登录返回登陆页面
     * @author 杨新杰
     * @date   2018/10/8 10:26
     */
    @RequestMapping(value = "/JoinUserPlatformIntegralTypeWithdrawSetting")
    @ResponseBody
    public ModelAndView JoinUserPlatformIntegralTypeWithdrawSetting(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/integral/withdraw_setting");
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述: 进入商品管理
     *
     * @param  request  HttpServletRequest服务类
     * @param  id       商品ID
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>登陆了返回积分商品分类管理页面, 没登录返回登陆页面
     * @author 杨新杰
     * @date   2018/10/8 10:26
     */
    @RequestMapping(value = "/JoinIntegralGoods")
    @ResponseBody
    public ModelAndView JoinIntegralGoods(HttpServletRequest request,Integer id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/integral/integralGoods");
            modelAndView.addObject("id",id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入商品新增/修改
     *
     * @param  request  HttpServletRequest服务类
     * @param  goods_id 商品ID
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>登陆了返回积分商品分类管理页面, 没登录返回登陆页面
     * @author 杨新杰
     * @date   2018/10/8 10:26
     */
    @RequestMapping(value = "/integralGoodsEdit")
    @ResponseBody
    public ModelAndView integralGoodsEdit(HttpServletRequest request,Integer goods_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/integral/integralGoods_add");
            modelAndView.addObject("id",goods_id);
        }
        return modelAndView;
    }

    /**
     * 进入商品订单管理界面
     * @return 登陆了返回积分商品分类管理页面, 没登录返回登陆页面
     */
    @RequestMapping(value = "/JoinIntegralOrder")
    @ResponseBody
    public ModelAndView JoinIntegralOrder(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/integral/integralOrder");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入发货页面
     *
     * @param  request  HttpServletRequest服务类
     * @param  order_id 订单ID
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>登陆了返回积分商品分类管理页面, 没登录返回登陆页面
     * @author 杨新杰
     * @date   2018/10/8 10:26
     */
    @RequestMapping(value = "/integralOrderEdit")
    @ResponseBody
    public ModelAndView integralOrderEdit(HttpServletRequest request,Integer order_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/integral/integralOrder_edit");
            modelAndView.addObject("id",order_id);
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述: 进入商品订单管理界面
     *
     * @param  request HttpServletRequest服务类
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>登陆了返回积分商品分类管理页面, 没登录返回登陆页面
     * @author 杨新杰
     * @date   2018/10/8 10:25
     */
    @RequestMapping(value = "/joinAddIntegralGoods")
    @ResponseBody
    public ModelAndView joinAddIntegralGoods(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/integral/integralGoods_add");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入分类修改或者新增页面
     *
     * @param  request HttpServletRequest服务类
     * @param  type_id 分类ID
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>登陆了返回积分商品分类管理页面, 没登录返回登陆页面
     * @author 杨新杰
     * @date   2018/10/8 10:24
     */
    @RequestMapping(value = "/integralTypeEdit")
    @ResponseBody
    public ModelAndView integralTypeEdit(HttpServletRequest request,Integer type_id){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/integral/integralType_edit");
            modelAndView.addObject("type_id",type_id);
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入商家积分商品管理
     *
     * @param  request  HttpServletRequest服务类
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>登陆了返回积分商品分类管理页面, 没登录返回登陆页面
     * @author 杨新杰
     * @date   2018/10/8 10:26
     */
    @RequestMapping(value = "/joinIntegralBusinessManage")
    @ResponseBody
    public ModelAndView joinIntegralBusinessManage(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/integral/BuinsessIntegralGoods");
        }
        return modelAndView;
    }


}
