package com.springmvc.service;

import com.springmvc.entity.UserOwnerAuthentication;

import java.util.List;
import java.util.Map;

public interface IUserOwnerAuthenticationService {
    /**
     * 通过用户id查询用户的业主认证记录
     * @param user_id
     * @return
     */
    UserOwnerAuthentication selectAuthenticationByUserId(Integer user_id);

    /**
     * 新增业主认证信息
     * @param userOwnerAuthentication
     * @return
     */
    int addUserOwnerAuthentication(UserOwnerAuthentication userOwnerAuthentication);

    /**
     * 修改业主认证信息
     * @param userOwnerAuthentication
     * @return
     */
    int updateUserOwnerAuthentication(UserOwnerAuthentication userOwnerAuthentication);

    /**
     * 通过用户id查询用户的业主认证信息返回Map
     * @param user_id
     * @return
     */
    Map<Object,Object> getOwnerAuthenticationInformation(Integer user_id);

    /**
     * 删除记录根据用户id
     * @param user_id
     * @return
     */
    int deleteRecordByUserId(Integer user_id);

    /**
     * 后台查询所有认证信息
     * @return
     */
    List<Map<Object,Object>> getAllOwnerAuthenticationAdmin();

    /**
     * 通过id查询记录
     * @param record_id
     * @return
     */
    UserOwnerAuthentication selectById(Integer record_id);

    /**
     * 修改认证状态
     * @param userOwnerAuthentication
     * @return
     */
    int updateState(UserOwnerAuthentication userOwnerAuthentication);

    /**
     *
     * 功能描述: 根据条件查询业主认证信息
     *
     * @param   map 条件
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 10:55
     */
    List<Map<String,Object>> adminSearchOwnerCertificationByConditions(Map<String,Object> map);
}
