package com.springmvc.dao;

import com.springmvc.entity.UserOpenCards;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserOpenCardsMapper {
    //新增记录
    int addRecord(UserOpenCards userOpenCards);
    //通过用户id查询记录
    UserOpenCards selectByUserId(Integer user_id);
    //查询所有记录
    List<Map<Object,Object>> selectAllRecord();
    //根据用户id更新有效期
    int updateTermByUserId(Date term_validity,Integer user_id);
}
