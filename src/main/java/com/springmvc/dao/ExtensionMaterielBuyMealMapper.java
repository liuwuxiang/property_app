package com.springmvc.dao;

import com.springmvc.entity.ExtensionMaterielBuyMeal;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 03:24
 * @Description:物料购买套餐Mapper
 */
public interface ExtensionMaterielBuyMealMapper {
    /**
     *
     * 功能描述: 获取某个物料的所有套餐
     *
     * @param: materiel_id-物料id
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/30 3:27 AM
     */
    List<Map<Object,Object>> selectAllRecordByMaterielId(Integer materiel_id);

    /**
     *
     * 功能描述: 通过套餐id查询套餐
     *
     * @param: record_id
     * @return: ExtensionMaterielBuyMeal
     * @author: zhangfan
     * @date: 2018/10/30 4:08 AM
     */
    ExtensionMaterielBuyMeal selectById(Integer record_id);
}
