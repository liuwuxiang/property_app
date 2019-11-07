package com.springmvc.service;

import com.springmvc.entity.WnkBusinessLevelIntegralDetail;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/28 23:21
 * @Description:万能卡商家等级积分明细服务接口类
 */
public interface IWnkBusinessLevelIntegralDetailService {
    /**
     *
     * 功能描述: 查询某商家的交易记录
     *
     * @param: business_id,transactions_type(交易类型(0-收入,1-支出))
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/28 11:19 PM
     */
    List<Map<Object,Object>> selectRecordByBusinessId(Integer business_id,Integer transactions_type);

    /**
     *
     * 功能描述: 插入新纪录
     *
     * @param: wnkBusinessLevelIntegralDetail
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/28 11:20 PM
     */
    int insertNewRecord(WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail);

    /**
     *
     * 功能描述: 插入赠送新记录
     *
     * @param wnkBusinessLevelIntegralDetail 等级积分信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/13 11:26 PM
     */
    int insertSendNewRecord(WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail);

    /**
     *
     * 功能描述: 插入用户消费扣积分记录
     *
     * @param wnkBusinessLevelIntegralDetail 插入信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/14 12:27 AM
     */
    int insertNewRecordUserXF(WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail);
}
