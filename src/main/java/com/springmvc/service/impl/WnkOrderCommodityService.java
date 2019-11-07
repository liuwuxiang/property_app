package com.springmvc.service.impl;

import com.springmvc.dao.WnkOrderCommodityMapper;
import com.springmvc.entity.WnkOrderCommodity;
import com.springmvc.service.IWnkOrderCommodityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/wnkOrderCommodityService")
public class WnkOrderCommodityService implements IWnkOrderCommodityService{
    @Resource
    private WnkOrderCommodityMapper wnkOrderCommodityMapper;

    @Override
    public List<Map<Object, Object>> selectCommodityByOrderId(Integer order_id) {
        return this.wnkOrderCommodityMapper.selectCommodityByOrderId(order_id);
    }

    @Override
    public int insertNewOrderCommodity(WnkOrderCommodity wnkOrderCommodity) {
        return this.wnkOrderCommodityMapper.insertNewOrderCommodity(wnkOrderCommodity);
    }

    /**
     *
     * 功能描述:根据商家ID查询商家销售总量
     *
     * @param  business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/10 11:56
     */
    @Override
    public int selectSalesTotalById(Integer business_id) {
        return this.wnkOrderCommodityMapper.selectSalesTotalById(business_id);
    }

    /**
     * 功能描述:查询用户销售构成信息
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/10 11:56
     */
    @Override
    public Map<String, Object> selectSalesCompositionByCommoditiesId(Integer business_id,Integer commodity_id) {
        return this.wnkOrderCommodityMapper.selectSalesCompositionByCommoditiesId(business_id,commodity_id);
    }

    /**
     * 功能描述:查询商户未入账信息
     *
     * @param business_id 商家ID
     * @param id
     * @return
     * @author 杨新杰
     * @date 2018/11/10 11:56
     */
    @Override
    public Map<String, Object> selectWaitAccountEntryByCommoditiesId(Integer business_id, Integer id) {
        return this.wnkOrderCommodityMapper.selectWaitAccountEntryByCommoditiesId(business_id,id);
    }
}
