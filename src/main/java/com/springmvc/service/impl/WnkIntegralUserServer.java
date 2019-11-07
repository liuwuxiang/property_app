package com.springmvc.service.impl;

import com.springmvc.dao.WnkIntegralUserMapper;
import com.springmvc.service.IWnkIntegralUserServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *  商家积分商城 - 用户管理-Service实现层
 *
 * @author 杨新杰
 * @Date 2018/10/12 11:47
 */
@Service("/wnkIntegralUserServer")
public class WnkIntegralUserServer implements IWnkIntegralUserServer {

    @Resource
    private WnkIntegralUserMapper wnkIntegralUserMapper;

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
    @Override
    public Map<String, Object> getUserIntegral(Integer business_id, Integer user_id) {
        return wnkIntegralUserMapper.getUserIntegral(business_id,user_id);
    }

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
    @Override
    public int addUserIntegral(Integer business_id, Integer user_id) {
        return wnkIntegralUserMapper.addUserIntegral(business_id,user_id);
    }

    /**
     * 功能描述: 更新用户积分余额
     *
     * @param business_id 商家ID
     * @param price       扣除积分数量
     * @param user_id     用户ID
     * @return 返回新增结果  1 为正常  0 为失败
     * @author 杨新杰
     * @date 2018/10/12 17:32
     */
    @Override
    public int updateIntegral(Integer user_id,Integer business_id, Integer price) {
        return wnkIntegralUserMapper.updateIntegral(user_id, business_id,price);
    }

    /**
     *
     * 功能描述:获取某个用户的积分商家
     *
     * @param:user_id
     * @return:List
     * @author: zhangfan
     * @date: 2018/10/29 7:22 AM
     */
    @Override
    public List<Map<Object, Object>> selectBusinessByUserId(Integer user_id) {
        return this.wnkIntegralUserMapper.selectBusinessByUserId(user_id);
    }

    @Override
    public Integer selectUserCountIntegralByUserId(Integer user_id) {
        return this.wnkIntegralUserMapper.selectUserCountIntegralByUserId(user_id);
    }

    /**
     *
     * 功能描述: 获取所有商家列表,用户端积分商城商家全部商家页面
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    15:36 2018/12/31
     */
    @Override
    public List<Map<String, Object>> selectIntegralBusinessAll(Integer user_id,Integer sort_list) {
        return this.wnkIntegralUserMapper.selectIntegralBusinessAll(user_id,sort_list);
    }

    /**
     * 功能描述: 获取用户对此商家的信息
     *
     * @param business_id@return
     * @author 杨新杰
     * @date 16:31 2018/12/31
     */
    @Override
    public Map<String, Object> selectIntegralBusinessInfoByBusinessId(Integer business_id,Integer user_id) {
        return this.wnkIntegralUserMapper.selectIntegralBusinessInfoByBusinessId(business_id, user_id);
    }

    /**
     * 功能描述: 获取商家的所有积分商品
     *
     * @param business_id
     * @return
     * @author 杨新杰
     * @date 16:53 2018/12/31
     */
    @Override
    public List<Map<String, Object>> selectIntegralBusinessGoodsByBusinessId(Integer business_id, Integer type_sort) {
        return this.wnkIntegralUserMapper.selectIntegralBusinessGoodsByBusinessId(business_id,type_sort);
    }

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
    public int increaseUserIntegral(Integer user_id,Integer business_id,Double price){
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",user_id);
        map.put("business_id",business_id);
        map.put("price",price);
        return wnkIntegralUserMapper.increaseUserIntegral(map);
    }
}
