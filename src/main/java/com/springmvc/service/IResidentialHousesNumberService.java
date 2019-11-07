package com.springmvc.service;

import com.springmvc.entity.ResidentialHousesNumber;

import java.util.List;
import java.util.Map;

public interface IResidentialHousesNumberService {
    /**
     * APP获取某个单元号下的所有的房间
     * @param unit_id
     * @return
     */
    List<Map<Object,Object>> selectAllHouseByUnitId(Integer unit_id);

    /**
     * 通过房间号id查询房间
     * @param house_id
     * @return
     */
    ResidentialHousesNumber selectById(Integer house_id);
}
