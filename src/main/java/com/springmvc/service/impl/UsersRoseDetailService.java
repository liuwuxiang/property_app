package com.springmvc.service.impl;

import com.springmvc.dao.UsersRoseDetailMapper;
import com.springmvc.entity.UsersRoseDetail;
import com.springmvc.service.IUsersRoseDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 21:44
 * @Description:用户玫瑰(银币)明细服务实现类
 */
@Service("/usersRoseDetailService")
public class UsersRoseDetailService implements IUsersRoseDetailService {
    @Resource
    private UsersRoseDetailMapper usersRoseDetailMapper;

    /**
     *
     * 功能描述: 查询某用户的交易记录
     *
     * @param: user_id,transactions_type(交易类型(0-收入,1-支出))
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/28 11:19 PM
     */
    @Override
    public List<Map<Object, Object>> selectRecordByUserId(Integer user_id, Integer transactions_type) {
        return this.usersRoseDetailMapper.selectRecordByUserId(user_id, transactions_type);
    }

    /**
     *
     * 功能描述: 插入新纪录
     *
     * @param: usersRoseDetail
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/28 11:20 PM
     */
    @Override
    public int insertNewRecord(UsersRoseDetail usersRoseDetail) {
        return this.usersRoseDetailMapper.insertNewRecord(usersRoseDetail);
    }
}
