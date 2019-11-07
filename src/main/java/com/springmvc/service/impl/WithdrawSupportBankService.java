package com.springmvc.service.impl;

import com.springmvc.dao.WithdrawSupportBankMapper;
import com.springmvc.entity.WithdrawSupportBank;
import com.springmvc.service.IWithdrawSupportBankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/withdrawSupportBankService")
public class WithdrawSupportBankService implements IWithdrawSupportBankService{
    @Resource
    private WithdrawSupportBankMapper withdrawSupportBankMapper;

    @Override
    public List<Map<Object, Object>> selectAllRecord() {
        return this.withdrawSupportBankMapper.selectAllRecord();
    }

    @Override
    public WithdrawSupportBank selectById(Integer id) {
        return this.withdrawSupportBankMapper.selectById(id);
    }

    @Override
    public List<Map<Object, Object>> selectAllAdmin() {
        return this.withdrawSupportBankMapper.selectAllAdmin();
    }

    @Override
    public int updateBank(WithdrawSupportBank withdrawSupportBank) {
        return this.withdrawSupportBankMapper.updateBank(withdrawSupportBank);
    }

    @Override
    public int addBank(WithdrawSupportBank withdrawSupportBank) {
        return this.withdrawSupportBankMapper.addBank(withdrawSupportBank);
    }

    @Override
    public WithdrawSupportBank selectByName(String bank_name) {
        return this.withdrawSupportBankMapper.selectByName(bank_name);
    }

    @Override
    public int deleteRecordById(Integer record_id) {
        return this.withdrawSupportBankMapper.deleteRecordById(record_id);
    }

    /**
     *
     * 功能描述: 根据条件查询银行信息
     *
     * @param   map 查询条件
     * @return: 返回查询银行信息
     * @author: 刘武祥
     * @date: 2019/1/10 0010 16:33
     */
    @Override
    public List<Map<String, Object>> adminSearchBankByConditions(Map<String, Object> map) {
        return this.withdrawSupportBankMapper.adminSearchBankByConditions(map);
    }
}
