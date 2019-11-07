package com.springmvc.service;

import java.util.List;
import java.util.Map;

public interface IUsersCreditService {
    /**
     * 查询某个用户的评级记录
     * @param user_id
     * @return
     */
    List<Map<Object,Object>> selectUserCreditRecord(Integer user_id);
}
