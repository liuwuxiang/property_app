package com.springmvc.dao;

import com.springmvc.entity.WnkIntegralType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 商家积分商城-商品分类DAO
 * @author 杨新杰
 * @Date 2018/10/10 15:53
 */
public interface WnkIntegralTypeMapper {

    /**
     * 功能描述:新增商品分类
     *
     * @param wnkIntegralType 商品分类实体类
     * @return 返回新增条目数
     * @author 杨新杰
     * @date 2018/10/10 16:14
     */
    int addWnkIntegralType(WnkIntegralType wnkIntegralType);

    /**
     * 功能描述:修改分类信息
     *
     * @param  wnkIntegralType 商品分类实体类
     * @return 返回修改的条目数
     * @author 刘武祥
     * @date   2018/10/10 0010 18:31
     */
    int editWnkIntegralType(WnkIntegralType wnkIntegralType);

    /**
     * 功能描述: 根据ID获取所有分类
     *
     * @param  business_id 商家ID
     * @return 返回所有商家信息
     * @author 刘武祥
     * @date   2018/10/10 0010 18:23
     */
    List<Map<String, Object>> getIntegralTypeAll(Integer business_id);

    /**
     *
     * 功能描述:  根据商家ID获取已经启用的分类
     *
     * @param  business_id 商家ID
     * @return 根据ID返回所有已经启用的分类
     * @author 杨新杰
     * @date   2018/10/11 10:32
     */
    List<Map<String,Object>> getIntegralTypeByTrue(Integer business_id);

    /**
     *
     * 功能描述: 根据商家ID获取已经启用的分类 - 添加/修改商品专用
     *
     * @param  business_id 商家ID
     * @return 返回结果
     * @author 杨新杰
     * @date   2018/10/11 14:14
     */
    List<Map<String,Object>> getIntegralTypeByTrueToGoods(Integer business_id);

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
    Map<String,Object> getIntegralTypeById(@Param("business_id") Integer business_id, @Param("id") Integer id);

    /**
     * 功能描述: 根据商家ID和分类ID获取分类信息
     *
     * @param business_id 商家ID
     * @param type_id     分类ID
     * @return 返回获取到的分类信息
     * @author 杨新杰
     * @date 2018/10/11 17:00
     */
    List<Map<String,Object>> getGoodsByTypeIdAndWnk(@Param("business_id") Integer business_id, @Param("type_id") Integer type_id);

    /**
     *
     * 功能描述: 获取所有万能卡积分商品分类
     *
     * @param:
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/31 12:06 AM
     */
    List<WnkIntegralType> getAllIntegralType();
}
