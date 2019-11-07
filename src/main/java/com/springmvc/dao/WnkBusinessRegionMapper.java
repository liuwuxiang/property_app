package com.springmvc.dao;

import com.springmvc.entity.WnkBusinessRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WnkBusinessRegionMapper {

    /**
     * 功能描述: 获取所有区域
     *
     * @return
     * @author 杨新杰
     * @date 15:47 2018/12/30
     */
    List<Map<String,Object>> selectWnkBusinessRegionAll();

    /**
     * 功能描述: 新增区域
     *
     * @param wnkBusinessRegion@return
     * @author 杨新杰
     * @date 15:47 2018/12/30
     */
    int insertWnkBusinessRegion(WnkBusinessRegion wnkBusinessRegion);

    /**
     * 功能描述: 更新区域
     *
     * @param wnkBusinessRegion@return
     * @author 杨新杰
     * @date 15:47 2018/12/30
     */
    int updateWnkBusinessRegion(WnkBusinessRegion wnkBusinessRegion);

    /**
     * 功能描述: 删除区域
     *
     * @param id@return
     * @author 杨新杰
     * @date 15:47 2018/12/30
     */
    int deleteWnkBusinessRegion(@Param("id") Integer id);

    /**
     *
     * 功能描述: 根据条件查询商户区域管理
     *
     * @param   map  查询条件
     * @return: 返回查询到的商户区域管理信息
     * @author: 刘武祥
     * @date: 2019/1/21 0021 11:07
     */
    List<Map<Object,Object>> adminSearchWnkBusinessRegionInfoByConditions(Map<String,Object> map);
}
