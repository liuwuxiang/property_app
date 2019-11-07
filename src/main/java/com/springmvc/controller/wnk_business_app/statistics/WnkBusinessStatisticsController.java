package com.springmvc.controller.wnk_business_app.statistics;

import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkCommodities;
import com.springmvc.entity.WnkStoreInformation;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 万能卡商家统计
 *
 * @author 杨新杰
 * @Date 2018/11/10 10:26
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class WnkBusinessStatisticsController {

    @Resource
    private WnkBusinessBalanceDetailService wnkBusinessBalanceDetailService;
    @Resource
    private WnkCommoditiesService wnkCommoditiesService;
    @Resource
    private WnkOrdersService wnkOrdersService;
    @Resource
    private WnkOrderCommodityService wnkOrderCommodityService;

    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;


    /**
     * 功能描述: 根据商家ID查询今日总收入
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/10 10:48
     */
    @RequestMapping(value = "/getTodayRevenue", method = RequestMethod.POST)
    @ResponseBody
    public Result getTodayRevenue(HttpServletRequest request, HttpServletResponse response, Integer business_id) {
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
                    Map<String, Object> map = wnkBusinessBalanceDetailService.selectTodayRevenueById(business_id);
                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                    if (wnkStoreInformation != null){
                        if (map == null){
                            map = new HashMap<String,Object>();
                            map.put("money","0.00");
                        }
                        map.put("store_name",wnkStoreInformation.getStore_name());
                    }
                    else{
                        if (map == null){
                            map = new HashMap<String,Object>();
                            map.put("money","0.00");
                        }
                        map.put("store_name","");
                    }
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
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
     * 功能描述: 根据商家ID查询商家总收入和总收入条目数
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/10 10:48
     */
    @RequestMapping(value = "/getRevenueTotalAndMoney", method = RequestMethod.POST)
    @ResponseBody
    public Result getRevenueTotalAndMoney(HttpServletRequest request, HttpServletResponse response, Integer business_id) {
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
                    Map<String, Object> map = wnkBusinessBalanceDetailService.selectRevenueTotalAndMoney(business_id);
                    if (map == null){
                        map = new HashMap<String,Object>();
                    }
                    if (map.get("total") == null || map.get("total").equals("")){
                        map.put("total",0);
                    }
                    if (map.get("money") == null || map.get("money").equals("")){
                        map.put("money",0.00);
                    }
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
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
     * 功能描述: 根据商家ID查询商家商品总量 销售总量 交易笔数 购买人次
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/10 10:48
     */
    @RequestMapping(value = "/getDataComposition", method = RequestMethod.POST)
    @ResponseBody
    public Result getDataComposition(HttpServletRequest request, HttpServletResponse response, Integer business_id) {
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
                    Map<String, Object> map = new HashMap<>();
                    // 商品总量
                    map.put("commodityTotal", this.wnkCommoditiesService.selectCommodityTotalById(business_id));
                    // 销售总量
                    map.put("salesTotal", this.wnkOrderCommodityService.selectSalesTotalById(business_id));
                    // 交易笔数
                    map.put("transactionTotal", this.wnkOrdersService.selectTransactionTotalById(business_id));
                    // 购买人数
                    map.put("buyTotal", this.wnkOrdersService.selectBuyTotalById(business_id));
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     * 功能描述: 获取销售构成信息和交易笔数构成信息
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/10 10:48
     */
    @RequestMapping(value = "/getSalesComposition", method = RequestMethod.POST)
    @ResponseBody
    public Result getSalesComposition(HttpServletRequest request, HttpServletResponse response, Integer business_id) {
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
                    List<Map<String,Object>> SalesCompositionList = new ArrayList<>();
                    // 先查询商家所有商品信息
                    List<Map<String, Object>> list = this.wnkCommoditiesService.selectCommoditiesInfoAllById(business_id);
                    // 遍历查询商品销售总量和交易笔数
                    for (Map<String, Object> m : list) {
                        Map<String, Object> map = this.wnkOrderCommodityService.selectSalesCompositionByCommoditiesId(business_id, (Integer) m.get("id"));
                        map.put("CommoditiesName",m.get("name"));
                        SalesCompositionList.add(map);
                    }
                    // 返回
                    result.setData(SalesCompositionList);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     * 功能描述: 获取等待入账的信息
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/10 10:48
     */
    @RequestMapping(value = "/getWaitAccountEntry", method = RequestMethod.POST)
    @ResponseBody
    public Result getWaitAccountEntry(HttpServletRequest request, HttpServletResponse response, Integer business_id) {
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
                    List<Map<String,Object>> SalesCompositionList = new ArrayList<>();
                    // 先查询商家所有商品信息
                    List<Map<String, Object>> list = this.wnkCommoditiesService.selectCommoditiesInfoAllById(business_id);
                    // 遍历查询商品销售总量和交易笔数
                    for (Map<String, Object> m : list) {
                        Map<String, Object> map = this.wnkOrderCommodityService.selectWaitAccountEntryByCommoditiesId(business_id, (Integer) m.get("id"));
                        map.put("CommoditiesName",m.get("name"));
                        SalesCompositionList.add(map);
                    }
                    // 返回
                    result.setData(SalesCompositionList);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

}
