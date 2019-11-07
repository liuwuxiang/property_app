package com.springmvc.dao;

import com.springmvc.entity.SystemNoticeMessage;

import java.util.List;
import java.util.Map;

public interface SystemNoticeMessageMapper {

    /** 
     *
     * 功能描述: 后台查询所有通知消息
     *
     * @param
     * @return: 后台查询所有通知消息
     * @author: 刘武祥
     * @date: 2019/2/12 0012 15:46
     */
    List<Map<Object,Object>> selectAllAdmin();

    /**
     *
     * 功能描述: 新增消息
     *
     * @param   systemNoticeMessage
     * @return: 新增消息
     * @author: 刘武祥
     * @date: 2019/2/12 0012 15:45
     */
    int addNotice(SystemNoticeMessage systemNoticeMessage);

    /** 
     *
     * 功能描述: 根据条件查询通知消息
     *
     * @param   map 查询条件
     * @return: 通知消息
     * @author: 刘武祥
     * @date: 2019/1/11 0011 14:49
     */
    List<Map<String,Object>> adminSearchMessageByConditions(Map<String,Object> map);
}
