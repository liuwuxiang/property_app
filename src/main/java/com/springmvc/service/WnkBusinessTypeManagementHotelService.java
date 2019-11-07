package com.springmvc.service;


import com.springmvc.entity.WnkBusinessTypeManagementHotel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface WnkBusinessTypeManagementHotelService {

    /**
     *
     * 方法说明:新增房态管理实体类
     *
     * @author 杨新杰
     * @Date  2019/1/22
     * @param wnkBusinessTypeManagementHotel 房态管理实体类
     * @return 返回新增条目数
     **/
    int insertHotelManagement(WnkBusinessTypeManagementHotel wnkBusinessTypeManagementHotel);

    /**
     *
     * 方法说明:更新房态管理实体类
     *
     * @author 杨新杰
     * @Date  2019/1/22
     * @param wnkBusinessTypeManagementHotel 房态管理实体类
     * @return 返回新增条目数
     **/
    int updateHotelManagement(WnkBusinessTypeManagementHotel wnkBusinessTypeManagementHotel);

    /**
     *
     * 方法说明:根据商家ID 商品ID 和时间戳查询是否有对应的记录
     *
     * @author 杨新杰
     * @Date  2019/1/22
     * @param wnkBusinessTypeManagementHotel 房态管理实体类
     * @return 返回查询结果
     **/
    WnkBusinessTypeManagementHotel selectHotelManagement(WnkBusinessTypeManagementHotel wnkBusinessTypeManagementHotel);

    /**
     *
     * 方法说明: 根据商家ID和商品ID查询是否有房态管理记录
     *
     * @author 杨新杰
     * @Date  2019/2/14
     * @Param
     * @return
     **/
    List<Map<String,Object>> selectHotelManagementByBusinessIdAndCommodityId(Integer businessId, Integer commodityId);

    /**
     *
     * 方法说明: 根据商家ID和商品ID更新所有房态管理对应商品的数据
     *
     * @author 杨新杰
     * @Date  2019/2/14
     * @Param
     * @return
     **/
    int updateHotelManagementByBusinessIdAndCCommodityId(Integer businessId, Integer commodityId,Integer inventory);
}
