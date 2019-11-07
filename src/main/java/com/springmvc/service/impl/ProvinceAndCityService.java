package com.springmvc.service.impl;

import com.springmvc.dao.ProvinceAndCityMapper;
import com.springmvc.entity.ProvinceAndCity;
import com.springmvc.service.IProvinceAndCityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/provinceAndCityService")
public class ProvinceAndCityService implements IProvinceAndCityService{
    @Resource
    private ProvinceAndCityMapper provinceAndCityMapper;

    @Override
    public List<Map<Object, Object>> selectAllProvince() {
        return this.provinceAndCityMapper.selectAllProvince();
    }

    @Override
    public List<Map<Object, Object>> selectProvinceAllCity(Integer province_id) {
        return this.provinceAndCityMapper.selectProvinceAllCity(province_id);
    }

    @Override
    public ProvinceAndCity selectById(Integer record_id) {
        return this.provinceAndCityMapper.selectById(record_id);
    }

    @Override
    public int addProvince(ProvinceAndCity provinceAndCity) {
        return this.provinceAndCityMapper.addProvince(provinceAndCity);
    }

    @Override
    public int updateProvince(ProvinceAndCity provinceAndCity) {
        return this.provinceAndCityMapper.updateProvince(provinceAndCity);
    }

    @Override
    public ProvinceAndCity selectByName(String province_name) {
        return this.provinceAndCityMapper.selectByName(province_name);
    }

    @Override
    public ProvinceAndCity selectCityByNameAndProvinceId(String city_name, Integer province_id) {
        return this.provinceAndCityMapper.selectCityByNameAndProvinceId(city_name, province_id);
    }

    @Override
    public int updateCity(ProvinceAndCity provinceAndCity) {
        return this.provinceAndCityMapper.updateCity(provinceAndCity);
    }

    @Override
    public int addCity(ProvinceAndCity provinceAndCity) {
        return this.provinceAndCityMapper.addCity(provinceAndCity);
    }
    /**
     *
     * 功能描述: 根据条件查询所有省份
     *
     * @param   map 查询条件
     * @return: 返回查询省份
     * @author: 刘武祥
     * @date: 2019/1/10 0010 17:19
     */
    @Override
    public List<Map<String, Object>> adminSearchProvinceAndCityByConditions(Map<String, Object> map) {
        return this.provinceAndCityMapper.adminSearchProvinceAndCityByConditions(map);
    }
}
