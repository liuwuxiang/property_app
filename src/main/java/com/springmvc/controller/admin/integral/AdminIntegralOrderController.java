package com.springmvc.controller.admin.integral;


import com.springmvc.entity.IntegralOrder;
import com.springmvc.entity.IntegralType;
import com.springmvc.service.IntegralOrderService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminIntegralOrderController {

    /**订单处理Service*/
    @Resource
    private IntegralOrderService integralOrderService;

    /**
     *
     * 功能描述:获取所有订单信息
     *
     * @param  request HttpServletRequest
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     * @author 杨新杰
     * @date   2018/10/8 10:06
     */
    @RequestMapping(value = "/getIntegralOrderAll",method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralOrderAll(HttpServletRequest request){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                // 业务逻辑开始
                List<Map<String,Object>> list = integralOrderService.getIntegralOrderAll();
                if (list.size() <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("无数据");
                } else {
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 根据条件查询订单管理信息
     *
     * @param   request     请求
     * @param   response    响应
     * @param   limit       分页条目数
     * @param   page        分页数
     * @param   order_id    订单id
     * @param   name        商品名称
     * @param   count       购买数量
     * @param   user_id     收件人id
     * @param   startTime   下单时间
     * @param   username    收件人姓名
     * @param   phone       收件人电话
     * @param   address     收件人地址
     * @param   status      订单状态
     * @param   express_name 快递商家名称
     * @param   express_id  快递单号
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/15 0015 17:03
     */
    @RequestMapping(value = "/adminSearchIntegralOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchIntegralOrder(HttpServletRequest request,
                                           HttpServletResponse response,
                                           Integer limit,
                                           Integer page,
                                           String order_id,
                                           String name,
                                           Integer count,
                                           Integer user_id,
                                           String startTime,
                                           String username,
                                           String phone,
                                           String address,
                                           String status,
                                           String express_name,
                                           String express_id

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
                Map<String,Object> map = new HashMap<>();
                map.put("statrt_index", limit * (page - 1));
                map.put("limit", limit);
                map.put("page", page);
                if (order_id != null && !"".equals(order_id)) {
                    map.put("order_id", order_id );
                }
                if (name != null && !"".equals(name)) {
                    map.put("name", "%" + name + "%");
                }
                if (count != null && !"".equals(count)) {
                    map.put("count",count);
                }
                if (user_id != null && !"".equals(user_id)) {
                    map.put("user_id",user_id);
                }
                map.put("startTime",startTime);

                if (username != null && !"".equals(username)) {
                    map.put("username","%" + username + "%");
                }
                if (phone != null && !"".equals(phone)) {
                    map.put("phone",phone);
                }
                if (address != null && !"".equals(address)) {
                    map.put("address",address);
                }
                map.put("status", ("".equals(status) ? null : status) );
                if (express_name != null && !"".equals(express_name)) {
                    map.put("express_name","%" + express_name + "%");
                }
                if (express_id != null && !"".equals(express_id)) {
                    map.put("express_id",express_id);
                }

                List<Map<Object,Object>> list = this.integralOrderService.adminSearchIntegralOrder(map);
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
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 订单发货
     *
     * @param  request  HttpServletRequest
     * @param  integralOrder 平台积分订单实体类
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     * @author 杨新杰
     * @date   2018/10/8 10:07
     */
    @RequestMapping(value = "/updateIntegralOrderExpress",method = RequestMethod.POST)
    @ResponseBody
    public Result updateIntegralOrderExpress(HttpServletRequest request, IntegralOrder integralOrder){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                // 业务逻辑开始
                int i = this.integralOrderService.updateIntegralOrderExpress(integralOrder);
                if (i <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("发货失败");
                } else {
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("发货成功");
                }
                // 业务逻辑结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


}
