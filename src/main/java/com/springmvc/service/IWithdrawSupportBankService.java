package com.springmvc.service;

import com.springmvc.entity.WithdrawSupportBank;

import java.util.List;
import java.util.Map;

public interface IWithdrawSupportBankService {
    /**
     * 获取所有提现支持的银行
     * @return
     */
    List<Map<Object,Object>> selectAllRecord();

    /**
     * 通过id查询银行
     * @param id
     * @return
     */
    WithdrawSupportBank selectById(Integer id);

    /**
     * 后台获取所有银行
     * @return
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     * 修改银行信息
     * @param withdrawSupportBank
     * @return
     */
    int updateBank(WithdrawSupportBank withdrawSupportBank);

    /**
     * 添加银行
     * @param withdrawSupportBank
     * @return
     */
    int addBank(WithdrawSupportBank withdrawSupportBank);

    /**
     * 通过银行名称查询银行
     * @param bank_name
     * @return
     */
    WithdrawSupportBank selectByName(String bank_name);

    /**
     * 根据id删除记录
     * @param record_id
     * @return
     */
    int deleteRecordById(Integer record_id);

    /**
     *
     * 功能描述: 根据条件查询银行信息
     *
     * @param   map
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 16:32
     */
    List<Map<String,Object>> adminSearchBankByConditions(Map<String,Object> map);
}
