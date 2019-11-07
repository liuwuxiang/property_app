package com.springmvc.dao;

import com.springmvc.entity.WnkIntegralGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 杨新杰
 * @Date 2018/10/11 14:50
 */
public interface WnkIntegralGoodsMapper {

    /**
     *
     * 功能描述: 新增商品
     *
     * @param  wnkIntegralGoods 商品实体类
     * @return 返回新增条目数  正确时候应该返回1
     * @author 杨新杰
     * @date   2018/10/11 14:49
     */
    int addIntegralGoods(WnkIntegralGoods wnkIntegralGoods);

    /**
     *
     * 功能描述: 修改商品信息
     *
     * @param  wnkIntegralGoods 商品实体类
     * @return 返回新增条目数  正确时候应该返回1
     * @author 杨新杰
     * @date   2018/10/11 14:49
     */
    int editIntegralGoods(WnkIntegralGoods wnkIntegralGoods);

    /**
     *
     * 功能描述: 根据ID获取商品信息
     *
     * @param  business_id  商家ID
     * @param  id           商品ID
     * @return
     * @author 杨新杰
     * @date   2018/10/11 15:20
     */
    Map<String,Object> getIntegralGoodsById(@Param("business_id") Integer business_id, @Param("id") Integer id);

    /**
     *
     * 功能描述:获取商家所有的商品
     *
     * @param  business_id 商家ID
     * @return 返回查询到的所有商品
     * @author 杨新杰
     * @date   2018/10/11 16:37
     */
    List<Map<String,Object>> getIntegralGoodsAll(Integer business_id);

    /**
     * 功能描述:获取商家所有的已经启用商品
     *
     * @param  business_id 商家ID
     * @return 返回查询到的所有商品
     * @author 杨新杰
     * @date 2018/10/11 16:37
     */
    List<Map<String,Object>> getIntegralGoodsAllAndTrue(Integer business_id);

    /**
     * 功能描述: 根据商品ID查询商品的信息
     *
     * @param business_id 商家ID
     * @param goods_id    商品ID
     * @return 返回商品信息
     * @author 杨新杰
     * @date 2018/10/12 9:56
     */
    Map<String,Object> getIntegralByIdAndWnk(@Param("business_id") Integer business_id,@Param("goods_id") Integer goods_id);

    /**
     *
     * 功能描述: 获取推荐商品
     *
     * @param: List
     * @return:
     * @author: zhangfan
     * @date: 2018/10/31 1:18 AM
     */
    List<Map<String,Object>> getRecommednGoods();

    /**
     *
     * 功能描述: 根据分类id获取商品
     *
     * @param:  type_id
     * @return:
     * @author: zhangfan
     * @date: 2018/10/31 3:00 AM
     */
    List<WnkIntegralGoods> getGoodsByTypeId(Integer type_id);

    /**
     * 功能描述: 获取所有积分商品
     *
     * @return
     * @author 杨新杰
     * @date 17:36 2018/12/31
     */
    List<Map<String,Object>> getAllIntegralBusinessGoods();

    /**
     * 功能描述: 更新商家积分商品推荐状态
     *
     * @param status
     * @param goods_id
     * @return
     * @author 杨新杰
     * @date 18:01 2018/12/31
     */
    int updateBusinessIntegralGoodsRecommendStatus(@Param("status") Integer status, @Param("goods_id") Integer goods_id);

    int updateBusinessIntegralGoodsCheckedStatus(@Param("status") Integer status, @Param("goods_id") Integer goods_id);

    /**
     *
     * 功能描述: 查询商家积分商品管理
     *
     * @param   map 查询条件
     * @return: java.util.List<java.util.Map<java.lang.Object,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/16 0016 10:53
     */
    List<Map<Object,Object>> adminSearchIntegralBusinessGoods(Map<String,Object> map);
}
