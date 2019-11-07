package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessRecommendUserRecordMapper;
import com.springmvc.entity.WnkBusinessRecommendUserRecord;
import com.springmvc.service.IWnkBusinessRecommendUserRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhangfan
 * @Date: 2018/10/29 04:31
 * @Description:商家推荐用户记录器服务实现类-用于记录万能卡商家推荐用户记录(记录商家推荐了多少个，每条记录满3状态变更为已兑换，依次循环)
 */
@Service("/wnkBusinessRecommendUserRecordService")
public class WnkBusinessRecommendUserRecordService implements IWnkBusinessRecommendUserRecordService {
    @Resource
    private WnkBusinessRecommendUserRecordMapper wnkBusinessRecommendUserRecordMapper;

    @Override
    public int insertRecord(Integer business_id) {
        return this.wnkBusinessRecommendUserRecordMapper.insertRecord(business_id);
    }

    @Override
    public WnkBusinessRecommendUserRecord selectNoExchangeByBusinessId(Integer business_id) {
        return this.wnkBusinessRecommendUserRecordMapper.selectNoExchangeByBusinessId(business_id);
    }

    @Override
    public int updateInformation(WnkBusinessRecommendUserRecord wnkBusinessRecommendUserRecord) {
        return this.wnkBusinessRecommendUserRecordMapper.updateInformation(wnkBusinessRecommendUserRecord);
    }

    @Override
    public int updateStateIsFinish(Integer record_id) {
        return this.wnkBusinessRecommendUserRecordMapper.updateStateIsFinish(record_id);
    }
}
