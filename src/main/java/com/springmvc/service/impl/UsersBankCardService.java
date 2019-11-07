package com.springmvc.service.impl;

import com.springmvc.dao.UsersBankCardMapper;
import com.springmvc.entity.UsersBankCard;
import com.springmvc.service.IUsersBankCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("/usersBankCardService")
public class UsersBankCardService implements IUsersBankCardService{
    @Resource
    private UsersBankCardMapper usersBankCardMapper;

    @Override
    public int insertUserBankCard(UsersBankCard usersBankCard) {
        return this.usersBankCardMapper.insertUserBankCard(usersBankCard);
    }

    @Override
    public UsersBankCard selectRecordByUserId(Integer user_id) {
        return this.usersBankCardMapper.selectRecordByUserId(user_id);
    }

    @Override
    public int updateBankCard(UsersBankCard usersBankCard) {
        return this.usersBankCardMapper.updateBankCard(usersBankCard);
    }
}
