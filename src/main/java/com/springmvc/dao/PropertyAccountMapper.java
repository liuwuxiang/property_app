package com.springmvc.dao;

import com.springmvc.entity.PropertyAccount;

public interface PropertyAccountMapper {

    /**
     *
     * 功能描述: 查询某个物业的主账号
     *
     * @param   residentials_id
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/19 0019 15:28
     */
    PropertyAccount selectByResidentialsId(Integer residentials_id);

    /**
     *
     * 功能描述: 通过账号查询
     *
     * @param   account
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/19 0019 15:28
     */
    PropertyAccount selectByAccount(String account);

    /**
     *
     * 功能描述: 添加物业主账号
     *
     * @param   propertyAccount
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/19 0019 15:28
     */
    int addPropertyPrimaryAccount(PropertyAccount propertyAccount);

    /**
     *
     * 功能描述: 更新账号姓名及电话
     *
     * @param   propertyAccount
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/19 0019 15:28
     */
    int updateNameAndMobile(PropertyAccount propertyAccount);
}
