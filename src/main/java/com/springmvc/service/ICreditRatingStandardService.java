package com.springmvc.service;

import com.springmvc.entity.CreditRatingStandard;

import java.util.List;
import java.util.Map;

public interface ICreditRatingStandardService {
    /**
     * 获取所有扣信标准
     * @return
     */
    List<Map<Object,Object>> selectAll();

    /**
     * 通过id查询记录
     * @param record_id
     * @return
     */
    CreditRatingStandard selectById(Integer record_id);

    /**
     * 更新评级标准信息
     * @param creditRatingStandard
     * @return
     */
    int updateInformation(CreditRatingStandard creditRatingStandard);

    /** 
     *
     * 功能描述: 根据扣信标准查询所有信用评级信息
     *
     * @param   map
     * @return: 
     * @author: 刘武祥
     * @date: 2019/1/10 0010 15:59
     */
    List<Map<String,Object>> adminSearchCreditRatingByConditions(Map<String,Object> map);
}
