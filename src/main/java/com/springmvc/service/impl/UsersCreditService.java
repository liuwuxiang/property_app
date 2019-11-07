package com.springmvc.service.impl;

import com.springmvc.dao.UsersCreditMapper;
import com.springmvc.service.IUsersCreditService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("/usersCreditService")
public class UsersCreditService implements IUsersCreditService{
    @Resource
    private UsersCreditMapper usersCreditMapper;

    @Override
    public List<Map<Object, Object>> selectUserCreditRecord(Integer user_id) {
        return this.usersCreditMapper.selectUserCreditRecord(user_id);
    }
}
