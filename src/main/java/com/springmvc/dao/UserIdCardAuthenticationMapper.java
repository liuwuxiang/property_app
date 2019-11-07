package com.springmvc.dao;

import com.springmvc.entity.UserIdCardAuthentication;

import java.util.List;
import java.util.Map;

public interface UserIdCardAuthenticationMapper {

    /**
     *
     * 功能描述: 通过用户id查询用户的身份证认证记录
     *
     * @param   user_id
     * @return: 用户id查询用户的身份证认证记录
     * @author: 刘武祥
     * @date: 2019/2/12 0012 11:17
     */
    UserIdCardAuthentication selectAuthenticationByUserId(Integer user_id);

    /**
     *
     * 功能描述: 新增身份认证信息
     *
     * @param   userIdCardAuthentication
     * @return: 新增身份认证信息
     * @author: 刘武祥
     * @date: 2019/2/12 0012 11:17
     */
    int addUserIdCardAuthentication(UserIdCardAuthentication userIdCardAuthentication);
    //
    /**
     *
     * 功能描述: 修改身份认真信息
     *
     * @param   userIdCardAuthentication
     * @return: 修改身份认真信息
     * @author: 刘武祥
     * @date: 2019/2/12 0012 11:16
     */
    int updateUserIdCardAuthentication(UserIdCardAuthentication userIdCardAuthentication);

    /**
     *
     * 功能描述: 根据用户id删除记录
     *
     * @param   user_id
     * @return: 用户id删除记录
     * @author: 刘武祥
     * @date: 2019/2/12 0012 11:16
     */
    int deleteRecordByUserId(Integer user_id);

    /**
     *
     * 功能描述: 后台获取所有实名认证信息
     *
     * @param
     * @return: 所有实名认证信息
     * @author: 刘武祥
     * @date: 2019/2/12 0012 11:16
     */
    List<Map<Object,Object>> getAllIdCardAuthenticationAdmin();

    /**
     *
     * 功能描述: 通过id查询记录
     *
     * @param   record_id
     * @return: id查询记录
     * @author: 刘武祥
     * @date: 2019/2/12 0012 11:15
     */
    UserIdCardAuthentication selectById(Integer record_id);

    /**
     *
     * 功能描述: 修改认证状态
     *
     * @param   userIdCardAuthentication
     * @return: 认证状态
     * @author: 刘武祥
     * @date: 2019/2/12 0012 11:15
     */
    int updateState(UserIdCardAuthentication userIdCardAuthentication);

    /**
     *
     * 功能描述: 通过条件查询实名认证信息
     *
     * @param   map 查询条件
     * @return: 返回查询的实名认证信息
     * @author: 刘武祥
     * @date: 2019/1/10 0010 15:21
     */
    List<Map<String,Object>> adminSearchRealNameCertificationByConditions(Map<String,Object> map);
}
