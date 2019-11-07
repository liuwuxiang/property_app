package com.springmvc.service;

import com.springmvc.entity.UserCollection;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/27 15:03
 * @Description:
 */
public interface IUserCollectionService {
    /**
     *
     * 功能描述: 获取某个用户的所有关注记录
     *
     * @param:user_id
     * @return:List
     * @author: zhangfan
     * @date: 2018/10/27 3:05 PM
     */
    List<Map<Object,Object>> selectCollectionByUserId(Integer user_id);

    /**
     *
     * 功能描述: 新增收藏记录
     *
     * @param: userCollection
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/27 3:53 PM
     */
    int addCollection(UserCollection userCollection);

    /**
     *
     * 功能描述: 删除收藏记录
     *
     * @param: user_id,business_id
     * @return: int
     * @author: zhangfan
     * @date: 2018/10/27 3:54 PM
     */
    int deleteCollection(Integer user_id,Integer business_id);

    /**
     *
     * 功能描述: 查询某用户对某商家的收藏记录
     *
     * @param: user_id,business_id
     * @return: List
     * @author: zhangfan
     * @date: 2018/10/27 3:54 PM
     */
    List<Map<Object,Object>> selectCollectionByUserIdAndBusinessId(Integer user_id,Integer business_id);
}
