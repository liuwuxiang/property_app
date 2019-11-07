package com.springmvc.service.impl;

import com.springmvc.dao.WnkRealNameAuthenticationMapper;
import com.springmvc.entity.WnkRealNameAuthentication;
import com.springmvc.service.IWnkRealNameAuthenticationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/wnkRealNameAuthenticationService")
public class WnkRealNameAuthenticationService implements IWnkRealNameAuthenticationService{
    @Resource
    private WnkRealNameAuthenticationMapper wnkRealNameAuthenticationMapper;

    @Override
    public int insertNewRecord(WnkRealNameAuthentication wnkRealNameAuthentication) {
        return this.wnkRealNameAuthenticationMapper.insertNewRecord(wnkRealNameAuthentication);
    }

    @Override
    public List<Map<Object, Object>> selectRecordByUserId(Integer user_id) {
        return this.wnkRealNameAuthenticationMapper.selectRecordByUserId(user_id);
    }
}
