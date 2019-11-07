package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessWithdrawOrderMapper;
import com.springmvc.entity.WnkBusinessWithdrawOrder;
import com.springmvc.service.IWnkBusinessWithdrawOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("/wnkBusinessWithdrawOrderService")
public class WnkBusinessWithdrawOrderService implements IWnkBusinessWithdrawOrderService{
    @Resource
    private WnkBusinessWithdrawOrderMapper wnkBusinessWithdrawOrderMapper;

    @Override
    public int insertWithdrawRecord(WnkBusinessWithdrawOrder wnkBusinessWithdrawOrder) {
        return this.wnkBusinessWithdrawOrderMapper.insertWithdrawRecord(wnkBusinessWithdrawOrder);
    }

    @Override
    public List<Map<Object, Object>> selectAllAdmin() {
        return this.wnkBusinessWithdrawOrderMapper.selectAllAdmin();
    }

    @Override
    public WnkBusinessWithdrawOrder selectById(Integer record_id) {
        return this.wnkBusinessWithdrawOrderMapper.selectById(record_id);
    }

    @Override
    public int updateStateNoCan(Date loan_date, Integer record_id) {
        return this.wnkBusinessWithdrawOrderMapper.updateStateNoCan(loan_date, record_id);
    }

    @Override
    public int updateStateFKSuccess(WnkBusinessWithdrawOrder wnkBusinessWithdrawOrder) {
        return this.wnkBusinessWithdrawOrderMapper.updateStateFKSuccess(wnkBusinessWithdrawOrder);
    }

    @Override
    public int updatealReadyPresented(Date loan_date, Integer record_id) {
        return this.wnkBusinessWithdrawOrderMapper.updatealReadyPresented(loan_date, record_id);
    }

    @Override
    public int deleteRecordById(Integer record_id) {
        return this.wnkBusinessWithdrawOrderMapper.deleteRecordById(record_id);
    }

    @Override
    public List<Map<Object, Object>> selectRecordByBusinessId(Integer business_id) {
        return this.wnkBusinessWithdrawOrderMapper.selectRecordByBusinessId(business_id);
    }

    /**
     * 功能描述: 根据商家ID和订单记录ID查询提现订单信息
     *
     * @param business_id 商家id
     * @param withdraw_id 提现订单记录ID
     * @return
     * @author 杨新杰
     * @date 2018/11/25 3:11
     */
    @Override
    public Map<Object, Object> selectWithdrawInfoById(Integer business_id, Integer withdraw_id) {
        return this.wnkBusinessWithdrawOrderMapper.selectWithdrawInfoById(business_id,withdraw_id);
    }

    /**
     *
     * 功能描述: 根据条件查询商家订单信息
     *
     * @param  map 查询条件
     * @return 商家订单信息
     * @author 刘武祥
     * @date   2018/11/30 0030 12:01
     */
    @Override
    public List<Map<Object, Object>> adminSearchBusinessWithdrawOrdersInfoByConditions(Map<String, Object> map) {
        return this.wnkBusinessWithdrawOrderMapper.adminSearchBusinessWithdrawOrdersInfoByConditions(map);
    }
}
