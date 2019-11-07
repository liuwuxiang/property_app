package com.springmvc.dao;

import com.springmvc.entity.ResidentialHousesNumber;

import java.util.List;
import java.util.Map;

public interface ResidentialHousesNumberMapper {
    //APP获取某个单元号下的所有的房间
    List<Map<Object,Object>> selectAllHouseByUnitId(Integer unit_id);
    //通过房间号id查询房间
    ResidentialHousesNumber selectById(Integer house_id);
}
