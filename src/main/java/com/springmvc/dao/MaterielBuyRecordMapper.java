package com.springmvc.dao;

import com.springmvc.entity.MaterielBuyRecord;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/11/18 00:25
 * @Description:商家物料购买记录Mapper
 */
public interface MaterielBuyRecordMapper {
    /**
     *
     * 功能描述: 查询某个商家某个物料的购买记录
     *
     * @param business_id 商家id
     * @param materiel_id 物料id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/18 12:31 AM
     */
    List<Map<Object,Object>> selectRecordByBusinessIdAndMaterielId(Integer business_id,Integer materiel_id);

    /**
     *
     * 功能描述: 插入商家物料购买记录
     *
     * @param materielBuyRecord 插入信息
     * @return:
     * @author: zhangfan
     * @date: 2018/11/18 12:32 AM
     */
    int inserRecord(MaterielBuyRecord materielBuyRecord);
}
