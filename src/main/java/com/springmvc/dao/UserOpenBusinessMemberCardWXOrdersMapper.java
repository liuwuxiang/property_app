package com.springmvc.dao;

import com.springmvc.entity.UserOpenBusinessMemberCardWXOrders;

/**
 * @author: zhangfan
 * @Date: 2018/11/6 23:20
 * @Description:用户向商家开卡微信支付订单Mapper
 */
public interface UserOpenBusinessMemberCardWXOrdersMapper {
    /**
     *
     * 功能描述: 插入新订单
     *
     * @param userOpenBusinessMemberCardWXOrders 订单信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/6 11:28 PM
     */
    int insertNewOrder(UserOpenBusinessMemberCardWXOrders userOpenBusinessMemberCardWXOrders);

    /**
     *
     * 功能描述: 通过订单号查询订单
     *
     * @param order_no 订单号
     * @return: UserOpenBusinessMemberCardWXOrders
     * @author: zhangfan
     * @date: 2018/11/6 11:29 PM
     */
     UserOpenBusinessMemberCardWXOrders selectByOrderNo(String order_no);

     /**
      *
      * 功能描述:通过订单号更新订单状态
      *
      * @param state-订单状态(0-待支付,1-已支付,0-支付失败)
      * @param order_no-订单号
      * @return:
      * @author: zhangfan
      * @date: 2018/11/6 11:29 PM
      */
     int updateOrderStateByOrderNo(Integer state,String order_no);
}
