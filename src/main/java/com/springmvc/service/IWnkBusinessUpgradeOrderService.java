package com.springmvc.service;

import com.springmvc.entity.WnkBusinessUpgradeOrder;

/**
 * @author: zhangfan
 * @Date: 2018/10/29 01:38
 * @Description:万能卡商家升级订单信息服务接口类
 */
public interface IWnkBusinessUpgradeOrderService {
    /**
     *
     * 功能描述: 新增商家升级订单信息
     *
     * @param: wnkBusinessUpgradeOrder
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 1:36 AM
     */
    int insertBusinessUpgradeOrder(WnkBusinessUpgradeOrder wnkBusinessUpgradeOrder);

    /**
     *
     * 功能描述: 通过订单号查询查询记录
     *
     * @param: order_no
     * @return: WnkBusinessUpgradeOrder
     * @author: zhangfan
     * @date: 2018/10/29 1:36 AM
     */
    WnkBusinessUpgradeOrder selectRecordByOrderNo(String order_no);

    /**
     *
     * 功能描述:通过订单号修改支付状态
     *
     * @param:wnkBusinessUpgradeOrder
     * @return:int
     * @author: zhangfan
     * @date: 2018/10/29 1:37 AM
     */
    int updateStateByOrderNo(WnkBusinessUpgradeOrder wnkBusinessUpgradeOrder);
}
