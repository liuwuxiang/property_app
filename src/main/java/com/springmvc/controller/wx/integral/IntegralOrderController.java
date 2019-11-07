package com.springmvc.controller.wx.integral;


import com.springmvc.entity.IntegralGoods;
import com.springmvc.entity.IntegralOrder;
import com.springmvc.entity.Users;
import com.springmvc.service.IntegralGoodsService;
import com.springmvc.service.IntegralOrderService;
import com.springmvc.service.impl.IntegralDetailService;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class IntegralOrderController {
    private static Logger logger = Logger.getLogger(GoodsController.class);
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;

    @Resource
    private IntegralOrderService integralOrderService;

    @Resource
    private IntegralGoodsService integerGoodsServer;

    @Resource
    private IntegralDetailService integralDetailService;


    /**
     *
     * 功能描述: 获取我的积分订单
     *
     * @param  request  HttpServletRequest服务类
     * @param  response HttpServletResponse服务类
     * @param  user_id  用户ID
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:37
     */
    @RequestMapping(value = "/getIntegralOrderById", method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralOrderById(HttpServletRequest request, HttpServletResponse response, Integer user_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                // 业务开始
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else {
                    List<Map<String, Object>> list = integralOrderService.getIntegralOrderById(user_id);
                    result.setData(list);
                    result.setMsg("获取成功");
                    result.setStatus(Result.SUCCESS);
                }
                // 业务结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    /**
     *
     * 功能描述: 获取订单和商品信息和用户ID
     *
     * @param  request  HttpServletRequest服务类
     * @param  response HttpServletResponse服务类
     * @param  user_id  用户ID
     * @param  id
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:37
     */
    @RequestMapping(value = "/getGoodsAndUserById", method = RequestMethod.POST)
    @ResponseBody
    public Result getGoodsAndUserById(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else {
                    IntegralGoods goodsById = integerGoodsServer.getGoodsById(id);

                    Map<String, Object> map = new HashMap<>();
                    map.put("goods", goodsById);
                    map.put("user_id", users.getId());
                    map.put("user_integral", users.getUser_integral());

                    result.setData(map);
                    result.setMsg("获取成功");
                    result.setStatus(Result.SUCCESS);
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    /**
     *
     * 功能描述: 新增订单
     *
     * @param  request  HttpServletRequest服务类
     * @param  response HttpServletResponse服务类
     * @param  user_id  用户ID
     * @param  integralOrder 订单实体类
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:37
     */
    @Transactional
    @RequestMapping(value = "/addGoodsOrder", method = RequestMethod.POST)
    @ResponseBody
    public Result addGoodsOrder(HttpServletRequest request, HttpServletResponse response, Integer user_id, IntegralOrder integralOrder) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else {
                    if (users.getUser_integral() - integralOrder.getPrice() < 0) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("参数非法");
                    } else {
                        // 业务开始
                        // 设置订单号
                        integralOrder.setOrderId(System.currentTimeMillis());
                        integralOrder.setUserId(user_id);
                        integralOrder.setStatus(0); // 0=已付款

                        integralOrder.setStartTime(new Timestamp(System.currentTimeMillis()));


                        Map<String, Object> map = new HashMap<>();
                        map.put("integral", integralOrder.getPrice());
                        map.put("user_id", user_id);

                        // 扣除积分
                        if (usersService.updateUserIntegralById(map) <= 0) {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("新增订单失败");
                        } else {
                            // 新增订单使用记录
                            integralDetailService.insertIntrgralDetailRecord(user_id,"商品兑换",integralOrder.getPrice(),1);

                            int ret = integralOrderService.addGoodsOrder(integralOrder);
                            // 新增订单
                            if (ret <= 0) {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("新增订单失败");
                            } else {
                                result.setData(map);
                                result.setMsg("新增订单成功");
                                result.setStatus(Result.SUCCESS);
                            }

                        }
                        // 业务结束
                    }

                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }




    /**
     *
     * 功能描述: 根据id获取订单信息
     *
     * @param  request  HttpServletRequest服务类
     * @param  response HttpServletResponse服务类
     * @param  user_id  用户ID
     * @param  id       订单ID
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:37
     */
    @RequestMapping(value = "/getOrderInfoById", method = RequestMethod.POST)
    @ResponseBody
    public Result getOrderInfoById(HttpServletRequest request, HttpServletResponse response, Integer user_id, String id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else {
                    // 业务开始
                    Map<String,Object>  map =  this.integralOrderService.getOrderInfoById(id);
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

}
