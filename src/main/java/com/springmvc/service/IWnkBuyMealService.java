package com.springmvc.service;

import com.springmvc.entity.WnkBuyMeal;

import java.util.List;
import java.util.Map;

public interface IWnkBuyMealService {
    /**
     * 获取所有记录
     * @return
     */
    List<Map<Object,Object>> selectAllRecord();

    /**
     * 通过id查询记录
     * @param record_id
     * @return
     */
    WnkBuyMeal selectById(Integer record_id);

    /**
     * 获取某个类型的套餐(0-普通，1-学生，2-尊贵)
     * @param type 0-普通，1-学生，2-尊贵
     * @return
     */
    List<Map<Object,Object>> selectByType(Integer type);

}
