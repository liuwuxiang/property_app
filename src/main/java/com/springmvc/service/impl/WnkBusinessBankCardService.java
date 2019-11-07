package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessBankCardMapper;
import com.springmvc.entity.WnkBusinessBankCard;
import com.springmvc.service.IWnkBusinessBankCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhangfan
 * @Date: 2018/10/29 00:50
 * @Description:万能卡商家银行卡信息服务实现类
 */
@Service("/wnkBusinessBankCardService")
public class WnkBusinessBankCardService implements IWnkBusinessBankCardService{
    @Resource
    private WnkBusinessBankCardMapper wnkBusinessBankCardMapper;

    @Override
    public int insertBusinessBankCard(WnkBusinessBankCard wnkBusinessBankCard) {
        return this.wnkBusinessBankCardMapper.insertBusinessBankCard(wnkBusinessBankCard);
    }

    @Override
    public WnkBusinessBankCard selectRecordByBusinessId(Integer business_id) {
        return this.wnkBusinessBankCardMapper.selectRecordByBusinessId(business_id);
    }

    @Override
    public int updateBankCard(WnkBusinessBankCard wnkBusinessBankCard) {
        return this.wnkBusinessBankCardMapper.updateBankCard(wnkBusinessBankCard);
    }
}
