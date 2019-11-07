package com.springmvc.service;

import com.springmvc.entity.WnkBusinessLevel;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/27 19:39
 * @Description:
 */
public interface IWnkBusinessLevelService {
    /**
     *
     * 功能描述: 通过商家id查询记录
     *
     * @param: record_id
     * @return: WnkBusinessLevel
     * @author: zhangfan
     * @date: 2018/10/27 7:38 PM
     */
    WnkBusinessLevel selectById(Integer record_id);
    /**
     *
     * 功能描述: 通过商家id查询默认等级
     *
     * @param:
     * @return: WnkBusinessLevel
     * @author: zhangfan
     * @date: 2018/10/27 7:38 PM
     */
    WnkBusinessLevel selectDefaultLevel();
    /**
     *
     * 功能描述: 通过商家id查询默认等级
     *
     * @param:
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/29 1:45 AM
     */
    List<Map<Object,Object>> selectNoDefaultLevel();

    /**
     *
     * 功能描述: 获取所有商家等级
     *
     * @param
     * @return:
     * @author: 杨新杰
     * @date: 16:50 2018/11/30
     */
    List<Map<String,Object>> selectAll();

}
