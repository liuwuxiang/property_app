package com.springmvc.service;

import com.springmvc.entity.UserCardAuthentication;

import java.util.List;
import java.util.Map;

public interface IUserCardAuthenticationService {
    /**
     * 通过用户id查询用户的车主认证记录
     * @param user_id 用户ID
     * @return
     */
    UserCardAuthentication selectAuthenticationByUserId(Integer user_id);

    /**
     * 新增车主认证信息
     * @param userCardAuthentication
     * @return
     */
    int addUserCardAuthentication(UserCardAuthentication userCardAuthentication);

    /**
     * 修改车主认证信息
     * @param userCardAuthentication
     * @return
     */
    int updateUserCardAuthentication(UserCardAuthentication userCardAuthentication);

    /**
     * 根据用户id删除记录
     * @param user_id
     * @return
     */
    int deleteRecordByUserId(Integer user_id);

    /**
     * 后台获取所有认证信息
     * @return
     */
    List<Map<Object,Object>> getAllCardAuthenticationAdmin();

    /**
     * 通过id查询记录
     * @param record_id
     * @return
     */
    UserCardAuthentication selectById(Integer record_id);

    /**
     * 更新认证状态
     * @param userCardAuthentication
     * @return
     */
    int updateState(UserCardAuthentication userCardAuthentication);

    /**
     *
     * 功能描述: 根据条件查询车主认证信息
     *
     * @param   map
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 12:29
     */
    List<Map<String,Object>> adminSearchCardCertificationByConditions(Map<String,Object> map);
}
