package com.springmvc.dao;

import com.springmvc.entity.RechargeOrder;

import java.util.List;
import java.util.Map;

public interface RechargeOrderMapper {

    /**
     *
     * 功能描述:后台获取所有用户充值记录
     *
     * @param
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:35
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     *
     * 功能描述: 后台获取所有商家充值记录
     *
     * @param
     * @return: 返回所有商家充值记录
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:35
     */
    List<Map<Object,Object>> selectAllBusiness();

    /**
     *
     * 功能描述: 新增线下充值订单
     *
     * @param   rechargeOrder
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:35
     */
    int addUnderLineRechargeOrder(RechargeOrder rechargeOrder);

    /**
     *
     * 功能描述: 通过订单号查询订单
     *
     * @param   system_order_no
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:36
     */
    RechargeOrder selectBySystemOrderNo(String system_order_no);

    /**
     *
     * 功能描述: 更新支付状态
     *
     * @param   rechargeOrder
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:36
     */
    int updatePayState(RechargeOrder rechargeOrder);

    /**
     * 功能描述: 根据条件查询订单信息
     *
     * @param map 条件Map
     * @return:
     * @author: 杨新杰
     * @date: 10:32 2018/11/29
     */
    List<Map<String,Object>> adminSearchRechargeOrderByConditions(Map<String,Object> map);

    /** 
     *
     * 功能描述: 商家充值订单模糊查询
     *
     * @param   map 条件Map
     * @return: 返回商家充值订单信息
     * @author: 刘武祥
     * @date: 2019/1/9 0009 10:37
     */
    List<Map<String,Object>> adminBusinessSearchRechargeOrderByConditions(Map<String,Object> map);

}
