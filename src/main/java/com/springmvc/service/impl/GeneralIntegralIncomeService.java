package com.springmvc.service.impl;

import com.springmvc.dao.GeneralIntegralIncomeMapper;
import com.springmvc.entity.GeneralIntegralIncome;
import com.springmvc.service.IGeneralIntegralIncomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("/generalIntegralIncomeService")
public class GeneralIntegralIncomeService implements IGeneralIntegralIncomeService{
    @Resource
    private GeneralIntegralIncomeMapper generalIntegralIncomeMapper;

    @Override
    public List<Map<Object, Object>> selectIndustryIncomeByUser(Integer user_id) {
        return this.generalIntegralIncomeMapper.selectIndustryIncomeByUser(user_id);
    }

    @Override
    public List<Map<Object, Object>> selectLimitIndustryIncomeByUser(Integer user_id, Integer day_number) {
        return this.generalIntegralIncomeMapper.selectLimitIndustryIncomeByUser(user_id,day_number);
    }

    @Override
    public int updateRecordUserId(Integer user_id, Integer isUser_id) {
        return this.generalIntegralIncomeMapper.updateRecordUserId(user_id, isUser_id);
    }

    @Override
    public List<Map<Object, Object>> selectIndustryRechargeIncomeByUser(Integer user_id) {
        return this.generalIntegralIncomeMapper.selectIndustryRechargeIncomeByUser(user_id);
    }

    @Override
    public int insertUserSilverCoinWithdrawRecord(GeneralIntegralIncome generalIntegralIncome) {
        if (generalIntegralIncome.getName() == null || generalIntegralIncome.getName().equals("")){
            generalIntegralIncome.setName("银币提现");
        }
        else{
            generalIntegralIncome.setIncome_date(new Date());
        }

        return this.generalIntegralIncomeMapper.insertUserSilverCoinWithdrawRecord(generalIntegralIncome);
    }

    @Override
    public int withdrawFK(GeneralIntegralIncome generalIntegralIncome) {
        return this.generalIntegralIncomeMapper.insertUserSilverCoinWithdrawRecord(generalIntegralIncome);
    }
}
