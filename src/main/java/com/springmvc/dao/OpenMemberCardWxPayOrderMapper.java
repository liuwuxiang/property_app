package com.springmvc.dao;

import com.springmvc.entity.OpenMemberCardWxPayOrder;

public interface OpenMemberCardWxPayOrderMapper {
    //新增开卡微信支付订单
    int insertOpenCardOrder(OpenMemberCardWxPayOrder openMemberCardWxPayOrder);
    //通过订单号查询订单
    OpenMemberCardWxPayOrder selectRecordByOrderNo(String order_no);
    //通过订单号修改支付状态
    int updateStateByOrderNo(Integer state,String order_no);
}
