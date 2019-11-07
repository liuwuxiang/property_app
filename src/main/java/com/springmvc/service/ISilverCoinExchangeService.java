package com.springmvc.service;

import com.springmvc.entity.SilverCoinExchange;

import java.util.List;
import java.util.Map;

public interface ISilverCoinExchangeService {
    /**
     * 新增银币兑换比例
     * @param silverCoinExchange
     * @return
     */
    int insertExchangeRecord(SilverCoinExchange silverCoinExchange);

    /**
     * 根据用户id查询并重新修改记录所属用户(第一个用户id用于修改，第二个用户id用户查询)
     * @param user_id
     * @param isUser_id
     * @return
     */
    int updateRecordUserId(Integer user_id,Integer isUser_id);

    /**
     * 后台查询所有兑换记录
     * @return
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     *
     * 功能描述:根据条件查询银币兑换记录
     *
     * @param   map
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/11 0011 17:19
     */
    List<Map<String,Object>> adminSearchSilverCoinExchangeByConditions(Map<String,Object> map);
}
