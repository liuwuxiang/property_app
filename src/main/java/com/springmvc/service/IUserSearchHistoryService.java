package com.springmvc.service;

import com.springmvc.entity.UserSearchHistory;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/17 16:54
 * @Description:用户搜索历史记录服务接口类
 */
public interface IUserSearchHistoryService {
    /**
     *
     * 功能描述: 获取某个用户的所有搜索记录
     *
     * @param user_id 用户ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 4:51 PM
     */
    List<Map<Object,Object>> selectUserAllSearchRecord(Integer user_id);

    /**
     *
     * 功能描述: 获取某个用户某个内容的搜索记录
     *
     * @param user_id 用户ID
     * @param search_content 搜索内容
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 4:52 PM
     */
    List<Map<Object,Object>> selectUserSearchRecordByContent(Integer user_id,String search_content);

    /**
     *
     * 功能描述: 插入搜索记录
     *
     * @param user_id 用户ID
     * @param search_content 搜索内容
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 4:53 PM
     */
    int insertSearchRecord(Integer user_id,String search_content);

    /**
     *
     * 功能描述: 根据用户ID删除记录
     *
     * @param user_id 用户ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 5:36 PM
     */
    int deleteRecordByUserId(Integer user_id);
}
