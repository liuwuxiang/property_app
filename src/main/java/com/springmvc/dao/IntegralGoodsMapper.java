package com.springmvc.dao;


import com.springmvc.entity.IntegralGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IntegralGoodsMapper {

    /**
     * 获取所有商品
     * @return
     */
    List<Map<String,Object>> getAllIntegralGoods();

    /**
     * 新增商品
     * @return
     */
    int addIntegralGoods(IntegralGoods integral);

    /**
     * 获取推荐商品
     * @return
     */
    List<Map<String,Object>> getRecommendGoodsInfo();

    /**
     * 根据ID获取商品
     * @return
     */
    IntegralGoods getGoodsById(Integer id);

    /**
     * 根据商品类别获取商品
     * @param type_id
     * @return
     */
    List<Map<String,Object>> getGoodsByTypeId(Integer type_id);

    int updateIntegralGoods(IntegralGoods integral);

    /**
     * 功能描述: 获取所有积分商品(排序)
     *
     * @param type_sort@return
     * @author 杨新杰
     * @date 10:31 2018/12/31
     */
    List<Map<String,Object>> getIntegralGoodsAll(@Param("type_sort") Integer type_sort);

    /**
     *
     * 功能描述: 根据条件查询商品
     *
     * @param   map 查询条件
     * @return: 返回查询商品
     * @author: 刘武祥
     * @date: 2019/1/15 0015 12:25
     */
    List<Map<Object,Object>> adminSearchIntegralGoods(Map<String,Object> map);


}
