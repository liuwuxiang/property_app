package com.springmvc.service.impl;

import com.springmvc.dao.UserCardAuthenticationMapper;
import com.springmvc.entity.UserCardAuthentication;
import com.springmvc.service.IUserCardAuthenticationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/userCardAuthenticationService")
public class UserCardAuthenticationService implements IUserCardAuthenticationService{
    @Resource
    private UserCardAuthenticationMapper userCardAuthenticationMapper;

    @Override
    public UserCardAuthentication selectAuthenticationByUserId(Integer user_id) {
        return this.userCardAuthenticationMapper.selectAuthenticationByUserId(user_id);
    }

    @Override
    public int addUserCardAuthentication(UserCardAuthentication userCardAuthentication) {
        return this.userCardAuthenticationMapper.addUserCardAuthentication(userCardAuthentication);
    }

    @Override
    public int updateUserCardAuthentication(UserCardAuthentication userCardAuthentication) {
        return this.userCardAuthenticationMapper.updateUserCardAuthentication(userCardAuthentication);
    }

    @Override
    public int deleteRecordByUserId(Integer user_id) {
        return this.userCardAuthenticationMapper.deleteRecordByUserId(user_id);
    }

    @Override
    public List<Map<Object, Object>> getAllCardAuthenticationAdmin() {
        return this.userCardAuthenticationMapper.getAllCardAuthenticationAdmin();
    }

    @Override
    public UserCardAuthentication selectById(Integer record_id) {
        return this.userCardAuthenticationMapper.selectById(record_id);
    }

    @Override
    public int updateState(UserCardAuthentication userCardAuthentication) {
        return this.userCardAuthenticationMapper.updateState(userCardAuthentication);
    }

    /**
     *
     * 功能描述: 根据条件查询车主认证信息
     *
     * @param   map
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/10 0010 12:30
     */
    @Override
    public List<Map<String, Object>> adminSearchCardCertificationByConditions(Map<String, Object> map) {
        return this.userCardAuthenticationMapper.adminSearchCardCertificationByConditions(map);
    }
}
