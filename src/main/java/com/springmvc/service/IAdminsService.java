package com.springmvc.service;

import com.springmvc.entity.Admins;

import java.util.List;
import java.util.Map;

/**
 * 管理员操作Service
 */
public interface IAdminsService {
    /**
     * 通过手机号以及登录密码查询管理员
     * @param mobile     手机号
     * @param login_pwd  密码
     * @return 返回查询结果,存在返回管理员信息,失败返回null
     */
    Admins selectByMobileAndLoginPWD(String mobile, String login_pwd);

    /**
     * 通过id查询管理员
     * @param id  管理员ID
     * @return 返回查询结果,存在返回管理员信息,失败返回null
     */
    Admins selectById(Integer id);

    /**
     * 获取所有管理员
     * @return 返回管理员list
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     * 通过手机号查询管理员
     * @param mobile 手机号码
     * @return 成功返回管理员信息 失败返回null
     */
    Admins selectByMobile(String mobile);

    /**
     * 更新管理员手机号和姓名
     * @param admins 管理员实体类
     * @return 成功返回1 失败返回0
     */
    int updateAdminMobileAndName(Admins admins);

    /**
     * 修改管理员登录密码
     * @param admins 管理员实体类
     * @return 成功返回1 失败返回0
     */
    int updateAdminLoginPwd(Admins admins);

    /**
     *
     * 功能描述: 插入新纪录
     *
     * @param admins 记录信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/11 10:57 PM
     */
    int addRecord(Admins admins);

    /**
     *
     * 功能描述: 更新管理员信息
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/11 11:21 PM
     */
    int updateAdminsInformation(Admins admins);

    /**
     *
     * 功能描述: 删除管理员
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/11 11:27 PM
     */
    int deleteRecordByAdminId(Integer record_id);

    /**
     *
     * 功能描述: 根据条件查询管理员管理信息
     *
     * @param   map 查询条件
     * @return: 返回管理员管理信息
     * @author: 刘武祥
     * @date: 2019/1/23 0023 16:48
     */
    List<Map<String,Object>> adminSearchAdminsByConditions(Map<String,Object> map);
}
