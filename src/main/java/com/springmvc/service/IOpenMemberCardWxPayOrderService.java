package com.springmvc.service;

import com.springmvc.entity.OpenMemberCardWxPayOrder;

public interface IOpenMemberCardWxPayOrderService {
    /**
     * 新增开卡微信支付订单
     * @param openMemberCardWxPayOrder
     * @return
     */
    int insertOpenCardOrder(OpenMemberCardWxPayOrder openMemberCardWxPayOrder);

    /**
     * 通过订单号查询订单
     * @param order_no
     * @return
     */
    OpenMemberCardWxPayOrder selectRecordByOrderNo(String order_no);

    /**
     * 通过订单号修改支付状态
     * @param state
     * @param order_no
     * @return
     */
    int updateStateByOrderNo(Integer state,String order_no);
}
