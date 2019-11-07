package com.springmvc.service;

import com.springmvc.entity.UserSecurityQuestion;

public interface IUserSecurityQuestionService {
    /**
     * 获取某个用户的密保问题
     * @param user_id
     * @return
     */
    UserSecurityQuestion selectUserSecurityQuestion(Integer user_id);

    /**
     * 新增用户密保问题
     * @param userSecurityQuestion
     * @return
     */
    int addUserSecurityQuestion(UserSecurityQuestion userSecurityQuestion);

    /**
     * 修改用户密保问题
     * @param userSecurityQuestion
     * @return
     */
    int updateUserSecurityQuestion(UserSecurityQuestion userSecurityQuestion);

    /**
     * 根据用户id删除记录
     * @param user_id
     * @return
     */
    int deleteRecordByUserId(Integer user_id);
}
