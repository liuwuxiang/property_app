package com.springmvc.service;

import com.springmvc.entity.Residentials;

import java.util.List;
import java.util.Map;

public interface IResidentialsService {
    /**
     * 获取某个城市下的所有小区
     * @param city_id
     * @return
     */
    List<Map<Object,Object>> selectResidentialsByCityId(Integer city_id);

    /**
     * 通过id查询小区
     * @param residentials_id
     * @return
     */
    Residentials selectById(Integer residentials_id);

    /**
     * 后台查询所有物业信息
     * @return
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     * 添加物业
     * @param residentials
     * @return
     */
    int addProperty(Residentials residentials);

    /**
     * 修改物业信息
     * @param residentials
     * @return
     */
    int updateResidentialsInformation(Residentials residentials);

    /**
     *
     * 功能描述: 根据条件查询物业中心信息
     *
     * @param   map
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/11 0011 11:47
     */
    List<Map<String,Object>> adminSearchPropertyByConditions(Map<String,Object> map);
}
