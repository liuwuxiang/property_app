package com.springmvc.dao;

import com.springmvc.entity.SilverCoinExchange;

import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 财务管理
 * @Author: 刘武祥
 * @Date: 2019/2/18 0018 11:25
 */
public interface SilverCoinExchangeMapper {

    /**
     *
     * 功能描述: 新增银币兑换比例
     *
     * @param   silverCoinExchange
     * @return: 返回新增银币兑换比例信息
     * @author: 刘武祥
     * @date: 2019/2/18 0018 11:24
     */
    int insertExchangeRecord(SilverCoinExchange silverCoinExchange);

    /**
     *
     * 功能描述: 根据用户id查询并重新修改记录所属用户(第一个用户id用于修改，第二个用户id用户查询)
     *
     * @param   user_id
     * @param   isUser_id
     * @return: 返回用户id查询并重新修改记录所属用户信息
     * @author: 刘武祥
     * @date: 2019/2/18 0018 11:23
     */
    int updateRecordUserId(Integer user_id,Integer isUser_id);
    //
    /**
     *
     * 功能描述: 后台查询所有兑换记录
     *
     * @param
     * @return: 返回所有兑换记录
     * @author: 刘武祥
     * @date: 2019/2/18 0018 11:23
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     *
     * 功能描述: 根据条件查询银币兑换记录
     *
     * @param   map 查询条件
     * @return: 返回查询银币兑换记录
     * @author: 刘武祥
     * @date: 2019/1/11 0011 17:20
     */
    List<Map<String,Object>> adminSearchSilverCoinExchangeByConditions(Map<String,Object> map);
}
