package com.springmvc.service.impl;

import com.springmvc.dao.UserCouponsMapper;
import com.springmvc.entity.UserCoupons;
import com.springmvc.service.IUserCouponsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 05:50
 * @Description:用户优惠券服务实现类
 */
@Service("/userCouponsService")
public class UserCouponsService implements IUserCouponsService {
    @Resource
    private UserCouponsMapper userCouponsMapper;

    @Override
    public List<Map<Object, Object>> selectAllRecordByUserId(Integer user_id) {
        return this.userCouponsMapper.selectAllRecordByUserId(user_id);
    }

    @Override
    public UserCoupons selectByMaterielIdAndUserId(Integer user_id, Integer materiel_id) {
        return this.userCouponsMapper.selectByMaterielIdAndUserId(user_id, materiel_id);
    }

    @Override
    public int insertMaterielBuyRecord(UserCoupons userCoupons) {
        return this.userCouponsMapper.insertMaterielBuyRecord(userCoupons);
    }

    @Override
    public int updateSurplusNumber(UserCoupons userCoupons) {
        return this.userCouponsMapper.updateSurplusNumber(userCoupons);
    }

    @Override
    public UserCoupons selectByBusinessTypeIdAndUserId(Integer business_type_id, Integer user_id) {
        return this.userCouponsMapper.selectByBusinessTypeIdAndUserId(business_type_id, user_id);
    }

    /**
     *
     * 功能描述:通过参数方式更新用户优惠券余额
     *
     * @param surplus_number 优惠券余额
     * @param record_id 记录id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/21 4:04 PM
     */
    @Override
    public int updateSurplusNumberByCS(Integer surplus_number, Integer record_id) {
        return this.userCouponsMapper.updateSurplusNumberByCS(surplus_number, record_id);
    }
}
