package com.springmvc.service;

import com.springmvc.entity.PropertyAccount;

public interface IPropertyAccountService {
    /**
     * 查询某个物业的主账号
     * @param residentials_id
     * @return
     */
    PropertyAccount selectByResidentialsId(Integer residentials_id);

    /**
     * 通过账号查询
     * @param account
     * @return
     */
    PropertyAccount selectByAccount(String account);

    /**
     * 添加物业主账号
     * @param propertyAccount
     * @return
     */
    int addPropertyPrimaryAccount(PropertyAccount propertyAccount);

    /**
     * 更新账号姓名及电话
     * @param propertyAccount
     * @return
     */
    int updateNameAndMobile(PropertyAccount propertyAccount);
}
