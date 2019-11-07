package com.springmvc.controller.wnk_business_wx.integral;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkIntegralGoods;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.WnkBusinessAccountService;
import com.springmvc.service.impl.WnkIntegralGoodsServer;
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
 * 商家积分商城后台 - 商品管理
 *
 * @author 杨新杰
 * @Date 2018/10/11 09:33
 */
@Controller
@RequestMapping(value = "/wnk_business")
public class WnkGoodsController {

    @Resource
    private LoginSessionIdsService sessionIdsService;

    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;

    @Resource
    private WnkIntegralGoodsServer wnkIntegralGoodsServer;


    /**
     * 功能描述:  新增万能卡商家积分商城商品
     *
     * @param request          HttpServletRequest服务类
     * @param response         HttpServletResponse服务类
     * @param business_id      商家ID
     * @param wnkIntegralGoods 商品实体类
     * @return 返回新增结果
     * @author 杨新杰
     * @date 2018/10/11 16:00
     */
    @RequestMapping(value = "/addIntegralGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result addIntegralGoods(HttpServletRequest request, HttpServletResponse response, Integer business_id, WnkIntegralGoods wnkIntegralGoods) {

        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    // 业务开始
                    wnkIntegralGoods.setBusiness_id(wnkBusinessAccount.getId());
                    int i = wnkIntegralGoodsServer.addIntegralGoods(wnkIntegralGoods);

                    if (i <= 0) {
                        result.setData(i);
                        result.setStatus(Result.FAIL);
                        result.setMsg("新增失败");
                    } else {
                        result.setData(i);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("新增成功");
                    }


                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     * 功能描述:  新增万能卡商家积分商城商品
     *
     * @param request          HttpServletRequest服务类
     * @param response         HttpServletResponse服务类
     * @param business_id      商家ID
     * @param wnkIntegralGoods 商品实体类
     * @return 返回新增结果
     * @author 杨新杰
     * @date 2018/10/11 16:00
     */
    @RequestMapping(value = "/editIntegralGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result editIntegralGoods(HttpServletRequest request, HttpServletResponse response, Integer business_id, WnkIntegralGoods wnkIntegralGoods) {

        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    // 业务开始
                    wnkIntegralGoods.setBusiness_id(wnkBusinessAccount.getId());
                    System.out.println(wnkIntegralGoods);
                    int i = wnkIntegralGoodsServer.editIntegralGoods(wnkIntegralGoods);
                    if (i <= 0) {
                        result.setData(i);
                        result.setStatus(Result.FAIL);
                        result.setMsg("修改失败");
                    } else {
                        result.setData(i);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("修改成功");
                    }


                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     * 功能描述: 根据商品ID和商家ID获取商品信息
     *
     * @param request     HttpServletRequest服务类
     * @param response    HttpServletResponse服务类
     * @param business_id 商家ID
     * @param id          商品ID
     * @return 返回查询到的商品信息
     * @author 杨新杰
     * @date 2018/10/11 15:59
     */
    @RequestMapping(value = "/getIntegralGoodsById", method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralGoodsById(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer id) {

        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    // 业务开始
                    Map<String, Object> map = wnkIntegralGoodsServer.getIntegralGoodsById(business_id, id);
                    if (map == null) {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("查询失败");
                    } else {
                        map.put("urlPath", ImageToolsController.imageShowURL + "?imageid=");
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("查询成功");
                    }
                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     * 功能描述: 获取所有商品
     *
     * @param request     HttpServletRequest服务类
     * @param response    HttpServletResponse服务类
     * @param business_id 商家ID
     * @param id          商品ID
     * @return 返回查询到的商品信息
     * @author 杨新杰
     * @date 2018/10/11 15:59
     */
    @RequestMapping(value = "/getIntegralGoodsAll", method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralGoodsAll(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer id) {

        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    // 业务开始
                    List<Map<String, Object>> list = wnkIntegralGoodsServer.getIntegralGoodsAll(business_id);
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg(ImageToolsController.imageShowURL + "?imageid=");
                    // 业务结束
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


}
