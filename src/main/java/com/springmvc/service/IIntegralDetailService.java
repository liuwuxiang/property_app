package com.springmvc.service;


import java.util.List;
import java.util.Map;

public interface IIntegralDetailService {
    /**
     * 插入积分明细记录
     * @param user_id  用户ID
     * @param name     记录名称
     * @param integral_number 积分个数
     * @param type 0=积分收入 1=积分支出
     * @return 正常返回1 错误返回0或者大于1
     */
    int insertIntrgralDetailRecord(Integer user_id, String name, Double integral_number, Integer type);

    /**
     * 查询某用户交易记录
     * @param user_id 用户ID
     * @return 查询到的交易记录,无交易记录返回null
     */
    List<Map<Object,Object>> selectUserRecordByUserId(Integer user_id);

    /**
     *
     * 功能描述:查询某用户某个交易类别的交易记录
     *
     * @param user_id 用户id
     * @param type 0-积分收益,1-积分消费
     * @return:
     * @author: zhangfan
     * @date: 2018/11/13 4:31 PM
     */
    List<Map<Object,Object>> selectUserRecordByUserIdAndType(Integer user_id,Integer type);
}
