package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessUpgradeOrderMapper;
import com.springmvc.entity.WnkBusinessUpgradeOrder;
import com.springmvc.service.IWnkBusinessUpgradeOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhangfan
 * @Date: 2018/10/29 01:38
 * @Description:万能卡商家等级升级订单信息服务实现类
 */
@Service("/wnkBusinessUpgradeOrderService")
public class WnkBusinessUpgradeOrderService implements IWnkBusinessUpgradeOrderService {
    @Resource
    private WnkBusinessUpgradeOrderMapper wnkBusinessUpgradeOrderMapper;

    @Override
    public int insertBusinessUpgradeOrder(WnkBusinessUpgradeOrder wnkBusinessUpgradeOrder) {
        return this.wnkBusinessUpgradeOrderMapper.insertBusinessUpgradeOrder(wnkBusinessUpgradeOrder);
    }

    @Override
    public WnkBusinessUpgradeOrder selectRecordByOrderNo(String order_no) {
        return this.wnkBusinessUpgradeOrderMapper.selectRecordByOrderNo(order_no);
    }

    @Override
    public int updateStateByOrderNo(WnkBusinessUpgradeOrder wnkBusinessUpgradeOrder) {
        return this.wnkBusinessUpgradeOrderMapper.updateStateByOrderNo(wnkBusinessUpgradeOrder);
    }
}
