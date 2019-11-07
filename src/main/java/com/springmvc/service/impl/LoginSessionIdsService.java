package com.springmvc.service.impl;

import com.springmvc.dao.LoginSessionIdsMapper;
import com.springmvc.entity.LoginSessionIds;
import com.springmvc.service.ILoginSessionIdsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("/loginSessionIdsService")
public class LoginSessionIdsService implements ILoginSessionIdsService{
    @Resource
    private LoginSessionIdsMapper sessionIdsMapper;

    @Override
    public int addSessionIdRecord(LoginSessionIds loginSessionIds) {
        return this.sessionIdsMapper.addSessionIdRecord(loginSessionIds);
    }

    @Override
    public LoginSessionIds selectBySessionId(String sessionIds) {
        return this.sessionIdsMapper.selectBySessionId(sessionIds);
    }

    @Override
    public int updateSessionIdByUserId(LoginSessionIds loginSessionIds) {
        return this.sessionIdsMapper.updateSessionIdByUserId(loginSessionIds);
    }

    @Override
    public LoginSessionIds selectByUserIdAndType(Integer user_id, Integer type) {
        return this.sessionIdsMapper.selectByUserIdAndType(user_id, type);
    }

    @Override
    public int deleteRecordBySessionId(String sessionId) {
        return this.sessionIdsMapper.deleteRecordBySessionId(sessionId);
    }
}
