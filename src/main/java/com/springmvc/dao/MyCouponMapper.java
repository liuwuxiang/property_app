package com.springmvc.dao;

import java.util.List;
import java.util.Map;

public interface MyCouponMapper {
    //根据用户id查询并重新修改记录所属用户(第一个用户id用于修改，第二个用户id用户查询)
    int updateRecordUserId(Integer user_id,Integer isUser_id);
    //获取某个用户的所有优惠券
    List<Map<Object,Object>> selectAllCouponByUser(Integer user_id);
}
