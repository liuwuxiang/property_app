package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessConsumptionIntegralDetailMapper;
import com.springmvc.entity.WnkBusinessConsumptionIntegralDetail;
import com.springmvc.service.IWnkBusinessConsumptionIntegralDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/28 22:10
 * @Description:万能卡商家消费积分服务实现类
 */
@Service("/wnkBusinessConsumptionIntegralDetailService")
public class WnkBusinessConsumptionIntegralDetailService implements IWnkBusinessConsumptionIntegralDetailService{
    @Resource
    private WnkBusinessConsumptionIntegralDetailMapper wnkBusinessConsumptionIntegralDetailMapper;

    @Override
    public List<Map<Object, Object>> selectRecordByBusinessId(Integer business_id, Integer transactions_type) {
        return this.wnkBusinessConsumptionIntegralDetailMapper.selectRecordByBusinessId(business_id, transactions_type);
    }

    @Override
    public int insertNewRecord(WnkBusinessConsumptionIntegralDetail wnkBusinessConsumptionIntegralDetail) {
        return this.wnkBusinessConsumptionIntegralDetailMapper.insertNewRecord(wnkBusinessConsumptionIntegralDetail);
    }
}
