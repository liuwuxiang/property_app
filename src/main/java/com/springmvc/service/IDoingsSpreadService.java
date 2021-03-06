package com.springmvc.service;

import java.util.List;
import java.util.Map;

import com.springmvc.entity.DoingsSpread;

import java.util.List;
import java.util.Map;

/**
 * @author 杨新杰
 * @Date 2018/11/2 15:01
 */
public interface IDoingsSpreadService {

    /**
     *
     * 功能描述: 查询所有未审核的商家活动
     *
     * @param   []
     * @return  java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author  杨新杰
     * @date    10:41 2018/12/19
     */
    List<Map<String,Object>> selectNoReviewAll();

    /**
     *
     * 功能描述: 获取所有已通过审核并且未下架的并且所有商家和用户或所有用户均可查看的文本推广消息
     *
     * @param:
     * @return: List
     * @author: zhangfan
     * @date: 2018/11/3 2:41 PM
     */
    List<Map<Object,Object>> selectNotDownTextMessageAndExaminePassJurisdictionForAllUserAndBusinessOrUser();

    /**
     *
     * 功能描述: 获取所有已通过审核并且未下架的并且只有商家会员可查看的文本推广消息
     *
     * @param business_id 商家id
     * @return: List
     * @author: zhangfan
     * @date: 2018/11/3 2:42 PM
     */
    List<Map<Object,Object>> selectNotDownTextMessageAndExaminePassJurisdictionForBusinessMember(Integer business_id);

    /**
     *
     * 功能描述: 获取所有已通过审核并且未下架的并且所有商家和用户或所有用户均可查看的轮播图推广消息
     *
     * @param:
     * @return: List
     * @author: zhangfan
     * @date: 2018/11/3 2:41 PM
     */
    List<Map<Object,Object>> selectNotDownImgMessageAndExaminePassJurisdictionForAllUserAndBusinessOrUser();

    /**
     *
     * 功能描述: 获取所有已通过审核并且未下架的并且只有商家会员可查看的轮播图推广消息
     *
     * @param business_id 商家id
     * @return: List
     * @author: zhangfan
     * @date: 2018/11/3 2:42 PM
     */
    List<Map<Object,Object>> selectNotDownImgMessageAndExaminePassJurisdictionForBusinessMember(Integer business_id);

    /**
     *
     * 功能描述: 查询所有活动 - 按时间从近到远排序
     *
     * @return
     * @author 杨新杰
     * @date   2018/11/3 10:45
     */
    List<Map<String,Object>> selectDoingsSpreadAll();

    /**
     *
     * 功能描述: 通过ID更新活动状态
     *
     * @param  doingsSpread 活动推广实体类
     * @return
     * @author 杨新杰
     * @date   2018/11/3 10:45
     */
    int updateDoingsSpreadStatue(DoingsSpread doingsSpread);

    /**
     *
     * 功能描述: 插入轮播点击后进入长图的广告
     *
     * @param  doingsSpread 活动推广实体类
     * @return
     * @author 杨新杰
     * @date   2018/11/3 10:45
     */
    int insertDoingsSpreadLunBoChangTu(DoingsSpread doingsSpread);

    /**
     *
     * 功能描述: 插入点击后进入店铺主页的广告
     *
     * @param  doingsSpread 活动推广实体类
     * @return
     * @author 杨新杰
     * @date   2018/11/3 10:45
     */
    int insertDoingsSpreadLunBoZhuye(DoingsSpread doingsSpread);

    /**
     *
     * 功能描述: 插入系统消息广告
     *
     * @param  doingsSpread 活动推广实体类
     * @return
     * @author 杨新杰
     * @date   2018/11/3 10:45
     */
    int insertDoingsSpreadSystemMsg(DoingsSpread doingsSpread);


    /**
     *
     * 功能描述:商家端获取所有商家可以查看的文字消息
     *
     * @return
     * @author 杨新杰
     * @date   2018/11/3 15:55
     */
    List<Map<Object,Object>> selectSystemMsgDoingsSpreadByIdAll();

    /**
     *
     * 功能描述:商家端商家推荐人的信息
     *
     * @return
     * @author 杨新杰
     * @date   2018/11/3 15:55
     */
    List<Map<Object,Object>> selectSystemMsgDoingsSpreadByRecommend(Integer recommend_business_id);


    /**
     *
     * 功能描述:商家端获取所有商家可以查看的轮播图消息-长页
     *
     * @return
     * @author 杨新杰
     * @date   2018/11/3 15:55
     */
    List<Map<Object,Object>> selectGalleryChangYeDoingsSpreadByIdAll();

    /**
     *
     * 功能描述:商家端获取所有商家可以查看的轮播图消息-长页 - 推荐人
     *
     * @param recommend_business_id 推荐人ID
     * @return
     * @author 杨新杰
     * @date   2018/11/3 15:55
     */
    List<Map<Object,Object>> selectGalleryChangYeDoingsSpreadByRecommendBusinessId(Integer recommend_business_id);

    /**
     *
     * 功能描述:根据商家ID获取自己所发布的所有广告
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/3 15:55
     */
    List<Map<Object,Object>> getBusinessIdAdById(Integer business_id,Integer type);

    /**
     *
     * 功能描述:根据商家ID获取未读消息数量
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/3 15:55
     */
    Map<String,Object> getAdUnread(Integer business_id);

    /**
     *
     * 功能描述:根据商家ID更新未读消息为已读 - 推广活动
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/3 15:55
     */
    int updateReadByBusinessId(Integer business_id);

    /**
     *
     * 功能描述:根据商家ID获取未读消息数量 - 系统消息
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/3 15:55
     */
    Map<String,Object> getSystemMsg(Integer business_id);

    /**
     *
     * 功能描述:查询未读消息
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/3 15:55
     */
    List<Map<String,Object>> selectDoingsSpreadReadFalseByAd(Integer business_id);

    /**
     *
     * 功能描述:更新指定信息为已读
     *
     * @param map
     * @return
     * @author 杨新杰
     * @date   2018/11/3 15:55
     */
    int UpdateDoingsSpreadReadTrue(String id,String business_id,String type);

    /**
     *
     * 功能描述:查询未读消息 - 系统消息
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/3 15:55
     */
    List<Map<String,Object>> selectSystemMsgReadFalseByAd(Integer business_id );

    /**
     *
     * 功能描述: 根据条件查询推广活动审核信息
     *
     * @param   map 查询条件
     * @return: 返回推广活动审核信息
     * @author: 刘武祥
     * @date: 2019/1/17 0017 11:04
     */
    List<Map<Object,Object>> adminSearchDoingsSpread(Map<String,Object> map);
}
