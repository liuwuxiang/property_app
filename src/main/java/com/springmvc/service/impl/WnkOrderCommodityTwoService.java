package com.springmvc.service.impl;

import com.springmvc.dao.WnkOrderCommodityTwoMapper;
import com.springmvc.entity.WnkOrderCommodityTwo;
import com.springmvc.service.IWnkOrderCommodityTwoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/8 17:15
 * @Description:
 */
@Service("/wnkOrderCommodityTwoService")
public class WnkOrderCommodityTwoService implements IWnkOrderCommodityTwoService {
    @Resource
    private WnkOrderCommodityTwoMapper wnkOrderCommodityTwoMapper;

    @Override
    public List<Map<Object, Object>> selectCommodityByOrderId(Integer order_id) {
        return this.wnkOrderCommodityTwoMapper.selectCommodityByOrderId(order_id);
    }

    @Override
    public int insertNewOrderCommodity(WnkOrderCommodityTwo wnkOrderCommodityTwo) {
        return this.wnkOrderCommodityTwoMapper.insertNewOrderCommodity(wnkOrderCommodityTwo);
    }

    @Override
    public int selectSalesTotalById(Integer business_id) {
        return this.wnkOrderCommodityTwoMapper.selectSalesTotalById(business_id);
    }

    @Override
    public Map<String, Object> selectSalesCompositionByCommoditiesId(Integer business_id, Integer commodity_id) {
        return this.wnkOrderCommodityTwoMapper.selectSalesCompositionByCommoditiesId(business_id, commodity_id);
    }

    @Override
    public Map<String, Object> selectWaitAccountEntryByCommoditiesId(Integer business_id, Integer commodity_id) {
        return this.wnkOrderCommodityTwoMapper.selectWaitAccountEntryByCommoditiesId(business_id, commodity_id);
    }

    @Override
    public int updateCommodityMakeNumberAndRefundNumber(WnkOrderCommodityTwo wnkOrderCommodityTwo) {
        return this.wnkOrderCommodityTwoMapper.updateCommodityMakeNumberAndRefundNumber(wnkOrderCommodityTwo);
    }

    /**
     *
     * 功能描述: 通过ID查询记录
     *
     * @param record_id 记录ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/9 11:35 PM
     */
    @Override
    public WnkOrderCommodityTwo selectByID(Integer record_id) {
        return this.wnkOrderCommodityTwoMapper.selectByID(record_id);
    }
}
