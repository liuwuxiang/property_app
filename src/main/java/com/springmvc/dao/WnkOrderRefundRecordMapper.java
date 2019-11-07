package com.springmvc.dao;

import com.springmvc.entity.WnkOrderRefundRecord;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/8 16:16
 * @Description:2.0版本商品订单退款记Mapper
 */
public interface WnkOrderRefundRecordMapper {
    /**
     *
     * 功能描述: 插入新纪录
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/8 5:22 PM
     */
    int insertNewRecord(WnkOrderRefundRecord wnkOrderRefundRecord);

    /**
     *
     * 功能描述: 查询某个订单的退款记录
     *
     * @param order_no 订单号
     * @return:
     * @author: zhangfan
     * @date: 2018/12/8 5:23 PM
     */
    List<Map<Object,Object>> selectByOrderNo(String order_no);

    /**
     *
     * 功能描述: 查询某个订单的各项退款总和
     *
     * @param order_no 订单号
     * @return:
     * @author: zhangfan
     * @date: 2018/12/10 12:05 AM
     */
    Map<Object,Object> selectRefundSumByOrderNo(String order_no);
}
