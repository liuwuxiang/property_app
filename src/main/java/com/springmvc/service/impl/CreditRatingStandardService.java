package com.springmvc.service.impl;

import com.springmvc.dao.CreditRatingStandardMapper;
import com.springmvc.entity.CreditRatingStandard;
import com.springmvc.service.ICreditRatingStandardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/creditRatingStandardService")
public class CreditRatingStandardService implements ICreditRatingStandardService{
    @Resource
    private CreditRatingStandardMapper creditRatingStandardMapper;

    @Override
    public List<Map<Object, Object>> selectAll() {
        return this.creditRatingStandardMapper.selectAll();
    }

    @Override
    public CreditRatingStandard selectById(Integer record_id) {
        return this.creditRatingStandardMapper.selectById(record_id);
    }

    @Override
    public int updateInformation(CreditRatingStandard creditRatingStandard) {
        return this.creditRatingStandardMapper.updateInformation(creditRatingStandard);
    }

    /** 
     *
     * 功能描述: 根据扣信标准查询所有信用评级信息
     *
     * @param   map 查询条件
     * @return: 所有信用评级信息
     * @author: 刘武祥
     * @date: 2019/1/10 0010 16:00
     */
    @Override
    public List<Map<String, Object>> adminSearchCreditRatingByConditions(Map<String, Object> map) {
        return this.creditRatingStandardMapper.adminSearchCreditRatingByConditions(map);
    }
}
