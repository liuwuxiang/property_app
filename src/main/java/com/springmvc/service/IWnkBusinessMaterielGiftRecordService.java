package com.springmvc.service;

import com.springmvc.entity.WnkBusinessMaterielGiftRecord;

import java.util.List;
import java.util.Map;

public interface IWnkBusinessMaterielGiftRecordService {

    /**
     *
     * 功能描述: 管理员查询物料赠送记录-所有
     *
     * @param  map 条件查询
     * @return  查询结果
     * @author  杨新杰
     * @date    11:26 2019/1/5
     */
    List<Map<String,Object>> selectMaterielGiftRecordAll(Map<String, Object> map);

    /**
     *
     * 功能描述: 插入物料赠送纪录
     *
     * @param    wbmgr 物料赠送实体类
     * @return
     * @author  杨新杰
     * @date    14:38 2019/1/5
     */
    int insertMaterielGiftRecord(WnkBusinessMaterielGiftRecord wbmgr);

    /**
     *
     * 功能描述: 根据ID查询赠送记录
     *
     * @param    gift_id 物料赠送记录ID
     * @return
     * @author  杨新杰
     * @date    14:38 2019/1/5
     */
    WnkBusinessMaterielGiftRecord selectMaterielGiftRecordById(Integer gift_id);

    /**
     *
     * 功能描述: 更新物料赠送记录 -  撤销
     *
     * @param   wbmgr 物料赠送记录实体类
     * @return
     * @author  杨新杰
     * @date    15:34 2019/1/5
     */
    int updateMaterielGiftRecordByBack(WnkBusinessMaterielGiftRecord wbmgr);
}
