package com.springmvc.service;

import com.springmvc.dao.ExtensionMaterielMealBuyWxOrderMapper;
import com.springmvc.entity.ExtensionMaterielMealBuyWxOrder;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 05:08
 * @Description:商户购买推广物料微信支付订单信息服务接口类
 */
public interface IExtensionMaterielMealBuyWxOrderService {
    /**
     *
     * 功能描述: 新增商家物料套餐购买信息
     *
     * @param: wnkBusinessRechargeOrder
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 2:33 AM
     */
    int insertMaterielMealBuyOrder(ExtensionMaterielMealBuyWxOrder extensionMaterielMealBuyWxOrder);
    /**
     *
     * 功能描述: 通过订单号查询查询记录
     *
     * @param: order_no
     * @return: ExtensionMaterielMealBuyWxOrder
     * @author: zhangfan
     * @date: 2018/10/29 2:33 AM
     */
    ExtensionMaterielMealBuyWxOrder selectRecordByOrderNo(String order_no);
    /**
     *
     * 功能描述: 通过订单号修改支付状态
     *
     * @param: wnkBusinessRechargeOrder
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 2:34 AM
     */
    int updateStateByOrderNo(ExtensionMaterielMealBuyWxOrder extensionMaterielMealBuyWxOrder);
}
