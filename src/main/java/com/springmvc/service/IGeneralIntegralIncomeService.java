package com.springmvc.service;

import com.springmvc.entity.GeneralIntegralIncome;

import java.util.List;
import java.util.Map;

public interface IGeneralIntegralIncomeService {
    /**
     * 查询用户的产业收入记录
     * @param user_id 用户ID
     * @return
     */
    List<Map<Object,Object>> selectIndustryIncomeByUser(Integer user_id);

    /**
     * 查询用户近几天的产业收益情况(day_number-要查询最近多少天的收益此处就传多少)
     * @param user_id    用户ID
     * @param day_number
     * @return
     */
    List<Map<Object,Object>> selectLimitIndustryIncomeByUser(Integer user_id,Integer day_number);

    /**
     * 根据用户id查询并重新修改记录所属用户(第一个用户id用于修改，第二个用户id用户查询)
     * @param user_id   用户ID
     * @param isUser_id
     * @return
     */
    int updateRecordUserId(Integer user_id,Integer isUser_id);

    /**
     * 查询用户通用积分充值记录
     * @param user_id 用户ID
     * @return
     */
    List<Map<Object,Object>> selectIndustryRechargeIncomeByUser(Integer user_id);

    /**
     * 新增用户银币提现收入记录
     * @param generalIntegralIncome
     * @return
     */
    int insertUserSilverCoinWithdrawRecord(GeneralIntegralIncome generalIntegralIncome);

    /**
     * 提现被拒返款
     * @param generalIntegralIncome
     * @return
     */
    int withdrawFK(GeneralIntegralIncome generalIntegralIncome);
}
