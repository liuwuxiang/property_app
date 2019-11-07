package com.springmvc.service;

import com.springmvc.entity.WnkIntegralGoods;

import java.util.List;
import java.util.Map;

/**
 * @author 杨新杰
 * @Date 2018/10/11 14:45
 */
public interface IWnkIntegralGoodsServer {

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
     * @return 修改成功返回1 失败返回0
     * @author 杨新杰
     * @date   2018/10/11 16:14
     */
    int editIntegralGoods(WnkIntegralGoods wnkIntegralGoods);

    /**
     *
     * 功能描述:
     *
     * @param  business_id  商家ID
     * @param  id           商品ID
     * @return 返回查询到的所有商品
     * @author 杨新杰
     * @date   2018/10/11 15:20
     */
    Map<String,Object> getIntegralGoodsById(Integer business_id, Integer id);

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
     *
     * 功能描述:获取商家所有的已经启用商品
     *
     * @param  business_id 商家ID
     * @return 返回查询到的所有商品
     * @author 杨新杰
     * @date   2018/10/11 16:37
     */
    List<Map<String,Object>> getIntegralGoodsAllAndTrue(Integer business_id);

    /**
     *
     * 功能描述: 根据商品ID查询商品的信息
     *
     * @param  business_id 商家ID
     * @param  goods_id    商品ID
     * @return 返回商品信息
     * @author 杨新杰
     * @date   2018/10/12 9:56
     */
    Map<String,Object> getIntegralByIdAndWnk(Integer business_id, Integer goods_id);

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
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/10/31 3:00 AM
     */
    List<WnkIntegralGoods> getGoodsByTypeId(Integer type_id);

    /**
     *
     * 功能描述: 获取所有积分商品
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    17:36 2018/12/31
     */
    List<Map<String,Object>> getAllIntegralBusinessGoods();

    /**
     *
     * 功能描述: 更新商家积分商品推荐状态
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    18:01 2018/12/31
     */
    int updateBusinessIntegralGoodsRecommendStatus(Integer status,Integer goods_id);

    /**
     *
     * 功能描述: 更新商家积分商品上架状态
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    18:01 2018/12/31
     */
    int updateBusinessIntegralGoodsCheckedStatus(Integer status, Integer goods_id);

    /**
     *
     * 功能描述: 根据条件查询商家积分商品管理
     *
     * @param   map 查询条件
     * @return: 返回查询到的商家积分商品管理
     * @author: 刘武祥
     * @date: 2019/1/16 0016 10:51
     */
    List<Map<Object,Object>> adminSearchIntegralBusinessGoods(Map<String,Object> map);
}
