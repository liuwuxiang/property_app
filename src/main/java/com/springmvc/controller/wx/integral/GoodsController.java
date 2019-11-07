package com.springmvc.controller.wx.integral;

import com.springmvc.entity.IntegralGoods;
import com.springmvc.entity.Users;
import com.springmvc.service.IntegralGoodsService;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.utils.LoginSessionCheckUtil;
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
@RequestMapping(value = "/wx/v1.0.0")
public class GoodsController {
    private static Logger logger = Logger.getLogger(GoodsController.class);
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private IntegralGoodsService integralGoodsService;

    private Result findIsLogin(Integer user_id, HttpServletRequest request, HttpServletResponse response) {
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
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    private Result outList(Result result, List list) {
        if (list == null) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
            return result;
        }
        if (list.size() <= 0) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        } else {
            Map<Object, Object> map = new HashMap<>();
            map.put("data", list);
            result.setData(list);
            result.setStatus(Result.SUCCESS);
            result.setMsg("获取成功");
        }
        return result;
    }

    /**
     *
     * 功能描述: 获取推荐商品
     *
     * @param  request  HttpServletRequest服务类
     * @param  response HttpServletResponse服务类
     * @param  user_id  用户ID
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:29
     */
    @RequestMapping(value = "/getRecommendGoodsInfo",method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getRecommendGoodsInfo(HttpServletRequest request, HttpServletResponse response, Integer user_id) {
        Result result = findIsLogin(user_id, request, response);
        if (!result.getStatus().equals(Result.FAIL) || !result.getStatus().equals(Result.NOLOGIN)) {
            List<Map<String, Object>> list = null;
            try {
                list = integralGoodsService.getRecommendGoodsInfo();
                if (list.size() > 0 ){
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                } else {
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("暂无数据");
                }
            } catch (Exception e) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("获取失败");
            }
            return outList(result, list);
        }
        return result;
    }


    /**
     *
     * 功能描述: 获取商品信息
     *
     * @param  request  HttpServletRequest服务类
     * @param  response HttpServletResponse服务类
     * @param  user_id  用户ID
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:29
     */
    @RequestMapping(value = "/getGoodsById", method = RequestMethod.POST, params = {"user_id", "id"})
    @ResponseBody
    public Result getGoodsById(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer id) {
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
                }
                if (id == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("必要参数为空");
                } else {
                    IntegralGoods goodsById = integralGoodsService.getGoodsById(id);
                    logger.info(goodsById.toString());
                    Map<String, Object> map = new HashMap<>();
                    map.put("IntegralGoods", goodsById);
                    map.put("integral",users.getUser_integral());
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
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
     * 功能描述: 根据分类ID获取对应商品
     *
     * @param  request  HttpServletRequest服务类
     * @param  response HttpServletResponse服务类
     * @param  user_id  用户ID
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:29
     */
    @RequestMapping(value = "/getGoodsByTypeId", method = RequestMethod.POST, params = {"user_id", "type_id"})
    @ResponseBody
    public Result getGoodsByTypeId(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer type_id){
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
                }
                if (type_id == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("必要参数为空");
                } else {

                    List<Map<String,Object>> list = integralGoodsService.getGoodsByTypeId(type_id);

                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
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
     * 功能描述: 根据分类ID获取对应商品
     *
     * @param  request  HttpServletRequest服务类
     * @param  response HttpServletResponse服务类
     * @param  user_id  用户ID
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:29
     */
    @RequestMapping(value = "/getIntegralGoodsAll", method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralGoodsAll(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer type_sort){
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
                    List<Map<String,Object>> list = integralGoodsService.getIntegralGoodsAll(type_sort);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        result.setData(list);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
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
