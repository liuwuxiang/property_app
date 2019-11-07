package com.springmvc.service;

import com.springmvc.entity.WnkBusinessBalanceDetail;

import java.util.List;
import java.util.Map;

public interface IWnkBusinessBalanceDetailService {
    /**
     * 查询某个商家的交易记录(type=0-收入,type=1-支出)
     * @param business_id 商家ID
     * @param type 0-收入,type=1-支出
     * @return
     */
    List<Map<Object,Object>> selectRecordByBusinessIdAndType(Integer business_id, Integer type);

    /**
     * 按月份统计所有收入
     * @return
     */
    List<Map<Object,Object>> countAmountByMonth();

    /**
     * 插入新纪录
     * @param wnkBusinessBalanceDetail
     * @return
     */
    int insertNewRecord(WnkBusinessBalanceDetail wnkBusinessBalanceDetail);

    /**
     * 根据id删除记录
     * @param record_id
     * @return
     */
    int deleteRecordById(Integer record_id);

    /**
     *
     * 功能描述:  根据商家ID查询今日总收入
     *
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/10 10:36
     */
    Map<String,Object> selectTodayRevenueById(Integer business_id);
    /**
     *
     * 功能描述: 根据商家ID查询商家总收入和总收入条目数
     *
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/10 10:48
     */
    Map<String,Object> selectRevenueTotalAndMoney(Integer business_id);

    /**
     *
     * 功能描述:  更新记录为同意/拒绝
     *
     * @param type  0 = 同意 1 = 拒绝
     * @return
     * @author 杨新杰
     * @date   2018/11/25 2:24
     */
    int updateBusinessBalanceDetailState(Integer type,Integer Withdraw_id);

    /**
     * 插入新纪录 (提现专用)
     * @param wnkBusinessBalanceDetail
     * @return
     */
    int insertNewRecordWithdraw(WnkBusinessBalanceDetail wnkBusinessBalanceDetail);
}
