package com.springmvc.dao;

import com.springmvc.entity.WnkBusinessBankCard;

/**
 * @author: zhangfan
 * @Date: 2018/10/29 00:45
 * @Description:万能卡商家银行卡信息Mapper
 */
public interface WnkBusinessBankCardMapper {
    /**
     *
     * 功能描述: 新增商家银行卡信息
     *
     * @param: wnkBusinessBankCard
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 12:47 AM
     */
    int insertBusinessBankCard(WnkBusinessBankCard wnkBusinessBankCard);
    /**
     *
     * 功能描述: 通过商家id查询记录
     *
     * @param: business_id
     * @return: WnkBusinessBankCard
     * @author: zhangfan
     * @date: 2018/10/29 12:48 AM
     */
    WnkBusinessBankCard selectRecordByBusinessId(Integer business_id);
    /**
     *
     * 功能描述: 通过商家id修改银行卡信息
     *
     * @param: wnkBusinessBankCard
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 12:48 AM
     */
    int updateBankCard(WnkBusinessBankCard wnkBusinessBankCard);
}
