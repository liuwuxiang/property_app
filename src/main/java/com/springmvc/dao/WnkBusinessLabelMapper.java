package com.springmvc.dao;

import com.springmvc.entity.WnkBusinessLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: 刘武祥
 * @Date: 2019/2/25 15:26
 * 
 */
public interface WnkBusinessLabelMapper {

    /**
     * 功能描述:查询商家服务内容或者特色内容
     *
     * @param type 0:服务内容 1:特色内容
     * @return 返回查询到的信息, 查询失败返回null
     * @auther 杨新杰
     * @date 2018/11/1 9:51
     */
    List<Map<String,Object>> getBusinessLabel(@Param("type") String type);

    /**
     * 功能描述:更具ID商家服务内容或者特色内容
     *
     * @param id 服务内容/特色内容ID
     * @return 返回查询到的信息, 查询失败返回null
     * @auther 杨新杰
     * @date 2018/11/1 9:51
     */
    Map<String,Object> selectLabelById(@Param("id") String id);

    /**
     * 功能描述:更具ID商家服务内容或者特色内容
     *
     * @param labelType 查询类型 0 - 服务内容 1-特色内容
     * @return 返回查询到的信息, 查询失败返回null
     * @auther 杨新杰
     * @date 2018/11/1 9:51
     */
    List<Map<String,Object>> selectLabelContentAll(@Param("labelType") Integer labelType);

    /**
     * 功能描述:根据ID获取特色/服务内容
     *
     * @param content_id   服务/特色内容ID
     * @param content_type 0-服务内容 1-特色内容
     * @return 返回查询到的信息, 查询失败返回null
     * @auther 杨新杰
     * @date 2018/11/1 9:51
     */
    Map<String,Object> selectContentInfoById(@Param("content_id") Integer content_id,@Param("content_type") Integer content_type);

    /**
     * 功能描述:更新特色/服务内容
     *
     * @param wnkBusinessLabel 特色/服务内容实体类
     * @return 返回查询到的信息, 查询失败返回null
     * @auther 杨新杰
     * @date 2018/11/1 9:51
     */
    int updateContentLabel(WnkBusinessLabel wnkBusinessLabel);

    /**
     * 功能描述:新增特色/服务内容
     *
     * @param wnkBusinessLabel 特色/服务内容实体类
     * @return 返回查询到的信息, 查询失败返回null
     * @auther 杨新杰
     * @date 2018/11/1 9:51
     */
    int insertContentLabel(WnkBusinessLabel wnkBusinessLabel);

    /**
     *
     * 功能描述: 根据条件查询特色标签管理
     *
     * @param   map 查询条件
     * @return: 返回特色标签管理信息
     * @auther: 刘武祥
     * @date: 2019/1/21 0021 14:44
     */
    List<Map<Object,Object>> adminSearchContentInfoByConditions(Map<String,Object> map);

    /**
     *
     * 功能描述: 根据条件查询服务标签管理
     *
     * @param   map 查询条件
     * @return: 返回特色标签管理信息
     * @auther: 刘武祥
     * @date: 2019/1/21 0021 14:44
     */
    List<Map<Object,Object>> adminSearchFContentInfoByConditions(Map<String,Object> map);
}
