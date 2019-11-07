package com.springmvc.service;


import com.springmvc.entity.IntegralGoods;

import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 商品管理
 * @Author: 刘武祥
 * @Date: 2019/2/20 0020 12:20
 */
public interface IntegralGoodsService {

    /**
     * 获取所有商品列表
     *
     * @return 返回查询到的商品列表
     */
    List<Map<String, Object>> getAllIntegralGoods();

    /**
     *
     * 功能描述: 新增一个商品
     *
     * @param   integral
     * @return 新增成功返回1 失败返回0
     * @author: 刘武祥
     * @date: 2019/1/16 0016 10:15
     */
    int addIntegralGoods(IntegralGoods integral);

    /**
     * 获取推荐商品
     * @return 返回查询结果
     */
    List<Map<String,Object>> getRecommendGoodsInfo();

    /**
     * 获取指定商品
     * @param id 商品ID
     * @return 返回指定商品
     */
    IntegralGoods getGoodsById(Integer id);

    /**
     * 根据商品类别获取商品
     * @param type_id 商品类别ID
     * @return 返回查询到的结果,无结果返回null
     */
    List<Map<String,Object>> getGoodsByTypeId(Integer type_id);

    /**
     * 根据ID修改商品
     * @param integral 商品信息实体类
     * @return  正常修改返回1 失败返回0
     */
    int updateIntegralGoods(IntegralGoods integral);

    /**
     *
     * 功能描述: 获取所有积分商品(排序)
     *
     * @param   type_sort
     * @return
     * @author  杨新杰
     * @date    10:31 2018/12/31
     */
    List<Map<String,Object>> getIntegralGoodsAll(Integer type_sort);

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
