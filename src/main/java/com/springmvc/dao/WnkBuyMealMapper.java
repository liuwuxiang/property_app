package com.springmvc.dao;

import com.springmvc.entity.WnkBuyMeal;

import java.util.List;
import java.util.Map;

public interface WnkBuyMealMapper {
    //获取所有记录
    List<Map<Object,Object>> selectAllRecord();
    //通过id查询记录
    WnkBuyMeal selectById(Integer record_id);
    //获取某个类型的套餐(0-普通，1-学生，2-尊贵)
    List<Map<Object,Object>> selectByType(Integer type);
}
