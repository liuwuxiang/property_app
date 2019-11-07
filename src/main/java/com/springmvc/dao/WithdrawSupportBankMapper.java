package com.springmvc.dao;

import com.springmvc.entity.WithdrawSupportBank;

import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 银行管理
 * @Author: 刘武祥
 * @Date: 2019/2/18 0018 10:49
 */
public interface WithdrawSupportBankMapper {

    /**
     *
     * 功能描述: 获取所有提现支持的银行
     *
     * @param
     * @return: 返回所有提现支持的银行信息
     * @author: 刘武祥
     * @date: 2019/2/18 0018 10:48
     */
    List<Map<Object,Object>> selectAllRecord();
    //
    /**
     *
     * 功能描述: 通过id查询银行
     *
     * @param   id
     * @return: 返回id查询到的银行信息
     * @author: 刘武祥
     * @date: 2019/2/18 0018 10:48
     */
    WithdrawSupportBank selectById(Integer id);

    /**
     *
     * 功能描述:后台获取所有银行
     *
     * @param
     * @return: 返回所有银行信息
     * @author: 刘武祥
     * @date: 2019/2/18 0018 10:44
     */
    List<Map<Object,Object>> selectAllAdmin();

    /** 
     *
     * 功能描述: 修改银行信息
     *
     * @param   withdrawSupportBank
     * @return: 返回修改银行信息
     * @author: 刘武祥
     * @date: 2019/2/18 0018 10:44
     */
    int updateBank(WithdrawSupportBank withdrawSupportBank);

    /**
     *
     * 功能描述: 添加银行
     *
     * @param   withdrawSupportBank
     * @return: 返回添加的银行信息
     * @author: 刘武祥
     * @date: 2019/2/18 0018 10:43
     */
    int addBank(WithdrawSupportBank withdrawSupportBank);

    /**
     *
     * 功能描述: 通过银行名称查询银行
     *
     * @param   bank_name 银行名称
     * @return: 返回银行名称查询到的银行
     * @author: 刘武祥
     * @date: 2019/2/18 0018 10:42
     */
    WithdrawSupportBank selectByName(String bank_name);

    /**
     *
     * 功能描述: 根据id删除记录
     *
     * @param   record_id
     * @return: 返回删除记录
     * @author: 刘武祥
     * @date: 2019/2/18 0018 10:41
     */
    int deleteRecordById(Integer record_id);

    /** 
     *
     * 功能描述: 根据条件查询银行信息
     *
     * @param   map 查询条件
     * @return: 返回查询银行信息
     * @author: 刘武祥
     * @date: 2019/1/10 0010 16:33
     */
    List<Map<String,Object>> adminSearchBankByConditions(Map<String,Object> map);
}
