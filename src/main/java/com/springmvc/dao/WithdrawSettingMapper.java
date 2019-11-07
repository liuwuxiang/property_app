package com.springmvc.dao;

import com.springmvc.entity.WithdrawSetting;
import org.apache.ibatis.annotations.Param;

public interface WithdrawSettingMapper {

    /**
     *
     * 功能描述: 根据类型查询是否有设置
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    12:31 2018/12/22
     */
    WithdrawSetting selectByType(@Param("type") Integer type);

    /**
     *
     * 功能描述: 更新提现设置
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    12:33 2018/12/22
     */
    int adminUpdateWithdrawSetting(WithdrawSetting withdrawSetting);

    /**
     *
     * 功能描述: 新增提现设置
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    12:36 2018/12/22
     */
    int adminInsertWithdrawSetting(WithdrawSetting withdrawSetting);
}
