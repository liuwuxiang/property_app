package com.springmvc.dao;

import com.springmvc.entity.WnkOrders;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WnkOrdersMapper {
    //查询某个商家的订单通过商家id以及状态
    List<Map<Object,Object>> selectOrderByBusinessIdAndState(Integer business_id,Integer state);
    //插入新订单
    int insertNewOrder(WnkOrders wnkOrders);
    //通过订单号查询订单
    WnkOrders selectByOrderNo(String order_no);
    //通过订单号更新订单状态
    int updateOrderStateByOrderNo(Integer state,String order_no);
    //通过订单号更新订单二维码
    int updateOrderQrcodeByOrderNo(String order_qrcode,String order_no);
    //按月统计所有订单
    List<Map<Object,Object>> countOrderByMonthAndState(Integer state,Integer business_id);
    //按月统计所有订单
    List<Map<Object,Object>> countOrderByMonthAndState2(Integer business_id);
    //按用户查询消费用户数量(查询消费用户总数列表)
    List<Map<Object,Object>> selectUserNumberByBusinessId(Integer business_id,Integer state);
    //查询某个商家的订单通过商家id以及状态、月份、年份
    List<Map<Object,Object>> selectOrderByBusinessIdAndStateAndMonthAndYeaar(Integer business_id,Integer state,String month_str,String year_str);
    //按用户以及月份、状态、商户查询消费用户数量(查询消费用户总数列表)
    List<Map<Object,Object>> selectUserNumberByBusinessIdAndMonthAndYear(Integer business_id,Integer state,String month_str,String year_str);
    //查询某个商家的订单通过商家id以及月份、年份
    List<Map<Object,Object>> selectOrderByBusinessIdAndMonthAndYeaar(Integer business_id,String month_str,String year_str);
    //按用户以及月份、商户查询消费用户数量(查询消费用户总数列表)
    List<Map<Object,Object>> selectUserNumberByBusinessIdAndMonthAndYearNoState(Integer business_id,String month_str,String year_str);
    //用户获取消费订单
    List<Map<Object,Object>> userSelectUserOrder(Integer user_id,Integer state);
    //通过id查询订单
    List<Map<Object,Object>> selectByIdReturnMap(Integer order_id);

    /**
     *
     * 功能描述: 获取除了待支付之外的交易笔数
     *
     * @param  business_id
     * @return
     * @author 杨新杰
     * @date   2018/11/10 11:52
     */
    int selectTransactionTotalById(@Param("business_id") Integer business_id);

    /**
     * 功能描述: 获取除了待支付之外的购买人数
     *
     * @param business_id 商家id
     * @return
     * @author 杨新杰
     * @date 2018/11/10 11:52
     */
    int selectBuyTotalById(@Param("business_id") Integer business_id);
}
