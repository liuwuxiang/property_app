package com.springmvc.service;

import com.springmvc.entity.WnkOrderCommodityTwo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/8 17:15
 * @Description:
 */
public interface IWnkOrderCommodityTwoService {
    //查询某个订单的商品
    List<Map<Object,Object>> selectCommodityByOrderId(Integer order_id);
    //插入新订单商品
    int insertNewOrderCommodity(WnkOrderCommodityTwo wnkOrderCommodityTwo);

    /**
     *
     * 功能描述:根据商家ID查询商家销售总量
     *
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/10 11:56
     */
    int selectSalesTotalById(@Param("business_id") Integer business_id);

    /**
     * 功能描述:根据商品ID查询商品销售量
     *
     * @param business_id  商家ID
     * @param commodity_id 商品ID
     * @return
     * @author 杨新杰
     * @date 2018/11/10 11:56
     */
    Map<String,Object> selectSalesCompositionByCommoditiesId(@Param("business_id")Integer business_id,@Param("commodity_id")Integer commodity_id);

    /**
     * 功能描述:查询商户未入账信息
     *
     * @param business_id 商家ID
     * @param id
     * @return
     * @author 杨新杰
     * @date 2018/11/10 11:56
     */
    Map<String,Object> selectWaitAccountEntryByCommoditiesId(@Param("business_id")Integer business_id,@Param("commodity_id")Integer commodity_id);

    /**
     *
     * 功能描述: 更新商品使用量和退款量
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/8 5:14 PM
     */
    int updateCommodityMakeNumberAndRefundNumber(WnkOrderCommodityTwo wnkOrderCommodityTwo);

    /**
     *
     * 功能描述: 通过ID查询记录
     *
     * @param record_id 记录ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/9 11:35 PM
     */
    WnkOrderCommodityTwo selectByID(Integer record_id);
}
