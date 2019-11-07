package com.springmvc.service;

import com.springmvc.entity.WithdrawSetting;

public interface IWithdrawSettingService {
    /**
     *
     * 功能描述: 更新或新增提现设置
     *
     * @param   withdrawSetting 提现设置实体类
     * @return  返回大于0则是成功
     * @author  杨新杰
     * @date    12:29 2018/12/22
     */
    int adminInsertOrUpdateWithdrawSetting(WithdrawSetting withdrawSetting);

    /**
     *
     * 功能描述: 根据类型查询提现条件
     *
     * @param   withdraw_type 提现类型
     * @return
     * @author  杨新杰
     * @date    15:05 2018/12/22
     */
    WithdrawSetting adminSelectWithdrawSetting(String withdraw_type);
}
