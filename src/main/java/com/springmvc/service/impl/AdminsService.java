package com.springmvc.service.impl;

import com.springmvc.dao.AdminsMapper;
import com.springmvc.entity.Admins;
import com.springmvc.service.IAdminsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/adminsService")
public class AdminsService implements IAdminsService{
    @Resource
    private AdminsMapper adminsMapper;
    /**
     *
     * 功能描述: 通过手机号以及登录密码查询管理员
     *
     * @param mobile     手机号
     * @param login_pwd  密  码
     * @return 返回查询结果,存在返回管理员信息,失败返回null
     * @author 刘武祥
     * @date   2018/10/22 0022 16:43
     */
    @Override
    public Admins selectByMobileAndLoginPWD(String mobile, String login_pwd) {

        return this.adminsMapper.selectByMobileAndLoginPWD(mobile, login_pwd);
    }

    /**
     *
     * 功能描述: 通过id查询管理员
     *
     * @param id  管理员ID
     * @return 返回查询结果,存在返回管理员信息,失败返回null
     * @author 刘武祥
     * @date   2018/10/22 0022 16:47
     */
    @Override
    public Admins selectById(Integer id) {

        return this.adminsMapper.selectById(id);
    }

    /**
     *
     * 功能描述: 获取所有管理员
     *
     * @return 返回管理员list集合
     * @author 刘武祥
     * @date   2018/10/22 0022 16:49
     */
    @Override
    public List<Map<Object, Object>> selectAllAdmin() {
        return this.adminsMapper.selectAllAdmin();
    }

    /**
     *
     * 功能描述: 通过手机号查询管理员
     *
     * @param  mobile 手机号码
     * @return 成功返回管理员信息 失败返回null
     * @author 刘武祥
     * @date   2018/10/22 0022 16:50
     */
    @Override
    public Admins selectByMobile(String mobile) {
        return this.adminsMapper.selectByMobile(mobile);
    }

    /**
     *
     * 功能描述: 更新管理员手机号和姓名
     *
     * @param admins 管理员实体类
     * @return 成功返回1 失败返回0
     * @author 刘武祥
     * @date   2018/10/22 0022 16:51
     */
    @Override
    public int updateAdminMobileAndName(Admins admins) {
        return this.adminsMapper.updateAdminMobileAndName(admins);
    }

    /**
     *
     * 功能描述: 修改管理员登录密码
     *
     * @param admins 管理员实体类
     * @return 成功返回1 失败返回0
     * @author 刘武祥
     * @date   2018/10/22 0022 16:54
     */
    @Override
    public int updateAdminLoginPwd(Admins admins) {
        return this.adminsMapper.updateAdminLoginPwd(admins);
    }

    @Override
    public int addRecord(Admins admins) {
        return this.adminsMapper.addRecord(admins);
    }

    @Override
    public int updateAdminsInformation(Admins admins) {
        return this.adminsMapper.updateAdminsInformation(admins);
    }

    @Override
    public int deleteRecordByAdminId(Integer record_id) {
        return this.adminsMapper.deleteRecordByAdminId(record_id);
    }

    /**
     *
     * 功能描述: 根据条件查询管理员管理信息
     *
     * @param   map 查询条件
     * @return: 返回管理员管理信息
     * @author: 刘武祥
     * @date: 2019/1/23 0023 16:48
     */
    @Override
    public List<Map<String, Object>> adminSearchAdminsByConditions(Map<String, Object> map) {
        return this.adminsMapper.adminSearchAdminsByConditions(map);
    }
}
