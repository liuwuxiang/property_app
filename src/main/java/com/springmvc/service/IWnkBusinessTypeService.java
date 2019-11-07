package com.springmvc.service;

import com.springmvc.entity.WnkBusinessType;

import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 万能卡分类后台管理业务层
 * @Author: 刘武祥
 * @Date: 2019/2/21 0021 10:48
 */
public interface IWnkBusinessTypeService {
    /**
     * 查询所有记录
     * @return
     */
    List<Map<Object,Object>> selectAllRecord();

    /**
     * 通过id查询记录
     * @param record_id
     * @return
     */
    WnkBusinessType selectById(Integer record_id);

    /**
     *
     * 功能描述: 后台查询所有记录
     *
     * @param:
     * @return: List<Map<Object,Object>>
     * @author: zhangfan
     * @date: 2018/11/14 3:33 AM
     */
    List<Map<Object,Object>> adminSelectAllRecord();

    /** 
     *
     * 功能描述: 新增万能卡商家分类信息
     *
     * @param wnkBusinessType 新增信息
     * @return:int
     * @author: 刘武祥
     * @date: 2018/12/26 0026 18:08
     */
    int addWnkBusinessInformationAction(WnkBusinessType wnkBusinessType);

    /**
     *
     * 功能描述: 更新万能卡商家分类信息
     *
     * @param wnkBusinessType 更新信息
     * @return:int
     * @author: zhangfan
     * @date: 2018/11/14 4:07 AM
     */
    int updateWnkTypeInformation(WnkBusinessType wnkBusinessType);

    /**
     *
     * 功能描述: 标记删除
     *
     * @param   id
     * @return: int
     * @author: 刘武祥
     * @date: 2018/12/30 0030 15:58
     */
    int deleteWnkBusinessTypeById(String id);

    /** 
     *
     * 功能描述: 根据条件查询商家分类管理
     *
     * @param   map 查询条件
     * @return: 返回商家分类管理
     * @author: 刘武祥
     * @date: 2019/1/18 0018 12:17
     */
    List<Map<Object,Object>> adminSearchWnkBusinessType(Map<String,Object> map);
}
