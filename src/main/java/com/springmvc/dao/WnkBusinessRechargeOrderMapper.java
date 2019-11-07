package com.springmvc.dao;

import com.springmvc.entity.WnkBusinessRechargeOrder;

/**
 * @author: zhangfan
 * @Date: 2018/10/29 02:30
 * @Description:万能卡商家充值订单Mapper
 */
public interface WnkBusinessRechargeOrderMapper {
    /**
     *
     * 功能描述: 新增商家充值订单信息
     *
     * @param: wnkBusinessRechargeOrder
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 2:33 AM
     */
    int insertBusinessRechargeOrder(WnkBusinessRechargeOrder wnkBusinessRechargeOrder);
    /**
     *
     * 功能描述: 通过订单号查询查询记录
     *
     * @param: order_no
     * @return: WnkBusinessRechargeOrder
     * @author: zhangfan
     * @date: 2018/10/29 2:33 AM
     */
    WnkBusinessRechargeOrder selectRecordByOrderNo(String order_no);
    /**
     *
     * 功能描述: 通过订单号修改支付状态
     *
     * @param: wnkBusinessRechargeOrder
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 2:34 AM
     */
    int updateStateByOrderNo(WnkBusinessRechargeOrder wnkBusinessRechargeOrder);
}
