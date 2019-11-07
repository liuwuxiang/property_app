package com.springmvc.service;

import com.springmvc.entity.UsersBankCard;

public interface IUsersBankCardService {
    /**
     * 新增用户银行卡信息
     * @param usersBankCard
     * @return
     */
    int insertUserBankCard(UsersBankCard usersBankCard);

    /**
     * 通过用户id查询记录
     * @param user_id
     * @return
     */
    UsersBankCard selectRecordByUserId(Integer user_id);

    /**
     * 通过用户id修改银行卡信息
     * @param usersBankCard
     * @return
     */
    int updateBankCard(UsersBankCard usersBankCard);
}
