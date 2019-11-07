package com.springmvc.service.impl;

import com.springmvc.dao.ResidentialHousesNumberMapper;
import com.springmvc.entity.ResidentialHousesNumber;
import com.springmvc.service.IResidentialHousesNumberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/residentialHousesNumberService")
public class ResidentialHousesNumberService implements IResidentialHousesNumberService{
    @Resource
    private ResidentialHousesNumberMapper residentialHousesNumberMapper;

    @Override
    public List<Map<Object, Object>> selectAllHouseByUnitId(Integer unit_id) {
        return this.residentialHousesNumberMapper.selectAllHouseByUnitId(unit_id);
    }

    @Override
    public ResidentialHousesNumber selectById(Integer house_id) {
        return this.residentialHousesNumberMapper.selectById(house_id);
    }
}
