package com.springmvc.service.impl;

import com.springmvc.dao.MaterielBuyRecordMapper;
import com.springmvc.entity.MaterielBuyRecord;
import com.springmvc.service.IMaterielBuyRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/11/18 00:26
 * @Description:商家物料购买记录Service实现类
 */
@Service("/materielBuyRecordService")
public class MaterielBuyRecordService implements IMaterielBuyRecordService {
    @Resource
    private MaterielBuyRecordMapper materielBuyRecordMapper;

    @Override
    public List<Map<Object, Object>> selectRecordByBusinessIdAndMaterielId(Integer business_id, Integer materiel_id) {
        return this.materielBuyRecordMapper.selectRecordByBusinessIdAndMaterielId(business_id, materiel_id);
    }

    @Override
    public int inserRecord(MaterielBuyRecord materielBuyRecord) {
        return this.materielBuyRecordMapper.inserRecord(materielBuyRecord);
    }
}
