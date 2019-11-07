package com.springmvc.dao;


import com.springmvc.entity.WnkBusinessTypeManagementHotel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WnkBusinessTypeManagementHotelMapper {

    /**
     * 方法说明:新增房态管理实体类
     *
     * @param wnkBusinessTypeManagementHotel 房态管理实体类
     * @return 返回新增条目数
     * @author 杨新杰
     * @Date 2019/1/22
     **/
    int insertHotelManagement(WnkBusinessTypeManagementHotel wnkBusinessTypeManagementHotel);

    /**
     * 方法说明:更新房态管理实体类
     *
     * @param wnkBusinessTypeManagementHotel 房态管理实体类
     * @return 返回新增条目数
     * @author 杨新杰
     * @Date 2019/1/22
     **/
    int updateHotelManagement(WnkBusinessTypeManagementHotel wnkBusinessTypeManagementHotel);

    /**
     * 方法说明:根据商家ID 商品ID 和时间戳查询是否有对应的记录
     *
     * @param wnkBusinessTypeManagementHotel 房态管理实体类
     * @return 返回查询结果
     * @author 杨新杰
     * @Date 2019/1/22
     **/
    WnkBusinessTypeManagementHotel selectHotelManagement(WnkBusinessTypeManagementHotel wnkBusinessTypeManagementHotel);

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
    List<Map<String,Object>> selectHotelManagementByBusinessIdAndCommodityId(@Param("businessId") Integer businessId,@Param("commodityId") Integer commodityId);


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
    int updateHotelManagementByBusinessIdAndCCommodityId(@Param("businessId") Integer businessId,@Param("commodityId") Integer commodityId,@Param("inventory") Integer inventory);
}
