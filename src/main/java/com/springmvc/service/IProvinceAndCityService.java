package com.springmvc.service;

import com.springmvc.entity.ProvinceAndCity;

import java.util.List;
import java.util.Map;

public interface IProvinceAndCityService {
    /**
     * App获取所有省份
     * @return
     */
    List<Map<Object,Object>> selectAllProvince();

    /**
     * APP通过省份id获取某个省份下的所有城市
     * @param province_id
     * @return
     */
    List<Map<Object,Object>> selectProvinceAllCity(Integer province_id);

    /**
     * 通过id查询记录
     * @param record_id
     * @return
     */
    ProvinceAndCity selectById(Integer record_id);

    /**
     * 新增省份
     * @param provinceAndCity
     * @return
     */
    int addProvince(ProvinceAndCity provinceAndCity);

    /**
     * 修改省份信息
     * @param provinceAndCity
     * @return
     */
    int updateProvince(ProvinceAndCity provinceAndCity);

    /**
     * 通过名称查询省份
     * @param province_name
     * @return
     */
    ProvinceAndCity selectByName(String province_name);

    /**
     * 通过名称及省份id查询城市
     * @param city_name
     * @param province_id
     * @return
     */
    ProvinceAndCity selectCityByNameAndProvinceId(String city_name,Integer province_id);

    /**
     * 通过id修改城市名称
     * @param provinceAndCity
     * @return
     */
    int updateCity(ProvinceAndCity provinceAndCity);

    /**
     * 新增城市
     * @param provinceAndCity
     * @return
     */
    int addCity(ProvinceAndCity provinceAndCity);

    /**
     *
     * 功能描述: 根据条件查询所有省份
     *
     * @param   map
     * @return:
     * @author: 刘武祥
     * @date: 2019/1/10 0010 17:18
     */
    List<Map<String,Object>> adminSearchProvinceAndCityByConditions(Map<String,Object> map);
}
