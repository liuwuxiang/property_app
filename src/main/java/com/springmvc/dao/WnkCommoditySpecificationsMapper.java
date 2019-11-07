package com.springmvc.dao;

import com.springmvc.entity.WnkCommoditySpecifications;
import org.apache.ibatis.annotations.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

public interface WnkCommoditySpecificationsMapper {
    //查询某个商品的所有规格
    List<Map<Object,Object>> selectByCommodityId(Integer commodity_id);
    //通过id查询记录
    WnkCommoditySpecifications selectById(Integer record_id);
    //插入新记录
    int insertNewRecord(WnkCommoditySpecifications wnkCommoditySpecifications);
    //查询某个商品下某个状态的所有规格
    List<Map<Object,Object>> selectByCommodityIdAndState(Integer commodity_id,Integer state);
    //更改规格信息状态
    int updateStateByRecordId(Integer state,Integer record_id);
    //更改规格信息名称
    int updateNameByRecordId(String name,Integer record_id);

    /**
     * 功能描述: 更新商品规格信息
     *
     * @param wnkCommoditySpecifications@return
     * @author 杨新杰
     * @date 2018/11/17 16:17
     */
    int updateCommoditySpecificationsInfo(WnkCommoditySpecifications wnkCommoditySpecifications);

    List<Map<Object,Object>> selectByCommodityIdByAll(Integer commodity_id);

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
    Map<Object,Object> selectWnkGuiGeMinPriceByBusinessId(Integer business_id);

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
     * 功能描述: 删除规格ID
     *
     * @param guige_id
     * @return
     * @author 杨新杰
     * @date 14:11 2018/12/30
     */
    int deleteBusinessCommodityGuiGeById(@Param("guige_id") Integer guige_id);
}
