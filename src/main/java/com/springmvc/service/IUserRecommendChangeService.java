package com.springmvc.service;

import com.springmvc.entity.UserRecommendChange;

/**
 * @author: zhangfan
 * @Date: 2018/12/16 22:04
 * @Description:用户推荐人变更记录表Service接口类
 */
public interface IUserRecommendChangeService {
    /**
     *
     * 功能描述: 插入变更记录
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/16 10:05 PM
     */
    int insertChangeRecord(UserRecommendChange userRecommendChange);
}
