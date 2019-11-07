package com.springmvc.service;

import com.springmvc.entity.SilverCoinDetailed;

import java.util.List;
import java.util.Map;

public interface ISilverCoinDetailedService {
    /**
     * 查询某个用户的银币明细
     * @param user_id
     * @return
     */
    List<Map<Object,Object>> selectSilverCoinDetailByUser(Integer user_id);

    /**
     * 根据用户id查询并重新修改记录所属用户(第一个用户id用于修改，第二个用户id用户查询)
     * @param user_id
     * @param isUser_id
     * @return
     */
    int updateRecordUserId(Integer user_id,Integer isUser_id);

    /**
     * 新增银币收支记录
     * @param silverCoinDetailed
     * @return
     */
    int insertExchangeRecord(SilverCoinDetailed silverCoinDetailed);

    /**
     * 删除记录
     * @param id
     * @return
     */
    int deleteRecordById(Integer id);

    /**
     * 新增团队兑换银币收益(此处user_id为兑换银币的用户id，silver_coin_number为推荐人获得的银币个数)
     * @param user_id    兑换银币的用户id
     * @param silver_coin_number 推荐人获得的银币个数
     * @return
     */
    int insertTeamExchangeRecord(Integer user_id,Integer silver_coin_number);

    /**
     * 获取所有可提现的银币记录
     * @param user_id 用户ID
     * @return
     */
    List<Map<Object,Object>> getCanWithdrawSilverCoinDetail(Integer user_id);

    /**
     * 获取用户可提现余额
     * @param user_id 用户ID
     * @return
     */
    Integer getCanWithdrawSilverCoinBalance(Integer user_id);

    /**
     * 将所有可提现的银币记录更改为已提现
     * @param user_id 用户ID
     * @return
     */
    int updateCanWithdrawState(Integer user_id);

    /**
     * 新增银币提现记录
     * @param silverCoinDetailed
     * @return
     */
    int insertWithdrawRecord(SilverCoinDetailed silverCoinDetailed);
}
