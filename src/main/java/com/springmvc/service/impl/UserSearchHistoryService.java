package com.springmvc.service.impl;

import com.springmvc.dao.UserSearchHistoryMapper;
import com.springmvc.entity.UserSearchHistory;
import com.springmvc.service.IUserSearchHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/17 16:54
 * @Description:用户搜索历史记录服务实现类
 */
@Service("/userSearchHistoryService")
public class UserSearchHistoryService implements IUserSearchHistoryService{
    @Resource
    private UserSearchHistoryMapper userSearchHistoryMapper;

    /**
     *
     * 功能描述: 获取某个用户的所有搜索记录
     *
     * @param user_id 用户ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 4:51 PM
     */
    @Override
    public List<Map<Object, Object>> selectUserAllSearchRecord(Integer user_id) {
        return this.userSearchHistoryMapper.selectUserAllSearchRecord(user_id);
    }

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
    @Override
    public List<Map<Object, Object>> selectUserSearchRecordByContent(Integer user_id, String search_content) {
        return this.userSearchHistoryMapper.selectUserSearchRecordByContent(user_id, search_content);
    }

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
    @Override
    public int insertSearchRecord(Integer user_id, String search_content) {
        List<Map<Object, Object>> list = this.userSearchHistoryMapper.selectUserSearchRecordByContent(user_id, search_content);
        if (list.size() > 0){
            return 1;
        }
        else{
            UserSearchHistory userSearchHistory = new UserSearchHistory();
            userSearchHistory.setUser_id(user_id);
            userSearchHistory.setSearch_content(search_content);
            return this.userSearchHistoryMapper.insertSearchRecord(userSearchHistory);
        }
    }

    /**
     *
     * 功能描述: 根据用户ID删除记录
     *
     * @param user_id 用户ID
     * @return:
     * @author: zhangfan
     * @date: 2018/12/17 5:36 PM
     */
    @Override
    public int deleteRecordByUserId(Integer user_id) {
        return this.userSearchHistoryMapper.deleteRecordByUserId(user_id);
    }
}
