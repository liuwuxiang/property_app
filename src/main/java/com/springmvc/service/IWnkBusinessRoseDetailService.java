package com.springmvc.service;

import com.springmvc.entity.WnkBusinessRoseDetail;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/28 23:48
 * @Description:万能卡商家玫瑰明细服务接口类
 */
public interface IWnkBusinessRoseDetailService {
    /**
     *
     * 功能描述: 查询某商家的交易记录
     *
     * @param: business_id,transactions_type(交易类型(0-收入,1-支出))
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/28 11:19 PM
     */
    List<Map<Object,Object>> selectRecordByBusinessId(Integer business_id, Integer transactions_type);

    /**
     *
     * 功能描述: 插入新纪录
     *
     * @param: wnkBusinessRoseDetail
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/28 11:20 PM
     */
    int insertNewRecord(WnkBusinessRoseDetail wnkBusinessRoseDetail);
}
