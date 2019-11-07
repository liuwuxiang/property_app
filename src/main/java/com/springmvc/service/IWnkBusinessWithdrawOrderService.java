package com.springmvc.service;

import com.springmvc.entity.WnkBusinessWithdrawOrder;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IWnkBusinessWithdrawOrderService {
    /**
     * 新增提现记录
     * @param wnkBusinessWithdrawOrder
     * @return
     */
    int insertWithdrawRecord(WnkBusinessWithdrawOrder wnkBusinessWithdrawOrder);

    /**
     * 后台查询所有提现记录
     * @return
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     * 通过id查询订单
     * @param record_id
     * @return
     */
    WnkBusinessWithdrawOrder selectById(Integer record_id);

    /**
     * 更新提现状态为拒绝提现
     * @param loan_date
     * @param record_id
     * @return
     */
    int updateStateNoCan(Date loan_date, Integer record_id);

    /**
     * 放款成功更新信息
     * @param wnkBusinessWithdrawOrder
     * @return
     */
    int updateStateFKSuccess(WnkBusinessWithdrawOrder wnkBusinessWithdrawOrder);

    /**
     * 将记录设置为已提现
     * @param loan_date
     * @param record_id
     * @return
     */
    int updatealReadyPresented(Date loan_date,Integer record_id);

    /**
     * 根据id删除记录
     * @param record_id
     * @return
     */
    int deleteRecordById(Integer record_id);

    /**
     * 获取商家提现记录
     * @param business_id
     * @return
     */
    List<Map<Object,Object>> selectRecordByBusinessId(Integer business_id);

    /**
     *
     * 功能描述: 根据商家ID和订单记录ID查询提现订单信息
     *
     * @param business_id 商家id
     * @param withdraw_id 提现订单记录ID
     * @return
     * @author 杨新杰
     * @date   2018/11/25 3:11
     */
    Map<Object,Object> selectWithdrawInfoById(Integer business_id, Integer withdraw_id);

    /**
     *
     * 功能描述: 根据条件查询商家订单信息
     *
     * @param  map 查询条件
     * @return 返回商家订单信息
     * @author 刘武祥
     * @date   2018/11/30 0030 12:01
     */
    List<Map<Object,Object>> adminSearchBusinessWithdrawOrdersInfoByConditions(Map<String,Object> map);
}
