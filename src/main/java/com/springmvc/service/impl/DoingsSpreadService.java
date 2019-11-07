package com.springmvc.service.impl;

import com.springmvc.dao.DoingsSpreadMapper;
import com.springmvc.entity.DoingsSpread;
import com.springmvc.service.IDoingsSpreadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 杨新杰
 * @Date 2018/11/2 15:01
 */
@Service("/doingsSpreadService")
public class DoingsSpreadService implements IDoingsSpreadService {
    @Resource
    private DoingsSpreadMapper doingsSpreadMapper;


    /**
     *
     * 功能描述: 查询所有未审核的商家活动
     *
     * @param   []
     * @return  java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author  杨新杰
     * @date    10:41 2018/12/19
     */
    @Override
    public List<Map<String,Object>> selectNoReviewAll(){
        return this.doingsSpreadMapper.selectNoReviewAll();
    }

    /**
     *
     * 功能描述: 获取所有已通过审核并且未下架的并且所有商家和用户或所有用户均可查看的文本推广消息
     *
     * @param:
     * @return: List
     * @author: zhangfan
     * @date: 2018/11/3 2:41 PM
     */
    @Override
    public List<Map<Object, Object>> selectNotDownTextMessageAndExaminePassJurisdictionForAllUserAndBusinessOrUser() {
        return this.doingsSpreadMapper.selectNotDownTextMessageAndExaminePassJurisdictionForAllUserAndBusinessOrUser();
    }

    /**
     *
     * 功能描述: 获取所有已通过审核并且未下架的并且只有商家会员可查看的文本推广消息
     *
     * @param business_id 商家id
     * @return: List
     * @author: zhangfan
     * @date: 2018/11/3 2:42 PM
     */
    @Override
    public List<Map<Object, Object>> selectNotDownTextMessageAndExaminePassJurisdictionForBusinessMember(Integer business_id) {
        return this.doingsSpreadMapper.selectNotDownTextMessageAndExaminePassJurisdictionForBusinessMember(business_id);
    }

    /**
     *
     * 功能描述: 获取所有已通过审核并且未下架的并且所有商家和用户或所有用户均可查看的轮播图推广消息
     *
     * @param:
     * @return: List
     * @author: zhangfan
     * @date: 2018/11/3 2:41 PM
     */
    @Override
    public List<Map<Object, Object>> selectNotDownImgMessageAndExaminePassJurisdictionForAllUserAndBusinessOrUser() {
        return this.doingsSpreadMapper.selectNotDownImgMessageAndExaminePassJurisdictionForAllUserAndBusinessOrUser();
    }

    /**
     *
     * 功能描述: 获取所有已通过审核并且未下架的并且只有商家会员可查看的轮播图推广消息
     *
     * @param business_id 商家id
     * @return: List
     * @author: zhangfan
     * @date: 2018/11/3 2:42 PM
     */
    @Override
    public List<Map<Object, Object>> selectNotDownImgMessageAndExaminePassJurisdictionForBusinessMember(Integer business_id) {
        return this.doingsSpreadMapper.selectNotDownImgMessageAndExaminePassJurisdictionForBusinessMember(business_id);
    }

    /**
     *
     * 功能描述: 查询所有活动 - 按时间从近到远排序
     *
     * @return
     * @author 杨新杰
     * @date   2018/11/3 10:45
     */
    @Override
    public List<Map<String, Object>> selectDoingsSpreadAll() {
        return doingsSpreadMapper.selectDoingsSpreadAll();
    }

    /**
     * 功能描述: 通过ID更新活动状态
     *
     * @param doingsSpread 活动推广实体类
     * @return
     * @author 杨新杰
     * @date 2018/11/3 10:45
     */
    @Override
    public int updateDoingsSpreadStatue(DoingsSpread doingsSpread) {
        return doingsSpreadMapper.updateDoingsSpreadStatue(doingsSpread);
    }

    /**
     * 功能描述: 插入轮播点击后进入长图的广告
     *
     * @param doingsSpread 活动推广实体类
     * @return
     * @author 杨新杰
     * @date 2018/11/3 10:45
     */
    @Override
    public int insertDoingsSpreadLunBoChangTu(DoingsSpread doingsSpread) {
        return doingsSpreadMapper.insertDoingsSpreadLunBoChangTu(doingsSpread);
    }

    /**
     * 功能描述: 插入点击后进入店铺主页的广告
     *
     * @param doingsSpread 活动推广实体类
     * @return
     * @author 杨新杰
     * @date 2018/11/3 10:45
     */
    @Override
    public int insertDoingsSpreadLunBoZhuye(DoingsSpread doingsSpread) {
        return doingsSpreadMapper.insertDoingsSpreadLunBoZhuye(doingsSpread);
    }

    /**
     * 功能描述: 插入系统消息广告
     *
     * @param doingsSpread 活动推广实体类
     * @return
     * @author 杨新杰
     * @date 2018/11/3 10:45
     */
    @Override
    public int insertDoingsSpreadSystemMsg(DoingsSpread doingsSpread) {
        return doingsSpreadMapper.insertDoingsSpreadSystemMsg(doingsSpread);
    }

    /**
     *
     * 功能描述:商家端获取所有商家可以查看的文字消息
     *
     * @return
     * @author 杨新杰
     * @date   2018/11/3 15:55
     */
    @Override
    public List<Map<Object, Object>> selectSystemMsgDoingsSpreadByIdAll() {
        return doingsSpreadMapper.selectSystemMsgDoingsSpreadByIdAll();
    }

    /**
     * 功能描述:商家端商家推荐人的信息
     *
     * @param recommend_business_id
     * @return
     * @author 杨新杰
     * @date 2018/11/3 15:55
     */
    @Override
    public List<Map<Object, Object>> selectSystemMsgDoingsSpreadByRecommend(Integer recommend_business_id) {
        return doingsSpreadMapper.selectSystemMsgDoingsSpreadByRecommend(recommend_business_id);
    }

    /**
     * 功能描述:商家端获取所有商家可以查看的轮播图消息-长页
     *
     * @return
     * @author 杨新杰
     * @date 2018/11/3 15:55
     */
    @Override
    public List<Map<Object, Object>> selectGalleryChangYeDoingsSpreadByIdAll() {
        return doingsSpreadMapper.selectGalleryChangYeDoingsSpreadByIdAll();
    }

    /**
     * 功能描述:商家端获取所有商家可以查看的轮播图消息-长页 - 推荐人
     *
     * @param recommend_business_id 推荐人ID
     * @return
     * @author 杨新杰
     * @date 2018/11/3 15:55
     */
    @Override
    public List<Map<Object, Object>> selectGalleryChangYeDoingsSpreadByRecommendBusinessId(Integer recommend_business_id) {
        return doingsSpreadMapper.selectGalleryChangYeDoingsSpreadByRecommendBusinessId(recommend_business_id);
    }

    /**
     * 功能描述:根据商家ID获取自己所发布的所有广告
     *
     * @param business_id 商家ID
     * @param type
     * @return
     * @author 杨新杰
     * @date 2018/11/3 15:55
     */
    @Override
    public List<Map<Object, Object>> getBusinessIdAdById(Integer business_id, Integer type) {
        return doingsSpreadMapper.getBusinessIdAdById(business_id,type);
    }

    /**
     * 功能描述:根据商家ID获取未读消息数量
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/3 15:55
     */
    @Override
    public Map<String, Object> getAdUnread(Integer business_id) {
        return this.doingsSpreadMapper.getAdUnread(business_id);
    }

    /**
     * 功能描述:根据商家ID更新未读消息为已读
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/3 15:55
     */
    @Override
    public int updateReadByBusinessId(Integer business_id) {
        return this.doingsSpreadMapper.updateReadByBusinessId(business_id);
    }

    /**
     * 功能描述:根据商家ID获取未读消息数量 - 系统消息
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/3 15:55
     */
    @Override
    public Map<String, Object> getSystemMsg(Integer business_id) {
        return this.doingsSpreadMapper.getSystemMsg(business_id);
    }

    /**
     * 功能描述:查询未读消息
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/3 15:55
     */
    @Override
    public List<Map<String, Object>> selectDoingsSpreadReadFalseByAd(Integer business_id) {
        return this.doingsSpreadMapper.selectDoingsSpreadReadFalseByAd(business_id);
    }

    /**
     * 功能描述:更新指定信息为已读
     *
     * @param id
     * @param business_id
     * @param type
     * @return
     * @author 杨新杰
     * @date 2018/11/3 15:55
     */
    @Override
    public int UpdateDoingsSpreadReadTrue(String id,String business_id ,String type) {
        return this.doingsSpreadMapper.UpdateDoingsSpreadReadTrue( id, business_id,type);
    }

    /**
     * 功能描述:查询未读消息 - 系统消息
     *
     * @param business_id 商家ID
     * @return
     * @author 杨新杰
     * @date 2018/11/3 15:55
     */
    @Override
    public List<Map<String, Object>> selectSystemMsgReadFalseByAd(Integer business_id) {
        return this.doingsSpreadMapper.selectSystemMsgReadFalseByAd(business_id);
    }

    /**
     *
     * 功能描述: 根据条件查询推广活动审核信息
     *
     * @param   map 查询条件
     * @return: 返回推广活动审核信息
     * @author: 刘武祥
     * @date: 2019/1/17 0017 11:04
     */
    @Override
    public List<Map<Object, Object>> adminSearchDoingsSpread(Map<String, Object> map) {
        return this.doingsSpreadMapper.adminSearchDoingsSpread(map);
    }

}
