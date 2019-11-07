package com.springmvc.service;

import com.springmvc.entity.RechargeOrder;

import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 充值订单
 * @Author: 刘武祥
 * @Date: 2019/2/19 0019 12:26
 */
public interface IRechargeOrderService {
    /**
     * 后台获取所有用户充值记录
     * @return
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     * 后台获取所有商家充值记录
     * @return
     */
    List<Map<Object,Object>> selectAllBusiness();

    /**
     * 新增线下充值订单
     * @param rechargeOrder
     * @return
     */
    int addUnderLineRechargeOrder(RechargeOrder rechargeOrder);

    /**
     * 通过订单号查询订单
     * @param system_order_no
     * @return
     */
    RechargeOrder selectBySystemOrderNo(String system_order_no);

    /**
     * 更新支付状态
     * @param rechargeOrder
     * @return
     */
    int updatePayState(RechargeOrder rechargeOrder);

    /**
     *
     * 功能描述: 根据条件查询用户订单信息
     *
     * @param   map 条件Map
     * @return:
     * @author: 杨新杰
     * @date: 10:32 2018/11/29
     */
    List<Map<String,Object>> adminSearchRechargeOrderByConditions(Map<String,Object> map);

    /**
     *
     * 功能描述: 根据条件查询商家订单信息
     *
     * @param   map 条件Map
     * @return:
     * @author: 杨新杰
     * @date: 10:32 2018/11/29
     */
    List<Map<String,Object>> adminBusinessSearchRechargeOrderByConditions(Map<String,Object> map);
}
