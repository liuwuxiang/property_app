package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessMaterielGiftRecordMapper;
import com.springmvc.entity.WnkBusinessMaterielGiftRecord;
import com.springmvc.service.IWnkBusinessMaterielGiftRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author 杨新杰
 * @date 2019/1/5 11:20
 */
@Service
public class WnkBusinessMaterielGiftRecordService implements IWnkBusinessMaterielGiftRecordService {

    @Resource
    private WnkBusinessMaterielGiftRecordMapper wnkBusinessMaterielGiftRecordMapper;

    /**
     * 功能描述: 管理员查询物料赠送记录-所有
     *
     * @param map 条件查询
     * @return 查询结果
     * @author 杨新杰
     * @date 11:26 2019/1/5
     */
    @Override
    public List<Map<String, Object>> selectMaterielGiftRecordAll(Map<String, Object> map) {
        return this.wnkBusinessMaterielGiftRecordMapper.selectMaterielGiftRecordAll(map);
    }

    /**
     * 功能描述: 插入物料赠送纪录
     *
     * @param wbmgr 物料赠送实体类
     * @return
     * @author 杨新杰
     * @date 14:38 2019/1/5
     */
    @Override
    public int insertMaterielGiftRecord(WnkBusinessMaterielGiftRecord wbmgr) {
        return this.wnkBusinessMaterielGiftRecordMapper.insertMaterielGiftRecord(wbmgr);
    }

    /**
     * 功能描述: 根据ID查询赠送记录
     *
     * @param gift_id 物料赠送记录ID
     * @return
     * @author 杨新杰
     * @date 14:38 2019/1/5
     */
    @Override
    public WnkBusinessMaterielGiftRecord selectMaterielGiftRecordById(Integer gift_id) {
        return this.wnkBusinessMaterielGiftRecordMapper.selectMaterielGiftRecordById(gift_id);
    }

    /**
     * 功能描述: 更新物料赠送记录 -  撤销
     *
     * @param wbmgr 物料赠送记录实体类
     * @return
     * @author 杨新杰
     * @date 15:34 2019/1/5
     */
    @Override
    public int updateMaterielGiftRecordByBack(WnkBusinessMaterielGiftRecord wbmgr) {
        return this.wnkBusinessMaterielGiftRecordMapper.updateMaterielGiftRecordByBack(wbmgr);
    }
}
