package com.springmvc.dao;

import com.springmvc.entity.UserOwnerAuthentication;

import java.util.List;
import java.util.Map;

public interface UserOwnerAuthenticationMapper {

    /**
     *
     * 功能描述: 通过用户id查询用户的业主认证记录
     *
     * @param   user_id
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 10:57
     */
    UserOwnerAuthentication selectAuthenticationByUserId(Integer user_id);

    /**
     *
     * 功能描述: 新增业主认证信息
     *
     * @param   userOwnerAuthentication
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 10:58
     */
    int addUserOwnerAuthentication(UserOwnerAuthentication userOwnerAuthentication);

    /**
     *
     * 功能描述: 修改业主认证信息
     *
     * @param   userOwnerAuthentication
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 10:58
     */
    int updateUserOwnerAuthentication(UserOwnerAuthentication userOwnerAuthentication);

    /**
     *
     * 功能描述: 通过用户id查询用户的业主认证信息返回Map
     *
     * @param   user_id
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 10:58
     */
    Map<Object,Object> getOwnerAuthenticationInformation(Integer user_id);

    /**
     *
     * 功能描述: 删除记录根据用户id
     *
     * @param   user_id
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 10:59
     */
    int deleteRecordByUserId(Integer user_id);

    /**
     *
     * 功能描述: 后台查询所有认证信息
     *
     * @param
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 11:01
     */
    List<Map<Object,Object>> getAllOwnerAuthenticationAdmin();

    /**
     *
     * 功能描述: 通过id查询记录
     *
     * @param   record_id
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 11:01
     */
    UserOwnerAuthentication selectById(Integer record_id);

    /** 
     *
     * 功能描述: 修改认证状态
     *
     * @param   userOwnerAuthentication
     * @return: 
     * @author: 刘武祥
     * @date: 2019/1/10 0010 11:01
     */
    int updateState(UserOwnerAuthentication userOwnerAuthentication);

    /**
     *
     * 功能描述: 根据条件查询业主认证信息
     *
     * @param   map
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 10:57
     */
    List<Map<String,Object>> adminSearchOwnerCertificationByConditions(Map<String,Object> map);
}
