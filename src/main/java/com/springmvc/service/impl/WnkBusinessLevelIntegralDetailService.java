package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessLevelIntegralDetailMapper;
import com.springmvc.entity.WnkBusinessLevelIntegralDetail;
import com.springmvc.service.IWnkBusinessLevelIntegralDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/28 23:22
 * @Description:万能卡商家等级积分明细服务实现类
 */
@Service("/wnkBusinessLevelIntegralDetailService")
public class WnkBusinessLevelIntegralDetailService implements IWnkBusinessLevelIntegralDetailService{
    @Resource
    private WnkBusinessLevelIntegralDetailMapper wnkBusinessLevelIntegralDetailMapper;

    @Override
    public List<Map<Object, Object>> selectRecordByBusinessId(Integer business_id,Integer transactions_type) {
        return this.wnkBusinessLevelIntegralDetailMapper.selectRecordByBusinessId(business_id,transactions_type);
    }

    @Override
    public int insertNewRecord(WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail) {
        if (wnkBusinessLevelIntegralDetail.getTransactions_type() != 1 && wnkBusinessLevelIntegralDetail.getTransactions_type() != 2){
            wnkBusinessLevelIntegralDetail.setUser_id(-1);
        }
        return this.wnkBusinessLevelIntegralDetailMapper.insertNewRecord(wnkBusinessLevelIntegralDetail);
    }

    @Override
    public int insertSendNewRecord(WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail) {
        return this.wnkBusinessLevelIntegralDetailMapper.insertSendNewRecord(wnkBusinessLevelIntegralDetail);
    }

    /**
     *
     * 功能描述: 插入用户消费扣积分记录
     *
     * @param wnkBusinessLevelIntegralDetail 插入信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/14 12:27 AM
     */
    @Override
    public int insertNewRecordUserXF(WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail) {
        return this.wnkBusinessLevelIntegralDetailMapper.insertNewRecordUserXF(wnkBusinessLevelIntegralDetail);
    }
}
