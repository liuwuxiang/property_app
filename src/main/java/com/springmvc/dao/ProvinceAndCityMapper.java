package com.springmvc.dao;

import com.springmvc.entity.ProvinceAndCity;

import java.util.List;
import java.util.Map;

public interface ProvinceAndCityMapper {

    /**
     *
     * 功能描述: App获取所有省份
     *
     * @param
     * @return: App获取所有省份
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:27
     */
    List<Map<Object,Object>> selectAllProvince();

    /** 
     *
     * 功能描述: APP通过省份id获取某个省份下的所有城市
     *
     * @param   province_id
     * @return: APP通过省份id获取某个省份下的所有城市
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:26
     */
    List<Map<Object,Object>> selectProvinceAllCity(Integer province_id);

    /** 
     *
     * 功能描述: 通过id查询记录
     *
     * @param   record_id
     * @return: 查询记录
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:25
     */
    ProvinceAndCity selectById(Integer record_id);

    /**
     *
     * 功能描述: 新增省份
     *
     * @param   provinceAndCity
     * @return: 返回新增省份
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:25
     */
    int addProvince(ProvinceAndCity provinceAndCity);

    /** 
     *
     * 功能描述: 修改省份信息
     *
     * @param   provinceAndCity
     * @return: 返回修改省份信息
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:24
     */
    int updateProvince(ProvinceAndCity provinceAndCity);

    /** 
     *
     * 功能描述: 通过名称查询省份
     *
     * @param   province_name
     * @return: 返回查询省份
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:23
     */
    ProvinceAndCity selectByName(String province_name);

    /** 
     *
     * 功能描述: 通过名称及省份id查询城市
     *
     * @param   city_name
     * @param   province_id
     * @return: 查询城市
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:22
     */
    ProvinceAndCity selectCityByNameAndProvinceId(String city_name,Integer province_id);
    
    /** 
     *
     * 功能描述: 通过id修改城市名称
     *
     * @param   
     * @return: 
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:22
     */
    int updateCity(ProvinceAndCity provinceAndCity);
    
    /** 
     *
     * 功能描述: 新增城市
     *
     * @param   
     * @return: 
     * @author: 刘武祥
     * @date: 2019/2/12 0012 12:22
     */
    int addCity(ProvinceAndCity provinceAndCity);

    /** 
     *
     * 功能描述: 根据条件查询所有省份
     *
     * @param   map
     * @return: 
     * @author: 刘武祥
     * @date: 2019/1/10 0010 17:19
     */
    List<Map<String,Object>> adminSearchProvinceAndCityByConditions(Map<String,Object> map);
}
