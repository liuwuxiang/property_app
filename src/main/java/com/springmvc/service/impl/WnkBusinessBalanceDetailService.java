package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessBalanceDetailMapper;
import com.springmvc.entity.WnkBusinessBalanceDetail;
import com.springmvc.service.IWnkBusinessBalanceDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/wnkBusinessBalanceDetailService")
public class WnkBusinessBalanceDetailService implements IWnkBusinessBalanceDetailService{
    @Resource
    private WnkBusinessBalanceDetailMapper wnkBusinessBalanceDetailMapper;

    @Override
    public List<Map<Object, Object>> selectRecordByBusinessIdAndType(Integer business_id, Integer type) {
        return this.wnkBusinessBalanceDetailMapper.selectRecordByBusinessIdAndType(business_id, type);
    }

    @Override
    public List<Map<Object, Object>> countAmountByMonth() {
        return this.wnkBusinessBalanceDetailMapper.countAmountByMonth();
    }

    @Override
    public int insertNewRecord(WnkBusinessBalanceDetail wnkBusinessBalanceDetail) {
        return this.wnkBusinessBalanceDetailMapper.insertNewRecord(wnkBusinessBalanceDetail);
    }

    @Override
    public int deleteRecordById(Integer record_id) {
        return this.wnkBusinessBalanceDetailMapper.deleteRecordById(record_id);
    }

    /**
     *
     * 功能描述:  根据商家ID查询今日总收入
     *
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/10 10:36
     */
    @Override
    public Map<String, Object> selectTodayRevenueById(Integer business_id) {
        return this.wnkBusinessBalanceDetailMapper.selectTodayRevenueById(business_id);
    }
    /**
     *
     * 功能描述: 根据商家ID查询商家总收入和总收入条目数
     *
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/10 10:48
     */
    @Override
    public Map<String, Object> selectRevenueTotalAndMoney(Integer business_id) {
        return this.wnkBusinessBalanceDetailMapper.selectRevenueTotalAndMoney(business_id);
    }

    /**
     * 功能描述:  更新记录为同意
     *
     * @param type 0 = 同意 1 = 拒绝
     * @return
     * @author 杨新杰
     * @date 2018/11/25 2:24
     */
    @Override
    public int updateBusinessBalanceDetailState(Integer type,Integer Withdraw_id) {
        return this.wnkBusinessBalanceDetailMapper.updateBusinessBalanceDetailState(type,Withdraw_id);
    }

    /**
     * 插入新纪录 (提现专用)
     *
     * @param wnkBusinessBalanceDetail
     * @return
     */
    @Override
    public int insertNewRecordWithdraw(WnkBusinessBalanceDetail wnkBusinessBalanceDetail) {
        return this.wnkBusinessBalanceDetailMapper.insertNewRecordWithdraw(wnkBusinessBalanceDetail);
    }
}
