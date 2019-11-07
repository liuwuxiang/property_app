package com.springmvc.service.impl;

import com.springmvc.dao.UserRecommendChangeMapper;
import com.springmvc.entity.UserRecommendChange;
import com.springmvc.service.IUserRecommendChangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhangfan
 * @Date: 2018/12/16 22:05
 * @Description:用户推荐人变更记录表Service实现类
 */
@Service("/userRecommendChangeService")
public class UserRecommendChangeService implements IUserRecommendChangeService{
    @Resource
    private UserRecommendChangeMapper userRecommendChangeMapper;

    /**
     *
     * 功能描述: 插入变更记录
     *
     * @param:
     * @return:
     * @author: zhangfan
     * @date: 2018/12/16 10:05 PM
     */
    @Override
    public int insertChangeRecord(UserRecommendChange userRecommendChange) {
        return this.userRecommendChangeMapper.insertChangeRecord(userRecommendChange);
    }
}
