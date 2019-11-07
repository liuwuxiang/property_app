package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessTypeManagementHotelMapper;
import com.springmvc.entity.WnkBusinessTypeManagementHotel;
import com.springmvc.service.WnkBusinessTypeManagementHotelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class WnkBusinessTypeManagementHotelServiceImpl implements WnkBusinessTypeManagementHotelService {


    @Resource
    private WnkBusinessTypeManagementHotelMapper wnkBusinessTypeManagementHotelMapper;

    /**
     * 方法说明:新增房态管理实体类
     *
     * @param wnkBusinessTypeManagementHotel 房态管理实体类
     * @return 返回新增条目数
     * @author 杨新杰
     * @Date 2019/1/22
     **/
    @Override
    public int insertHotelManagement(WnkBusinessTypeManagementHotel wnkBusinessTypeManagementHotel) {
        return this.wnkBusinessTypeManagementHotelMapper.insertHotelManagement(wnkBusinessTypeManagementHotel);
    }

    /**
     * 方法说明:更新房态管理实体类
     *
     * @param wnkBusinessTypeManagementHotel 房态管理实体类
     * @return 返回新增条目数
     * @author 杨新杰
     * @Date 2019/1/22
     **/
    @Override
    public int updateHotelManagement(WnkBusinessTypeManagementHotel wnkBusinessTypeManagementHotel) {
        return this.wnkBusinessTypeManagementHotelMapper.updateHotelManagement(wnkBusinessTypeManagementHotel);
    }

    /**
     * 方法说明:根据商家ID 商品ID 和时间戳查询是否有对应的记录
     *
     * @param wnkBusinessTypeManagementHotel 房态管理实体类
     * @return 返回查询结果
     * @author 杨新杰
     * @Date 2019/1/22
     **/
    @Override
    public WnkBusinessTypeManagementHotel selectHotelManagement(WnkBusinessTypeManagementHotel wnkBusinessTypeManagementHotel) {
        return this.wnkBusinessTypeManagementHotelMapper.selectHotelManagement(wnkBusinessTypeManagementHotel);
    }

    /**
     * 方法说明: 根据商家ID和商品ID查询是否有房态管理记录
     *
     * @param businessId
     * @param commodityId
     * @return
     * @author 杨新杰
     * @Date 2019/2/14
     * @Param
     */
    @Override
    public List<Map<String, Object>> selectHotelManagementByBusinessIdAndCommodityId(Integer businessId, Integer commodityId) {
        return this.wnkBusinessTypeManagementHotelMapper.selectHotelManagementByBusinessIdAndCommodityId(businessId,commodityId);
    }

    /**
     * 方法说明: 根据商家ID和商品ID更新所有房态管理对应商品的数据
     *
     * @param businessId
     * @param commodityId
     * @return
     * @author 杨新杰
     * @Date 2019/2/14
     * @Param
     */
    @Override
    public int updateHotelManagementByBusinessIdAndCCommodityId(Integer businessId, Integer commodityId,Integer inventory) {
        return this.wnkBusinessTypeManagementHotelMapper.updateHotelManagementByBusinessIdAndCCommodityId(businessId,commodityId,inventory);
    }
}
