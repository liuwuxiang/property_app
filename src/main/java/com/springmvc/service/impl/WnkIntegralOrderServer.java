package com.springmvc.service.impl;

import com.springmvc.dao.WnkIntegralOrderMapper;
import com.springmvc.entity.WnkIntegralOrder;
import com.springmvc.service.IWnkIntegralOrderServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 杨新杰
 * @Date 2018/10/10 11:58
 */
@Service("/wnkIntegralOrderServer")
public class WnkIntegralOrderServer implements IWnkIntegralOrderServer {

    @Resource
    private WnkIntegralOrderMapper wnkIntegralOrderMapper;
    /**
     *
     * 功能描述: 根据商家ID获取订单信息
     *
     * @param  business_id 商家ID
     * @param  type        0-全部订单,1-未完成订单,2-已完成订单
     * @return 返回获取到的订单信息,如果没有信息则为null
     * @author 杨新杰
     * @date   2018/10/10 12:03
     */
    @Override
    public List<Map<String, Object>> getIntegralOrderByBusinessId(Integer business_id, Integer type) {
        return wnkIntegralOrderMapper.getIntegralOrderByBusinessId(business_id,type);
    }

    /**
     * 功能描述: 获取当前用户在对应商家的订单信息
     *
     * @param business_id 商家ID
     * @param user_id     用户ID
     * @return 返回获取到的订单信息, 如果没有信息则为null
     * @author 杨新杰
     * @date 2018/10/10 12:03
     */
    @Override
    public List<Map<String, Object>> getIntegralWnkOrderById(Integer business_id, Integer user_id) {
        return wnkIntegralOrderMapper.getIntegralWnkOrderById(business_id,user_id);
    }

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
    @Override
    public Map<String, Object> getIntegralWnkOrderByGoodsId(Integer business_id, Integer user_id, String order_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("business_id",business_id);
        map.put("user_id",user_id);
        map.put("order_id",order_id);
        return wnkIntegralOrderMapper.getIntegralWnkOrderByGoodsId(map);
    }

    /**
     *
     * 功能描述: 新增商家积分订单
     *
     * @param  wnkIntegralOrder 积分订单实体类
     * @return 成功返回1 失败返回0
     * @author 杨新杰
     * @date   2018/10/13 17:43
     */
    @Override
    public int addIntegralOrder(WnkIntegralOrder wnkIntegralOrder) {
        return wnkIntegralOrderMapper.addIntegralOrder(wnkIntegralOrder);
    }

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
    @Override
    public Map<String, Object> getIntegralWnkOrderByOrderIdAndUserIdAndBusinessId(Integer business_id, Integer user_id, String  order_no) {
        Map<String , Object> map = new HashMap<>();
        map.put("business_id",business_id);
        map.put("user_id",user_id);
        map.put("order_id",order_no);
        return wnkIntegralOrderMapper.getIntegralWnkOrderByOrderIdAndUserIdAndBusinessId(map);
    }

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
    @Override
    public int updateIntegralOrderEndTime(Integer business_id, Integer user_id, String order_id) {
        Map<String,Object> map = new HashMap<>();
        map.put("business_id",business_id);
        map.put("user_id",user_id);
        map.put("order_id",order_id);
        return wnkIntegralOrderMapper.updateIntegralOrderEndTime(map);
    }

    /**
     * 功能描述: 获取当前用户在对应商家的订单信息
     *
     * @param user_id     用户ID
     * @return 返回获取到的订单信息, 如果没有信息则为null
     * @author 杨新杰
     * @date 2018/10/10 12:03
     */
    @Override
    public List<Map<String,Object>> getAllIntegralWnkOrderByUserId(Integer user_id) {
        return wnkIntegralOrderMapper.getAllIntegralWnkOrderByUserId(user_id);
    }

    /**
     * 功能描述: 获取当前用户的所有积分商品订单信息
     *
     * @param user_id     用户ID
     * @return 返回获取到的订单信息, 如果没有信息则为null
     * @author 杨新杰
     * @date 2018/10/10 12:03
     */
    @Override
    public List<Map<String, Object>> getIntegralWnkOrderByUserId(Integer user_id) {
        return this.wnkIntegralOrderMapper.getIntegralWnkOrderByUserId(user_id);
    }
}
