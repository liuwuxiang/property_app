package com.springmvc.service;

import com.springmvc.entity.WnkBusinessTypeOpenCard;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/11/5 00:51
 * @Description:万能卡商家分类用户开卡记录(用于记录用户在需要用户单独开卡的万能卡分类商家处的开卡记录)服务接口类
 */
public interface IWnkBusinessTypeOpenCardService {
    /**
     *
     * 功能描述: 插入开卡记录
     *
     * @param wnkBusinessTypeOpenCard 开卡信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/5 12:51 AM
     */
    int insertOpenCardRecord(WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard);

    /**
     *
     * 功能描述: 查询某用户在某商家处的开卡状态
     *
     * @param user_id 用户id
     * @param business_id 商家id
     * @return: WnkBusinessTypeOpenCard
     * @author: zhangfan
     * @date: 2018/11/5 1:09 AM
     */
    WnkBusinessTypeOpenCard selectOpenCardStateByUserIdAndBusinessId(Integer user_id,Integer business_id);

    /**
     *
     * 功能描述:更新卡片有效期
     *
     * @param wnkBusinessTypeOpenCard 卡片信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/5 2:28 AM
     */
    int updateCardEndTime(WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard);

    /**
     *
     * 功能描述: 获取用户的所有健身卡
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    10:38 2018/12/29
     */
    List<Map<String,Object>> selectOpenCardFitnessByUserId(Map<String,Object> map);

    /**
     *
     * 功能描述: 通过ID获取健身卡信息
     *
     * @param
     * @return
     * @author  杨新杰
     * @date    10:38 2018/12/29
     */
    Map<String,Object> selectFitnessCardInfoById(Integer user_id, Integer card_id);

    /**
     *
     * 功能描述: 商家会员管理查询
     *
     * @param   business_id 商家ID
     * @param   sort_type   排序方式
     * @param   gender      性别
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:50 2018/12/29
     */
    List<Map<String,Object>> selectBusinessVip(Integer business_id, Integer sort_type, Integer gender);

    /**
     *
     * 功能描述: 商家会员管理查询 - 通过手机号码或者用户名
     *
     * @param   business_id 商家ID
     * @param   content     搜索结果
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:50 2018/12/29
     */
    List<Map<String,Object>> selectBusinessVipSearch(String business_id, String content);

    /**
     *
     * 功能描述: 查询用户在商家的开卡
     *
     * @param   business_id 商家ID
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:50 2018/12/29
     */
    WnkBusinessTypeOpenCard selectOpenCardStateGymByUserIdAndBusinessId(Integer user_id, Integer business_id);

    /**
     *
     * 功能描述: 获取用户信息
     *
     * @param   business_id 商家ID
     * @param   user_id     用户ID
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    14:50 2018/12/29
     */
    Map<String,Object> selectUserGymCardInfoByUserId(Integer user_id, Integer business_id);
}
