package com.springmvc.dao;

import com.springmvc.entity.WnkCommodityTypes;

import java.util.List;
import java.util.Map;

public interface WnkCommodityTypesMapper {
    //查询某个商家的所有分类
    List<Map<Object,Object>> selectAllTypeByBusinessId(Integer business_id);
    //通过id查询分类
    WnkCommodityTypes selectById(Integer record_id);
    //添加分类
    int addType(WnkCommodityTypes wnkCommodityTypes);
    //修改分类信息
    int updateType(WnkCommodityTypes wnkCommodityTypes);
    //修改分类状态
    int setTypeState(WnkCommodityTypes wnkCommodityTypes);
    //通过名称以及商家id查询分类
    WnkCommodityTypes selectByNameAndBusinessId(String type_name,Integer business_id);
    /**
     *
     * 功能描述: 修改分类删除状态
     *
     * @param delete_state 删除状态(0-未删除,1-已删除)
     * @param type_id 分类ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/20 10:14 PM
     */
    int updateTypeDeleteState(Integer delete_state,Integer type_id);
}
