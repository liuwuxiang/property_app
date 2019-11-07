package com.springmvc.service.impl;

import com.springmvc.dao.UserReceivingAddressMapper;
import com.springmvc.entity.UserReceivingAddress;
import com.springmvc.service.IUserReceivingAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("/userReceivingAddressService")
public class UserReceivingAddressService implements IUserReceivingAddressService{
    @Resource
    private UserReceivingAddressMapper userReceivingAddressMapper;

    @Override
    public UserReceivingAddress selectReceivingAddressByUserId(Integer user_id) {
        return this.userReceivingAddressMapper.selectReceivingAddressByUserId(user_id);
    }

    @Override
    public int addUserReceivingAddress(UserReceivingAddress userReceivingAddress) {
        return this.userReceivingAddressMapper.addUserReceivingAddress(userReceivingAddress);
    }

    @Override
    public int updateUserReceivingAddress(UserReceivingAddress userReceivingAddress) {
        return this.userReceivingAddressMapper.updateUserReceivingAddress(userReceivingAddress);
    }

    @Override
    public Map<Object, Object> selectReceivingAddressByUserIdReturnMap(Integer user_id) {
        return this.userReceivingAddressMapper.selectReceivingAddressByUserIdReturnMap(user_id);
    }

    @Override
    public int deleteRecordByUserId(Integer user_id) {
        return this.userReceivingAddressMapper.deleteRecordByUserId(user_id);
    }
}
