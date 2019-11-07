package com.springmvc.service.impl;

import com.springmvc.dao.GeneralIntegralExpenditureMapper;
import com.springmvc.entity.GeneralIntegralExpenditure;
import com.springmvc.service.IGeneralIntegralExpenditureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/generalIntegralExpenditureService")
public class GeneralIntegralExpenditureService implements IGeneralIntegralExpenditureService{
    @Resource
    private GeneralIntegralExpenditureMapper generalIntegralExpenditureMapper;

    @Override
    public List<Map<Object, Object>> selectExpenditureByUser(Integer user_id) {
        return this.generalIntegralExpenditureMapper.selectExpenditureByUser(user_id);
    }

    @Override
    public List<Map<Object, Object>> selectWithdrawExpenditureByUser(Integer user_id) {
        return this.generalIntegralExpenditureMapper.selectWithdrawExpenditureByUser(user_id);
    }

    @Override
    public int updateRecordUserId(Integer user_id, Integer isUser_id) {
        return this.generalIntegralExpenditureMapper.updateRecordUserId(user_id, isUser_id);
    }

    @Override
    public int insertExchangeSilverCoinRecord(GeneralIntegralExpenditure generalIntegralExpenditure) {
        return this.generalIntegralExpenditureMapper.insertExchangeSilverCoinRecord(generalIntegralExpenditure);
    }

    @Override
    public int deleteRecordById(Integer id) {
        return this.generalIntegralExpenditureMapper.deleteRecordById(id);
    }

    @Override
    public int insertWithdrawRecord(GeneralIntegralExpenditure generalIntegralExpenditure) {
        return this.generalIntegralExpenditureMapper.insertWithdrawRecord(generalIntegralExpenditure);
    }
}
