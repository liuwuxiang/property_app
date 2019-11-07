package com.springmvc.service;

import com.springmvc.entity.UsersRoseDetail;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 21:44
 * @Description:用户玫瑰(银币)明细服务接口类
 */
public interface IUsersRoseDetailService {
    /**
     *
     * 功能描述: 查询某用户的交易记录
     *
     * @param: user_id,transactions_type(交易类型(0-收入,1-支出))
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/28 11:19 PM
     */
    List<Map<Object,Object>> selectRecordByUserId(Integer user_id, Integer transactions_type);

    /**
     *
     * 功能描述: 插入新纪录
     *
     * @param: usersRoseDetail
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/28 11:20 PM
     */
    int insertNewRecord(UsersRoseDetail usersRoseDetail);
}
