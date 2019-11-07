package com.springmvc.service.impl;

import com.springmvc.dao.IntegralOrderMapper;
import com.springmvc.entity.IntegralOrder;
import com.springmvc.service.IntegralOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/integralOrderService")
public class IntegralOrderServiceImpl implements IntegralOrderService {

    @Resource
    private IntegralOrderMapper integralOrderMapper;

    /**
     * 根据ID获取所有商品列表
     *
     * @param user_id
     */
    @Override
    public List<Map<String,Object>> getIntegralOrderById(Integer user_id) {
        return integralOrderMapper.getIntegralOrderById(user_id);
    }

    /**
     * 新增订单
     *
     * @param integralOrder
     * @return
     */
    @Override
    public int addGoodsOrder(IntegralOrder integralOrder) {
        return integralOrderMapper.addGoodsOrder(integralOrder);
    }

    /**
     * 查询所有订单信息
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getIntegralOrderAll() {
        return integralOrderMapper.getIntegralOrderAll();
    }

    /**
     * 发货
     *
     * @param integralOrder
     * @return
     */
    @Override
    public int updateIntegralOrderExpress(IntegralOrder integralOrder) {
        return integralOrderMapper.updateIntegralOrderExpress(integralOrder);
    }

    /**
     * 根据订单ID获取必要的信息
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getOrderInfoById(String id) {
        return integralOrderMapper.getOrderInfoById(id);
    }

    /**
     *
     * 功能描述: 根据条件查询订单管理信息
     *
     * @param   map 查询条件
     * @return: java.util.List<java.util.Map<java.lang.Object,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/15 0015 17:10
     */
    @Override
    public List<Map<Object, Object>> adminSearchIntegralOrder(Map<String, Object> map) {
        return this.integralOrderMapper.adminSearchIntegralOrder(map);
    }
}
