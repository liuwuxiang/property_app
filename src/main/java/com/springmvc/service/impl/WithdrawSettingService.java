package com.springmvc.service.impl;

import com.springmvc.dao.WithdrawSettingMapper;
import com.springmvc.entity.WithdrawSetting;
import com.springmvc.service.IWithdrawSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 功能描述:
 *
 * @author 杨新杰
 * @date 2018/12/22 11:21
 */
@Service("/withdrawSettingService")
public class WithdrawSettingService implements IWithdrawSettingService {

    @Resource
    private WithdrawSettingMapper withdrawSettingMapper;

    /**
     * 功能描述: 更新或新增提现设置
     *
     * @param withdrawSetting 提现设置实体类
     * @return 返回大于0则是成功
     * @author 杨新杰
     * @date 12:29 2018/12/22
     */
    @Override
    public int adminInsertOrUpdateWithdrawSetting(WithdrawSetting withdrawSetting) {
        WithdrawSetting setting = withdrawSettingMapper.selectByType(withdrawSetting.getType());
        int i = 0;
        if (setting != null) {
            i = withdrawSettingMapper.adminUpdateWithdrawSetting(withdrawSetting);
        } else {
            i = withdrawSettingMapper.adminInsertWithdrawSetting(withdrawSetting);
        }
        return i;
    }

    /**
     * 功能描述: 根据类型查询提现条件
     *
     * @param withdraw_type 提现类型
     * @return
     * @author 杨新杰
     * @date 15:05 2018/12/22
     */
    @Override
    public WithdrawSetting adminSelectWithdrawSetting(String withdraw_type) {
        return this.withdrawSettingMapper.selectByType(Integer.valueOf(withdraw_type));
    }
}
