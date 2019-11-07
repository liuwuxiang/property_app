package com.springmvc.dao;

import com.springmvc.entity.CreditRatingStandard;

import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 信用评级
 * @Author: 刘武祥
 * @Date: 2019/2/18 0018 10:30
 */
public interface CreditRatingStandardMapper {

    /**
     *
     * 功能描述: 获取所有扣信标准
     *
     * @param
     * @return:
     * @author: 刘武祥
     * @date: 2019/2/18 0018 10:29
     */
    List<Map<Object,Object>> selectAll();

    /**
     *
     * 功能描述: 通过id查询记录
     *
     * @param   record_id
     * @return: 返回查询记录
     * @author: 刘武祥
     * @date: 2019/2/18 0018 10:29
     */
    CreditRatingStandard selectById(Integer record_id);

    /**
     *
     * 功能描述: 更新评级标准信息
     *
     * @param   creditRatingStandard
     * @return: 返回更新评级标准信息
     * @author: 刘武祥
     * @date: 2019/2/18 0018 10:28
     */
    int updateInformation(CreditRatingStandard creditRatingStandard);

    /** 
     *
     * 功能描述: 根据扣信标准查询所有信用评级信息
     *
     * @param   map 查询条件
     * @return: 所有信用评级信息
     * @author: 刘武祥
     * @date: 2019/1/10 0010 16:01
     */
    List<Map<String,Object>> adminSearchCreditRatingByConditions(Map<String,Object> map);
}
