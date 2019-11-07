package com.springmvc.service;

import java.util.List;
import java.util.Map;

public interface IResidentialUnitService {
    /**
     * APP获取某个栋号下的所有单元号
     * @param building_id
     * @return
     */
    List<Map<Object,Object>> selectAllUnitByBuildingId(Integer building_id);
}
