package com.springmvc.dao;

import com.springmvc.entity.IntegralDetail;

import java.util.List;
import java.util.Map;

public interface IntegralDetailMapper {
    //插入积分明细记录
    int insertTransactionRecord(IntegralDetail integralDetail);
    //查询某用户交易记录
    List<Map<Object,Object>> selectUserRecordByUserId(Integer user_id);
    /**
     *
     * 功能描述:查询某用户某个交易类别的交易记录
     *
     * @param user_id 用户id
     * @param type 0-积分收益,1-积分消费
     * @return:
     * @author: zhangfan
     * @date: 2018/11/13 4:31 PM
     */
    List<Map<Object,Object>> selectUserRecordByUserIdAndType(Integer user_id,Integer type);
}
