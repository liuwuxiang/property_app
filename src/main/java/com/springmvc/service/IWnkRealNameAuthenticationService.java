package com.springmvc.service;

import com.springmvc.entity.WnkRealNameAuthentication;

import java.util.List;
import java.util.Map;

public interface IWnkRealNameAuthenticationService {
    /**
     * 插入新记录
     * @param wnkRealNameAuthentication
     * @return
     */
    int insertNewRecord(WnkRealNameAuthentication wnkRealNameAuthentication);

    /**
     * 查询某个用户的认证记录
     * @param user_id
     * @return
     */
    List<Map<Object,Object>> selectRecordByUserId(Integer user_id);
}
