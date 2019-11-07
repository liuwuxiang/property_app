package com.springmvc.service.impl;

import com.springmvc.dao.ConsumptionIntegralExpenditureMapper;
import com.springmvc.entity.ConsumptionIntegralExpenditure;
import com.springmvc.service.IConsumptionIntegralExpenditureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/consumptionIntegralExpenditureService")
public class ConsumptionIntegralExpenditureService implements IConsumptionIntegralExpenditureService{
    @Resource
    private ConsumptionIntegralExpenditureMapper consumptionIntegralExpenditureMapper;

    @Override
    public List<Map<Object, Object>> selectExpenditureByUser(Integer user_id) {
        return this.consumptionIntegralExpenditureMapper.selectExpenditureByUser(user_id);
    }

    @Override
    public int updateRecordUserId(Integer user_id, Integer isUser_id) {
        return this.consumptionIntegralExpenditureMapper.updateRecordUserId(user_id, isUser_id);
    }

    @Override
    public int insertExchangeSilverCoinRecord(ConsumptionIntegralExpenditure consumptionIntegralExpenditure) {
        return this.consumptionIntegralExpenditureMapper.insertExchangeSilverCoinRecord(consumptionIntegralExpenditure);
    }

    @Override
    public int deleteRecordById(Integer id) {
        return this.consumptionIntegralExpenditureMapper.deleteRecordById(id);
    }
}
