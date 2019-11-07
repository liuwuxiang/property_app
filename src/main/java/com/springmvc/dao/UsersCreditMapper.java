package com.springmvc.dao;

import java.util.List;
import java.util.Map;

public interface UsersCreditMapper {
    //查询某个用户的评级记录
    List<Map<Object,Object>> selectUserCreditRecord(Integer user_id);
}
