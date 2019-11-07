package com.springmvc.controller.wnk_business_wx.integral;

import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.WnkBusinessAccountService;
import com.springmvc.service.impl.WnkIntegralOrderServer;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 商家积分商城后台 - 订单管理
 * @author 杨新杰
 * @Date 2018/10/10 11:44
 */
@Controller
@RequestMapping(value = "/wnk_business")
public class OrderController {

    @Resource
    private LoginSessionIdsService sessionIdsService;

    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;

    @Resource
    private WnkIntegralOrderServer wnkIntegralOrderServer;

    /**
     *
     * 功能描述: 根据商家ID获取订单
     *
     * @param  request      HttpServletRequest服务类
     * @param  response     HttpServletResponse服务类
     * @param  business_id  商家ID
     * @param  type         获取方式 0-所有 1-已支付 2-已完成
     * @return 获取结果
     * @author 杨新杰
     * @date   2018/10/10 15:42
     */
    @RequestMapping(value = "/getIntegralOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralOrder(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    // 业务开始
                    List<Map<String, Object>> data = wnkIntegralOrderServer.getIntegralOrderByBusinessId(business_id, type);
                    result.setData(data);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                    // 业务结束
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 根据商家ID,和订单ID获取订单
     *
     * @param  request      HttpServletRequest服务类
     * @param  response     HttpServletResponse服务类
     * @param  business_id  商家ID
     * @param  order_no     订单ID
     * @param  user_id      用户ID
     * @return 获取结果
     * @author 杨新杰
     * @date   2018/10/10 15:42
     */
    @RequestMapping(value = "/getIntegralOrderByOrderId",method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralOrderByOrderId(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Integer business_id,
                                            String  order_no,
                                            Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    // 业务开始
                    // 1 获取订单详情
                    System.out.println("business_id:"+business_id);
                    System.out.println("user_id"+user_id);
                    System.out.println("order_no"+order_no);
                    Map<String, Object> ret = wnkIntegralOrderServer.getIntegralWnkOrderByOrderIdAndUserIdAndBusinessId(business_id, user_id, order_no);
                    if (ret == null ){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此订单不存在或者不是本商家订单");
                    } else {
                        result.setData(ret);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }
                    // 业务结束
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 订单发货操作(使用订单)
     *
     * @param  request      HttpServletRequest服务类
     * @param  response     HttpServletResponse服务类
     * @param  business_id  商家ID
     * @param  order_id     订单ID
     * @param  user_id      用户ID
     * @return 获取结果
     * @author 杨新杰
     * @date   2018/10/10 15:42
     */
    @RequestMapping(value = "/integralOrderUse",method = RequestMethod.POST)
    @ResponseBody
    public Result integralOrderUse(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Integer business_id,
                                            String  order_id,
                                            Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    // 业务开始
                    int i = wnkIntegralOrderServer.updateIntegralOrderEndTime(business_id,user_id,order_id);
                    if (i<= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("发货失败");
                    } else {
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("发货成功");
                    }
                    // 业务结束
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
