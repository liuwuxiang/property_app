package com.springmvc.service.impl;

import com.springmvc.dao.UserScanCodeWxPayMapper;
import com.springmvc.entity.UserScanCodeWxPay;
import com.springmvc.service.IUserScanCodeWxPayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhangfan
 * @Date: 2018/10/29 05:54
 * @Description:扫码支付订单服务实现类
 */
@Service("/userScanCodeWxPayService")
public class UserScanCodeWxPayService implements IUserScanCodeWxPayService {
    @Resource
    private UserScanCodeWxPayMapper userScanCodeWxPayMapper;

    @Override
    public int insertBusinessUpgradeOrder(UserScanCodeWxPay userScanCodeWxPay) {
        return this.userScanCodeWxPayMapper.insertBusinessUpgradeOrder(userScanCodeWxPay);
    }

    @Override
    public UserScanCodeWxPay selectRecordByOrderNo(String order_no) {
        return this.userScanCodeWxPayMapper.selectRecordByOrderNo(order_no);
    }

    @Override
    public int updateStateByOrderNo(UserScanCodeWxPay userScanCodeWxPay) {
        return this.userScanCodeWxPayMapper.updateStateByOrderNo(userScanCodeWxPay);
    }
}
