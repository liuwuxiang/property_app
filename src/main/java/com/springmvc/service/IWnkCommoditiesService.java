package com.springmvc.service;

import com.springmvc.entity.WnkCommodities;

import java.util.List;
import java.util.Map;

public interface IWnkCommoditiesService {
    /**
     * 查询某个分类下的所有商品
     * @param type_id
     * @return
     */
    List<Map<Object,Object>> selectAllCommodityByTypeId(Integer type_id);

    /**
     * 通过id查询商品
     * @param record_id
     * @return
     */
    WnkCommodities selectById(Integer record_id);

    /**
     * 添加商品
     * @param wnkCommodities
     * @return
     */
    int addCommodity(WnkCommodities wnkCommodities);

    /**
     * 修改商品信息
     * @param wnkCommodities
     * @return
     */
    int updateCommodity(WnkCommodities wnkCommodities);

    /**
     * 将某个分类下的所有商品状态设置为删除
     * @param type_id
     * @return
     */
    int deleteCommodity(Integer type_id);

    int selectCommodityTotalById(Integer business_id);

    /**
     * 查询商家所有商品信息
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/10 11:43
     */
    List<Map<String,Object>> selectCommoditiesInfoAllById(Integer business_id);

    /**
     *
     * 功能描述: 更新商品标签
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/20 12:27 AM
     */
    int updateCommodityTag(WnkCommodities wnkCommodities);

    /**
     *
     * 功能描述: 用户端查询某个分类下的商品
     *
     * @param type_id 商品分类id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/28 4:12 PM
     */
    List<Map<Object,Object>> userSelectAllCommodityByTypeId(Integer type_id);

    /**
     *
     * 功能描述: 通过商品名称模糊查询商家
     *
     * @param lat 纬度
     * @param longt 经度
     * @param fuzz_commodity_name 商品模糊名称
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 6:28 PM
     */
    List<Map<Object,Object>> selectBusinessByFuzzCommodityName(Double lat,Double longt,String fuzz_commodity_name);

    /**
     *
     * 功能描述:根据分类ID修改商品删除状态
     *
     * @param delete_state 删除状态(0-未删除,1-已删除)
     * @param type_id 商品分类ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/20 10:18 PM
     */
    int updateDeleteStateByTypeId(Integer delete_state,Integer type_id);

    /**
     *
     * 功能描述: 根据商品ID修改商品删除状态
     *
     * @param delete_state 删除状态(0-未删除,1-已删除)
     * @param commodity_id 商品ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/20 10:53 PM
     */
    int updateDeleteStateByCommodityId(Integer delete_state,Integer commodity_id);

    /**
     * 查询商家商品中赠送额度最高的商品的赠送额度
     * @param businessId 商家ID
     * @author 杨新杰
     * @return 返回查询结果
     */
    Map<String,Object> selectCommoditiesSpecificationsServiceMaxGiftNounByBusinessId(Integer businessId);

    /**
     *
     * 方法说明:查询商家的所有商品信息(排除下架的和禁用的)
     *
     * @author 杨新杰
     * @param businessId 商家ID
     * @return  查询结果
     **/
    List<Map<String,Object>> selectCommoditiesInfoAllExcludeDelAndUnderById(Integer businessId);
}
