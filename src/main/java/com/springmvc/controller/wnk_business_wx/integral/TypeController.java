package com.springmvc.controller.wnk_business_wx.integral;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkIntegralType;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.WnkBusinessAccountService;
import com.springmvc.service.impl.WnkIntegralTypeServer;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
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

/**
 * 商家积分商城后台 - 积分商品分类管理
 * @author 杨新杰
 * @Date 2018/10/10 15:44
 */
@Controller
@RequestMapping(value = "/wnk_business")
public class TypeController {

    @Resource
    private LoginSessionIdsService sessionIdsService;

    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;

    @Resource
    private WnkIntegralTypeServer wnkIntegralTypeServer;

    /**
     *
     * 功能描述: 新增商品分类
     *
     * @param  request          HttpServletRequest 服务类
     * @param  response         HttpServletResponse 服务类
     * @param  business_id      商家ID
     * @param  wnkIntegralType  商品分类实体类
     * @return 返回新增结果
     * @author 杨新杰
     * @date   2018/10/10 16:41
     */
    @RequestMapping(value = "/addWnkIntegralType",method = RequestMethod.POST)
    @ResponseBody
    public Result addWnkIntegralType(HttpServletRequest request, HttpServletResponse response, Integer business_id, WnkIntegralType wnkIntegralType){
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
                    System.out.println(wnkIntegralType);
                    int ret = wnkIntegralTypeServer.addWnkIntegralType(wnkIntegralType);
                    if (ret <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("新增失败");
                    } else {
                        result.setData(ret);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("新增成功");
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
    * 功能描述: 修改分类信息
    * @param  request          HttpServletRequest 服务类
    * @param  response         HttpServletResponse 服务类
    * @param  wnkIntegralType  商家分类实体类
    * @return 返回修改条目数
    * @author 刘武祥
    * @date   2018/10/11 0011 12:10
    */
    @RequestMapping(value = "/editWnkIntegralType",method = RequestMethod.POST)
    @ResponseBody
    public Result editWnkIntegralType(HttpServletRequest request,HttpServletResponse response,WnkIntegralType wnkIntegralType, Integer business_id){
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
                    int re = wnkIntegralTypeServer.editWnkIntegralType(wnkIntegralType);
                    if (re <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("修改失败");
                    } else {
                        result.setData(re);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("修改成功");
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
     * 功能描述: 获取所有分类
     *
     * @param  request          HttpServletRequest 服务类
     * @param  response         HttpServletResponse 服务类
     * @param  business_id      商家ID
     * @return 返回获取到的所有分类
     * @author 刘武祥
     * @date   2018/10/10 0010 17:20
     */
    @RequestMapping(value = "/getIntegralTypeAll",method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralTypeAll(HttpServletRequest request, HttpServletResponse response, Integer business_id){

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
                    System.out.println(1);
                    // 业务开始
                    List<Map<String, Object>> list = wnkIntegralTypeServer.getIntegralTypeAll(business_id);
                    if (list == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("获取失败");
                    } else {
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg(ImageToolsController.imageShowURL + "?imageid=");
                    }
                    System.out.println(2);
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
     * 功能描述: 根据商家ID获取已经启用的分类
     *
     * @param  request          HttpServletRequest 服务类
     * @param  response         HttpServletResponse 服务类
     * @param  business_id      商家ID
     * @return 返回获取到的所有分类
     * @author 刘武祥
     * @date   2018/10/10 0010 17:20
     */
    @RequestMapping(value = "/getIntegralTypeByTrue",method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralTypeByTrue(HttpServletRequest request, HttpServletResponse response, Integer business_id){
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
                    List<Map<String, Object>> list = wnkIntegralTypeServer.getIntegralTypeByTrue(business_id);
                    if (list == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("获取失败");
                    } else {
                        result.setData(list);
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
     * 功能描述: 根据商家ID获取已经启用的分类 - 添加/修改商品专用
     *
     * @param  request          HttpServletRequest 服务类
     * @param  response         HttpServletResponse 服务类
     * @param  business_id      商家ID
     * @return 返回获取到的所有分类
     * @author 刘武祥
     * @date   2018/10/10 0010 17:20
     */
    @RequestMapping(value = "/getIntegralTypeByTrueToGoods",method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralTypeByTrueToGoods(HttpServletRequest request, HttpServletResponse response, Integer business_id){
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
                    System.out.println(1);
                    // 业务开始
                    List<Map<String, Object>> list = wnkIntegralTypeServer.getIntegralTypeByTrueToGoods(business_id);
                    if (list == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("获取失败");
                    } else {
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }
                    System.out.println(2);
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
     * 功能描述: 根据商家ID和分类ID获取商品分类
     *
     * @param  request          HttpServletRequest 服务类
     * @param  response         HttpServletResponse 服务类
     * @param  business_id      商家ID
     * @return 返回获取到的所有分类
     * @author 刘武祥
     * @date   2018/10/10 0010 17:20
     */
    @RequestMapping(value = "/getIntegralTypeById",method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralTypeById(HttpServletRequest request, HttpServletResponse response, Integer business_id,Integer id){
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
                    Map<String, Object> map = wnkIntegralTypeServer.getIntegralTypeById(business_id,id);
                    if (map == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("获取失败");
                    } else {
                        map.put("urlPath", ImageToolsController.imageShowURL + "?imageid=");
                        result.setData(map);
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




}
