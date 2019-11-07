package com.springmvc.service.impl;

import com.springmvc.dao.WnkBusinessTypeOpenCardMapper;
import com.springmvc.entity.WnkBusinessTypeOpenCard;
import com.springmvc.service.IWnkBusinessTypeOpenCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/11/5 00:52
 * @Description:万能卡商家分类用户开卡记录(用于记录用户在需要用户单独开卡的万能卡分类商家处的开卡记录)服务实现类
 */
@Service("/wnkBusinessTypeOpenCardService")
public class WnkBusinessTypeOpenCardService implements IWnkBusinessTypeOpenCardService {
    @Resource
    private WnkBusinessTypeOpenCardMapper wnkBusinessTypeOpenCardMapper;

    /**
     *
     * 功能描述: 插入开卡记录
     *
     * @param wnkBusinessTypeOpenCard 开卡信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/5 12:51 AM
     */
    @Override
    public int insertOpenCardRecord(WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard) {
        return this.wnkBusinessTypeOpenCardMapper.insertOpenCardRecord(wnkBusinessTypeOpenCard);
    }

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
    @Override
    public WnkBusinessTypeOpenCard selectOpenCardStateByUserIdAndBusinessId(Integer user_id, Integer business_id) {
        return this.wnkBusinessTypeOpenCardMapper.selectOpenCardStateByUserIdAndBusinessId(user_id, business_id);
    }

    /**
     *
     * 功能描述:更新卡片有效期
     *
     * @param wnkBusinessTypeOpenCard 卡片信息
     * @return: int
     * @author: zhangfan
     * @date: 2018/11/5 2:28 AM
     */
    @Override
    public int updateCardEndTime(WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard) {
        return this.wnkBusinessTypeOpenCardMapper.updateCardEndTime(wnkBusinessTypeOpenCard);
    }

    /**
     * 功能描述: 获取用户的所有健身卡
     *
     * @author 杨新杰
     * @date 10:38 2018/12/29
     */
    @Override
    public List<Map<String, Object>> selectOpenCardFitnessByUserId(Map<String,Object> map) {
        return this.wnkBusinessTypeOpenCardMapper.selectOpenCardFitnessByUserId(map);
    }

    /**
     * 功能描述: 通过ID获取健身卡信息
     *
     * @param user_id
     * @param card_id
     * @return
     * @author 杨新杰
     * @date 10:38 2018/12/29
     */
    @Override
    public Map<String, Object> selectFitnessCardInfoById(Integer user_id, Integer card_id) {
        return this.wnkBusinessTypeOpenCardMapper.selectFitnessCardInfoById(user_id,card_id);
    }

    /**
     * 功能描述: 商家会员管理查询
     *
     * @param business_id 商家ID
     * @param sort_type   排序方式
     * @param gender      性别
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 14:50 2018/12/29
     */
    @Override
    public List<Map<String, Object>> selectBusinessVip(Integer business_id, Integer sort_type, Integer gender) {
        return this.wnkBusinessTypeOpenCardMapper.selectBusinessVip(business_id,sort_type,gender);
    }

    /**
     * 功能描述: 商家会员管理查询 - 通过手机号码或者用户名
     *
     * @param business_id 商家ID
     * @param content     搜索结果
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 14:50 2018/12/29
     */
    @Override
    public List<Map<String, Object>> selectBusinessVipSearch(String business_id, String content) {
        return this.wnkBusinessTypeOpenCardMapper.selectBusinessVipSearch(business_id,content);
    }

    /**
     * 功能描述: 查询用户在商家的开卡 - 健身卡
     *
     * @param user_id
     * @param business_id 商家ID
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 14:50 2018/12/29
     */
    @Override
    public WnkBusinessTypeOpenCard selectOpenCardStateGymByUserIdAndBusinessId(Integer user_id, Integer business_id) {
        return this.wnkBusinessTypeOpenCardMapper.selectOpenCardStateGymByUserIdAndBusinessId(user_id,business_id);
    }

    /**
     * 功能描述: 获取用户信息
     *
     * @param user_id     用户ID
     * @param business_id 商家ID
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 14:50 2018/12/29
     */
    @Override
    public Map<String, Object> selectUserGymCardInfoByUserId(Integer user_id, Integer business_id) {
        return this.wnkBusinessTypeOpenCardMapper.selectUserGymCardInfoByUserId(user_id,business_id);
    }

}
