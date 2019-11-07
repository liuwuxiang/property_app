package com.springmvc.dao;

import com.springmvc.entity.WnkBusinessRegister;

import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 商户管理
 * @Author: 刘武祥
 * @Date: 2019/2/19 0019 14:51
 */
public interface WnkBusinessRegisterMapper {

    /**
     *
     * 功能描述: 添加商户注册信息
     *
     * @param   wnkBusinessRegister
     * @return:
     * @author: 刘武祥
     * @date: 2019/2/19 0019 14:49
     */
    int insertBusinessRegister(WnkBusinessRegister wnkBusinessRegister);
    //
    /**
     *
     * 功能描述: 手机号查询注册商家信息
     *
     * @param   login_account
     * @return:
     * @author: 刘武祥
     * @date: 2019/2/19 0019 14:49
     */
    List<Map<Object,Object>> selectByMobile(String login_account);

    /**
     *
     * 功能描述: 获取所有待审核的商家信息
     *
     * @param
     * @return:
     * @author: 刘武祥
     * @date: 2019/2/19 0019 14:48
     */
    List<Map<Object,Object>> getAllNoAuditedBusiness();

    /**
     *
     * 功能描述: 通过id查询记录
     *
     * @param   record_id
     * @return:
     * @author: 刘武祥
     * @date: 2019/2/19 0019 14:48
     */
    WnkBusinessRegister selectById(Integer record_id);

    /**
     *
     * 功能描述: 更新申请信息
     *
     * @param   wnkBusinessRegister
     * @return:
     * @author: 刘武祥
     * @date: 2019/2/19 0019 14:48
     */
    int updateInformation(WnkBusinessRegister wnkBusinessRegister);

    /**
     *
     * 功能描述: 根据条件查询待审核商家信息
     *
     * @param   maps 查询条件
     * @return: 返回待审核商家信息
     * @author: 刘武祥
     * @date: 2019/1/12 0012 16:43
     */
    List<Map<Object,Object>> adminSearchNoAuditedWnkBusiness(Map<String,Object> maps);
}
