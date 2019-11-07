package com.springmvc.service;

import java.util.List;
import java.util.Map;

public interface IResidentialBuildingService {
    /**
     * 获取某个小区下的所有栋号
     * @param residential_id
     * @return
     */
    List<Map<Object,Object>> selectAllBuildingByResidentialId(Integer residential_id);
}
