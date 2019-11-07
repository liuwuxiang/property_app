package com.springmvc.service;

import com.springmvc.entity.ConsumptionIntegralExpenditure;

import java.util.List;
import java.util.Map;


public interface IConsumptionIntegralExpenditureService {
    /**
     * 查询用户支出明细
     * @param user_id 用户ID
     * @return 返回用户支出明细List<Map<Object,Object>>
     */
    List<Map<Object,Object>> selectExpenditureByUser(Integer user_id);

    /**
     * 根据用户id查询并重新修改记录所属用户(第一个用户id用于修改，第二个用户id用户查询)
     * @param user_id
     * @param isUser_id
     * @return
     */
    int updateRecordUserId(Integer user_id,Integer isUser_id);

    /**
     * 新增银币兑换支出记录
     * @param consumptionIntegralExpenditure
     * @return
     */
    int insertExchangeSilverCoinRecord(ConsumptionIntegralExpenditure consumptionIntegralExpenditure);

    /**
     * 根据id删除记录
     * @param id id
     * @return
     */
    int deleteRecordById(Integer id);
}
