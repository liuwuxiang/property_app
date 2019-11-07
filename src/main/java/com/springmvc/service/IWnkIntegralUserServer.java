package com.springmvc.service;

import java.util.List;
import java.util.Map;

/**
 * 商家积分商城 - 用户管理-Service接口层
 *
 * @author 杨新杰
 * @Date 2018/10/12 11:47
 */
public interface IWnkIntegralUserServer {

    /**
     *
     * 功能描述:
     *
     * @param  business_id  商家ID
     * @param  user_id      用户ID
     * @return 返回查询到的结果
     * @author 杨新杰
     * @date   2018/10/12 12:24
     */
    Map<String , Object > getUserIntegral(Integer business_id, Integer user_id);

    /**
     *
     * 功能描述: 新增用户在对应商家的积分记录
     *
     * @param business_id  商家ID
     * @param user_id      用户ID
     * @return 返回新增结果  1 为正常  0 为失败
     * @author 杨新杰
     * @date   2018/10/12 17:32
     */
    int addUserIntegral(Integer business_id, Integer user_id);


    /**
     *
     * 功能描述: 更新用户积分余额
     *
     * @param business_id  商家ID
     * @param price        扣除积分数量
     * @param user_id      用户ID
     * @return 返回新增结果  1 为正常  0 为失败
     * @author 杨新杰
     * @date   2018/10/12 17:32
     */
    int updateIntegral(Integer user_id,Integer business_id, Integer price);

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
     * 功能描述: 获取某个用户的商家积分总额
     *
     * @param user_id 用户id
     * @return: Integer
     * @author: zhangfan
     * @date: 2018/11/4 2:19 AM
     */
    Integer selectUserCountIntegralByUserId(Integer user_id);

    /**
     *
     * 功能描述: 获取所有商家列表,用户端积分商城商家全部商家页面
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    15:36 2018/12/31
     */
    List<Map<String,Object>> selectIntegralBusinessAll(Integer user_id,Integer sort_list);

    /**
     *
     * 功能描述: 获取用户对此商家的信息
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    16:31 2018/12/31
     */
    Map<String,Object> selectIntegralBusinessInfoByBusinessId(Integer business_id,Integer user_id);

    /**
     *
     * 功能描述: 获取商家的所有积分商品
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    16:53 2018/12/31
     */
    List<Map<String, Object>> selectIntegralBusinessGoodsByBusinessId(Integer business_id, Integer type_sort);

    /**
     *
     * 功能描述: 用户增长积分
     *
     * @param  user_id      用户ID
     * @param  business_id  商家ID
     * @param  price        积分数量
     * @return
     * @author 杨新杰
     * @date   2018/10/15 14:23
     */
     int increaseUserIntegral(Integer user_id,Integer business_id,Double price);
}
