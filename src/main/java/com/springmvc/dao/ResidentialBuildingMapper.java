package com.springmvc.dao;

import java.util.List;
import java.util.Map;

public interface ResidentialBuildingMapper {
    //获取某个小区下的所有栋号
    List<Map<Object,Object>> selectAllBuildingByResidentialId(Integer residential_id);
}
