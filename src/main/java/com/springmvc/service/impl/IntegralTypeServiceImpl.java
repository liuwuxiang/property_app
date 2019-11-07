package com.springmvc.service.impl;

import com.springmvc.dao.IntegralTypeMapper;
import com.springmvc.entity.IntegralType;
import com.springmvc.service.IntegralTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("/integralTypeService")
public class IntegralTypeServiceImpl implements IntegralTypeService {

    @Resource
    private IntegralTypeMapper integralTypeMapper;

    /**
     * 获取所有商品分类
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllIntegralTypeTrue() {
        return integralTypeMapper.getAllIntegralTypeTrue();
    }

    /**
     * 获取所有商品分类
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllIntegralType() {
        return integralTypeMapper.getAllIntegralType();
    }

    /**
     * 根据ID查询商品分类信息
     *
     * @param id
     * @return
     */
    @Override
    public IntegralType getIntegralTypeByID(Integer id) {
        return integralTypeMapper.getIntegralTypeByID(id);
    }

    /**
     * 根据ID更新类别细腻
     *
     * @param integralType
     * @return
     */
    @Override
    public int updateIntegralGoodsType(IntegralType integralType) {
        return integralTypeMapper.updateIntegralGoodsType(integralType);
    }

    /**
     * 新增商品分类
     *
     * @param integralType
     * @return
     */
    @Override
    public int addIntegralGoodsType(IntegralType integralType) {
        return integralTypeMapper.addIntegralGoodsType(integralType);
    }

    /**
     *
     * 功能描述: 根据条件查询商品类别
     *
     * @param   map 查询条件
     * @return: 返回商品类别
     * @author: 刘武祥
     * @date: 2019/1/21 0021 12:12
     */
    @Override
    public List<Map<Object, Object>> adminSearchIntegralTypeInfoByConditions(Map<String, Object> map) {
        return this.integralTypeMapper.adminSearchIntegralTypeInfoByConditions(map);
    }
}
