package com.springmvc.dao;

import com.springmvc.entity.UserReceivingAddress;

import java.util.Map;

public interface UserReceivingAddressMapper {
    //查询某个用户的收货地址
    UserReceivingAddress selectReceivingAddressByUserId(Integer user_id);
    //新增用户收货地址
    int addUserReceivingAddress(UserReceivingAddress userReceivingAddress);
    //修改用户收货地址
    int updateUserReceivingAddress(UserReceivingAddress userReceivingAddress);
    //查询某个用户的收货地址返回Map
    Map<Object,Object> selectReceivingAddressByUserIdReturnMap(Integer user_id);
    //根据用户id删除记录
    int deleteRecordByUserId(Integer user_id);

}
