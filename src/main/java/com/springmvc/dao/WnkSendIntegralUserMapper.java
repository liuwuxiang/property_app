package com.springmvc.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/11/2 16:07
 * @Description:商家赠送给用户的积分余额(用于记录用户在某个商家处“赠送积分”的余额)Mapper
 */
public interface WnkSendIntegralUserMapper {
    /**
     *
     * 功能描述:
     *
     * @param  business_id  商家ID
     * @param  user_id      用户ID
     * @return 返回查询到的结果
     * @author 张凡
     * @date   2018/10/12 12:24
     */
    Map<String,Object> getUserIntegral(@Param("business_id") Integer business_id, @Param("user_id") Integer user_id);

    /**
     *
     * 功能描述: 新增用户在对应商家的积分记录
     *
     * @param business_id  商家ID
     * @param user_id      用户ID
     * @return 返回新增结果  1 为正常  0 为失败
     * @author 张凡
     * @date   2018/10/12 17:32
     */
    int addUserIntegral(@Param("business_id") Integer business_id, @Param("user_id") Integer user_id);

    /**
     * 更新用户积分余额
     * @param price 扣除的积分数量
     * @return 成功返回1 失败返回0
     */
    int updateIntegral(@Param("user_id") Integer user_id, @Param("business_id") Integer business_id, @Param("price") Double price);

    /**
     * 更新用户积分余额
     *
     * @param user_id
     * @param business_id
     * @param price       增加的积分数量
     * @return 成功返回1 失败返回0
     */
    int updateIntegralUp(@Param("user_id") Integer user_id, @Param("business_id") Integer business_id, @Param("price") Double price);

    /**
     *
     * 功能描述: 用户增长积分
     *
     * @param  user_id      用户ID
     * @param  business_id  商家ID
     * @param  price        积分数量
     * @return
     * @author 张凡
     * @date   2018/10/15 14:23
     */
    int increaseUserIntegral(@Param("user_id") Integer user_id, @Param("business_id") Integer business_id, @Param("price") Double price);

    /**
     *
     * 功能描述:获取某个用户的积分商家
     *
     * @param:user_id
     * @return:List
     * @author: zhangfan
     * @date: 2018/10/29 7:22 AM
     */
    List<Map<Object,Object>> selectBusinessByUserId(Integer user_id);

    /**
     *
     * 功能描述: 获取某个用户的赠送积分总额
     *
     * @param user_id 用户id
     * @return: Integer
     * @author: zhangfan
     * @date: 2018/11/4 2:11 AM
     */
    Double selectUserCountIntegralByUserId(Integer user_id);


}
