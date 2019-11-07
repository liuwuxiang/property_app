package com.springmvc.dao;

import com.springmvc.entity.SystemMessages;
import com.springmvc.entity.SystemNoticeMessage;

import java.util.List;
import java.util.Map;

public interface SystemMessagesMapper {
    //新增系统消息
    int insertSystemMessage(SystemMessages systemMessages);
    //查询某个用户的所有系统消息
    List<Map<Object,Object>> selectUserAllMessage(Integer user_id);
    //根据消息id查询消息
    SystemMessages selectMessageDetailByMessageId(Integer message_id);
    //更新消息为已读状态
    int updateMessageForRead(Integer message_id);
}
