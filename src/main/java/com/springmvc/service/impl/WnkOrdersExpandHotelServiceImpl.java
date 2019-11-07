package com.springmvc.service.impl;

import com.springmvc.dao.WnkOrdersExpandHotelMapper;
import com.springmvc.entity.WnkOrdersExpandHotel;
import com.springmvc.service.WnkOrdersExpandHotelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WnkOrdersExpandHotelServiceImpl implements WnkOrdersExpandHotelService {

    @Resource
    private WnkOrdersExpandHotelMapper wnkOrdersExpandHotelMapper;

    /**
     * 方法说明:新增扩展信息
     *
     * @param wnkOrdersExpandHotel 万能卡酒店商家订单扩展信息实体类
     * @return 新增结果
     * @author 杨新杰
     * @Date 2019/1/24
     **/
    @Override
    public int insertInfo(WnkOrdersExpandHotel wnkOrdersExpandHotel) {
        return this.wnkOrdersExpandHotelMapper.insertInfo(wnkOrdersExpandHotel);
    }

    /**
     * 方法说明:更新扩展信息
     *
     * @param wnkOrdersExpandHotel 万能卡酒店商家订单扩展信息实体类
     * @return 更新结果
     * @author 杨新杰
     * @Date 2019/1/24
     **/
    @Override
    public int updateInfo(WnkOrdersExpandHotel wnkOrdersExpandHotel) {
        return this.wnkOrdersExpandHotelMapper.updateInfo(wnkOrdersExpandHotel);
    }

    /**
     * 方法说明:根据订单ID查询扩展信息
     *
     * @param orederId
     * @return 查询结果
     * @author 杨新杰
     * @Date 2019/1/24
     **/
    @Override
    public WnkOrdersExpandHotel selectExpandInfoByOrderId(Integer orederId) {
        return this.wnkOrdersExpandHotelMapper.selectExpandInfoByOrderId(orederId);
    }
}
