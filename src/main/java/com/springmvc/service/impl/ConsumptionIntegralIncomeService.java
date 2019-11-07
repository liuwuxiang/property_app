package com.springmvc.service.impl;

import com.springmvc.dao.ConsumptionIntegralIncomeMapper;
import com.springmvc.entity.ConsumptionIntegralIncome;
import com.springmvc.service.IConsumptionIntegralIncomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/consumptionIntegralIncomeService")
public class ConsumptionIntegralIncomeService implements IConsumptionIntegralIncomeService{
    @Resource
    private ConsumptionIntegralIncomeMapper consumptionIntegralIncomeMapper;

    @Override
    public List<Map<Object, Object>> selectRechargeIncomeByUser(Integer user_id) {
        return this.consumptionIntegralIncomeMapper.selectRechargeIncomeByUser(user_id);
    }

    @Override
    public Integer updateRecordUserId(Integer user_id, Integer isUser_id) {
        return this.consumptionIntegralIncomeMapper.updateRecordUserId(user_id, isUser_id);
    }

    @Override
    public int addIncome(ConsumptionIntegralIncome consumptionIntegralIncome) {
        return this.consumptionIntegralIncomeMapper.addIncome(consumptionIntegralIncome);
    }
}
