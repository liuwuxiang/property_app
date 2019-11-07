package com.springmvc.dao;

import com.springmvc.entity.ConsumptionIntegralIncome;

import java.util.List;
import java.util.Map;

public interface ConsumptionIntegralIncomeMapper {
    //查询用户的充值明细
    List<Map<Object,Object>> selectRechargeIncomeByUser(Integer user_id);
    //根据用户id查询记录并重新修改所属用户
    Integer updateRecordUserId(Integer user_id,Integer isUser_id);
    //新增收入
    int addIncome(ConsumptionIntegralIncome consumptionIntegralIncome);
}
