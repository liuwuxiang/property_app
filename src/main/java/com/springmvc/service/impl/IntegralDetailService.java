package com.springmvc.service.impl;

import com.springmvc.dao.IntegralDetailMapper;
import com.springmvc.entity.IntegralDetail;
import com.springmvc.entity.Users;
import com.springmvc.service.IIntegralDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("/integralDetailService")
public class IntegralDetailService implements IIntegralDetailService{

    @Resource
    private IntegralDetailMapper integralDetailMapper;
    @Resource
    private UsersService usersService;

    @Override
    public int insertIntrgralDetailRecord(Integer user_id, String name, Double integral_number, Integer type) {
        IntegralDetail integralDetail = new IntegralDetail();
        integralDetail.setName(name);
        integralDetail.setUser_id(user_id);
        integralDetail.setTransaction_integral_number(integral_number);
        integralDetail.setTransaction_date(new Date());
        integralDetail.setType(type);
        if (type == 0){
            Users users = this.usersService.selectById(user_id);
            if (users != null){
                users.setUser_integral(users.getUser_integral() + integral_number.intValue());
                this.usersService.updateUserIntegralShoppingIntegral(users);
            }
        }
        return this.integralDetailMapper.insertTransactionRecord(integralDetail);
    }

    @Override
    public List<Map<Object, Object>> selectUserRecordByUserId(Integer user_id) {
        return this.integralDetailMapper.selectUserRecordByUserId(user_id);
    }

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
    @Override
    public List<Map<Object, Object>> selectUserRecordByUserIdAndType(Integer user_id, Integer type) {
        return this.integralDetailMapper.selectUserRecordByUserIdAndType(user_id, type);
    }
}
