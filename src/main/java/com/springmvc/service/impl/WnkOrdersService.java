package com.springmvc.service.impl;

import com.springmvc.dao.WnkOrdersMapper;
import com.springmvc.entity.WnkOrders;
import com.springmvc.service.IWnkOrdersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/wnkOrdersService")
public class WnkOrdersService implements IWnkOrdersService{
    @Resource
    private WnkOrdersMapper wnkOrdersMapper;

    @Override
    public List<Map<Object, Object>> selectOrderByBusinessIdAndState(Integer business_id, Integer state) {
        return this.wnkOrdersMapper.selectOrderByBusinessIdAndState(business_id, state);
    }

    @Override
    public int insertNewOrder(WnkOrders wnkOrders) {
        return this.wnkOrdersMapper.insertNewOrder(wnkOrders);
    }

    @Override
    public WnkOrders selectByOrderNo(String order_no) {
        return this.wnkOrdersMapper.selectByOrderNo(order_no);
    }

    @Override
    public int updateOrderStateByOrderNo(Integer state, String order_no) {
        return this.wnkOrdersMapper.updateOrderStateByOrderNo(state, order_no);
    }

    @Override
    public int updateOrderQrcodeByOrderNo(String order_qrcode, String order_no) {
        return this.wnkOrdersMapper.updateOrderQrcodeByOrderNo(order_qrcode, order_no);
    }

    @Override
    public List<Map<Object, Object>> countOrderByMonthAndState(Integer state,Integer business_id) {
        return this.wnkOrdersMapper.countOrderByMonthAndState(state,business_id);
    }

    @Override
    public List<Map<Object, Object>> countOrderByMonthAndState2(Integer business_id) {
        return this.wnkOrdersMapper.countOrderByMonthAndState2(business_id);
    }

    @Override
    public List<Map<Object, Object>> selectUserNumberByBusinessId(Integer business_id,Integer state) {
        return this.wnkOrdersMapper.selectUserNumberByBusinessId(business_id,state);
    }

    @Override
    public List<Map<Object, Object>> selectOrderByBusinessIdAndStateAndMonthAndYeaar(Integer business_id, Integer state, String month_str, String year_str) {
        return this.wnkOrdersMapper.selectOrderByBusinessIdAndStateAndMonthAndYeaar(business_id, state, month_str, year_str);
    }

    @Override
    public List<Map<Object, Object>> selectUserNumberByBusinessIdAndMonthAndYear(Integer business_id, Integer state, String month_str, String year_str) {
        return this.wnkOrdersMapper.selectUserNumberByBusinessIdAndMonthAndYear(business_id, state, month_str, year_str);
    }

    @Override
    public List<Map<Object, Object>> selectOrderByBusinessIdAndMonthAndYeaar(Integer business_id, String month_str, String year_str) {
        return this.wnkOrdersMapper.selectOrderByBusinessIdAndMonthAndYeaar(business_id, month_str, year_str);
    }

    @Override
    public List<Map<Object, Object>> selectUserNumberByBusinessIdAndMonthAndYearNoState(Integer business_id, String month_str, String year_str) {
        return this.wnkOrdersMapper.selectUserNumberByBusinessIdAndMonthAndYearNoState(business_id, month_str, year_str);
    }

    @Override
    public List<Map<Object, Object>> userSelectUserOrder(Integer user_id, Integer state) {
        return this.wnkOrdersMapper.userSelectUserOrder(user_id, state);
    }

    @Override
    public List<Map<Object,Object>> selectByIdReturnMap(Integer order_id) {
        return this.wnkOrdersMapper.selectByIdReturnMap(order_id);
    }

    /**
     *
     * 功能描述: 获取除了待支付之外的交易笔数
     *
     * @param  business_id
     * @return
     * @author 杨新杰
     * @date   2018/11/10 11:52
     */
    @Override
    public int selectTransactionTotalById(Integer business_id) {
        return this.wnkOrdersMapper.selectTransactionTotalById(business_id);
    }

    /**
     * 功能描述: 获取除了待支付之外的购买人数
     *
     * @param business_id 商家id
     * @return
     * @author 杨新杰
     * @date 2018/11/10 11:52
     */
    @Override
    public int selectBuyTotalById(Integer business_id) {
        return this.wnkOrdersMapper.selectBuyTotalById(business_id);
    }
}
