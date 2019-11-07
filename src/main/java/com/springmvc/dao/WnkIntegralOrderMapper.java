package com.springmvc.dao;

import com.springmvc.entity.WnkIntegralOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 杨新杰
 * @Date 2018/10/10 12:02
 * @Description:
 */
public interface WnkIntegralOrderMapper {

    /**
     *
     * 功能描述: 根据商家ID获取订单信息
     *
     * @param  business_id 商家ID
     * @param  status      0-全部订单,1-未完成订单,2-已完成订单
     * @return 返回获取到的订单信息,如果没有信息则为null
     * @author 杨新杰
     * @date   2018/10/10 12:03
     */
    List<Map<String,Object>> getIntegralOrderByBusinessId(@Param("business_id") Integer business_id, @Param("status") Integer status);

    /**
     * 功能描述: 获取当前用户在对应商家的订单信息
     *
     * @param business_id 商家ID
     * @param user_id     用户ID
     * @return 返回获取到的订单信息, 如果没有信息则为null
     * @author 杨新杰
     * @date 2018/10/10 12:03
     */
    List<Map<String,Object>> getIntegralWnkOrderById(@Param("business_id")Integer business_id, @Param("user_id")Integer user_id);


    /**
     * 功能描述: 获取当前用户的所有积分商品订单信息
     *
     * @param user_id     用户ID
     * @return 返回获取到的订单信息, 如果没有信息则为null
     * @author 杨新杰
     * @date 2018/10/10 12:03
     */
    List<Map<String,Object>> getIntegralWnkOrderByUserId(@Param("user_id")Integer user_id);

    /**
     *
     * 功能描述: 查询用户在此商家的订单 - 根据订单ID
     *
     * @param  business_id 商家ID
     * @param  user_id     用户ID
     * @param  order_id    订单ID
     * @return 返回订单详情
     * @author 杨新杰
     * @date   2018/10/13 11:17
     */
    Map<String,Object> getIntegralWnkOrderByGoodsId(Map<String,Object> map);

    /**
     *
     * 功能描述: 新增商家积分订单
     *
     * @param  wnkIntegralOrder 积分订单实体类
     * @return 成功返回1 失败返回0
     * @author 杨新杰
     * @date   2018/10/13 17:43
     */
    int addIntegralOrder(WnkIntegralOrder wnkIntegralOrder);

    /**
     *
     * 功能描述: 通过商家ID 用户ID 订单ID 来获取订单信息.
     *
     * @param  business_id 商家ID
     * @param  user_id     用户ID
     * @param  order_no    订单ID
     * @return 有则返回订单信息,没有则返回null
     * @author 杨新杰
     * @date   2018/10/15 10:35
     */
    Map<String,Object> getIntegralWnkOrderByOrderIdAndUserIdAndBusinessId(Map<String,Object> map);

    /**
     *
     * 功能描述:更新订单信息(商家发货操作)
     *
     * @param  business_id 商家ID
     * @param  user_id     用户ID
     * @param  order_id    订单ID
     * @return
     * @author 杨新杰
     * @date   2018/10/15 12:31
     */
    int updateIntegralOrderEndTime(Map<String,Object> map);

    /**
     * 功能描述: 获取当前用户在对应商家的订单信息
     *
     * @param user_id     用户ID
     * @return 返回获取到的订单信息, 如果没有信息则为null
     * @author 杨新杰
     * @date 2018/10/10 12:03
     */
    List<Map<String,Object>> getAllIntegralWnkOrderByUserId(@Param("user_id")Integer user_id);
}
