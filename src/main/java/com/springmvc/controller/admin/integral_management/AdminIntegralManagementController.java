package com.springmvc.controller.admin.integral_management;

import com.springmvc.service.impl.AboutUsService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author 杨新杰
 * @date 2018/12/11 10:37
 */
@Controller
@RequestMapping("/admin")
public class AdminIntegralManagementController {


    // joinIntegralManagement 进入平台积分设置
    @Resource
    private AboutUsService aboutUsService;
    /**
     *
     * 功能描述: 进入平台积分设置
     *
     * @author 杨新杰
     * @date   2018/11/10 16:02
     */
    @RequestMapping(value = "/joinIntegralManagement",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView joinIntegralManagement(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/integral_management/integral_management");
        }
        return modelAndView;
    }


    /**
     *
     * 功能描述: 平台积分兑换信息保存/更新
     *
     * @param   exchange_bili       兑换比例
     * @param   exchange_start_time 开发时间
     * @param   exchange_shop_time  结束时间
     * @param   min_exchange_number 最低兑换数量
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    11:34 2018/12/12
     */
    @RequestMapping(value = "/updateIntegralExchangeInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result updateIntegralExchangeInfo(HttpServletRequest request,String exchange_bili,String exchange_start_time,String exchange_shop_time,String min_exchange_number){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                if (this.aboutUsService.updateIntegralExchangeInfo(exchange_bili,exchange_start_time,exchange_shop_time,min_exchange_number) > 0){
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
     * 功能描述: 平台积分兑换信息保存/更新
     *
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    11:34 2018/12/12
     */
    @RequestMapping(value = "/selectIntegralExchangeInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result selectIntegralExchangeInfo(HttpServletRequest request){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("exchange_bili",this.aboutUsService.selectAboutByType("23"));
                map.put("exchange_start_time",this.aboutUsService.selectAboutByType("24"));
                map.put("exchange_shop_time",this.aboutUsService.selectAboutByType("25"));
                map.put("min_exchange_number",this.aboutUsService.selectAboutByType("26"));

                result.setData(map);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }



}
