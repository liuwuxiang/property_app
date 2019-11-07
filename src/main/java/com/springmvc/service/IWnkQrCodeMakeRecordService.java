package com.springmvc.service;

import com.springmvc.entity.WnkQrCodeMakeRecord;

import java.util.List;
import java.util.Map;

public interface IWnkQrCodeMakeRecordService {
    /**
     * 新增使用记录
     * @param wnkQrCodeMakeRecord
     * @return
     */
    int insertMakeRecord(WnkQrCodeMakeRecord wnkQrCodeMakeRecord);

    /**
     * 查询当月使用数量
     * @param user_id
     * @param business_type_id
     * @return
     */
    int selectCurrentMakeNumber(Integer user_id,Integer business_type_id);

    /**
     * 查询某个商家的订单
     * @param business_id
     * @return
     */
    List<Map<Object,Object>> selectByBusinessId(Integer business_id);

    /**
     * 按用户查询消费用户数量(查询消费用户总数列表)
     * @param business_id
     * @return
     */
    List<Map<Object,Object>> selectUserNumberByBusinessId(Integer business_id);

    /**
     * 获取某个用户的订单数据
     * @param user_id
     * @return
     */
    List<Map<Object,Object>> selectByUserId(Integer user_id);

    /**
     * 商家查询使用次数
     * @param business_id
     * @return
     */
    int selectCurrentMakeNumberReturnListByBusinessId(Integer business_id);

    /**
     * 通过id查询订单
     * @param record_id
     * @return
     */
    WnkQrCodeMakeRecord selectById(Integer record_id);

    /**
     * 通过订单号查询订单
     * @param order_no
     * @return
     */
    WnkQrCodeMakeRecord selectByOrderNo(String order_no);

    /**
     * 通过订单号更新订单状态
     * @param state
     * @param order_no
     * @return
     */
    int updateOrderStateByOrderNo(Integer state,String order_no);
}
