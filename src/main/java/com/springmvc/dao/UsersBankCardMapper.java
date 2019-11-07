package com.springmvc.dao;

import com.springmvc.entity.UsersBankCard;

public interface UsersBankCardMapper {
    //新增用户银行卡信息
    int insertUserBankCard(UsersBankCard usersBankCard);
    //通过用户id查询记录
    UsersBankCard selectRecordByUserId(Integer user_id);
    //通过用户id修改银行卡信息
    int updateBankCard(UsersBankCard usersBankCard);
}
