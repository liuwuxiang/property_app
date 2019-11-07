package com.springmvc.service.impl;

import com.springmvc.dao.DoingSpreadUserReadRecordMapper;
import com.springmvc.entity.DoingSpreadUserReadRecord;
import com.springmvc.service.IDoingSpreadUserReadRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhangfan
 * @Date: 2018/11/7 01:00
 * @Description:推广消息用户已读记录服务实现类
 */
@Service("/doingSpreadUserReadRecordService")
public class DoingSpreadUserReadRecordService implements IDoingSpreadUserReadRecordService{
    @Resource
    private DoingSpreadUserReadRecordMapper doingSpreadUserReadRecordMapper;

    @Override
    public int insertNewOrder(DoingSpreadUserReadRecord doingSpreadUserReadRecord) {
        return this.doingSpreadUserReadRecordMapper.insertNewOrder(doingSpreadUserReadRecord);
    }

    @Override
    public DoingSpreadUserReadRecord selectByUserIdAndMessageId(Integer user_id, Integer message_id) {
        return this.doingSpreadUserReadRecordMapper.selectByUserIdAndMessageId(user_id, message_id);
    }
}
