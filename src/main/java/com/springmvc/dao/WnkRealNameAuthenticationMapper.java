package com.springmvc.dao;

import com.springmvc.entity.WnkRealNameAuthentication;

import java.util.List;
import java.util.Map;

public interface WnkRealNameAuthenticationMapper {
    //插入新记录
    int insertNewRecord(WnkRealNameAuthentication wnkRealNameAuthentication);
    //查询某个用户的认证记录
    List<Map<Object,Object>> selectRecordByUserId(Integer user_id);
}
