package com.springmvc.service;

import com.springmvc.entity.WnkCommoditySpecifications;

import java.util.List;
import java.util.Map;

public interface IWnkCommoditySpecificationsService {
    /**
     * 查询某个商品的所有规格
     * @param commodity_id
     * @return
     */
    List<Map<Object,Object>> selectByCommodityId(Integer commodity_id);

    /**
     * 通过id查询记录
     * @param record_id
     * @return
     */
    WnkCommoditySpecifications selectById(Integer record_id);

    /**
     * 插入新记录
     * @param wnkCommoditySpecifications
     * @return
     */
    int insertNewRecord(WnkCommoditySpecifications wnkCommoditySpecifications);

    /**
     * 查询某个商品下某个状态的所有规格
     * @param commodity_id
     * @param state
     * @return
     */
    List<Map<Object,Object>> selectByCommodityIdAndState(Integer commodity_id,Integer state);

    /**
     * 更改规格信息状态
     * @param state
     * @param record_id
     * @return
     */
    int updateStateByRecordId(Integer state,Integer record_id);

    /**
     * 更改规格信息名称
     * @param name
     * @param record_id
     * @return
     */
    int updateNameByRecordId(String name,Integer record_id);

    /**
     *
     * 功能描述: 更新商品规格信息
     *
     * @param
     * @return
     * @author 杨新杰
     * @date   2018/11/17 16:17
     */
    int updateCommoditySpecificationsInfo(WnkCommoditySpecifications wnkCommoditySpecifications);

    /**
     * 查询商品所有规格 包括禁用的
     * @param id 商品ID
     * @return
     */
    List<Map<Object,Object>> selectByCommodityIdAll(Integer id);

    /**
     *
     * 功能描述: 更新规格库存
     *
     * @param wnkCommoditySpecifications 规格信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/27 4:50 PM
     */
    int updateSpecificationStock(WnkCommoditySpecifications wnkCommoditySpecifications);

    /**
     *
     * 功能描述: 查询某个商家下所有执行万能卡权益的规格数据
     *
     * @param business_id 商家id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/27 5:53 PM
     */
    List<Map<Object,Object>> selectWnkGuiGeByBusinessId(Integer business_id);

    /**
     *
     * 功能描述:查询某个商品下所有执行万能卡权益的规格数据
     *
     * @param commodity_id 商品id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/27 6:32 PM
     */
    List<Map<Object,Object>> selectWnkGuiGeByCommodity(Integer commodity_id);

    /**
     *
     * 功能描述: 查询某个商家下已开启万能卡权益的规格最低价
     *
     * @param business_id 商家id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/28 3:32 PM
     */
    Map<Object, Object> selectWnkGuiGeMinPriceByBusinessId(Integer business_id);

    /**
     *
     * 功能描述:查询某个商家下已启用的的规格最低价
     *
     * @param business_id 商家id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/28 3:42 PM
     */
    Map<Object,Object> selectWnkGuiGeStartByBusinessId(Integer business_id);

    /**
     *
     * 功能描述: 删除规格ID
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    14:11 2018/12/30
     */
    int deleteBusinessCommodityGuiGeById(Integer guige_id);

    /**
     *
     * 方法说明:根据时间戳和商品ID获取对应日期的商品库存
     *
     * @author 杨新杰
     * @param timeInMillis 时间戳
     * @param commoditiesId 商品ID
     * @return 查询到的结果
     **/
    Map<Object, Object> selectCommoditiesInventoryByDateAndCommoditiesId(Long timeInMillis, Integer commoditiesId,Integer business_id);

    /**
     * 方法说明:查询指定时间段之内指定商品的库存数量
     *
     * @param objectMap map包含的字段:
     *                  <p>joinTime      : 入住时间</p>
     *                  <p>outTime       : 退房时间</p>
     *                  <p>commoditiesId : 商品ID</p>
     *                  <p>businessId    : 商家ID</p>
     * @return 返回指定时间段内最小的库存数量
     * @author 杨新杰
     * @Date 2019/1/22
     **/
    Integer selectDayInventoryNumberByCommoditiesId(Map<String,Object> objectMap);

    /**
     * 方法说明:更新指定时间段之内指定商品的库存数量
     *
     * @param objectMap map包含的字段:
     *                  <p>joinTime      : 入住时间</p>
     *                  <p>outTime       : 退房时间</p>
     *                  <p>commoditiesId : 商品ID</p>
     *                  <p>businessId    : 商家ID</p>
     * @return 返回指定时间段内最小的库存数量
     * @author 杨新杰
     * @Date 2019/1/22
     **/
    void updateDayInventoryNumberByCommoditiesIdAndJoinAndOutTimeAndBusinessId(Map<String,Object> objectMap);

    /**
     * 方法说明:更新指定时间段之内指定商品的库存数量
     *
     * @param objectMap map包含的字段:
     *                  <p>joinTime      : 入住时间</p>
     *                  <p>outTime       : 退房时间</p>
     *                  <p>commoditiesId : 商品ID</p>
     *                  <p>businessId    : 商家ID</p>
     * @return 返回指定时间段内最小的库存数量
     * @author 杨新杰
     * @Date 2019/1/22
     **/
    void updateDayInventoryNumberByCommoditiesIdAndJoinAndOutTimeAndBusinessIdAndUp(Map<String,Object> objectMap);
}
