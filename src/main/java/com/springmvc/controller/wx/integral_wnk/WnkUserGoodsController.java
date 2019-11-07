package com.springmvc.controller.wx.integral_wnk;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.dao.WnkIntegralUserMapper;
import com.springmvc.entity.Users;
import com.springmvc.entity.WnkIntegralOrder;
import com.springmvc.entity.WnkIntegralUser;
import com.springmvc.entity.WnkStoreInformation;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.qrCode.QRCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 万能卡商家积分商城 - 用户操作控制器
 * @author 杨新杰
 * @Date 2018/10/12 09:41
 */
@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class WnkUserGoodsController {

    @Resource
    private WnkIntegralGoodsServer wnkIntegralGoodsServer;

    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersService usersService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;



    /**
     *
     * 功能描述: 获取所有已经启用的商品
     *
     * @param  request     HttpServletRequest服务类
     * @param  business_id 商家ID
     * @param  user_id     用户ID
     * @return 返回此查询结果
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/getIntegralGoodsByTrue",method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralTypeByTrue(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer user_id) {
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
                    List<Map<String, Object>> map = wnkIntegralGoodsServer.getIntegralGoodsAllAndTrue(business_id);
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg(ImageToolsController.imageShowURL + "?imageid=");

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
     * 功能描述: 根据商品ID查询商品的信息
     *
     * @param  request     HttpServletRequest服务类
     * @param  business_id 商家ID
     * @param  user_id     用户ID
     * @return 返回商品信息
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    @RequestMapping(value = "/getIntegralByIdAndWnk",method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralByIdAndWnk(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer user_id,Integer goods_id) {
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
                    Map<String,Object> map = wnkIntegralGoodsServer.getIntegralByIdAndWnk(business_id,goods_id);
                    if (map == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品不存在");
                    }
                    else{
                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                        if (wnkStoreInformation != null){
                            map.put("store_name",wnkStoreInformation.getStore_name());
                        }
                        else{
                            map.put("store_name","");
                        }
                        map.put("imgPath",ImageToolsController.imageShowURL+"?imageid=");
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
            result.setMsg("获取失败");
        }
        return result;
    }






}
