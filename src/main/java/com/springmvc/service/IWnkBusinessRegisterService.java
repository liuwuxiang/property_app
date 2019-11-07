package com.springmvc.service;

import com.springmvc.entity.WnkBusinessRegister;

import java.util.List;
import java.util.Map;

public interface IWnkBusinessRegisterService {
    /**
     * 添加商户注册信息
     * @param wnkBusinessRegister
     * @return
     */
    int insertBusinessRegister(WnkBusinessRegister wnkBusinessRegister);

    /**
     * 手机号查询注册商家信息
     * @param login_account
     * @return
     */
    List<Map<Object,Object>> selectByMobile(String login_account);

    /**
     * 获取所有待审核的商家信息
     * @return
     */
    List<Map<Object,Object>> getAllNoAuditedBusiness();

    /**
     * 通过id查询记录
     * @param record_id
     * @return
     */
    WnkBusinessRegister selectById(Integer record_id);

    /**
     * 更新申请信息
     * @param wnkBusinessRegister
     * @return
     */
    int updateInformation(WnkBusinessRegister wnkBusinessRegister);

    /**
     *
     * 功能描述: 根据条件查询待审核商家信息
     *
     * @param   maps
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/12 0012 16:42
     */
    List<Map<Object,Object>> adminSearchNoAuditedWnkBusiness(Map<String,Object> maps);
}
