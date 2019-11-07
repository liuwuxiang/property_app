package com.springmvc.dao;

import com.springmvc.entity.IntegralOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IntegralOrderMapper {

    List<Map<String,Object>> getIntegralOrderById(Integer user_id);

    int addGoodsOrder(IntegralOrder integralOrder);

    List<Map<String,Object>> getIntegralOrderAll();

    int updateIntegralOrderExpress(IntegralOrder integralOrder);

    Map<String,Object> getOrderInfoById(@Param("id") String id);

    /**
     *
     * 功能描述: 根据条件查询订单管理信息
     *
     * @param   map
     * @return: 返回查询到的订单管理信息
     * @author: 刘武祥
     * @date: 2019/1/15 0015 17:07
     */
    List<Map<Object,Object>> adminSearchIntegralOrder(Map<String,Object> map);
}
