package com.springmvc.service.impl;

import com.springmvc.dao.OpenMemberCardWxPayOrderMapper;
import com.springmvc.entity.OpenMemberCardWxPayOrder;
import com.springmvc.service.IOpenMemberCardWxPayOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("/openMemberCardWxPayOrderService")
public class OpenMemberCardWxPayOrderService implements IOpenMemberCardWxPayOrderService{
    @Resource
    private OpenMemberCardWxPayOrderMapper openMemberCardWxPayOrderMapper;

    @Override
    public int insertOpenCardOrder(OpenMemberCardWxPayOrder openMemberCardWxPayOrder) {
        return this.openMemberCardWxPayOrderMapper.insertOpenCardOrder(openMemberCardWxPayOrder);
    }

    @Override
    public OpenMemberCardWxPayOrder selectRecordByOrderNo(String order_no) {
        return this.openMemberCardWxPayOrderMapper.selectRecordByOrderNo(order_no);
    }

    @Override
    public int updateStateByOrderNo(Integer state, String order_no) {
        return this.openMemberCardWxPayOrderMapper.updateStateByOrderNo(state, order_no);
    }
}
