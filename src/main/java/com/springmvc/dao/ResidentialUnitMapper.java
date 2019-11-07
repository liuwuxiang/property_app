package com.springmvc.dao;

import java.util.List;
import java.util.Map;

public interface ResidentialUnitMapper {
    //APP获取某个栋号下的所有单元号
    List<Map<Object,Object>> selectAllUnitByBuildingId(Integer building_id);
}
