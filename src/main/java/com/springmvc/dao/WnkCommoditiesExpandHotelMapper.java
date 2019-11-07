package com.springmvc.dao;

import com.springmvc.entity.WnkCommoditiesExpandHotel;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 杨新杰
 */
public interface WnkCommoditiesExpandHotelMapper {

    /**
     * 新增商品扩展信息
     *
     * @param wnkCommoditiesExpandHotel 商品扩展信息 实体类
     * @return 新增条目数
     * @author 杨新杰
     */
    int insertExpandInfo(WnkCommoditiesExpandHotel wnkCommoditiesExpandHotel);

    /**
     * 通过商品ID 查询商品扩展信息
     *
     * @param commodityId 商品ID
     * @return 返回查询到的数据
     */
    WnkCommoditiesExpandHotel selectCommoditiesExpandHotelByCommoditiesId(@Param("commodity_id") Integer commodityId);


    /**
     * 更新商品扩展信息
     *
     * @param wnkCommoditiesExpandHotel 商品扩展信息 实体类
     * @return 新增条目数
     * @author 杨新杰
     */
    int updateExpandInfo(WnkCommoditiesExpandHotel wnkCommoditiesExpandHotel);
}
