package com.springmvc.dao;

import com.springmvc.entity.WnkBusinessWithdrawOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WnkBusinessWithdrawOrderMapper {
    //新增提现记录
    int insertWithdrawRecord(WnkBusinessWithdrawOrder wnkBusinessWithdrawOrder);
    //后台查询所有提现记录
    List<Map<Object,Object>> selectAllAdmin();
    //通过id查询订单
    WnkBusinessWithdrawOrder selectById(Integer record_id);
    //更新提现状态为拒绝提现
    int updateStateNoCan(Date loan_date,Integer record_id);
    //放款成功更新信息
    int updateStateFKSuccess(WnkBusinessWithdrawOrder wnkBusinessWithdrawOrder);
    //将记录设置为已提现
    int updatealReadyPresented(Date loan_date,Integer record_id);
    //根据id删除记录
    int deleteRecordById(Integer record_id);
    //获取商家提现记录
    List<Map<Object,Object>> selectRecordByBusinessId(Integer business_id);

    /**
     * 功能描述: 根据商家ID和订单记录ID查询提现订单信息
     *
     * @param business_id 商家id
     * @param withdraw_id 提现订单记录ID
     * @return
     * @author 杨新杰
     * @date 2018/11/25 3:11
     */
    Map<Object,Object> selectWithdrawInfoById(@Param("business_id") Integer business_id, @Param("withdraw_id") Integer withdraw_id);

    /**
     *
     * 功能描述: 根据条件查询商家订单信息
     *
     * @param  map 查询条件
     * @return 商家订单信息
     * @author 刘武祥
     * @date   2018/11/30 0030 12:01
     */
    List<Map<Object,Object>> adminSearchBusinessWithdrawOrdersInfoByConditions(Map<String,Object> map);
}
