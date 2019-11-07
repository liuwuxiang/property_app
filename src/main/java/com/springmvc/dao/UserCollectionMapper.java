package com.springmvc.dao;

import com.springmvc.entity.UserCollection;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/27 14:57
 * @Description:用于记录用户收藏记录Mapper
 */
public interface UserCollectionMapper {
    //获取某个用户的所有关注记录
    List<Map<Object,Object>> selectCollectionByUserId(Integer user_id);
    //新增收藏记录
    int addCollection(UserCollection userCollection);
    //删除收藏记录
    int deleteCollection(Integer user_id,Integer business_id);
    //查询某用户对某商家的收藏记录
    List<Map<Object,Object>> selectCollectionByUserIdAndBusinessId(Integer user_id,Integer business_id);
}
