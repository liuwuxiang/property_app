package com.springmvc.service;

import com.springmvc.entity.WnkBusinessFitnessRecord;

import java.util.List;
import java.util.Map;

public interface IWnkBusinessFitnessRecordService {



    /**
     *
     * 功能描述: 查询用户进/出门状态
     *
     * @param   user_id      用户ID
     * @param   business_id  商家ID
     * @return
     * @author  杨新杰
     * @date    17:25 2018/12/29
     */
    Map<String,Object> selectUserGymCardStatusByUserId (Integer user_id, Integer business_id);

    /**
     *
     * 功能描述:  插入用户进/出门记录
     *
     * @param   w
     * @return
     * @author  杨新杰
     * @date    18:03 2018/12/29
     */
    int insertUserGymCardStatusByUserId(WnkBusinessFitnessRecord w);

    /**
     * 功能描述: 获取健身卡使用记录
     *
     * @param user_id     用户ID
     * @return:
     * @author: zhangfan
     * @date: 2018/11/5 2:13 AM
     */
    List<Map<String,Object>> selectFitnessCardDetailById(Integer user_id, Integer business_id);
}
