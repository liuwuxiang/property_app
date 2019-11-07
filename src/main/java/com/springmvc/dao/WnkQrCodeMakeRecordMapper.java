package com.springmvc.dao;

import com.springmvc.entity.WnkQrCodeMakeRecord;

import java.util.List;
import java.util.Map;

public interface WnkQrCodeMakeRecordMapper {
    //新增使用记录
    int insertMakeRecord(WnkQrCodeMakeRecord wnkQrCodeMakeRecord);
    //查询当月使用数量
    int selectCurrentMakeNumber(Integer user_id,Integer business_type_id);
    //查询某个商家的订单
    List<Map<Object,Object>> selectByBusinessId(Integer business_id);
    //按用户查询消费用户数量(查询消费用户总数列表)
    List<Map<Object,Object>> selectUserNumberByBusinessId(Integer business_id);
    //获取某个用户的订单数据
    List<Map<Object,Object>> selectByUserId(Integer user_id);
    //查询当月使用数量
    List<Map<Object,Object>> selectCurrentMakeNumberReturnList(Integer user_id,Integer business_type_id);
    //商家查询使用次数
    List<Map<Object,Object>> selectCurrentMakeNumberReturnListByBusinessId(Integer business_id);
    //通过id查询订单
    WnkQrCodeMakeRecord selectById(Integer record_id);
    //通过订单号查询订单
    WnkQrCodeMakeRecord selectByOrderNo(String order_no);
    //通过订单号更新订单状态
    int updateOrderStateByOrderNo(Integer state,String order_no);
}
