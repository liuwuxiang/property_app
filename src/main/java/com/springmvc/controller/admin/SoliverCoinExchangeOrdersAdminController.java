package com.springmvc.controller.admin;

import com.springmvc.service.impl.SilverCoinExchangeService;
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
 *
 * 功能描述: 财务管理-银币兑换订单
 * @Author: 刘武祥
 * @Date: 2019/2/18 0018 11:56
 */
@Controller
@RequestMapping(value = "/admin")
public class SoliverCoinExchangeOrdersAdminController {
    @Resource
    private SilverCoinExchangeService silverCoinExchangeService;

    /**
     *
     * 功能描述: 进入银币兑换订单管理界面
     *
     * @param   request
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: 刘武祥
     * @date: 2019/2/18 0018 11:56
     */
    @RequestMapping(value = "/soliverCoinExchangeOrders")
    @ResponseBody
    public ModelAndView soliverCoinExchangeOrders(HttpServletRequest request){
        
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
        modelAndView = new ModelAndView("admin/control/soliver_coin_exchange_orders/soliver_coin_exchange_orders");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述: 获取所有银币兑换记录
     *
     * @param   request
     * @param   response
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/2/18 0018 11:55
     */
    @RequestMapping(value = "/getAllSilverCoinExchange", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllSilverCoinExchange(HttpServletRequest request, HttpServletResponse response){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                List<Map<Object,Object>> list = this.silverCoinExchangeService.selectAllAdmin();
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
     * 功能描述: 根据条件查询银币兑换记录
     *
     * @param   request                 请求
     * @param   response                响应
     * @param   limit                   分页条目数
     * @param   page                    分页数
     * @param   user_mobile             兑换用户
     * @param   consume_integral_number 消耗积分个数
     * @param   silver_coin_number      银币兑换个数
     * @param   type                    兑换方式(0-消费积分兑换银币,1-通用积分兑换银币)
     * @param   exchange_date           兑换时间
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/11 0011 17:18
     */
    @RequestMapping(value = "/adminSearchSilverCoinExchangeByConditions", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchSilverCoinExchangeByConditions(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            Integer limit,
                                                            Integer page,
                                                            String user_mobile,
                                                            Integer consume_integral_number,
                                                            Integer silver_coin_number,
                                                            Integer type,
                                                            String exchange_date

    ) {

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
                if (user_mobile != null && !"".equals(user_mobile)) {
                    map.put("user_mobile", "%" + user_mobile + "%");
                }
                if (consume_integral_number != null && !"".equals(consume_integral_number)) {
                    map.put("consume_integral_number", "%" + consume_integral_number + "%");
                }
                if (silver_coin_number != null && !"".equals(silver_coin_number)) {
                    map.put("silver_coin_number", "%" + silver_coin_number + "%");
                }
                map.put("type", ("".equals(type) ? null : type));
                map.put("exchange_date", exchange_date);
                List<Map<String, Object>> list = this.silverCoinExchangeService.adminSearchSilverCoinExchangeByConditions(map);
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
}

