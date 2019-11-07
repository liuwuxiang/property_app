package com.springmvc.service.impl;

import com.springmvc.dao.WnkIntegralTypeMapper;
import com.springmvc.entity.WnkIntegralType;
import com.springmvc.service.IWnkIntegralTypeServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 商家积分商城 - 商品分类管理-Service实现层
 *
 * @author 杨新杰
 * @Date 2018/10/10 16:10
 */
@Service("/wnkIntegralTypeServer")
public class WnkIntegralTypeServer implements IWnkIntegralTypeServer {

    @Resource
    private WnkIntegralTypeMapper wnkIntegralTypeMapper;

    /**
     * 功能描述:新增商品分类
     *
     * @param wnkIntegralType 商品分类实体类
     * @return 返回新增条目数
     * @author 杨新杰
     * @date 2018/10/10 16:14
     */
    @Override
    public int addWnkIntegralType(WnkIntegralType wnkIntegralType) {
        return wnkIntegralTypeMapper.addWnkIntegralType(wnkIntegralType);
    }

    /**
     * 功能描述:修改分类信息
     *
     * @param  wnkIntegralType 商品分类实体类
     * @return 返回修改的条目数
     * @author 刘武祥
     * @date   2018/10/10 0010 18:31
     */
    public int editWnkIntegralType(WnkIntegralType wnkIntegralType){
        return wnkIntegralTypeMapper.editWnkIntegralType(wnkIntegralType);
    }

    /**
     * 功能描述: 根据ID获取所有分类
     *
     * @param  business_id 商家ID
     * @return 返回所有商家信息
     * @author 刘武祥
     * @date   2018/10/10 0010 18:23
     */
    @Override
    public List<Map<String, Object>> getIntegralTypeAll(Integer business_id) {
        return wnkIntegralTypeMapper.getIntegralTypeAll(business_id);
    }

    /**
     *
     * 功能描述:  根据商家ID获取已经启用的分类
     *
     * @param  business_id 商家ID
     * @return 根据ID返回所有已经启用的分类
     * @author 杨新杰
     * @date   2018/10/11 10:32
     */
    @Override
    public List<Map<String, Object>> getIntegralTypeByTrue(Integer business_id) {
        return wnkIntegralTypeMapper.getIntegralTypeByTrue(business_id);
    }

    /**
     *
     * 功能描述: 根据商家ID获取已经启用的分类 - 添加/修改商品专用
     *
     * @param  business_id 商家ID
     * @return 返回结果
     * @author 杨新杰
     * @date   2018/10/11 14:14
     */
    @Override
    public List<Map<String, Object>> getIntegralTypeByTrueToGoods(Integer business_id) {
        return wnkIntegralTypeMapper.getIntegralTypeByTrueToGoods(business_id);
    }

    /**
     *
     * 功能描述: 根据商家ID和分类ID获取分类信息
     *
     * @param  business_id 商家ID
     * @param  id          分类ID
     * @return 返回获取到的分类信息
     * @author 杨新杰
     * @date   2018/10/11 17:00
     */
    @Override
    public Map<String, Object> getIntegralTypeById(Integer business_id, Integer id) {
        return wnkIntegralTypeMapper.getIntegralTypeById(business_id,id);
    }

    /**
     * 功能描述: 根据商家ID和分类ID获取分类信息
     *
     * @param business_id 商家ID
     * @param type_id     分类ID
     * @return 返回获取到的分类信息
     * @author 杨新杰
     * @date 2018/10/11 17:00
     */
    @Override
    public List<Map<String, Object>> getGoodsByTypeIdAndWnk(Integer business_id, Integer type_id) {
        return wnkIntegralTypeMapper.getGoodsByTypeIdAndWnk(business_id,type_id);
    }

    @Override
    public List<WnkIntegralType> getAllIntegralType() {
        return this.wnkIntegralTypeMapper.getAllIntegralType();
    }
}
