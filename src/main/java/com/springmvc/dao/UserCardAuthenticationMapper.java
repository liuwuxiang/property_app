package com.springmvc.dao;

import com.springmvc.entity.UserCardAuthentication;

import java.util.List;
import java.util.Map;

public interface UserCardAuthenticationMapper {
    //通过用户id查询用户的车主认证记录
    UserCardAuthentication selectAuthenticationByUserId(Integer user_id);
    //新增车主认证信息
    int addUserCardAuthentication(UserCardAuthentication userCardAuthentication);
    //修改车主认证信息
    int updateUserCardAuthentication(UserCardAuthentication userCardAuthentication);
    //根据用户id删除记录
    int deleteRecordByUserId(Integer user_id);
    //后台获取所有认证信息
    List<Map<Object,Object>> getAllCardAuthenticationAdmin();
    //通过id查询记录
    UserCardAuthentication selectById(Integer record_id);
    //更新认证状态
    int updateState(UserCardAuthentication userCardAuthentication);

    /**
     *
     * 功能描述: 根据条件查询车主认证信息
     *
     * @param   map
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 12:31
     */
    List<Map<String,Object>> adminSearchCardCertificationByConditions(Map<String,Object> map);
}
