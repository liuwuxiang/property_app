package com.springmvc.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/11/2 16:49
 * @Description:用户赠送积分余额明细Mapper
 */
public interface WnkSendIntegralDetailUserMapper {
    /**
     *
     * 功能描述: 新增商家积分商城收入支出记录
     *
     * @param  name         收入名称
     * @param  business_id  商家ID
     * @param  user_id      用户ID
     * @param  income_amount        交易积分数量
     * @param  type         0-收入 1-支出
     * @return 成功返回1  失败返回0
     * @author 张凡
     * @date   2018/10/15 14:45
     */
    int addIntegralRecord(Map<String, Object> map);

    /**
     *
     * 功能描述:  获取用户在对应商家的消费明细
     *
     * @param  business_id 商家ID
     * @param  user_id     用户ID
     * @return
     * @author 张凡
     * @date   2018/10/15 15:10
     */
    List<Map<String, Object>> getIntegralCountById(@Param("business_id") Integer business_id, @Param("user_id") Integer user_id);

    /**
     *
     * 功能描述:获取用户在商家处的积分明细(区分收入与支出)
     *
     * @param business_id 商家ID
     * @param user_id 用户ID
     * @param type 收支类型(0-收入 1-支出)
     * @return:
     * @author: zhangfan
     * @date: 2018/10/31 4:38 AM
     */
    List<Map<String,Object>> getIntegralCountByIdAndType(@Param("business_id") Integer business_id, @Param("user_id") Integer user_id, @Param("type") Integer type);
}
