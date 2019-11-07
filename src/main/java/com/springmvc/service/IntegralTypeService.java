package com.springmvc.service;

import com.springmvc.entity.IntegralType;

import java.util.List;
import java.util.Map;

public interface IntegralTypeService {

    /**
     * 获取所有启用的商品分类
     * @return 返回查询结果
     */
    List<Map<String,Object>> getAllIntegralTypeTrue();

    /**
     * 获取所有商品分类
     * @return 返回查询结果
     */
    List<Map<String,Object>> getAllIntegralType();

    /**
     *  根据ID查询商品分类信息
     * @param id 商品ID
     * @return 返回商品分类信息
     */
    IntegralType getIntegralTypeByID(Integer id);

    /**
     * 根据ID更新类别信息
     * @param integralType  类别实体类
     * @return 成功返回1 失败返回0
     */
    int updateIntegralGoodsType(IntegralType integralType);

    /**
     * 新增商品分类
     * @param integralType 类别实体类
     * @return 成功返回1 失败返回0
     */
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
