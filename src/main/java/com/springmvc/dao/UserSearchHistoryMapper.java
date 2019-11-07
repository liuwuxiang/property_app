package com.springmvc.dao;

import com.springmvc.entity.UserSearchHistory;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/17 16:47
 * @Description:用户搜索历史记录Mapper
 */
public interface UserSearchHistoryMapper {
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
     * @param userSearchHistory 插入的信息实体类
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 4:53 PM
     */
    int insertSearchRecord(UserSearchHistory userSearchHistory);

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
