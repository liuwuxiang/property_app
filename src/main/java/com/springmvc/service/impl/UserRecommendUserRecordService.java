package com.springmvc.service.impl;

import com.springmvc.dao.UserRecommendUserRecordMapper;
import com.springmvc.entity.UserRecommendUserRecord;
import com.springmvc.service.IUserRecommendUserRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhangfan
 * @Date: 2018/11/3 17:17
 * @Description:用户推荐用户记录器服务实现类-用于记录用户推荐用户记录(记录用户推荐了多少个开卡，每条记录满3状态变更为已兑换，依次循环)
 */
@Service("/userRecommendUserRecordService")
public class UserRecommendUserRecordService implements IUserRecommendUserRecordService {
    @Resource
    private UserRecommendUserRecordMapper userRecommendUserRecordMapper;

    /**
     *
     * 功能描述: 添加记录
     *
     * @param: user_id
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 4:29 AM
     */
    @Override
    public int insertRecord(Integer user_id) {
        return this.userRecommendUserRecordMapper.insertRecord(user_id);
    }

    /**
     *
     * 功能描述: 查询用户未兑换的记录
     *
     * @param: user_id
     * @return: UserRecommendUserRecord
     * @author: zhangfan
     * @date: 2018/10/29 4:29 AM
     */
    @Override
    public UserRecommendUserRecord selectNoExchangeByUserId(Integer user_id) {
        return this.userRecommendUserRecordMapper.selectNoExchangeByUserId(user_id);
    }

    /**
     *
     * 功能描述: 更新用户推荐开卡数量
     *
     * @param: userRecommendUserRecord
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 4:30 AM
     */
    @Override
    public int updateInformation(UserRecommendUserRecord userRecommendUserRecord) {
        return this.userRecommendUserRecordMapper.updateInformation(userRecommendUserRecord);
    }

    /**
     *
     * 功能描述: 更新兑换状态为已兑换
     *
     * @param: record_id
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/29 4:30 AM
     */
    @Override
    public int updateStateIsFinish(Integer record_id) {
        return this.userRecommendUserRecordMapper.updateStateIsFinish(record_id);
    }
}
