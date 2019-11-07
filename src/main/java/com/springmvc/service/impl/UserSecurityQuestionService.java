package com.springmvc.service.impl;

import com.springmvc.dao.UserSecurityQuestionMapper;
import com.springmvc.entity.UserSecurityQuestion;
import com.springmvc.service.IUserSecurityQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("/userSecurityQuestionService")
public class UserSecurityQuestionService implements IUserSecurityQuestionService{
    @Resource
    private UserSecurityQuestionMapper userSecurityQuestionMapper;

    @Override
    public UserSecurityQuestion selectUserSecurityQuestion(Integer user_id) {
        return this.userSecurityQuestionMapper.selectUserSecurityQuestion(user_id);
    }

    @Override
    public int addUserSecurityQuestion(UserSecurityQuestion userSecurityQuestion) {
        return this.userSecurityQuestionMapper.addUserSecurityQuestion(userSecurityQuestion);
    }

    @Override
    public int updateUserSecurityQuestion(UserSecurityQuestion userSecurityQuestion) {
        return this.userSecurityQuestionMapper.updateUserSecurityQuestion(userSecurityQuestion);
    }

    @Override
    public int deleteRecordByUserId(Integer user_id) {
        return this.userSecurityQuestionMapper.deleteRecordByUserId(user_id);
    }
}
