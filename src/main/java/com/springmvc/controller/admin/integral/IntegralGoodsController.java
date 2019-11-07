package com.springmvc.controller.admin.integral;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.IntegralGoods;
import com.springmvc.entity.IntegralOrder;
import com.springmvc.entity.WnkIntegralGoods;
import com.springmvc.service.IntegralGoodsService;
import com.springmvc.service.impl.WnkIntegralGoodsServer;
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
/**
 *
 * 功能描述: 商品管理
 * @Author: 刘武祥
 * @Date: 2019/2/20 0020 11:20
 */
@Controller
@RequestMapping("/admin")
public class IntegralGoodsController {

    /**商品操作service*/
    @Resource
    private IntegralGoodsService integralGoodsService;

    @Resource
    private WnkIntegralGoodsServer wnkIntegralGoodsServer;

    /**
     *
     * 功能描述: 获取所有商品
     *
     * @param  request HttpServletRequest
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     * @author 杨新杰
     * @date   2018/10/8 10:19
     */
    @RequestMapping(value = "/getAllIntegralGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllIntegralGoods(HttpServletRequest request) {
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
                List<Map<String,Object>> list = this.integralGoodsService.getAllIntegralGoods();
                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                } else {
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
                // 业务结束
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
     * 功能描述: 根据条件查询商品
     *
     * @param   request     请求
     * @param   response    响应
     * @param   name        商品名称
     * @param   price       商品价格
     * @param   trader      商家名称
     * @param   detail      商品详情
     * @param   isRecommend 是否上架(0=是 1=否)
     * @param   is_checked  是否推荐(0=是 1=否)
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/15 0015 12:19
     */
    @RequestMapping(value = "/adminSearchIntegralGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchIntegralGoods(HttpServletRequest request,
                                           HttpServletResponse response,
                                           Integer limit,
                                           Integer page,
                                           String name,
                                           Double price,
                                           String trader ,
                                           String detail,
                                           String isRecommend,
                                           Integer is_checked)
    {

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
                if (name != null && !"".equals(name)) {
                    map.put("name", "%" + name + "%");
                }
                if (price != null && !"".equals(price)) {
                    map.put("price", price );
                }
                if (trader != null && !"".equals(trader)) {
                    map.put("trader", "%" + trader + "%");
                }
                if (detail != null && !"".equals(detail)){
                    map.put("detail","%"+detail+"%");
                }
                map.put("isRecommend", ("".equals(isRecommend) ? null : isRecommend) );
                map.put("is_checked", ("".equals(is_checked) ? null : is_checked) );
                List<Map<Object,Object>> list = this.integralGoodsService.adminSearchIntegralGoods(map);
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
     * 功能描述:添加商品
     *
     * @param  request HttpServletRequest服务类
     * @param integral 商品实体类
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         返回新增结果<br>
     * @author 杨新杰
     * @date   2018/10/8 10:20
     */
    @RequestMapping(value = "/addIntegralGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result addIntegralGoods(HttpServletRequest request, IntegralGoods integral) {
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
                Integer list = this.integralGoodsService.addIntegralGoods(integral);
                if (list <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("新增失败");
                } else {
                    result.setData(list);
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
     * 功能描述: 修改积分商品
     *
     * @param  request  HttpServletRequest服务类
     * @param  integral 商品实体类
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         返回新增结果
     * @author 杨新杰
     * @date   2018/10/8 10:21
     */
    @RequestMapping(value = "/updateIntegralGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result updateIntegralGoods(HttpServletRequest request, IntegralGoods integral) {
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
                int i = this.integralGoodsService.updateIntegralGoods(integral);
                if (i <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("修改失败");
                } else {
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("修改成功");
                }
                // 业务结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("新增失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 根据ID查找商品
     *
     * @param  request  HttpServletRequest服务类
     * @param  goods_id 商品ID
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     * @author 杨新杰
     * @date   2018/10/8 10:22
     */
    @RequestMapping(value = "/getIntegralGoodsById", method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralGoodsById(HttpServletRequest request, Integer goods_id) {
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
                IntegralGoods integralGoods = this.integralGoodsService.getGoodsById(goods_id);
                if (integralGoods == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品不存在");
                } else {
                    result.setData(integralGoods);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
                // 业务结束
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("新增失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 获取所有商家积分商品
     *
     * @param  request  HttpServletRequest服务类
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     * @author 杨新杰
     * @date   2018/10/8 10:22
     */
    @RequestMapping(value = "/getAllIntegralBusinessGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllIntegralBusinessGoods(HttpServletRequest request) {
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
                List<Map<String, Object>> allIntegralBusinessGoods = this.wnkIntegralGoodsServer.getAllIntegralBusinessGoods();
                if (allIntegralBusinessGoods.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商品不存在");
                } else {
                    for (Map<String,Object> map : allIntegralBusinessGoods){
                        map.put("img",ImageToolsController.imageShowURL + "?imageid=" + map.get("img"));
                    }
                    result.setData(allIntegralBusinessGoods);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
//                IntegralGoods integralGoods = this.integralGoodsService.getGoodsById(goods_id);

                // 业务结束
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 根据条件查询商家积分商品管理
     *
     * , ,
     * @param   request       请求
     * @param   response      响应
     * @param   limit         分页条目数
     * @param   page          分页总数
     * @param   name          商品名称
     * @param   price         商品价格
     * @param   business_name 商家名称
     * @param   is_recommend  是否推荐(0=是 1=否)
     * @param   is_checked    是否上架(0=是 1=否)
     * @return: com.springmvc.utils.Result
     * @author: 刘武祥
     * @date: 2019/1/16 0016 10:45
     */
    @RequestMapping(value = "/adminSearchIntegralBusinessGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSearchIntegralBusinessGoods(HttpServletRequest request,
                                           HttpServletResponse response,
                                           Integer limit,
                                           Integer page,
                                           String business_name,
                                           String name,
                                           Double price,
                                           String is_recommend,
                                           Integer is_checked)
    {

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
                if (business_name != null && !"".equals(business_name)) {
                    map.put("business_name", "%" + business_name + "%");
                }
                if (name != null && !"".equals(name)) {
                    map.put("name", "%" + name + "%");
                }
                if (price != null && !"".equals(price)) {
                    map.put("price", price );
                }
                map.put("is_recommend", ("".equals(is_recommend) ? null : is_recommend) );
                map.put("is_checked", ("".equals(is_checked) ? null : is_checked) );

                List<Map<Object,Object>> list = this.wnkIntegralGoodsServer.adminSearchIntegralBusinessGoods(map);

                if (list.size() <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
                else{

                    for (Map<Object,Object> maps : list){
                        maps.put("img",ImageToolsController.imageShowURL + "?imageid=" + maps.get("img"));
                    }
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
     * 功能描述: 更新商家商品推荐状态
     *
     * @param  request  HttpServletRequest服务类
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     * @author 杨新杰
     * @date   2018/10/8 10:22
     */
    @RequestMapping(value = "/updateBusinessIntegralGoodsRecommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public Result updateBusinessIntegralGoodsRecommendStatus(HttpServletRequest request,Integer status,Integer goods_id) {
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
                if (this.wnkIntegralGoodsServer.updateBusinessIntegralGoodsRecommendStatus(status,goods_id) > 0 ){
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("操作成功");
                } else {
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("操作失败");
                }
                // 业务结束
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }
    /**
     *
     * 功能描述: 更新商家商品推荐状态
     *
     * @param  request  HttpServletRequest服务类
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     * @author 杨新杰
     * @date   2018/10/8 10:22
     */
    @RequestMapping(value = "/updateBusinessIntegralGoodsCheckedStatus", method = RequestMethod.POST)
    @ResponseBody
    public Result updateBusinessIntegralGoodsCheckedStatus(HttpServletRequest request,Integer status,Integer goods_id) {
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
                if (this.wnkIntegralGoodsServer.updateBusinessIntegralGoodsCheckedStatus(status,goods_id) > 0 ){
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("操作成功");
                } else {
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("操作失败");
                }
                // 业务结束
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

}
