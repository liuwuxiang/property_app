package com.springmvc.service;

import com.springmvc.entity.ConsumptionIntegralIncome;

import java.util.List;
import java.util.Map;

public interface IConsumptionIntegralIncomeService {
    /**
     * 查询用户的充值明细
     * @param user_id 用户ID
     * @return
     */
    List<Map<Object,Object>> selectRechargeIncomeByUser(Integer user_id);

    /**
     * 根据用户id查询记录并重新修改所属用户
     * @param user_id
     * @param isUser_id
     * @return
     */
    Integer updateRecordUserId(Integer user_id,Integer isUser_id);

    /**
     * 新增收入
     * @param consumptionIntegralIncome
     * @return
     */
    int addIncome(ConsumptionIntegralIncome consumptionIntegralIncome);
}
