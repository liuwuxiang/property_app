package com.springmvc.service;

import com.springmvc.entity.UsersWithdrawOrder;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IUsersWithdrawOrderService {
    /**
     * 新增提现记录
     * @param usersWithdrawOrder
     * @return
     */
    int insertWithdrawRecord(UsersWithdrawOrder usersWithdrawOrder);

    /**
     * 根据用户id查询并重新修改记录所属用户(第一个用户id用于修改，第二个用户id用户查询)
     * @param user_id
     * @param isUser_id
     * @return
     */
    int updateRecordUserId(Integer user_id,Integer isUser_id);

    /**
     * 删除记录
     * @param id
     * @return
     */
    int deleteRecordById(Integer id);

    /**
     * 后台查询所有提现订单
     * @return
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     * 通过id查询订单
     * @param record_id
     * @return
     */
    UsersWithdrawOrder selectById(Integer record_id);

    /**
     * 更改提现状态为拒绝提现
     * @param date
     * @param record_id
     * @return
     */
    int updateStateNoCan(Date date, Integer record_id);

    /**
     * 放款成功更新信息
     * @param usersWithdrawOrder
     * @return
     */
    int updateStateFKSuccess(UsersWithdrawOrder usersWithdrawOrder);

    /**
     * 将记录设置为已提现
     * @param loan_date
     * @param record_id
     * @return
     */
    int updatealReadyPresented(Date loan_date,Integer record_id);

    /**
     *
     * 功能描述: 根据条件查询用户订单信息
     *
     * @param  map
     * @return 用户订单信息
     * @author 刘武祥
     * @date   2018/11/30 0030 11:58
     */
    List<Map<Object,Object>> adminSearchUserWithdrawOrdersInfoByConditions(Map<String,Object> map);


}
