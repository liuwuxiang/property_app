package com.springmvc.controller.wx.integral;


import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class WxMainIntegralController {

    @Resource
    private LoginSessionIdsService sessionIdsService;


    private ModelAndView findIsLogin(HttpServletRequest request) {
        ModelAndView modelAndView = null;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request, 2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin;
        if (user_id_str == null) {
            isLogin = false;
        } else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id, 1, this.sessionIdsService) == null) {
                isLogin = false;
            } else {
                isLogin = true;
            }
        }
        if (!isLogin) {
            modelAndView = new ModelAndView("wx/login");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 进入我的商品详情页
     *
     * @param  request HttpServletRequest服务类
     * @param  id 商品ID
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:32
     */
    @RequestMapping("/joinIntegralDetail")
    @ResponseBody
    public ModelAndView joinIntegralDetail(HttpServletRequest request, @Param("id") Integer id) {
        ModelAndView isLogin = findIsLogin(request);
        if (isLogin == null) {
            isLogin = new ModelAndView("/wx/integral_mall/detail");
            isLogin.addObject("id", id);
        }
        return isLogin;
    }

    /**
     *
     * 功能描述: 进入我的积分
     *
     * @param  request HttpServletRequest服务类
     * @param  id 用户ID
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:32
     */
    @RequestMapping("/joinMyIntegral")
    @ResponseBody
    public ModelAndView joinMyIntegral(HttpServletRequest request, @Param("id") Integer id) {
        ModelAndView isLogin = findIsLogin(request);
        if (isLogin == null) {
            isLogin = new ModelAndView("/wx/integral_mall/count");
            isLogin.addObject("id",id);
        }
        return isLogin;
    }

    /**
     *
     * 功能描述: 进入我的积分订单
     *
     * @param  request HttpServletRequest服务类
     * @param  id 用户ID
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:32
     */
    @RequestMapping("/joinMyIntegralOrder")
    @ResponseBody
    public ModelAndView joinMyIntegralOrder(HttpServletRequest request, @Param("id") Integer id) {
        ModelAndView isLogin = findIsLogin(request);
        if (isLogin == null) {
            isLogin = new ModelAndView("/wx/integral_mall/record");
            isLogin.addObject("id", id);
        }
        return isLogin;
    }

    /**
     *
     * 功能描述: 进入商品分类商品页面
     *
     * @param  request HttpServletRequest服务类
     * @param  type_id 分类ID
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:32
     */
    @RequestMapping("/joinGoodsType")
    @ResponseBody
    public ModelAndView joinGoodsType(HttpServletRequest request, Integer type_id) {
        ModelAndView isLogin = findIsLogin(request);
        if (isLogin == null) {
            isLogin = new ModelAndView("/wx/integral_mall/category");
            isLogin.addObject("id", type_id);
        }
        return isLogin;
    }

    /**
     *
     * 功能描述: 进入下单页面
     *
     * @param  request HttpServletRequest服务类
     * @param  id 商品ID
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:32
     */
    @RequestMapping("/joinGoodsChange")
    @ResponseBody
    public ModelAndView joinGoodsChange(HttpServletRequest request, Integer id) {
        ModelAndView isLogin = findIsLogin(request);
        if (isLogin == null) {
            isLogin = new ModelAndView("/wx/integral_mall/confirm_order");
            isLogin.addObject("id", id);
        }
        return isLogin;
    }

    /**
     *
     * 功能描述: 进入订单详情页
     *
     * @param  request  HttpServletRequest服务类
     * @param  goods_id 商品ID
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:32
     */
    @RequestMapping("/joinOrderDetailed")
    @ResponseBody
    public ModelAndView joinOrderDetailed(HttpServletRequest request, String goods_id) {
        ModelAndView isLogin = findIsLogin(request);
        if (isLogin == null) {
            isLogin = new ModelAndView("/wx/integral_mall/order_detailed");
            isLogin.addObject("id", goods_id);
        }
        return isLogin;
    }

    /**
     *
     * 功能描述: 进入交易提现页面
     *
     * @param  request  HttpServletRequest服务类
     * @param  goods_id 商品ID
     * @return <br>返回类型:org.springframework.web.servlet.ModelAndView<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:32
     */
    @RequestMapping("/joinMyIntegralCashOrder")
    @ResponseBody
    public ModelAndView joinMyIntegralCashOrder(HttpServletRequest request, String goods_id) {
        ModelAndView isLogin = findIsLogin(request);
        if (isLogin == null) {
            isLogin = new ModelAndView("/wx/integral_mall/integral_order_cash");
            isLogin.addObject("id", goods_id);
        }
        return isLogin;
    }

}
