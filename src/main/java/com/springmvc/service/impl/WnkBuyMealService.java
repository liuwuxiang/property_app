package com.springmvc.service.impl;

import com.springmvc.dao.WnkBuyMealMapper;
import com.springmvc.entity.WnkBuyMeal;
import com.springmvc.service.IWnkBuyMealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/wnkBuyMealService")
public class WnkBuyMealService implements IWnkBuyMealService{
    @Resource
    private WnkBuyMealMapper wnkBuyMealMapper;


    @Override
    public List<Map<Object, Object>> selectAllRecord() {
        return this.wnkBuyMealMapper.selectAllRecord();
    }

    @Override
    public WnkBuyMeal selectById(Integer record_id) {
        return this.wnkBuyMealMapper.selectById(record_id);
    }

    @Override
    public List<Map<Object, Object>> selectByType(Integer type) {
        return this.wnkBuyMealMapper.selectByType(type);
    }
}
