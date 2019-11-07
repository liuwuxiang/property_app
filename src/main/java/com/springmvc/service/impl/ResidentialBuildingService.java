package com.springmvc.service.impl;

import com.springmvc.dao.ResidentialBuildingMapper;
import com.springmvc.service.IResidentialBuildingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/residentialBuildingService")
public class ResidentialBuildingService implements IResidentialBuildingService{
    @Resource
    private ResidentialBuildingMapper residentialBuildingMapper;

    @Override
    public List<Map<Object, Object>> selectAllBuildingByResidentialId(Integer residential_id) {
        return this.residentialBuildingMapper.selectAllBuildingByResidentialId(residential_id);
    }
}
