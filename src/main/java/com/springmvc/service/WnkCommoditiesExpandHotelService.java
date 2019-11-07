package com.springmvc.service;

import com.springmvc.entity.WnkCommoditiesExpandHotel;

/**
 * 商品扩展信息操作 (酒店)
 * @author 杨新杰
 */
public interface WnkCommoditiesExpandHotelService {
    /**
     *
     * 新增商品扩展信息
     *
     * @author 杨新杰
     * @param wnkCommoditiesExpandHotel 商品扩展信息 实体类
     * @return 新增条目数
     */
    int insertExpandInfo(WnkCommoditiesExpandHotel wnkCommoditiesExpandHotel);

    /**
     *
     * 通过商品ID 查询商品扩展信息
     *
     * @param commodityId 商品ID
     * @return 返回查询到的数据
     */
    WnkCommoditiesExpandHotel selectCommoditiesExpandHotelByCommoditiesId(Integer commodityId);

    /**
     *
     * 更新商品扩展信息
     *
     * @author 杨新杰
     * @param wnkCommoditiesExpandHotel 商品扩展信息 实体类
     * @return 新增条目数
     */
    int updateExpandInfo(WnkCommoditiesExpandHotel wnkCommoditiesExpandHotel,Integer businessId , Integer commodityId,Integer inventory);
}
