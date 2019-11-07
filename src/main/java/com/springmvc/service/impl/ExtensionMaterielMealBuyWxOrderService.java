package com.springmvc.service.impl;

import com.springmvc.dao.ExtensionMaterielMealBuyWxOrderMapper;
import com.springmvc.entity.ExtensionMaterielMealBuyWxOrder;
import com.springmvc.service.IExtensionMaterielMealBuyWxOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 05:08
 * @Description:商户购买推广物料微信支付订单信息服务实现类
 */
@Service("/extensionMaterielMealBuyWxOrderService")
public class ExtensionMaterielMealBuyWxOrderService implements IExtensionMaterielMealBuyWxOrderService {
    @Resource
    private ExtensionMaterielMealBuyWxOrderMapper extensionMaterielMealBuyWxOrderMapper;

    @Override
    public int insertMaterielMealBuyOrder(ExtensionMaterielMealBuyWxOrder extensionMaterielMealBuyWxOrder) {
        return this.extensionMaterielMealBuyWxOrderMapper.insertMaterielMealBuyOrder(extensionMaterielMealBuyWxOrder);
    }

    @Override
    public ExtensionMaterielMealBuyWxOrder selectRecordByOrderNo(String order_no) {
        return this.extensionMaterielMealBuyWxOrderMapper.selectRecordByOrderNo(order_no);
    }

    @Override
    public int updateStateByOrderNo(ExtensionMaterielMealBuyWxOrder extensionMaterielMealBuyWxOrder) {
        return this.extensionMaterielMealBuyWxOrderMapper.updateStateByOrderNo(extensionMaterielMealBuyWxOrder);
    }
}
