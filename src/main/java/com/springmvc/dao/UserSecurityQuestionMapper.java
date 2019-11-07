package com.springmvc.dao;

import com.springmvc.entity.UserSecurityQuestion;

public interface UserSecurityQuestionMapper {
    //获取某个用户的密保问题
    UserSecurityQuestion selectUserSecurityQuestion(Integer user_id);
    //新增用户密保问题
    int addUserSecurityQuestion(UserSecurityQuestion userSecurityQuestion);
    //修改用户密保问题
    int updateUserSecurityQuestion(UserSecurityQuestion userSecurityQuestion);
    //根据用户id删除记录
    int deleteRecordByUserId(Integer user_id);
}
