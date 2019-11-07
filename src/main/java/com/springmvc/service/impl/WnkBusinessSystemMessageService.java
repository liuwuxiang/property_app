package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessSystemMessageMapper;
import com.springmvc.entity.WnkBusinessSystemMessage;
import com.springmvc.service.IWnkBusinessSystemMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/11/8 16:47
 * @Description:万能卡商家系统消息服务实现类
 */
@Service("/wnkBusinessSystemMessageService")
public class WnkBusinessSystemMessageService implements IWnkBusinessSystemMessageService {
    @Resource
    private WnkBusinessSystemMessageMapper wnkBusinessSystemMessageMapper;

    @Override
    public int insertSystemMessage(WnkBusinessSystemMessage wnkBusinessSystemMessage) {
        return this.wnkBusinessSystemMessageMapper.insertSystemMessage(wnkBusinessSystemMessage);
    }

    @Override
    public List<Map<Object, Object>> selectBusinessAllMessage(Integer business_id) {
        return this.wnkBusinessSystemMessageMapper.selectBusinessAllMessage(business_id);
    }
}
