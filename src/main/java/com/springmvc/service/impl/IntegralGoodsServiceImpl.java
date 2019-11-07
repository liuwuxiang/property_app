package com.springmvc.service.impl;

import com.springmvc.dao.IntegralGoodsMapper;
import com.springmvc.entity.IntegralGoods;
import com.springmvc.service.IntegralGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 商品管理
 * @Author: 刘武祥
 * @Date: 2019/2/20 0020 12:20
 */
@Service("/integralGoodsService")
public class IntegralGoodsServiceImpl implements IntegralGoodsService {

    @Resource
    private IntegralGoodsMapper integralGoodsMapper;

    /**
     * 获取所有商品列表
     *
     * @return 返回查询到的商品列表
     */
    @Override
    public List<Map<String, Object>> getAllIntegralGoods() {
        return integralGoodsMapper.getAllIntegralGoods();
    }

    /**
     * 新增一个商品
     *
     * @return
     */
    @Override
    public int addIntegralGoods(IntegralGoods integral) {
        return integralGoodsMapper.addIntegralGoods(integral);
    }

    /**
     * 获取推荐商品
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getRecommendGoodsInfo() {
        return integralGoodsMapper.getRecommendGoodsInfo();
    }

    /**
     * 获取指定商品
     *
     * @param id 商品ID
     * @return 返回指定商品
     */
    @Override
    public IntegralGoods getGoodsById(Integer id) {
        return integralGoodsMapper.getGoodsById(id);
    }

    /**
     * 根据商品类别获取商品
     *
     * @param type_id
     * @return
     */
    @Override
    public List<Map<String, Object>> getGoodsByTypeId(Integer type_id) {
        return integralGoodsMapper.getGoodsByTypeId(type_id);
    }

    /**
     * 根据ID修改商品
     *
     * @param integral
     * @return
     */
    @Override
    public int updateIntegralGoods(IntegralGoods integral) {
        return integralGoodsMapper.updateIntegralGoods(integral);
    }

    /**
     * 功能描述: 获取所有积分商品(排序)
     *
     * @param type_sort
     * @return
     * @author 杨新杰
     * @date 10:31 2018/12/31
     */
    @Override
    public List<Map<String, Object>> getIntegralGoodsAll(Integer type_sort) {
        return this.integralGoodsMapper.getIntegralGoodsAll(type_sort);
    }

    /**
     *
     * 功能描述: 根据条件查询商品
     *
     * @param   map 查询条件
     * @return: 返回查询商品
     * @author: 刘武祥
     * @date: 2019/1/15 0015 12:25
     */
    @Override
    public List<Map<Object, Object>> adminSearchIntegralGoods(Map<String, Object> map) {
        return this.integralGoodsMapper.adminSearchIntegralGoods(map);
    }


}
