package com.springmvc.dao;

import com.springmvc.entity.WnkOrdersExpandHotel;
import org.apache.ibatis.annotations.Param;

public interface WnkOrdersExpandHotelMapper {

    /**
     * 方法说明:新增扩展信息
     *
     * @param wnkOrdersExpandHotel 万能卡酒店商家订单扩展信息实体类
     * @return 新增结果
     * @author 杨新杰
     * @Date 2019/1/24
     **/
    int insertInfo(WnkOrdersExpandHotel wnkOrdersExpandHotel);

    /**
     * 方法说明:更新扩展信息
     *
     * @param wnkOrdersExpandHotel 万能卡酒店商家订单扩展信息实体类
     * @return 更新结果
     * @author 杨新杰
     * @Date 2019/1/24
     **/
    int updateInfo(WnkOrdersExpandHotel wnkOrdersExpandHotel);

    /**
     * 方法说明:根据订单ID查询扩展信息
     *
     * @param orederId
     * @return 查询结果
     * @author 杨新杰
     * @Date 2019/1/24
     **/
    WnkOrdersExpandHotel selectExpandInfoByOrderId(@Param("orederId") Integer orederId);
}
