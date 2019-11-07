package com.springmvc.service;

import com.springmvc.entity.WnkMaintain;

import java.util.List;
import java.util.Map;

/**
 *
 * 功能描述: 系统维护员管理服务层
 * @Author: 刘武祥
 * @Date: 2019/2/21 0021 15:35
 */
public interface IWnkMaintainService {
    /**
     *
     * 功能描述: 根据商家类别ID查询对应分类维护员信息.(已启用的维护员信息)
     *
     * @param  business_type_id 商家类别ID
     * @return
     * @author 杨新杰
     * @date   2018/11/14 14:15
     */
    WnkMaintain selectMaintainInfoByBusinessTypeId(Integer business_type_id);

    /**
     *
     * 功能描述: 查询所有维护员信息
     *
     * @return
     * @author 杨新杰
     * @date   2018/11/14 14:15
     */
    List<Map<String,Object>> selectMaintainInfoAll();

    /**
     *
     * 功能描述: 查询所有维护员信息
     *
     * @param maintain_id  维护员ID
     * @return
     * @author 杨新杰
     * @date   2018/11/14 14:15
     */
    Map<String,Object> selectMaintainInfoById(String maintain_id);

    /**
     *
     * 功能描述: 新增维护员
     *
     * @param wnkMaintain  维护员实体类
     * @return
     * @author 杨新杰
     * @date   2018/11/14 14:15
     */
    int insertMaintain(WnkMaintain wnkMaintain);

    /**
     *
     * 功能描述: 更新维护员
     *
     * @param wnkMaintain  维护员实体类
     * @return
     * @author 杨新杰
     * @date   2018/11/14 14:15
     */
    int updateMaintain(WnkMaintain wnkMaintain);

    /**
     *
     * 功能描述: 查询此分类ID是否有维护员
     *
     * @param business_type_id 商家分类ID
     * @return
     * @author 杨新杰
     * @date   2018/11/14 14:15
     */
    WnkMaintain selectMaintainInfoByBusinessTypeIdAll(Integer business_type_id);

    /**
     *
     * 功能描述: 根据条件查询系统维护员管理
     *
     * @param   map 查询条件
     * @return: 返回系统维护员管理
     * @author: 刘武祥
     * @date: 2019/1/18 0018 16:55
     */
    List<Map<Object,Object>> adminSearchMaintain(Map<String,Object> map);
}
