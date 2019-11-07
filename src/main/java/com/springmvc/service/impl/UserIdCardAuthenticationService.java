package com.springmvc.service.impl;

import com.springmvc.dao.UserIdCardAuthenticationMapper;
import com.springmvc.entity.UserIdCardAuthentication;
import com.springmvc.service.IUserIdCardAuthenticationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/userIdCardAuthenticationService")
public class UserIdCardAuthenticationService implements IUserIdCardAuthenticationService{
    @Resource
    private UserIdCardAuthenticationMapper userIdCardAuthenticationMapper;

    @Override
    public UserIdCardAuthentication selectAuthenticationByUserId(Integer user_id) {
        return this.userIdCardAuthenticationMapper.selectAuthenticationByUserId(user_id);
    }

    @Override
    public int addUserIdCardAuthentication(UserIdCardAuthentication userIdCardAuthentication) {
        return this.userIdCardAuthenticationMapper.addUserIdCardAuthentication(userIdCardAuthentication);
    }

    @Override
    public int updateUserIdCardAuthentication(UserIdCardAuthentication userIdCardAuthentication) {
        return this.userIdCardAuthenticationMapper.updateUserIdCardAuthentication(userIdCardAuthentication);
    }

    @Override
    public int deleteRecordByUserId(Integer user_id) {
        return this.userIdCardAuthenticationMapper.deleteRecordByUserId(user_id);
    }

    @Override
    public List<Map<Object, Object>> getAllIdCardAuthenticationAdmin() {
        return this.userIdCardAuthenticationMapper.getAllIdCardAuthenticationAdmin();
    }

    @Override
    public UserIdCardAuthentication selectById(Integer record_id) {
        return this.userIdCardAuthenticationMapper.selectById(record_id);
    }

    @Override
    public int updateState(UserIdCardAuthentication userIdCardAuthentication) {
        return this.userIdCardAuthenticationMapper.updateState(userIdCardAuthentication);
    }

    /**
     *
     * 功能描述: 通过条件查询实名认证信息
     *
     * @param   map 查询条件
     * @return: 返回查询的实名认证信息
     * @author: 刘武祥
     * @date: 2019/1/10 0010 15:20
     */
    @Override
    public List<Map<String, Object>> adminSearchRealNameCertificationByConditions(Map<String, Object> map) {
        return this.userIdCardAuthenticationMapper.adminSearchRealNameCertificationByConditions(map);
    }
}
