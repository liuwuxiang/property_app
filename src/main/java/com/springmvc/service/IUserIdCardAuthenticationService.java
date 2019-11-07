package com.springmvc.service;

import com.springmvc.entity.UserIdCardAuthentication;

import java.util.List;
import java.util.Map;

public interface IUserIdCardAuthenticationService {
    /**
     * 通过用户id查询用户的身份证认证记录
     * @param user_id
     * @return
     */
    UserIdCardAuthentication selectAuthenticationByUserId(Integer user_id);

    /**
     * 新增身份认证信息
     * @param userIdCardAuthentication
     * @return
     */
    int addUserIdCardAuthentication(UserIdCardAuthentication userIdCardAuthentication);

    /**
     * 修改身份认真信息
     * @param userIdCardAuthentication
     * @return
     */
    int updateUserIdCardAuthentication(UserIdCardAuthentication userIdCardAuthentication);

    /**
     * 根据用户id删除记录
     * @param user_id
     * @return
     */
    int deleteRecordByUserId(Integer user_id);

    /**
     * 后台获取所有实名认证信息
     * @return
     */
    List<Map<Object,Object>> getAllIdCardAuthenticationAdmin();

    /**
     * 通过id查询记录
     * @param record_id
     * @return
     */
    UserIdCardAuthentication selectById(Integer record_id);

    /**
     * 修改认证状态
     * @param userIdCardAuthentication
     * @return
     */
    int updateState(UserIdCardAuthentication userIdCardAuthentication);

    /**
     *
     * 功能描述: 通过条件查询实名认证信息
     *
     * @param   map
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 15:19
     */
    List<Map<String,Object>> adminSearchRealNameCertificationByConditions(Map<String,Object> map);
}
