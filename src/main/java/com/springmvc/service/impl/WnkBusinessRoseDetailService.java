package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessRoseDetailMapper;
import com.springmvc.entity.WnkBusinessRoseDetail;
import com.springmvc.service.IWnkBusinessRoseDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/28 23:49
 * @Description:万能卡商家玫瑰明细服务实现类
 */
@Service("/wnkBusinessRoseDetailService")
public class WnkBusinessRoseDetailService implements IWnkBusinessRoseDetailService{
    @Resource
    private WnkBusinessRoseDetailMapper wnkBusinessRoseDetailMapper;

    @Override
    public List<Map<Object, Object>> selectRecordByBusinessId(Integer business_id, Integer transactions_type) {
        return this.wnkBusinessRoseDetailMapper.selectRecordByBusinessId(business_id, transactions_type);
    }

    @Override
    public int insertNewRecord(WnkBusinessRoseDetail wnkBusinessRoseDetail) {
        return this.wnkBusinessRoseDetailMapper.insertNewRecord(wnkBusinessRoseDetail);
    }
}
