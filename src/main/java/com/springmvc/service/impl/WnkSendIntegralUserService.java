package com.springmvc.service.impl;

import com.springmvc.dao.WnkSendIntegralUserMapper;
import com.springmvc.service.IWnkSendIntegralUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/11/2 16:14
 * @Description:商家赠送给用户的积分余额(用于记录用户在某个商家处“赠送积分”的余额)服务实现类
 */
@Service("/wnkSendIntegralUserService")
public class WnkSendIntegralUserService implements IWnkSendIntegralUserService {
    @Resource
    private WnkSendIntegralUserMapper  wnkSendIntegralUserMapper;


    @Override
    public Map<String, Object> getUserIntegral(Integer business_id, Integer user_id) {
        return this.wnkSendIntegralUserMapper.getUserIntegral(business_id, user_id);
    }

    @Override
    public int addUserIntegral(Integer business_id, Integer user_id) {
        return this.wnkSendIntegralUserMapper.addUserIntegral(business_id, user_id);
    }

    @Override
    public int updateIntegral(Integer user_id, Integer business_id, Double price) {
        return this.wnkSendIntegralUserMapper.updateIntegral(user_id, business_id, price);
    }

    /**
     * 更新用户积分余额
     *
     * @param user_id
     * @param business_id
     * @param price       增加的积分数量
     * @return 成功返回1 失败返回0
     */
    @Override
    public int updateIntegralUp(Integer user_id, Integer business_id, Double price) {
        return this.wnkSendIntegralUserMapper.updateIntegralUp(user_id,business_id,price);
    }

    @Override
    public int increaseUserIntegral(Integer user_id, Integer business_id, Double price) {
        return this.wnkSendIntegralUserMapper.increaseUserIntegral(user_id, business_id, price);
    }

    @Override
    public List<Map<Object, Object>> selectBusinessByUserId(Integer user_id) {
        return this.wnkSendIntegralUserMapper.selectBusinessByUserId(user_id);
    }

    @Override
    public Double selectUserCountIntegralByUserId(Integer user_id) {
        return this.wnkSendIntegralUserMapper.selectUserCountIntegralByUserId(user_id);
    }
}
