package com.springmvc.service;

import com.springmvc.entity.UserScanCodeWxPay;

/**
 * @author: zhangfan
 * @Date: 2018/10/29 05:54
 * @Description:扫码支付订单服务接口类
 */
public interface IUserScanCodeWxPayService {
    /**
     *
     * 功能描述: 新增扫码支付订单信息
     *
     * @param: userScanCodeWxPay
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 5:52 AM
     */
    int insertBusinessUpgradeOrder(UserScanCodeWxPay userScanCodeWxPay);
    /**
     *
     * 功能描述: 通过订单号查询查询记录
     *
     * @param: order_no
     * @return: UserScanCodeWxPay
     * @author: zhangfan
     * @date: 2018/10/29 5:52 AM
     */
    UserScanCodeWxPay selectRecordByOrderNo(String order_no);
    /**
     *
     * 功能描述: 通过订单号修改支付状态
     *
     * @param: userScanCodeWxPay
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 5:53 AM
     */
    int updateStateByOrderNo(UserScanCodeWxPay userScanCodeWxPay);
}
