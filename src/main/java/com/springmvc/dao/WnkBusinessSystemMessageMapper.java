package com.springmvc.dao;

import com.springmvc.entity.WnkBusinessSystemMessage;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/11/8 16:41
 * @Description:万能卡商家系统消息Mapper
 */
public interface WnkBusinessSystemMessageMapper {
    //新增系统消息
    int insertSystemMessage(WnkBusinessSystemMessage wnkBusinessSystemMessage);
    //查询某个商家的所有系统消息
    List<Map<Object,Object>> selectBusinessAllMessage(Integer business_id);
}
