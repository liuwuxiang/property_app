package com.springmvc.service.impl;

import com.springmvc.dao.UserCollectionMapper;
import com.springmvc.entity.UserCollection;
import com.springmvc.service.IUserCollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/27 15:03
 * @Description:
 */
@Service("/userCollectionService")
public class UserCollectionService implements IUserCollectionService{
    @Resource
    private UserCollectionMapper userCollectionMapper;

    @Override
    public List<Map<Object, Object>> selectCollectionByUserId(Integer user_id) {
        return this.userCollectionMapper.selectCollectionByUserId(user_id);
    }

    @Override
    public int addCollection(UserCollection userCollection) {
        return this.userCollectionMapper.addCollection(userCollection);
    }

    @Override
    public int deleteCollection(Integer user_id, Integer business_id) {
        return this.userCollectionMapper.deleteCollection(user_id, business_id);
    }

    @Override
    public List<Map<Object, Object>> selectCollectionByUserIdAndBusinessId(Integer user_id, Integer business_id) {
        return this.userCollectionMapper.selectCollectionByUserIdAndBusinessId(user_id, business_id);
    }
}
