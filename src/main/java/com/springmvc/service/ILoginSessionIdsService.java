package com.springmvc.service;

import com.springmvc.entity.LoginSessionIds;

/**
 * 用户Session操作SService
 */
public interface ILoginSessionIdsService {
    /**
     * 新增SessionId记录
     * @param loginSessionIds
     * @return
     */
    int addSessionIdRecord(LoginSessionIds loginSessionIds);

    /**
     * 查询sessionId
     * @param sessionIds
     * @return
     */
    LoginSessionIds selectBySessionId(String sessionIds);

    /**
     * 通过userId修改sessionId
     * @param loginSessionIds
     * @return
     */
    int updateSessionIdByUserId(LoginSessionIds loginSessionIds);

    /**
     * 通过用户id以及使用类别查询记录
     * @param user_id  用户ID
     * @param type     类别，0-后台,1-用户端app,2-用户端微信版
     * @return
     */
    LoginSessionIds selectByUserIdAndType(Integer user_id, Integer type);

    /**
     * 根据sessionId删除记录
     * @param sessionId sessionId
     * @return
     */
    int deleteRecordBySessionId(String sessionId);
}
