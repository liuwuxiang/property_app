package com.springmvc.dao;

import com.springmvc.entity.DoingSpreadUserReadRecord;

/**
 * @author: zhangfan
 * @Date: 2018/11/7 00:54
 * @Description:推广消息用户已读记录Mapper
 */
public interface DoingSpreadUserReadRecordMapper {
    /**
     *
     * 功能描述:插入新纪录
     *
     * @param doingSpreadUserReadRecord-记录信息
     * @return:int
     * @author: zhangfan
     * @date: 2018/11/7 12:58 AM
     */
    int insertNewOrder(DoingSpreadUserReadRecord doingSpreadUserReadRecord);

    /**
     *
     * 功能描述: 通过用户id以及消息id查询记录
     *
     * @param user_id-用户id
     * @param message_id-消息id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/7 12:59 AM
     */
    DoingSpreadUserReadRecord selectByUserIdAndMessageId(Integer user_id,Integer message_id);
}
