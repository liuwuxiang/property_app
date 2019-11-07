package com.springmvc.dao;

import com.springmvc.entity.SilverCoinDetailed;

import java.util.List;
import java.util.Map;

public interface SilverCoinDetailedMapper {
    //查询某个用户的银币明细
    List<Map<Object,Object>> selectSilverCoinDetailByUser(Integer user_id);
    //根据用户id查询并重新修改记录所属用户(第一个用户id用于修改，第二个用户id用户查询)
    int updateRecordUserId(Integer user_id,Integer isUser_id);
    //新增银币收支记录
    int insertExchangeRecord(SilverCoinDetailed silverCoinDetailed);
    //删除记录
    int deleteRecordById(Integer id);
    //获取所有可提现的银币记录
    List<Map<Object,Object>> getCanWithdrawSilverCoinDetail(Integer user_id);
    //获取用户可提现余额
    Integer getCanWithdrawSilverCoinBalance(Integer user_id);
    //将所有可提现的银币记录更改为已提现
    int updateCanWithdrawState(Integer user_id);
    //新增银币提现记录
    int insertWithdrawRecord(SilverCoinDetailed silverCoinDetailed);
}
