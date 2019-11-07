package com.springmvc.dao;

import com.springmvc.entity.WnkBusinessGoodsTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 杨新杰
 * @Date 2018/11/17 14:25
 */
public interface WnkBusinessGoodsTagMapper {

    /**
     * 功能描述: 根据商家ID查询商品标签
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/17 14:28
     */
    List<Map<String,Object>> selectBusinessGoodsTagById(@Param("business_id") Integer business_id,@Param("type") Integer type);

    /**
     * 功能描述: 新增商品标签
     *
     * @param wnkBusinessGoodsTag 商品标签实体类
     * @return
     * @author 杨新杰
     * @date 2018/11/17 14:41
     */
    int insertBusinessGoodsTag(WnkBusinessGoodsTag wnkBusinessGoodsTag);

    /**
     *
     * 功能描述: 更新商品标签
     *
     * @param wnkBusinessGoodsTag 商品标签实体类
     * @return
     * @author 杨新杰
     * @date   2018/11/17 14:45
     */
    int updateBusinessGoodsTag(WnkBusinessGoodsTag wnkBusinessGoodsTag);

    /**
     *
     * 功能描述: 根据标签ID查询商品标签
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    16:33 2018/12/27
     */
    WnkBusinessGoodsTag selectGoodsTagInfoById(@Param("tag_id") Integer tag_id);
}
