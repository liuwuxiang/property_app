package com.springmvc.service;

import com.springmvc.entity.SystemNoticeMessage;

import java.util.List;
import java.util.Map;

public interface ISystemNoticeMessageService {
    /**
     * 后台查询所有通知消息
     * @return
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     * 新增消息
     * @param systemNoticeMessage
     * @return
     */
    int addNotice(SystemNoticeMessage systemNoticeMessage);

    /**
     *
     * 功能描述: 根据条件查询通知消息
     *
     * @param   map 查询条件
     * @return: 返回查询通知消息
     * @author: 刘武祥
     * @date: 2019/1/11 0011 14:48
     */
    List<Map<String,Object>> adminSearchMessageByConditions(Map<String,Object> map);
}
