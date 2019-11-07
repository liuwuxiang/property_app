package com.springmvc.service.impl;

import com.springmvc.dao.WnkIntegralIncomeMapper;
import com.springmvc.service.IWnkIntegralIncomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨新杰
 * @Date 2018/10/15 14:42
 */
@Service("/wnkIntegralIncomeService")
public class WnkIntegralIncomeService implements IWnkIntegralIncomeService {

    @Resource
    private WnkIntegralIncomeMapper wnkIntegralIncomeMapper;
    /**
     *
     * 功能描述: 新增商家积分商城收入支出记录
     *
     * @param  name         收入名称
     * @param  business_id  商家ID
     * @param  user_id      用户ID
     * @param  price        收入数量
     * @param  type         0-收入 1-支出
     * @return 成功返回1  失败返回0
     * @author 杨新杰
     * @date   2018/10/15 14:45
     */
    @Override
    public int addIntegralRecord(String name, Double price, Integer user_id, Integer business_id, int type) {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("price",price);
        map.put("user_id",user_id);
        map.put("business_id",business_id);
        map.put("type",type);
        return wnkIntegralIncomeMapper.addIntegralRecord(map);
    }

    /**
     *
     * 功能描述:  获取用户在对应商家的消费明细
     *
     * @param  business_id 商家ID
     * @param  user_id     用户ID
     * @return
     * @author 杨新杰
     * @date   2018/10/15 15:10
     */
    @Override
    public List<Map<String, Object>> getIntegralCountById(Integer business_id, Integer user_id) {
        return this.wnkIntegralIncomeMapper.getIntegralCountById(business_id,user_id);
    }

    /**
     *
     * 功能描述:获取用户在商家处的积分明细(区分收入与支出)
     *
     * @param business_id 商家ID
     * @param user_id 用户ID
     * @param income_type 收支类型(0-收入 1-支出)
     * @return:
     * @author: zhangfan
     * @date: 2018/10/31 4:38 AM
     */
    @Override
    public List<Map<String, Object>> getIntegralCountByIdAndType(Integer business_id, Integer user_id, Integer income_type) {
        return this.wnkIntegralIncomeMapper.getIntegralCountByIdAndType(business_id, user_id, income_type);
    }
}
