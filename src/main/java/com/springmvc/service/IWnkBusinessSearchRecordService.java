package com.springmvc.service;

import com.springmvc.entity.WnkBusinessSearchRecord;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/17 17:09
 * @Description:商家搜索记录服务接口类，用于提取热门搜索商家
 */
public interface IWnkBusinessSearchRecordService {
    /**
     *
     * 功能描述: 通过商家ID查询记录
     *
     * @param business_id 商家ID
     * @return: WnkBusinessSearchRecord
     * @author: zhangfan
     * @date: 2018/12/17 5:06 PM
     */
    WnkBusinessSearchRecord selectByBusinessId(Integer business_id);

    /**
     *
     * 功能描述: 获取搜索次数最大的十条记录
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 5:08 PM
     */
    List<Map<Object,Object>> selectSearchMaxTenRecord();

    /**
     *
     * 功能描述: 插入记录
     *
     * @param search_business_name 搜索的商家名字
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 5:08 PM
     */
    int insertRecord(String search_business_name);

    /**
     *
     * 功能描述: 更新搜索次数
     *
     * @param search_number 搜索次数
     * @param business_id 商家ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 5:08 PM
     */
    int updateSearchNumber(Integer search_number,Integer business_id);
}
