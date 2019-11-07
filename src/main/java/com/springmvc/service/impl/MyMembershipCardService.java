package com.springmvc.service.impl;

import com.springmvc.dao.MyMembershipCardMapper;
import com.springmvc.service.IMyMembershipCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/myMembershipCardService")
public class MyMembershipCardService implements IMyMembershipCardService {
    @Resource
    private MyMembershipCardMapper myMembershipCardMapper;

    @Override
    public int updateRecordUserId(Integer user_id, Integer isUser_id) {
        return this.myMembershipCardMapper.updateRecordUserId(user_id, isUser_id);
    }

    @Override
    public List<Map<Object, Object>> selectAllMemberCards(Integer user_id) {
        return this.myMembershipCardMapper.selectAllMemberCards(user_id);
    }
}
