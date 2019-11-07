package com.springmvc.service.impl;

import com.springmvc.dao.WnkCommodityTypesMapper;
import com.springmvc.entity.WnkCommodityTypes;
import com.springmvc.service.IWnkCommodityTypesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/wnkCommodityTypesService")
public class WnkCommodityTypesService implements IWnkCommodityTypesService{
    @Resource
    private WnkCommodityTypesMapper wnkCommodityTypesMapper;

    @Override
    public List<Map<Object, Object>> selectAllTypeByBusinessId(Integer business_id) {
        return this.wnkCommodityTypesMapper.selectAllTypeByBusinessId(business_id);
    }

    @Override
    public WnkCommodityTypes selectById(Integer record_id) {
        return this.wnkCommodityTypesMapper.selectById(record_id);
    }

    @Override
    public int addType(WnkCommodityTypes wnkCommodityTypes) {
        return this.wnkCommodityTypesMapper.addType(wnkCommodityTypes);
    }

    @Override
    public int updateType(WnkCommodityTypes wnkCommodityTypes) {
        return this.wnkCommodityTypesMapper.updateType(wnkCommodityTypes);
    }

    @Override
    public int setTypeState(WnkCommodityTypes wnkCommodityTypes) {
        return this.wnkCommodityTypesMapper.setTypeState(wnkCommodityTypes);
    }

    @Override
    public WnkCommodityTypes selectByNameAndBusinessId(String type_name, Integer business_id) {
        return this.wnkCommodityTypesMapper.selectByNameAndBusinessId(type_name, business_id);
    }

    /**
     *
     * 功能描述: 修改分类删除状态
     *
     * @param delete_state 删除状态(0-未删除,1-已删除)
     * @param type_id 分类ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/20 10:14 PM
     */
    @Override
    public int updateTypeDeleteState(Integer delete_state, Integer type_id) {
        return this.wnkCommodityTypesMapper.updateTypeDeleteState(delete_state, type_id);
    }
}
