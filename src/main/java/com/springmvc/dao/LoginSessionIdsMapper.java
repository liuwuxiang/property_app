package com.springmvc.dao;

import com.springmvc.entity.LoginSessionIds;

public interface LoginSessionIdsMapper {
    //新增SessionId记录
    int addSessionIdRecord(LoginSessionIds loginSessionIds);
    //查询sessionId
    LoginSessionIds selectBySessionId(String sessionIds);
    //通过userId修改sessionId
    int updateSessionIdByUserId(LoginSessionIds loginSessionIds);
    //通过用户id以及使用类别查询记录(type:类别，0-后台,1-用户端app,2-用户端微信版)
    LoginSessionIds selectByUserIdAndType(Integer user_id, Integer type);
    //根据sessionId删除记录
    int deleteRecordBySessionId(String sessionId);
}
