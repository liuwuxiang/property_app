package com.springmvc.service.impl;

import com.springmvc.dao.WnkOrderMakeRecordMapper;
import com.springmvc.entity.WnkOrderMakeRecord;
import com.springmvc.service.IWnkOrderMakeRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/8 17:30
 * @Description:
 */
@Service("/wnkOrderMakeRecordService")
public class WnkOrderMakeRecordService implements IWnkOrderMakeRecordService{
    @Resource
    private WnkOrderMakeRecordMapper wnkOrderMakeRecordMapper;

    @Override
    public int insertNewRecord(WnkOrderMakeRecord wnkOrderMakeRecord) {
        return this.wnkOrderMakeRecordMapper.insertNewRecord(wnkOrderMakeRecord);
    }

    @Override
    public List<Map<Object, Object>> selectByOrderNo(String order_no) {
        return this.wnkOrderMakeRecordMapper.selectByOrderNo(order_no);
    }
}
