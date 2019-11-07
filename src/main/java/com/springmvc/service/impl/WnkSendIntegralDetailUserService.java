package com.springmvc.service.impl;

import com.springmvc.dao.WnkSendIntegralDetailUserMapper;
import com.springmvc.service.IWnkSendIntegralDetailUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/11/2 17:07
 * @Description:用户赠送积分余额明细服务实现类
 */
@Service("/wnkSendIntegralDetailUserService")
public class WnkSendIntegralDetailUserService implements IWnkSendIntegralDetailUserService {
    @Resource
    private WnkSendIntegralDetailUserMapper wnkSendIntegralDetailUserMapper;

    /**
     *
     * 功能描述: 新增商家积分商城收入支出记录
     *
     * @param  name         收入名称
     * @param  business_id  商家ID
     * @param  user_id      用户ID
     * @param  income_amount        交易积分数量
     * @param  type         0-收入 1-支出
     * @return 成功返回1  失败返回0
     * @author 张凡
     * @date   2018/10/15 14:45
     */
    @Override
    public int addIntegralRecord(String name, Double income_amount, Integer user_id, Integer business_id, int type) {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("income_amount",income_amount);
        map.put("user_id",user_id);
        map.put("business_id",business_id);
        map.put("type",type);
        return this.wnkSendIntegralDetailUserMapper.addIntegralRecord(map);
    }

    @Override
    public List<Map<String, Object>> getIntegralCountById(Integer business_id, Integer user_id) {
        return this.wnkSendIntegralDetailUserMapper.getIntegralCountById(business_id, user_id);
    }

    @Override
    public List<Map<String, Object>> getIntegralCountByIdAndType(Integer business_id, Integer user_id, Integer type) {
        return this.wnkSendIntegralDetailUserMapper.getIntegralCountByIdAndType(business_id, user_id, type);
    }
}
