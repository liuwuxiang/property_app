package com.springmvc.service;

import com.springmvc.entity.UserReceivingAddress;

import java.util.Map;

public interface IUserReceivingAddressService {
    /**
     * 查询某个用户的收货地址
     * @param user_id
     * @return
     */
    UserReceivingAddress selectReceivingAddressByUserId(Integer user_id);

    /**
     * 新增用户收货地址
     * @param userReceivingAddress
     * @return
     */
    int addUserReceivingAddress(UserReceivingAddress userReceivingAddress);

    /**
     * 修改用户收货地址
     * @param userReceivingAddress
     * @return
     */
    int updateUserReceivingAddress(UserReceivingAddress userReceivingAddress);

    /**
     * 查询某个用户的收货地址返回Map
     * @param user_id
     * @return
     */
    Map<Object,Object> selectReceivingAddressByUserIdReturnMap(Integer user_id);

    /**
     * 根据用户id删除记录
     * @param user_id
     * @return
     */
    int deleteRecordByUserId(Integer user_id);
}
