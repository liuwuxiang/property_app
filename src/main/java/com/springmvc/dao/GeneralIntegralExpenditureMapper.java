package com.springmvc.dao;

import com.springmvc.entity.GeneralIntegralExpenditure;

import java.util.List;
import java.util.Map;

public interface GeneralIntegralExpenditureMapper {
    //查询用户消费明细
    List<Map<Object,Object>> selectExpenditureByUser(Integer user_id);
    //查询用户提现明细
    List<Map<Object,Object>> selectWithdrawExpenditureByUser(Integer user_id);
    //根据用户id查询并重新修改记录所属用户(第一个用户id用于修改，第二个用户id用户查询)
    int updateRecordUserId(Integer user_id,Integer isUser_id);
    //新增银币兑换支出记录
    int insertExchangeSilverCoinRecord(GeneralIntegralExpenditure generalIntegralExpenditure);
    //根据id删除记录
    int deleteRecordById(Integer id);
    //新增提现支出记录
    int insertWithdrawRecord(GeneralIntegralExpenditure generalIntegralExpenditure);
}
