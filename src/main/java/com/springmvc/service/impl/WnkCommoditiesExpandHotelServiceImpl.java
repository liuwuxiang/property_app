package com.springmvc.service.impl;

import com.springmvc.dao.WnkCommoditiesExpandHotelMapper;
import com.springmvc.entity.WnkCommoditiesExpandHotel;
import com.springmvc.service.WnkBusinessTypeManagementHotelService;
import com.springmvc.service.WnkCommoditiesExpandHotelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:商品扩展信息操作 (酒店) 持久层
 *
 * @author 杨新杰
 * @date 2019/1/17 15:55
 */
@Service
public class WnkCommoditiesExpandHotelServiceImpl implements WnkCommoditiesExpandHotelService {

    @Resource
    private WnkCommoditiesExpandHotelMapper wnkCommoditiesExpandHotelMapper;

    @Resource
    private WnkBusinessTypeManagementHotelService wnkBusinessTypeManagementHotelService;

    /**
     * 新增商品扩展信息
     *
     * @param wnkCommoditiesExpandHotel 商品扩展信息 实体类
     * @return 新增条目数
     * @author 杨新杰
     */
    @Override
    public int insertExpandInfo(WnkCommoditiesExpandHotel wnkCommoditiesExpandHotel) {
        return wnkCommoditiesExpandHotelMapper.insertExpandInfo(wnkCommoditiesExpandHotel);
    }

    /**
     * 通过商品ID 查询商品扩展信息
     *
     * @param commodityId 商品ID
     * @return 返回查询到的数据
     */
    @Override
    public WnkCommoditiesExpandHotel selectCommoditiesExpandHotelByCommoditiesId(Integer commodityId) {
        return this.wnkCommoditiesExpandHotelMapper.selectCommoditiesExpandHotelByCommoditiesId(commodityId);
    }

    /**
     * 更新商品扩展信息
     *
     * @param wnkCommoditiesExpandHotel 商品扩展信息 实体类
     * @return 新增条目数
     * @author 杨新杰
     */
    @Override
    public int updateExpandInfo(WnkCommoditiesExpandHotel wnkCommoditiesExpandHotel,Integer businessId , Integer commodityId,Integer inventory) {
        // 更新规格信息
        int i = this.wnkCommoditiesExpandHotelMapper.updateExpandInfo(wnkCommoditiesExpandHotel);
        if (i > 0 ){
            // 更新房态管理信息
            List<Map<String,Object>> list =  wnkBusinessTypeManagementHotelService.selectHotelManagementByBusinessIdAndCommodityId(businessId,commodityId);
            if (list.size() > 0 ){
                i = wnkBusinessTypeManagementHotelService.updateHotelManagementByBusinessIdAndCCommodityId(businessId,commodityId,inventory);
            }
        }
        return i;
    }


}
