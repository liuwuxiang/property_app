package com.springmvc.service.impl;

import com.springmvc.dao.SystemNoticeMessageMapper;
import com.springmvc.entity.SystemNoticeMessage;
import com.springmvc.service.ISystemNoticeMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/systemNoticeMessageService")
public class SystemNoticeMessageService implements ISystemNoticeMessageService{
    @Resource
    private SystemNoticeMessageMapper systemNoticeMessageMapper;

    @Override
    public List<Map<Object, Object>> selectAllAdmin() {
        return this.systemNoticeMessageMapper.selectAllAdmin();
    }

    @Override
    public int addNotice(SystemNoticeMessage systemNoticeMessage) {
        return this.systemNoticeMessageMapper.addNotice(systemNoticeMessage);
    }

    /**
     *
     * 功能描述: 根据条件查询通知消息
     *
     * @param   map 查询条件
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author: 刘武祥
     * @date: 2019/1/11 0011 14:49
     */
    @Override
    public List<Map<String, Object>> adminSearchMessageByConditions(Map<String, Object> map) {
        return this.systemNoticeMessageMapper.adminSearchMessageByConditions(map);
    }
}
