package com.springmvc.service.impl;

import com.springmvc.dao.MyCouponMapper;
import com.springmvc.service.IMyCouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/myCouponService")
public class MyCouponService implements IMyCouponService{
    @Resource
    private MyCouponMapper myCouponMapper;

    @Override
    public int updateRecordUserId(Integer user_id, Integer isUser_id) {
        return this.myCouponMapper.updateRecordUserId(user_id, isUser_id);
    }

    @Override
    public List<Map<Object, Object>> selectAllCouponByUser(Integer user_id) {
        return this.myCouponMapper.selectAllCouponByUser(user_id);
    }
}
