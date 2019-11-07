package com.springmvc.service.impl;

import com.springmvc.dao.ResidentialUnitMapper;
import com.springmvc.service.IResidentialUnitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/residentialUnitService")
public class ResidentialUnitService implements IResidentialUnitService{
    @Resource
    private ResidentialUnitMapper residentialUnitMapper;

    @Override
    public List<Map<Object, Object>> selectAllUnitByBuildingId(Integer building_id) {
        return this.residentialUnitMapper.selectAllUnitByBuildingId(building_id);
    }
}
