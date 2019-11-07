package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessRechargeOrderMapper;
import com.springmvc.entity.WnkBusinessRechargeOrder;
import com.springmvc.service.IWnkBusinessRechargeOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhangfan
 * @Date: 2018/10/29 02:35
 * @Description:万能卡商家服务实现类
 */
@Service("/wnkBusinessRechargeOrderService")
public class WnkBusinessRechargeOrderService implements IWnkBusinessRechargeOrderService {
    @Resource
    private WnkBusinessRechargeOrderMapper wnkBusinessRechargeOrderMapper;

    @Override
    public int insertBusinessRechargeOrder(WnkBusinessRechargeOrder wnkBusinessRechargeOrder) {
        return this.wnkBusinessRechargeOrderMapper.insertBusinessRechargeOrder(wnkBusinessRechargeOrder);
    }

    @Override
    public WnkBusinessRechargeOrder selectRecordByOrderNo(String order_no) {
        return this.wnkBusinessRechargeOrderMapper.selectRecordByOrderNo(order_no);
    }

    @Override
    public int updateStateByOrderNo(WnkBusinessRechargeOrder wnkBusinessRechargeOrder) {
        return this.wnkBusinessRechargeOrderMapper.updateStateByOrderNo(wnkBusinessRechargeOrder);
    }
}
