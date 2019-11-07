package com.springmvc.service;

import java.util.List;
import java.util.Map;

/**
 * @author 杨新杰
 * @Date 2018/10/15 14:42
 */
public interface IWnkIntegralIncomeService {

    /**
     *
     * 功能描述: 新增商家积分商城收入支出记录
     *
     * @param  name         收入名称
     * @param  business_id  商家ID
     * @param  user_id      用户ID
     * @param  price        收入数量
     * @param  type         0-收入 1-支出
     * @return 成功返回1  失败返回0
     * @author 杨新杰
     * @date   2018/10/15 14:45
     */
    int addIntegralRecord(String name, Double price, Integer user_id, Integer business_id, int type);

    /**
     *
     * 功能描述:  获取用户在对应商家的消费明细
     *
     * @param  business_id 商家ID
     * @param  user_id     用户ID
     * @return
     * @author 杨新杰
     * @date   2018/10/15 15:10
     */
    List<Map<String, Object>> getIntegralCountById(Integer business_id, Integer user_id);

    /**
     *
     * 功能描述:获取用户在商家处的积分明细(区分收入与支出)
     *
     * @param business_id 商家ID
     * @param user_id 用户ID
     * @param income_type 收支类型(0-收入 1-支出)
     * @return:
     * @author: zhangfan
     * @date: 2018/10/31 4:38 AM
     */
    List<Map<String,Object>> getIntegralCountByIdAndType(Integer business_id, Integer user_id, Integer income_type);
}
