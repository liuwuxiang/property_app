package com.springmvc.dao;

import com.springmvc.entity.WnkOrderMakeRecord;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/8 16:19
 * @Description:2.0版本商品使用记录Mapper
 */
public interface WnkOrderMakeRecordMapper {
    /**
     *
     * 功能描述: 插入新纪录
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/8 5:29 PM
     */
    int insertNewRecord(WnkOrderMakeRecord wnkOrderMakeRecord);

    /**
     *
     * 功能描述: 查询某个订单的使用记录
     *
     * @param order_no 订单号
     * @return:
     * @author: zhangfan
     * @date: 2018/12/8 5:29 PM
     */
    List<Map<Object,Object>> selectByOrderNo(String order_no);
}
