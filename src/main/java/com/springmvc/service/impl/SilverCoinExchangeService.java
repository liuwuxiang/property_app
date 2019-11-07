package com.springmvc.service.impl;

import com.springmvc.dao.SilverCoinExchangeMapper;
import com.springmvc.entity.SilverCoinExchange;
import com.springmvc.service.ISilverCoinExchangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 财务管理
 * @Author: 刘武祥
 * @Date: 2019/2/18 0018 11:57
 */
@Service("/silverCoinExchangeService")
public class SilverCoinExchangeService implements ISilverCoinExchangeService{
    @Resource
    private SilverCoinExchangeMapper silverCoinExchangeMapper;

    @Override
    public int insertExchangeRecord(SilverCoinExchange silverCoinExchange) {
        return this.silverCoinExchangeMapper.insertExchangeRecord(silverCoinExchange);
    }

    @Override
    public int updateRecordUserId(Integer user_id, Integer isUser_id) {
        return this.silverCoinExchangeMapper.updateRecordUserId(user_id, isUser_id);
    }

    @Override
    public List<Map<Object, Object>> selectAllAdmin() {
        return this.silverCoinExchangeMapper.selectAllAdmin();
    }

    /**
     *
     * 功能描述: 根据条件查询银币兑换记录
     *
     * @param   map 查询条件
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/11 0011 17:21
     */
    @Override
    public List<Map<String, Object>> adminSearchSilverCoinExchangeByConditions(Map<String, Object> map) {
        return this.silverCoinExchangeMapper.adminSearchSilverCoinExchangeByConditions(map);
    }
}
