package com.springmvc.dao;

import com.springmvc.entity.ExtensionMateriel;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 02:23
 * @Description:推广物料(用于供商家选择)Mapper
 */
public interface ExtensionMaterielMapper {
    /**
     *
     * 功能描述: 获取所有记录
     *
     * @param:
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/30 2:25 AM
     */
    List<Map<Object,Object>> selectAllRecord();

    /**
     *
     * 功能描述: 通过id查询物料
     *
     * @param: record_id
     * @return: ExtensionMateriel
     * @author: zhangfan
     * @date: 2018/10/30 5:59 AM
     */
    ExtensionMateriel selectById(Integer record_id);

    /**
     *
     * 功能描述: 查询万能卡物料信息
     *
     * @param:
     * @return:ExtensionMateriel
     * @author: zhangfan
     * @date: 2018/11/3 7:57 PM
     */
    ExtensionMateriel selectWnkInformation();

    /**
     *
     * 功能描述:后台查询物料信息
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/11/17 7:03 PM
     */
    List<Map<String,Object>> selectAdminWnkInformation();

    /**
     *
     * 功能描述: 通过物料id修改物料信息
     *
     * @param: extensionMateriel
     * @return:
     * @author: zhangfan
     * @date: 2018/11/17 7:54 PM
     */
    int updateMaterielById(ExtensionMateriel extensionMateriel);

    /**
     *
     * 功能描述: 通过分类id查询物料信息
     *
     * @param business_type_id 商家分类id
     * @return:
     * @author: zhangfan
     * @date: 2018/11/21 4:14 PM
     */
    List<Map<Object,Object>> selectMatensionByTypeId(Integer business_type_id);

    /**
     *
     * 功能描述: 根据条件查询物料管理信息
     *
     * @param   map 查询条件
     * @return: 返回物料管理信息
     * @author: 刘武祥
     * @date: 2019/1/18 0018 14:47
     */
    List<Map<Object,Object>> adminSearchExtensionMateriel(Map<String,Object> map);
}
