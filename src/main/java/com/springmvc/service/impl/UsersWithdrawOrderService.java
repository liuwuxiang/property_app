package com.springmvc.service.impl;

import com.springmvc.dao.UsersWithdrawOrderMapper;
import com.springmvc.entity.UsersWithdrawOrder;
import com.springmvc.service.IUsersWithdrawOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("/usersWithdrawOrderService")
public class UsersWithdrawOrderService implements IUsersWithdrawOrderService {
    @Resource
    private UsersWithdrawOrderMapper usersWithdrawOrderMapper;

    @Override
    public int insertWithdrawRecord(UsersWithdrawOrder usersWithdrawOrder) {
        return this.usersWithdrawOrderMapper.insertWithdrawRecord(usersWithdrawOrder);
    }

    @Override
    public int updateRecordUserId(Integer user_id, Integer isUser_id) {
        return this.usersWithdrawOrderMapper.updateRecordUserId(user_id, isUser_id);
    }

    @Override
    public int deleteRecordById(Integer id) {
        return this.usersWithdrawOrderMapper.deleteRecordById(id);
    }

    @Override
    public List<Map<Object, Object>> selectAllAdmin() {
        return this.usersWithdrawOrderMapper.selectAllAdmin();
    }

    @Override
    public UsersWithdrawOrder selectById(Integer record_id) {
        return this.usersWithdrawOrderMapper.selectById(record_id);
    }

    @Override
    public int updateStateNoCan(Date date, Integer record_id) {
        return this.usersWithdrawOrderMapper.updateStateNoCan(date, record_id);
    }

    @Override
    public int updateStateFKSuccess(UsersWithdrawOrder usersWithdrawOrder) {
        return this.usersWithdrawOrderMapper.updateStateFKSuccess(usersWithdrawOrder);
    }

    @Override
    public int updatealReadyPresented(Date loan_date,Integer record_id) {
        return this.usersWithdrawOrderMapper.updatealReadyPresented(loan_date, record_id);
    }

    /**
     *
     * 功能描述: 根据条件查询用户订单信息
     *
     * @param  map  查询条件
     * @return 用户订单信息
     * @author 刘武祥
     * @date   2018/11/30 0030 11:58
     */
    @Override
    public List<Map<Object, Object>> adminSearchUserWithdrawOrdersInfoByConditions(Map<String, Object> map) {
        return this.usersWithdrawOrderMapper.adminSearchUserWithdrawOrdersInfoByConditions(map);
    }



}
