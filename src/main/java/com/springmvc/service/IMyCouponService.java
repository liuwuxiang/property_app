package com.springmvc.service;

import java.util.List;
import java.util.Map;

public interface IMyCouponService {
    /**
     * 根据用户id查询并重新修改记录所属用户(第一个用户id用于修改，第二个用户id用户查询)
     * @param user_id    用于修改
     * @param isUser_id  用于查询
     * @return
     */
    int updateRecordUserId(Integer user_id,Integer isUser_id);

    /**
     * 获取某个用户的所有优惠券
     * @param user_id 用户ID
     * @return 返回查询结果,无记录返回null
     */
    List<Map<Object,Object>> selectAllCouponByUser(Integer user_id);
}
