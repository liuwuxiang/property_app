package com.springmvc.service;

import com.springmvc.entity.WnkOrdersExpandHotel;
import org.springframework.stereotype.Service;


public interface WnkOrdersExpandHotelService {

    /**
     *
     * 方法说明:新增扩展信息
     *
     * @author 杨新杰
     * @Date  2019/1/24
     * @param wnkOrdersExpandHotel 万能卡酒店商家订单扩展信息实体类
     * @return 新增结果
     **/
    int insertInfo(WnkOrdersExpandHotel wnkOrdersExpandHotel);

    /**
     *
     * 方法说明:更新扩展信息
     *
     * @author 杨新杰
     * @Date  2019/1/24
     * @param wnkOrdersExpandHotel 万能卡酒店商家订单扩展信息实体类
     * @return 更新结果
     **/
    int updateInfo(WnkOrdersExpandHotel wnkOrdersExpandHotel);

    /**
     *
     * 方法说明:根据订单ID查询扩展信息
     *
     * @author 杨新杰
     * @Date  2019/1/24
     * @param wnkOrdersExpandHotel 万能卡酒店商家订单扩展信息实体类
     * @return 查询结果
     **/
    WnkOrdersExpandHotel selectExpandInfoByOrderId(Integer orederId);

}
