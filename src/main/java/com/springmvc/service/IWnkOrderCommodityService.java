package com.springmvc.service;

import com.springmvc.entity.WnkOrderCommodity;

import java.util.List;
import java.util.Map;

public interface IWnkOrderCommodityService {
    /**
     * 查询某个订单的商品
     * @param order_id
     * @return
     */
    List<Map<Object,Object>> selectCommodityByOrderId(Integer order_id);

    /**
     * 插入新订单商品
     * @param wnkOrderCommodity
     * @return
     */
    int insertNewOrderCommodity(WnkOrderCommodity wnkOrderCommodity);

    /**
     *
     * 功能描述:根据商家ID查询商家销售总量
     *
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/10 11:56
     */
    int selectSalesTotalById(Integer business_id);

    /**
     *
     * 功能描述:查询用户销售构成信息
     *
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/10 11:56
     */
    Map<String,Object> selectSalesCompositionByCommoditiesId(Integer business_id,Integer commodity_id);

    /**
     *
     * 功能描述:查询商户未入账信息
     *
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/10 11:56
     */
    Map<String,Object> selectWaitAccountEntryByCommoditiesId(Integer business_id, Integer id);
}
