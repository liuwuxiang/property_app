package com.springmvc.dao;

import com.springmvc.entity.Residentials;

import java.util.List;
import java.util.Map;

public interface ResidentialsMapper {

    /**
     *
     * 功能描述: 获取某个城市下的所有小区
     *
     * @param   city_id
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/19 0019 14:30
     */
    List<Map<Object,Object>> selectResidentialsByCityId(Integer city_id);

    /**
     *
     * 功能描述: 通过id查询小区
     *
     * @param   residentials_id
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/19 0019 14:30
     */
    Residentials selectById(Integer residentials_id);

    /**
     *
     * 功能描述: 后台查询所有物业信息
     *
     * @param
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/19 0019 14:31
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     *
     * 功能描述: 添加物业
     *
     * @param   residentials
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/19 0019 14:31
     */
    int addProperty(Residentials residentials);

    /**
     *
     * 功能描述: 修改物业信息
     *
     * @param   residentials
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/19 0019 14:32
     */
    int updateResidentialsInformation(Residentials residentials);

    /**
     *
     * 功能描述: 根据条件查询物业中心信息
     *
     * @param   map 查询条件
     * @return: 物业中心信息
     * @author: 刘武祥
     * @date: 2019/1/11 0011 11:49
     */
    List<Map<String,Object>> adminSearchPropertyByConditions(Map<String,Object> map);
}
