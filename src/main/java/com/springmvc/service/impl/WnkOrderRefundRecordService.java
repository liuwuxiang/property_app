package com.springmvc.service.impl;

import com.springmvc.dao.WnkOrderRefundRecordMapper;
import com.springmvc.entity.WnkOrderRefundRecord;
import com.springmvc.service.IWnkOrderRefundRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/8 17:25
 * @Description:2.0版本商品订单退款记录Service实现类
 */
@Service("/wnkOrderRefundRecordService")
public class WnkOrderRefundRecordService implements IWnkOrderRefundRecordService{
    @Resource
    private WnkOrderRefundRecordMapper wnkOrderRefundRecordMapper;


    @Override
    public int insertNewRecord(WnkOrderRefundRecord wnkOrderRefundRecord) {
        return this.wnkOrderRefundRecordMapper.insertNewRecord(wnkOrderRefundRecord);
    }

    @Override
    public List<Map<Object, Object>> selectByOrderNo(String order_no) {
        return this.wnkOrderRefundRecordMapper.selectByOrderNo(order_no);
    }

    /**
     *
     * 功能描述: 查询某个订单的各项退款总和
     *
     * @param order_no 订单号
     * @return:
     * @author: zhangfan
     * @date: 2018/12/10 12:05 AM
     */
    @Override
    public Map<Object, Object> selectRefundSumByOrderNo(String order_no) {
        return this.wnkOrderRefundRecordMapper.selectRefundSumByOrderNo(order_no);
    }
}
