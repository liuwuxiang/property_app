package com.springmvc.service.impl;

import com.springmvc.dao.SecurityQuestionMapper;
import com.springmvc.service.ISecurityQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/securityQuestionService")
public class SecurityQuestionService implements ISecurityQuestionService{
    @Resource
    private SecurityQuestionMapper securityQuestionMapper;

    @Override
    public List<Map<Object, Object>> selectAllSecurityQuestion() {
        return this.securityQuestionMapper.selectAllSecurityQuestion();
    }
}
