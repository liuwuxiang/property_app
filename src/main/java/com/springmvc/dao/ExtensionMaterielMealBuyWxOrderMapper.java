package com.springmvc.dao;

import com.springmvc.entity.ExtensionMaterielMealBuyWxOrder;
import com.springmvc.entity.WnkBusinessRechargeOrder;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 05:04
 * @Description:商户购买推广物料微信支付订单信息Mapper
 */
public interface ExtensionMaterielMealBuyWxOrderMapper {
    /**
     *
     * 功能描述: 新增商家物料套餐购买信息
     *
     * @param: extensionMaterielMealBuyWxOrder
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
     * @param: extensionMaterielMealBuyWxOrder
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 2:34 AM
     */
    int updateStateByOrderNo(ExtensionMaterielMealBuyWxOrder extensionMaterielMealBuyWxOrder);
}
