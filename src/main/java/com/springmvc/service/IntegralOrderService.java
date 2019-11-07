package com.springmvc.service;


import com.springmvc.entity.IntegralOrder;

import java.util.List;
import java.util.Map;

public interface IntegralOrderService {

    /**
     * 根据ID获取所有商品列表
     * @param user_id 用户ID
     * @return 返回商品列表
     */
    List<Map<String,Object>> getIntegralOrderById(Integer user_id);

    /**
     * 新增订单
     * @param integralOrder 订单实体类
     * @return 成功返回1 失败返回0
     */
    int addGoodsOrder(IntegralOrder integralOrder);

    /**
     * 查询所有订单信息
     * @return 返回查询结果, 无记录返回null
     */
    List<Map<String,Object>> getIntegralOrderAll();

    /**
     * 发货
     * @param integralOrder 订单实体类
     * @return 成功返回1 失败返回0
     */
    int updateIntegralOrderExpress(IntegralOrder integralOrder);

    /**
     * 根据订单ID获取必要的信息
     * @param id 订单id
     * @return 返回查询结果,无记录返回null
     */
    Map<String,Object> getOrderInfoById(String id);

    /**
     *
     * 功能描述: 根据条件查询订单管理信息
     *
     * @param   map
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/15 0015 17:06
     */
    List<Map<Object,Object>> adminSearchIntegralOrder(Map<String,Object> map);
}
