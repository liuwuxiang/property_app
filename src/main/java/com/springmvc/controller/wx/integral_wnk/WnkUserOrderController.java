package com.springmvc.controller.wx.integral_wnk;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.dao.SuggestionFeedbackMapper;
import com.springmvc.entity.Users;
import com.springmvc.entity.WnkIntegralOrder;
import com.springmvc.service.impl.*;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.qrCode.QRCode;
import net.sf.json.JSONObject;
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
 * 万能卡商家积分商城 - 订单操作控制器
 * @author 杨新杰
 * @Date 2018/10/12 09:41
 */
@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class WnkUserOrderController {

    @Resource
    private WnkIntegralOrderServer wnkIntegralOrderServer;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersService usersService;
    @Resource
    private WnkIntegralUserServer wnkIntegralUserServer;
    @Resource
    private WnkIntegralIncomeService wnkIntegralIncomeService;

    /**
     *
     * 功能描述: 查询用户在此商家的订单
     *
     * @param  request     HttpServletRequest服务类
     * @param  business_id 商家ID
     * @param  user_id     用户ID
     * @return 返回查询结果
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/getIntegralWnkOrderById",method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralWnkOrderById(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer user_id) {
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
                    List<Map<String, Object>> list = wnkIntegralOrderServer.getIntegralWnkOrderById(business_id, user_id);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg(ImageToolsController.imageShowURL + "?imageid=");
                    }


                    // 业务结束
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
     * 功能描述: 查询用户的商家积分订单
     *
     * @param  request     HttpServletRequest服务类
     * @param  user_id     用户ID
     * @return 返回查询结果
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/getIntegralWnkOrderByUserId",method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getIntegralWnkOrderByUserId(HttpServletRequest request, HttpServletResponse response, Integer user_id) {
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
                    List<Map<String, Object>> list = wnkIntegralOrderServer.getIntegralWnkOrderByUserId(user_id);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg(ImageToolsController.imageShowURL + "?imageid=");
                    }


                    // 业务结束
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
     * 功能描述: 查询用户在此商家的订单 - 根据订单ID
     *
     * @param  request     HttpServletRequest服务类
     * @param  business_id 商家ID
     * @param  user_id     用户ID
     * @param  order_id    订单ID
     * @return 返回查询结果
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/getIntegralWnkOrderByGoodsId",method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralWnkOrderByGoodsId(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer user_id,String order_id) {
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
                    Map<String, Object> map = wnkIntegralOrderServer.getIntegralWnkOrderByGoodsId(business_id, user_id, order_id);
                    map.put("goodsImgPath",ImageToolsController.imageShowURL + "?imageid=");
                    map.put("qrcodeShowURL",ImageToolsController.qrcodeShowURL + "?imageid=");
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                    // 业务结束
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
     * @param  request     HttpServletRequest服务类
     * @param  response    HttpServletResponse服务类
     * @param  wnkIntegralOrder 万能卡订单实体类
     * @return 返回商品信息
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/addGoodsOrderWnk",method = RequestMethod.POST)
    @ResponseBody
    public Result addGoodsOrderWnk(HttpServletRequest request, HttpServletResponse response, WnkIntegralOrder wnkIntegralOrder) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(wnkIntegralOrder.getUser_id(), 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(wnkIntegralOrder.getUser_id());
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else {
                    Map<String,Object> integralMap = this.wnkIntegralUserServer.getUserIntegral(wnkIntegralOrder.getBusiness_id(),users.getId());
                    if (integralMap == null || (Double)integralMap.get("integral") < wnkIntegralOrder.getPrice()){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("余额不足");
                    }
                    else{
                        // 业务开始
                        // 生成订单ID
                        wnkIntegralOrder.setOrder_id(String.valueOf(System.currentTimeMillis()));
                        // 生成二维码所需参数
//                    String qrcodeUrl = ImageToolsController.projectImageShowURL+
//                            "wnk_business/getIntegralOrderByOrderId?business_id="+wnkIntegralOrder.getBusiness_id()+
//                            "&order_id="+wnkIntegralOrder.getOrder_id();
                        //订单二维码
                        Map<Object,Object> orderQrcodeMap = new HashMap<Object,Object>();
                        orderQrcodeMap.put("user_id",wnkIntegralOrder.getUser_id());
                        orderQrcodeMap.put("type",3);
                        orderQrcodeMap.put("order_no",wnkIntegralOrder.getOrder_id());
                        JSONObject json = JSONObject.fromObject(orderQrcodeMap);
                        // 生成二维码
                        QRCode.generateQRCode(json.toString(),wnkIntegralOrder.getOrder_id());
                        wnkIntegralOrder.setQrcode(wnkIntegralOrder.getOrder_id()+".png");
                        // 扣除用户积分
                        int i = wnkIntegralUserServer.updateIntegral(wnkIntegralOrder.getUser_id(),wnkIntegralOrder.getBusiness_id(),wnkIntegralOrder.getPrice());
                        // 添加扣除积分记录 1 = 扣除
                        this.wnkIntegralIncomeService.addIntegralRecord("积分商品兑换",wnkIntegralOrder.getPrice().doubleValue(),wnkIntegralOrder.getUser_id(),wnkIntegralOrder.getBusiness_id(),1);

                        if (i<=0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("积分不足");
                        } else {
                            // 创建订单
                            int ii = wnkIntegralOrderServer.addIntegralOrder(wnkIntegralOrder);
                            if (ii <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("订单创建失败");
                            } else {
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("订单创建成功");
                            }
                        }


                        // 业务结束
                    }
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
