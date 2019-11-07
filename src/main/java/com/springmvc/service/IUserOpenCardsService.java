package com.springmvc.service;

import com.springmvc.entity.UserOpenCards;
import com.springmvc.entity.WnkBuyMeal;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IUserOpenCardsService {
    /**
     * 新增记录
     * @param userOpenCards
     * @return
     */
    int addRecord(UserOpenCards userOpenCards);

    /**
     * 通过用户id查询记录
     * @param user_id 用户DI
     * @return
     */
    UserOpenCards selectByUserId(Integer user_id);

    /**
     * 查询所有记录
     * @return
     */
    List<Map<Object,Object>> selectAllRecord();

    /**
     * 根据用户id更新有效期
     * @param term_validity
     * @param user_id
     * @return
     */
    int updateTermByUserId(Date term_validity, Integer user_id);

    /**
     * 用户开卡/续费(type=0-青春万能卡,1-超级万能卡)
     * @param user_id   用户ID
     * @param type      0-青春万能卡,1-超级万能卡
     * @param user_type
     * @return
     */
    int userOpenCardOrRenew(Integer user_id,Integer type,Integer user_type);

    /**
     * 进行用户开卡推荐人相关操作
     * @param user_id
     * @param wnkBuyMeal
     * @return
     */
    int userOpenCardRecommendOption(Integer user_id, WnkBuyMeal wnkBuyMeal);
}
