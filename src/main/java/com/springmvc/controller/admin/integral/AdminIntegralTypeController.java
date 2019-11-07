package com.springmvc.controller.admin.integral;


import com.springmvc.entity.IntegralType;
import com.springmvc.service.IntegralTypeService;
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
public class AdminIntegralTypeController {


    /**积分商城商品分类service*/
    @Resource
    private IntegralTypeService integralTypeService;

    /**
     *
     * 功能描述: 获取所有分类
     *
     * @param  request HttpServletRequest
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     * @author 杨新杰
     * @date   2018/10/8 10:10
     */
    @RequestMapping(value = "/getAllIntegralType", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllIntegralType(HttpServletRequest request) {
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
                List<Map<String, Object>> list = integralTypeService.getAllIntegralType();
                if (list.size() <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
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
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 根据条件查询商品类别
     *
     * @param   request   请求
     * @param   response  响应
     * @param   limit     分页条目数
     * @param   page      分页
     * @param   name      分类名称
     * @param   isDelete  启用状态(0-启用,1-禁用)
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/21 0021 12:06
     */
    @RequestMapping(value = "/adminSearchIntegralTypeInfoByConditions",method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchIntegralTypeInfoByConditions(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Integer limit,
                                                               Integer page,
                                                               String name,
                                                               String isDelete){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("后台暂未登录");
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("start_index",limit * (page - 1));
                map.put("limit",limit);
                map.put("page",page);
                if (name != null && !"".equals(name)) {
                    map.put("name", "%"+name+"%");
                }
                map.put("isDelete",("".equals(isDelete) ? null : isDelete));
                List<Map<Object,Object>> list = this.integralTypeService.adminSearchIntegralTypeInfoByConditions(map);
                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据!");
                }else {
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
     * 功能描述: 根据ID获取类别信息
     *
     * @param  request  HttpServletRequest
     * @param  id       类别ID
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         返回类别信息实体类<br>
     * @author 杨新杰
     * @date   2018/10/8 10:11
     */
    @RequestMapping(value = "/getIntegralGoodsType", method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralGoodsType(HttpServletRequest request, Integer id) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                if (id == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("数据不合法");
                } else {
                    // 业务开始
                    IntegralType type = integralTypeService.getIntegralTypeByID(id);
                    result.setData(type);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
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
     *
     * 功能描述: 更新商品类别信息
     *
     * @param  request HttpServletRequest
     * @param  integralType 商品类别实体类
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     * @author 杨新杰
     * @date   2018/10/8 10:13
     */
    @RequestMapping(value = "/updateIntegralGoodsType", method = RequestMethod.POST)
    @ResponseBody
    public Result updateIntegralGoodsType(HttpServletRequest request, IntegralType integralType) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                // 业务开始
                int i = integralTypeService.updateIntegralGoodsType(integralType);
                if (i <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("更新失败");
                } else {
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("更新成功");
                }
                // 业务结束
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
     * 功能描述: 添加商品类别
     *
     * @param  request HttpServletRequest
     * @param integralType 商品类别实体类
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     * @author 杨新杰
     * @date   2018/10/8 10:15
     */
    @RequestMapping(value = "/addIntegralGoodsType", method = RequestMethod.POST)
    @ResponseBody
    public Result addIntegralGoodsType(HttpServletRequest request, IntegralType integralType) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                // 业务开始
                int i = integralTypeService.addIntegralGoodsType(integralType);
                if (i <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("新增失败");
                } else {
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("新增成功");
                }
                // 业务结束
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
     * 功能描述: 获取所有已经启用的商品分类
     *
     * @param  request HttpServletRequest
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     * @author 杨新杰
     * @date   2018/10/8 10:16
     */
    @RequestMapping(value = "/getAllIntegralTypeTrue",method = RequestMethod.POST)
    @ResponseBody
    public Result getAllIntegralTypeTrue(HttpServletRequest request){

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                // 业务开始
                List<Map<String,Object>> list = integralTypeService.getAllIntegralTypeTrue();
                if (list == null || list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("获取失败");
                } else {
                    Map<String,Object> map = new HashMap<>();
                    map.put("list",list);
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
                // 业务结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

}
