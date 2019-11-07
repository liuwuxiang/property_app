package com.springmvc.service.impl;

import com.springmvc.dao.UserOwnerAuthenticationMapper;
import com.springmvc.entity.UserOwnerAuthentication;
import com.springmvc.service.IUserOwnerAuthenticationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("/userOwnerAuthenticationService")
public class UserOwnerAuthenticationService implements IUserOwnerAuthenticationService{
    @Resource
    private UserOwnerAuthenticationMapper userOwnerAuthenticationMapper;

    @Override
    public UserOwnerAuthentication selectAuthenticationByUserId(Integer user_id) {
        return this.userOwnerAuthenticationMapper.selectAuthenticationByUserId(user_id);
    }

    @Override
    public int addUserOwnerAuthentication(UserOwnerAuthentication userOwnerAuthentication) {
        return this.userOwnerAuthenticationMapper.addUserOwnerAuthentication(userOwnerAuthentication);
    }

    @Override
    public int updateUserOwnerAuthentication(UserOwnerAuthentication userOwnerAuthentication) {
        return this.userOwnerAuthenticationMapper.updateUserOwnerAuthentication(userOwnerAuthentication);
    }

    @Override
    public Map<Object, Object> getOwnerAuthenticationInformation(Integer user_id) {
        return this.userOwnerAuthenticationMapper.getOwnerAuthenticationInformation(user_id);
    }

    @Override
    public int deleteRecordByUserId(Integer user_id) {
        return this.userOwnerAuthenticationMapper.deleteRecordByUserId(user_id);
    }

    @Override
    public List<Map<Object, Object>> getAllOwnerAuthenticationAdmin() {
        return this.userOwnerAuthenticationMapper.getAllOwnerAuthenticationAdmin();
    }

    @Override
    public UserOwnerAuthentication selectById(Integer record_id) {
        return this.userOwnerAuthenticationMapper.selectById(record_id);
    }

    @Override
    public int updateState(UserOwnerAuthentication userOwnerAuthentication) {
        return this.userOwnerAuthenticationMapper.updateState(userOwnerAuthentication);
    }

    /**
     *
     * 功能描述: 根据条件查询业主认证信息
     *
     * @param       map
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 10:56
     */
    @Override
    public List<Map<String, Object>> adminSearchOwnerCertificationByConditions(Map<String, Object> map) {
        return this.userOwnerAuthenticationMapper.adminSearchOwnerCertificationByConditions(map);
    }
}
