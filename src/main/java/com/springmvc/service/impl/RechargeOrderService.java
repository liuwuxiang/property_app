package com.springmvc.service.impl;

import com.springmvc.dao.RechargeOrderMapper;
import com.springmvc.entity.RechargeOrder;
import com.springmvc.service.IRechargeOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 充值订单
 * @Author: 刘武祥
 * @Date: 2019/2/19 0019 12:26
 */
@Service("/rechargeOrderService")
public class RechargeOrderService implements IRechargeOrderService{
    @Resource
    private RechargeOrderMapper rechargeOrderMapper;

    /**
     *
     * 功能描述: 后台获取所有用户充值记录
     *
     * @param
     * @return: Map
     * @author: 刘武祥
     * @date: 2018/12/30 0030 12:14
     */
    @Override
    public List<Map<Object, Object>> selectAllAdmin() {
        return this.rechargeOrderMapper.selectAllAdmin();
    }

    /**
     *
     * 功能描述: 后台获取所有商家充值记录
     *
     * @param
     * @return: 返回所有商家充值记录
     * @author: 刘武祥
     * @date: 2018/12/30 0030 12:14
     */
    @Override
    public List<Map<Object, Object>> selectAllBusiness() {
        return this.rechargeOrderMapper.selectAllBusiness();
    }


    @Override
    public int addUnderLineRechargeOrder(RechargeOrder rechargeOrder) {
        return this.rechargeOrderMapper.addUnderLineRechargeOrder(rechargeOrder);
    }

    @Override
    public RechargeOrder selectBySystemOrderNo(String system_order_no) {
        return this.rechargeOrderMapper.selectBySystemOrderNo(system_order_no);
    }

    @Override
    public int updatePayState(RechargeOrder rechargeOrder) {
        return this.rechargeOrderMapper.updatePayState(rechargeOrder);
    }

    /**
     * 功能描述: 根据条件查询用户订单信息
     *
     * @param map 条件Map
     * @return:
     * @author: 杨新杰
     * @date: 10:32 2018/11/29
     */
    @Override
    public List<Map<String, Object>> adminSearchRechargeOrderByConditions(Map<String, Object> map) {
        return this.rechargeOrderMapper.adminSearchRechargeOrderByConditions(map);
    }

    /**
     * 功能描述: 根据条件查询商家订单信息
     *
     * @param map 条件Map
     * @return:
     * @author: 杨新杰
     * @date: 10:32 2018/11/29
     */
    @Override
    public List<Map<String, Object>> adminBusinessSearchRechargeOrderByConditions(Map<String, Object> map) {
        return this.rechargeOrderMapper.adminBusinessSearchRechargeOrderByConditions(map);
    }
}
