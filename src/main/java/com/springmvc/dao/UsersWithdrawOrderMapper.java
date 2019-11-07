package com.springmvc.dao;

import com.springmvc.entity.UsersWithdrawOrder;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述:用户提现订单
 *
 * @author 刘武祥
 * @date: 2019/1/19 0019 17:21
 */
public interface UsersWithdrawOrderMapper {

    /** 
     *
     * 功能描述: 新增提现记录
     *
     * @param   usersWithdrawOrder
     * @return: 
     * @author: 刘武祥
     * @date: 2019/1/19 0019 17:21
     */
    int insertWithdrawRecord(UsersWithdrawOrder usersWithdrawOrder);

    /**
     *
     * 功能描述: 根据用户id查询并重新修改记录所属用户(第一个用户id用于修改，第二个用户id用户查询)
     *
     * @param   user_id
     * @param   isUser_id
     * @return: 返回根据用户id查询并重新修改记录所属用户
     * @author: 刘武祥
     * @date: 2019/1/19 0019 17:20
     */
    int updateRecordUserId(Integer user_id, Integer isUser_id);

    /**
     *
     * 功能描述: 删除记录
     *
     * @param   id
     * @return: 返回删除记录
     * @author: 刘武祥
     * @date: 2019/1/19 0019 17:19
     */
    int deleteRecordById(Integer id);

    /**
     *
     * 功能描述: 后台查询所有提现订单
     *
     * @param
     * @return: 返回所有订单
     * @author: 刘武祥
     * @date: 2019/1/19 0019 17:19
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     *
     * 功能描述: 通过id查询订单
     *
     * @param   record_id
     * @return: 查询出的订单
     * @author: 刘武祥
     * @date: 2019/1/19 0019 17:18
     */
    UsersWithdrawOrder selectById(Integer record_id);

    /**
     *
     * 功能描述: 更改提现状态为拒绝提现
     *
     * @param   date      时间
     * @param   record_id
     * @return: 返回更改后的提现状态的拒绝提现
     * @author: 刘武祥
     * @date: 2019/1/19 0019 17:16
     */
    int updateStateNoCan(Date date,Integer record_id);

    /**
     *
     * 功能描述: 放款成功更新信息
     *
     * @param   usersWithdrawOrder
     * @return: 返回更新信息
     * @author: 刘武祥
     * @date: 2019/1/19 0019 17:14
     */
    int updateStateFKSuccess(UsersWithdrawOrder usersWithdrawOrder);

    /**
     *
     * 功能描述: 将记录设置为已提现
     *
     * @param   loan_date
     * @param   record_id
     * @return: 返回记录设置为已提现
     * @author: 刘武祥
     * @date: 2019/1/19 0019 17:14
     */
    int updatealReadyPresented(Date loan_date,Integer record_id);

    /**
     *
     * 功能描述: 根据条件查询订单信息
     *
     * @param   map 查询条件
     * @return: 返回条件查询出的订单信息
     * @author: 刘武祥
     * @date: 2019/1/19 0019 17:10
     */
    List<Map<Object,Object>> adminSearchUserWithdrawOrdersInfoByConditions(Map<String,Object> map);

}
