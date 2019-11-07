package com.springmvc.dao;

import com.springmvc.entity.IntegralType;

import java.util.List;
import java.util.Map;

public interface IntegralTypeMapper {
    List<Map<String,Object>> getAllIntegralTypeTrue();

    List<Map<String,Object>> getAllIntegralType();

    IntegralType getIntegralTypeByID(Integer id);

    int updateIntegralGoodsType(IntegralType integralType);

    int addIntegralGoodsType(IntegralType integralType);

    /**
     *
     * 功能描述: 根据条件查询商品类别
     *
     * @param   map 查询条件
     * @return: 返回商品类别
     * @author: 刘武祥
     * @date: 2019/1/21 0021 12:12
     */
    List<Map<Object,Object>> adminSearchIntegralTypeInfoByConditions(Map<String,Object> map);
}
